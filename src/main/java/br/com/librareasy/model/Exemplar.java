package br.com.librareasy.model;
import java.util.Objects;

public class Exemplar {
    private static int contadorId=1;
    private final int idExemplar;
    private Livro livro;
    private String editora;
    private StatusLivro statusLivro;
    private EstadoConservacao estadoConservacao;
    private String anoPubli;

    public Exemplar(Livro livro, StatusLivro statusLivro, EstadoConservacao estadoConservacao, String editora, String anoPubli) {
        this.idExemplar = contadorId++;
        setLivro(livro);
        setEditora(editora);
        setAnoPubli(anoPubli);
        setStatusLivro(statusLivro);
        setEstadoConservacao(estadoConservacao);
    }

    public void setAnoPubli(String anoPubli) {
        if (anoPubli == null){
            throw new IllegalArgumentException("Ano de publicação não deve ser nulo.");
        }
        int ano = Integer.parseInt(anoPubli);
        int anoAtual = java.time.Year.now().getValue();
        if(ano>anoAtual) throw new IllegalArgumentException("Ano inválido");
        this.anoPubli = anoPubli;
    }

    /**
     * Verifica se está disponível
     */
    public boolean estaDisponivel(){
        return this.statusLivro == StatusLivro.Disponivel;
    }

    /**
     * Marca o exemplar como emprestado
     */
    public void marcarEmprestado(){
        if(!estaDisponivel()) throw new IllegalStateException("Livro não está disponível");
        this.statusLivro = StatusLivro.Emprestado;
    }

    /**
     * Marca o exemplar como disponível
     */
    public void marcarDisponivel(){
        if(!estaDisponivel()) throw new IllegalStateException("Livro não está disponível");
        this.statusLivro = StatusLivro.Disponivel;
    }

    public void setEditora(String editora) {
        if (editora == null || editora.trim().isEmpty()) throw new IllegalArgumentException("Editora não pode ser nulo ou vazio.");
        this.editora = editora;
    }

    public int getIdExemplar() {
        return idExemplar;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = Objects.requireNonNull(livro, "Livro associado não pode ser nulo.");
    }

    public StatusLivro getStatusLivro() {
        return statusLivro;
    }

    public void setStatusLivro(StatusLivro statusLivro) {
        this.statusLivro = Objects.requireNonNull(statusLivro, "Status do livro não pode ser nulo.");
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = Objects.requireNonNull(estadoConservacao, "Estado de conservação não pode ser nulo.");
    }

    /**
     * Formatação de exibição
     *
     * @return idExemplar, titulo, statusLivro e editora
     */
    public String toString(){
        return String.format("ID: %d | Livro: %-20s | Status: %-12s | Editora: %s",
        idExemplar, livro.getTitulo(), statusLivro, editora);
    }
}
