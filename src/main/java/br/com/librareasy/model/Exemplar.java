package br.com.librareasy.model;

import java.util.Objects;

public class Exemplar {
    private static int contadorId=1;

    private final int idExemplar;
    private Livro livro;
    private StatusLivro statusLivro;
    private EstadoConservacao estadoConservacao;
    private String isbn;

    public Exemplar(Livro livro, StatusLivro statusLivro, EstadoConservacao estadoConservacao, String isbn) {
        this.idExemplar = contadorId++;
        setLivro(livro);
        setStatusLivro(statusLivro);
        setEstadoConservacao(estadoConservacao);
        setIsbn(isbn);
    }

    public int getIdExemplar() {
        return idExemplar;
    }

    public void setIsbn(String isbn){
        int tamanho = isbn.length();
        if(tamanho!=13) throw new IllegalArgumentException("Tamanho incompatível");
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
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

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = Objects.requireNonNull(estadoConservacao, "Estado de conservação não pode ser nulo.");
    }
}
