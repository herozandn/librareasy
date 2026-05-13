package br.com.librareasy.model;

import br.com.librareasy.tads.FilaEstatica;

public class Livro {
    private static int contadorId=1;

    private final int idLivro;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPubli;
    private FilaEstatica<Usuario> filaEspera;
    private FilaEstatica<Reserva> filaReserva;

    public Livro(String titulo, String autor, String editora, int anoPubli) {
        this.idLivro = contadorId++;
        setTitulo(titulo);
        setAutor(autor);
        setEditora(editora);
        setAnoPubli(anoPubli);
        this.filaEspera = new FilaEstatica<>(10);
        this.filaReserva = new FilaEstatica<>(10);
    }

    public FilaEstatica<Usuario> getFilaEspera() {return filaEspera;}

    public FilaEstatica<Reserva> getFilaReserva() {return filaReserva;}

    public int getIdLivro() {
        return idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) throw new IllegalArgumentException("Título não pode ser nulo ou vazio.");
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        if (autor == null || autor.trim().isEmpty()) throw new IllegalArgumentException("Autor não pode ser nulo ou vazio.");
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        if (editora == null || editora.trim().isEmpty()) throw new IllegalArgumentException("Editora não pode ser nulo ou vazio.");
        this.editora = editora;
    }

    public int getAnoPubli() {
        return anoPubli;
    }

    public void setAnoPubli(int anoPubli) {
        if (anoPubli <= 0) throw new IllegalArgumentException("Ano de publicação deve ser positivo.");
        this.anoPubli = anoPubli;
    }
}

