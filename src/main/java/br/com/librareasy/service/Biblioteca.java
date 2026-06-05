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

    /* Gestão do acervo */

    public void registrarLog(String tipo, String mensagem){
        if(!logs.isFull()) {
            logs.push(new LogOperacao(tipo, mensagem));
        }
    }

    public void exibirHistoricoLogs() {
        if (logs.isEmpty()) {
            out.println("Nenhum log registrado");
            return;
        }

        out.println("\n=== HISTÓRICO DE OPERAÇÕES ===");
        
        // Pilha auxiliar para não perder os dados
        PilhaEstatica<LogOperacao> auxiliar = new PilhaEstatica<>(logs.size());

        // Desempilha da original, imprime e guarda na auxiliar
        while (!logs.isEmpty()) {
            LogOperacao log = logs.pop();
            out.println(log.toString());
            auxiliar.push(log);
        }

        // Devolve tudo para a pilha original para manter o estado
        while (!auxiliar.isEmpty()) {
            logs.push(auxiliar.pop());
        }
    }

    /**
     * Exibir acervo completo
     */
    public void exibirAcervo(){
        if(exemplares.isEmpty()) {
            out.println("Acervo vazio!");
            return;
        }

        out.println("\n=== ACERVO ===");
        for (int i = 0; i < exemplares.size(); i++) {
            Exemplar e = exemplares.get(i);
            out.println(e.toString());
        }
        out.println("\n");
    }
    /**
     * Cadastra um novo exemplar. Se o livro (título) não existir, ele é criado automaticamente.
     * @throws IllegalStateException se a capacidade de livros ou exemplares for atingida.
     * @throws IllegalArgumentException se o título for inválido.
     */
    public void cadastrarExemplar(String titulo, String autor, String editora, String anoPubli, String isbn) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido para cadastro.");
        }

        //Tentar encontrar livro existente pelo título
        Livro livroParaAssociar = livros.encontrar(l -> l.getTitulo().equalsIgnoreCase(titulo));

        //Se não existir, cria o Livro primeiro
        if (livroParaAssociar == null) {
            livroParaAssociar = new Livro(titulo, autor, isbn);
            livros.add(livroParaAssociar);
        }

        //Cria e adiciona o Exemplar associado ao Livro
        Exemplar novoExemplar = new Exemplar(livroParaAssociar, StatusLivro.Disponivel, EstadoConservacao.NOVO, anoPubli, editora);
        exemplares.add(novoExemplar);
        registrarLog("CADASTRO-EXEMPLAR", "Livro: " + titulo + " | Editora: " + editora);
    }

    /**
     * Remove um exemplar da lista
     * @param idExemplar id do exemplar a ser removido
     * @throws IllegalArgumentException se ele não existe na lista de exemplares
     * @throws IllegalStateException se não é possível removê-lo por conta do status
     */
    public void removerExemplar(int idExemplar) {
        //Procura indice dele na lista
        int indice = exemplares.encontrarIndice(e -> e.getIdExemplar()==idExemplar);
        //Verifica se ele existe
        if (indice==-1) throw new IllegalArgumentException("Exemplar inexistente");
        //Pegamos o exemplar
        Exemplar exemplar = exemplares.get(indice);

        if(!exemplar.estaDisponivel()){
            throw new IllegalStateException("Não é possível remover o exemplar");
        }

        exemplares.remove(indice);
        registrarLog("REMOÇÃO-EXEMPLAR", "ID: " + idExemplar + " | Livro: " + exemplar.getLivro().getTitulo());
    }


    /**
     * Altera o status de um exemplar específico.
     * @param idExemplar ID único do exemplar.
     * @param novoStatus Novo status (Disponível, Emprestado, Reservado).
     * @throws IllegalArgumentException se o exemplar não for encontrado.
     */
    public void alterarStatusExemplar(int idExemplar, StatusLivro novoStatus) {
        Exemplar exemplar = exemplares.encontrar(e -> e.getIdExemplar() == idExemplar);

        if (exemplar == null) {
            throw new IllegalArgumentException("Exemplar com ID " + idExemplar + " não encontrado");
        }

        exemplar.setStatusLivro(novoStatus);
    }

    /**
     * Altera o estado de conservação de um exemplar.
     * @param idExemplar ID único do exemplar.
     * @param novoEstado Novo estado (NOVO, BOM, USADO).
     * @throws IllegalArgumentException se o exemplar não for encontrado.
     */
    public void alterarConservacaoExemplar(int idExemplar, EstadoConservacao novoEstado) {
        Exemplar exemplar = exemplares.encontrar(e -> e.getIdExemplar() == idExemplar);

        if (exemplar == null) {
            throw new IllegalArgumentException("Exemplar com ID " + idExemplar + " não encontrado");
        }

        exemplar.setEstadoConservacao(novoEstado);
    }

    /* Gestão de usuários */

    /**
     * Cadastra um novo usuário.
     * @param nome Nome do usuário.
     * @param tipoUsuario Tipo (Aluno, Professor, Administrador).
     * @throws IllegalStateException se a capacidade de usuários for atingida.
     * @throws IllegalArgumentException se o nome for inválido ou o usuário já estiver cadastrado.
     */
    public void cadastrarUsuario(String nome, TipoUsuario tipoUsuario) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome inválido");
        }
        if (tipoUsuario == null) {
            throw new IllegalArgumentException("Tipo de usuário é obrigatório");
        }

        //Verificar se já existe um usuário com esse nome
        Usuario existente = usuarios.encontrar(u -> u.getNome().equalsIgnoreCase(nome));
        if (existente != null) {
            throw new IllegalArgumentException("Usuário com nome '" + nome + "' já existe no sistema");
        }

        //Criar e adicionar o usuário
        usuarios.add(new Usuario(nome, tipoUsuario));
        registrarLog("CADASTRO-USUÁRIO", "Nome: " + nome + " | Tipo: " + tipoUsuario);
    }

    /**
     * Remove usuário
     * @param idUsuario id do usuário
     * @throws IllegalArgumentException se o usuário não existir
     * @throws IllegalStateException se o usuário ainda tiver empréstimos
     */
    public void removerUsuario(int idUsuario) {
        //Busca o índice do usuário na lista
        int indice = usuarios.encontrarIndice(u -> u.getIdUsuario()==idUsuario);
        //Verifica se ele existe
        if (indice==-1) throw new IllegalArgumentException("Usuario inexistente");

        Usuario usuario = usuarios.get(indice);
        if(usuario.temPendencias()) {
            throw new IllegalStateException("Usuário com empréstimos ativos");
        }
        usuarios.remove(indice);
        registrarLog("REMOÇÃO-USUÁRIO", "ID: " + idUsuario + " | Nome: " + usuario.getNome());
    }

    /* Transações */
    /**
     * Realizar empréstimo
     * Busca o Usuário
     * Busca o Livro
     * Verifica na lista do livro se há algum Exemplar "Disponível"
     * Se sim, cria o objeto Emprestimo, muda o status do exemplar para "Emprestado" e salva a data de hoje.
     */

    /* Método auxiliador */
    private Exemplar buscarPrimeiroDisponivel(String titulo) {
        return exemplares.encontrar(e ->
                        e.getLivro().getTitulo().equalsIgnoreCase(titulo) &&
                        e.getStatusLivro() == StatusLivro.Disponivel);
    }

    public void realizarEmprestimo(String nomeUsuario, String titulo, Data hoje){
        //Busca usuário
        Usuario usuario = usuarios.encontrar(u -> u.getNome().equals(nomeUsuario));
        if(usuario==null) throw new IllegalArgumentException("usuário inexistente");
        //Verificar capacidade de empréstimo
        if(usuario.getEmprestimosAtuais().isFull()) throw new IllegalStateException("Usuário já atingiu o limite de empréstimos");
        //Verifica se o exemplar está disponível
        Exemplar disponivel = buscarPrimeiroDisponivel(titulo);
        //Se não encontrou
        if(disponivel==null) throw new IllegalStateException("Nenhum livro encontrado");

        Emprestimo novo = new Emprestimo(disponivel, usuario, hoje);
        historicoEmprestimos.add(novo);
        usuario.getEmprestimosAtuais().add(novo);
        disponivel.setStatusLivro(StatusLivro.Emprestado);
        registrarLog("EMPRÉSTIMO", "Livro: " + titulo + " | Usuário: " + usuario.getNome());
    }

    public void realizarDevolucao(int idExemplar, Data dataHoje){
        //Busca o empréstimo ativo
        Emprestimo emprestimo = historicoEmprestimos.encontrar(
                e -> e.getExemplarEmprestado().getIdExemplar() == idExemplar &&
                        e.getStatusEmprestimo()==StatusEmprestimo.Ativo
        );
        if(emprestimo==null) throw new IllegalArgumentException("Nenhum empréstimo ativo");
        //Estabelece a data de devolução
        emprestimo.registrarDevolucao(dataHoje);
        //Calcula a multa
        double valorMulta = emprestimo.multa();
        //Encontra o usuário e remove o empréstimo da sua lista
        Usuario usuario = emprestimo.getUsuario();
        int indiceUsuario = usuario.getEmprestimosAtuais().encontrarIndice(
                e -> e.getExemplarEmprestado().getIdExemplar() == idExemplar
        );
        if(indiceUsuario!=-1){
            usuario.getEmprestimosAtuais().remove(indiceUsuario);
        }
        //Estabelece como disponível o exemplar
        emprestimo.getExemplarEmprestado().setStatusLivro(StatusLivro.Disponivel);
        out.println("Multa: R$ " + valorMulta);
        registrarLog("DEVOLUÇÃO", "Exemplar: " + idExemplar + " | Usuário: " + usuario.getNome());


    }

    public void renovarEmprestimo(int idExemplar){
        //Encontra o empréstimo
        Emprestimo emprestimo = historicoEmprestimos.encontrar(
                e -> e.getExemplarEmprestado().getIdExemplar() == idExemplar &&
                        e.getStatusEmprestimo() == StatusEmprestimo.Ativo
        );
        //Verifica se existe
        if(emprestimo==null) throw new IllegalArgumentException("Nenhum empréstimo ativo");
        //Encontra o livro
        Livro livroExemplar = emprestimo.getExemplarEmprestado().getLivro();
        //Verifica se ele está reservado
        if(temReserva(livroExemplar)) throw new IllegalStateException("Título reservado!");
        emprestimo.renovar();
        registrarLog("RENOVAÇÃO", "Exemplar: " + idExemplar + " | Novo prazo: " + emprestimo.getPrazoDevolucao());
    }

    private boolean temReserva(Livro livro){
        return reservas.encontrar(r -> r.getLivro().equals(livro)) != null;
    }


    /**
     * Realizar reserva
     * Regras:
     * Duplicidade: O usuário não pode reservar o mesmo livro duas vezes
     * Posse: O usuário não pode reservar um livro que ele já está com ele emprestado
     */
    public void realizarReserva(Usuario usuario, String titulo, Data hoje){
        //Busca o livro
        Livro livro = livros.encontrar(l -> l.getTitulo().equalsIgnoreCase(titulo));
        if(livro==null) throw new IllegalArgumentException("Livro não encontrado");

        //Verifica se o usuário já tem o livro
        boolean temReserva = reservas.encontrar(r -> r.getUsuario().equals(usuario) &&
                r.getLivro().equals(livro)) != null;
        if(temReserva) throw new IllegalStateException("Usuário já possui reserva");

        //Cria a reserva
        Reserva reserva = new Reserva(livro, usuario, hoje);
        reservas.add(reserva);
        out.println("Reserva realizada");
        registrarLog("RESERVA", "Livro: " + titulo + " | Usuário: " + usuario.getNome());
    }
}
