package br.com.librareasy.service;

import br.com.librareasy.model.*;
import br.com.librareasy.tads.ListaEstatica;

import java.util.Objects;

public class Biblioteca {

    private ListaEstatica<Usuario> usuarios;
    private ListaEstatica<Livro> livros;
    private ListaEstatica<Exemplar> exemplares;
    private ListaEstatica<Emprestimo> historicoEmprestimos;

    public Biblioteca(int acervoCap, int usuariosCap, int exemplaresCap, int historicoCap){
        this.livros = new ListaEstatica<>(acervoCap);
        this.usuarios = new ListaEstatica<>(usuariosCap);
        this.exemplares = new ListaEstatica<>(exemplaresCap);
        this.historicoEmprestimos = new ListaEstatica<>(historicoCap);
    }

    /* Gestão do acervo */

    /**
     * Cadastra um novo exemplar. Se o livro (título) não existir, ele é criado automaticamente.
     * @throws IllegalStateException se a capacidade de livros ou exemplares for atingida.
     * @throws IllegalArgumentException se o título for inválido.
     */
    public void cadastrarExemplar(String titulo, String autor, String editora, int anoPubli, String isbn) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Título inválido para cadastro.");
        }

        //Tentar encontrar livro existente pelo título
        Livro livroParaAssociar = livros.encontrar(l -> l.getTitulo().equalsIgnoreCase(titulo));

        //Se não existir, cria o Livro primeiro
        if (livroParaAssociar == null) {
            livroParaAssociar = new Livro(titulo, autor, editora, anoPubli);
            livros.add(livroParaAssociar);
        }

        //Cria e adiciona o Exemplar associado ao Livro
        Exemplar novoExemplar = new Exemplar(livroParaAssociar, StatusLivro.Disponivel, EstadoConservacao.NOVO, isbn);
        exemplares.add(novoExemplar);
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
            throw new IllegalArgumentException("Exemplar com ID " + idExemplar + " não encontrado.");
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
            throw new IllegalArgumentException("Exemplar com ID " + idExemplar + " não encontrado.");
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
            throw new IllegalArgumentException("Nome inválido!");
        }
        if (tipoUsuario == null) {
            throw new IllegalArgumentException("Tipo de usuário é obrigatório.");
        }

        //Verificar se já existe um usuário com esse nome
        Usuario existente = usuarios.encontrar(u -> u.getNome().equalsIgnoreCase(nome));
        if (existente != null) {
            throw new IllegalArgumentException("Usuário com nome '" + nome + "' já existe no sistema.");
        }

        //Criar e adicionar o usuário
        usuarios.add(new Usuario(nome, tipoUsuario));
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

    public void realizarEmprestimo(Usuario usuario, String titulo, Data hoje, Data prazo){
        //Verifica se o exemplar está disponível
        Exemplar disponivel = buscarPrimeiroDisponivel(titulo);
        //Se não encontrou
        if(disponivel==null) throw new IllegalStateException("Nenhum livro encontrado");

        Emprestimo novo = new Emprestimo(disponivel, usuario, hoje, prazo, StatusLivro.Emprestado);
        historicoEmprestimos.add(novo);
        disponivel.setStatusLivro(StatusLivro.Emprestado);
    }

    public void realizarDevolucao(int idExemplar){
        //Encontra o exemplar
        Exemplar e = exemplares.encontrar(exemplar -> exemplar.getIdExemplar()==idExemplar);
        if(e==null) throw new IllegalArgumentException("Exemplar não existe");

        //Modifica status
        e.setStatusLivro(StatusLivro.Disponivel);
    }
}
