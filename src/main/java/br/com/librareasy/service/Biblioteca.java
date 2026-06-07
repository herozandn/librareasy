package br.com.librareasy.service;

import br.com.librareasy.model.*;
import br.com.librareasy.tads.ListaEstatica;
import br.com.librareasy.tads.PilhaEstatica;
import static java.lang.System.out;

public class Biblioteca {
    private ListaEstatica<Usuario> usuarios;
    private ListaEstatica<Livro> livros;
    private ListaEstatica<Exemplar> exemplares;
    private ListaEstatica<Emprestimo> historicoEmprestimos;
    private ListaEstatica<Reserva> reservas;
    private PilhaEstatica<LogOperacao> logs;

    public Biblioteca(int acervoCap, int usuariosCap, int exemplaresCap, int historicoCap, int reservasCap, int logsCap){
        this.livros = new ListaEstatica<>(acervoCap);
        this.usuarios = new ListaEstatica<>(usuariosCap);
        this.exemplares = new ListaEstatica<>(exemplaresCap);
        this.historicoEmprestimos = new ListaEstatica<>(historicoCap);
        this.reservas = new ListaEstatica<>(reservasCap);
        this.logs = new PilhaEstatica<>(logsCap);
    }

    /**
     * Registra operação na pilha de operações
     * @param tipo tipo de log
     * @param mensagem mensagem do log
     */
    public void registrarLog(String tipo, String mensagem){
        if(!logs.isFull()) {
            logs.push(new LogOperacao(tipo, mensagem));
        }
    }

    /**
     * Exibe a pilha de logs
     */
    public void exibirHistoricoLogs() {
        if (logs.isEmpty()) {
            out.println("Nenhum log registrado");
            return;
        }
        out.println("\n=== HISTÓRICO DE OPERAÇÕES ===");
        //Pilha auxiliar
        PilhaEstatica<LogOperacao> auxiliar = new PilhaEstatica<>(logs.size());
        while (!logs.isEmpty()) {
            LogOperacao log = logs.pop();
            out.println(log.toString());
            auxiliar.push(log);
        }
        while (!auxiliar.isEmpty()) {
            logs.push(auxiliar.pop());
        }
    }

    /**
     * Exibe o acervo de livros
     */
    public void exibirAcervo(){
        if(exemplares.isEmpty()) {
            out.println("Acervo vazio");
            return;
        }
        out.println("\n=== ACERVO ===");
        for (int i = 0; i < exemplares.size(); i++) {
            Exemplar e = exemplares.get(i);
            out.println(e.toString());
        }
    }

