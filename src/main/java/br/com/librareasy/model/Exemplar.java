package br.com.librareasy.model;

public class Exemplar {
    private int idExemplar;
    private Livro livro;
    private StatusLivro statusLivro;
    private EstadoConservacao estadoConservacao;

    public Exemplar(int idExemplar, Livro livro, StatusLivro statusLivro, EstadoConservacao estadoConservacao) {
        this.idExemplar = idExemplar;
        this.livro = livro;
        this.statusLivro = statusLivro;
        this.estadoConservacao = estadoConservacao;
    }

    public int getIdExemplar() {
        return idExemplar;
    }

    public void setIdExemplar(int idExemplar) {
        this.idExemplar = idExemplar;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public StatusLivro getStatusLivro() {
        return statusLivro;
    }

    public void setStatusLivro(StatusLivro statusLivro) {
        this.statusLivro = statusLivro;
    }

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = estadoConservacao;
    }
}