    /**
     * Cadastrar exemplar
     * @param titulo titulo da obra
     * @param autor autor
     * @param editora editora
     * @param anoPubli ano de publicação da edição
     * @param isbn código ISBN
     * @throws IllegalArgumentException se o título for inválido
     */
    public void cadastrarExemplar(String titulo, String autor, String editora, String anoPubli, String isbn) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido.");
        }

        //Se o livro não existir no acervo, cria-o e associa o exemplar a ele
        Livro livroParaAssociar = livros.encontrar(l -> l.getTitulo().equalsIgnoreCase(titulo));
        if (livroParaAssociar == null) {
            livroParaAssociar = new Livro(titulo, autor, isbn);
            livros.add(livroParaAssociar);
        }

        //Se o livro já existir então só cria o exemplar
        Exemplar novoExemplar = new Exemplar(livroParaAssociar, StatusLivro.Disponivel, EstadoConservacao.NOVO, editora, anoPubli);
        exemplares.add(novoExemplar);
        registrarLog("CADASTRO-EXEMPLAR", "Livro: " + titulo);
    }

    /**
     * Remove o exemplar
     * @param idExemplar código de identificação
     * @throws IllegalArgumentException se o exemplar não existir
     * @throws IllegalStateException se o exemplar não estiver disponível
     */
    public void removerExemplar(int idExemplar) {
        //Procura exemplar
        int indice = exemplares.encontrarIndice(e -> e.getIdExemplar() == idExemplar);
        if (indice == -1) throw new IllegalArgumentException("Exemplar inexistente");
        Exemplar exemplar = exemplares.get(indice);
        //Exemplar precisa estar disponível para ser excluído do acervo
        if(!exemplar.estaDisponivel()){
            throw new IllegalStateException("Exemplar não está disponível");
        }
        exemplares.remove(indice);
        registrarLog("REMOÇÃO-EXEMPLAR", "ID: " + idExemplar);
    }

    /**
     * Casdastra usuário
     * @param nome nome do usuário
     * @param tipoUsuario tipo de usuário
     * @throws IllegalArgumentException se nome inválido ou usuário já existente
     */
    public void cadastrarUsuario(String nome, TipoUsuario tipoUsuario) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome inválido");

        //Não pode existir dois usuários com mesmo nome
        Usuario existente = usuarios.encontrar(u -> u.getNome().equalsIgnoreCase(nome));
        if (existente != null) throw new IllegalArgumentException("Usuário já existe");
        usuarios.add(new Usuario(nome, tipoUsuario));
        registrarLog("CADASTRO-USUÁRIO", "Nome: " + nome);
    }

    /**
     * Remove usuário
     * @param idUsuario id do usuário
     * @throws IllegalArgumentException se o usuário não existir
     * @throws IllegalStateException se o usuário tiver pendências
     */
    public void removerUsuario(int idUsuario) {
        //Procura o usuário
        int indice = usuarios.encontrarIndice(u -> u.getIdUsuario() == idUsuario);
        if (indice == -1) throw new IllegalArgumentException("Usuario inexistente");
        Usuario usuario = usuarios.get(indice);
        //Só remove o usuário se ele não tiver nenhum livro consigo
        if(usuario.temPendencias()) throw new IllegalStateException("Usuário com pendências");
        usuarios.remove(indice);
        registrarLog("REMOÇÃO-USUÁRIO", "ID: " + idUsuario);
    }

    /**
     * Realiza empréstimo
     * @param nomeUsuario nome do usuário
     * @param titulo título da obra
     * @param hoje data do empréstimo
     * @throws IllegalArgumentException se o usuário não existir
     * @throws IllegalStateException se o exemplar não estiver disponível ou não existir
     */
    public void realizarEmprestimo(String nomeUsuario, String titulo, Data hoje){
        Usuario usuario = usuarios.encontrar(u -> u.getNome().equalsIgnoreCase(nomeUsuario));
        if(usuario == null) throw new IllegalArgumentException("Usuário inexistente");
        if(usuario.getEmprestimosAtuais().isFull()) throw new IllegalStateException("Limite atingido");
        Exemplar disponivel = exemplares.encontrar(e -> e.getLivro().getTitulo().equalsIgnoreCase(titulo) && e.estaDisponivel());
        if(disponivel == null) throw new IllegalStateException("Nenhum exemplar disponível");

        Emprestimo novo = new Emprestimo(disponivel, usuario, hoje);
        historicoEmprestimos.add(novo);
        usuario.getEmprestimosAtuais().add(novo);
        disponivel.marcarEmprestado();
        registrarLog("EMPRÉSTIMO", "Livro: " + titulo + " | Usuário: " + nomeUsuario);
    }

    /**
     * Finaliza o empréstimo
     * @param idExemplar id do exemplar
     * @param dataHoje data da devolução
     * @throws IllegalArgumentException seo empréstimo não existir
     */
    public void realizarDevolucao(int idExemplar, Data dataHoje){
        Emprestimo emprestimo = historicoEmprestimos.encontrar(e -> e.getExemplarEmprestado().getIdExemplar() == idExemplar && e.getStatusEmprestimo() == StatusEmprestimo.Ativo);
        if(emprestimo == null) throw new IllegalArgumentException("Nenhum empréstimo ativo");
        
        emprestimo.registrarDevolucao(dataHoje);
        double valorMulta = emprestimo.multa();
        Usuario usuario = emprestimo.getUsuario();
        int idx = usuario.getEmprestimosAtuais().encontrarIndice(e -> e.getExemplarEmprestado().getIdExemplar() == idExemplar);
        if(idx != -1) usuario.getEmprestimosAtuais().remove(idx);
        
        emprestimo.getExemplarEmprestado().marcarDisponivel();
        out.println("Multa: R$ " + valorMulta);
        registrarLog("DEVOLUÇÃO", "Exemplar: " + idExemplar);
    }

    /**
     * Renova o empréstimo
     * @param idExemplar
     * @throws IllegalArgumentException se nenhum empréstimo ativo
     * @throws IllegalStateException se o livro já tiver sido reservado
     */
    public void renovarEmprestimo(int idExemplar){
        Emprestimo emprestimo = historicoEmprestimos.encontrar(e -> e.getExemplarEmprestado().getIdExemplar() == idExemplar && e.getStatusEmprestimo() == StatusEmprestimo.Ativo);
        if(emprestimo == null) throw new IllegalArgumentException("Nenhum empréstimo ativo");

        //Se o livro tiver sido reservado não é possível fazer a renovação do empréstimo
        boolean reservado = reservas.encontrar(r -> r.getLivro().equals(emprestimo.getExemplarEmprestado().getLivro())) != null;
        if(reservado) throw new IllegalStateException("Livro reservado");

        //Não criamos um novo empréstimo, só postergamos a data de devolução
        emprestimo.renovar();
        registrarLog("RENOVAÇÃO", "Exemplar: " + idExemplar);

        //Posteriormente: poderia haver um limite de renovações em sequência
    }

    /**
     * Realiza reserva
     * @param nomeUsuario nome usuário
     * @param titulo título da obra
     * @param hoje data da reserva
     * @throws IllegalArgumentException usuário ou livro inexistente
     * @throws IllegalStateException se o livro já possui reserva
     */
    public void realizarReserva(String nomeUsuario, String titulo, Data hoje){
        Usuario usuario = usuarios.encontrar(u -> u.getNome().equalsIgnoreCase(nomeUsuario));
        if(usuario == null) throw new IllegalArgumentException("Usuário inexistente");
        Livro livro = livros.encontrar(l -> l.getTitulo().equalsIgnoreCase(titulo));
        if(livro == null) throw new IllegalArgumentException("Livro inexistente");

        boolean jaReservou = reservas.encontrar(r -> r.getUsuario().equals(usuario) && r.getLivro().equals(livro)) != null;
        if(jaReservou) throw new IllegalStateException("Já possui reserva");

        reservas.add(new Reserva(livro, usuario, hoje));
        registrarLog("RESERVA", "Livro: " + titulo + " | Usuário: " + nomeUsuario);
    }
}