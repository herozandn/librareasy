package br.com.librareasy.model;

public class Livro {
    private int idLivro;
    private String titulo;
    private String autor;
    private String editora;
    private int anoPubli;
    private StatusLivro estadoLivro;

    public Livro(int idLivro, String titulo, String autor, String editora, int anoPubli) {
        this.idLivro = idLivro;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.anoPubli = anoPubli;
        this.estadoLivro = StatusLivro.Disponivel;
    }

    public int getIdLivro() {
        return idLivro;
    }

    public void setIdLivro(int idLivro) {
        this.idLivro = idLivro;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAnoPubli() {
        return anoPubli;
    }

    public void setAnoPubli(int anoPubli) {
        this.anoPubli = anoPubli;
    }

    public StatusLivro getEstadoLivro() {
        return estadoLivro;
    }

    public void setEstadoLivro(StatusLivro estadoLivro) {
        this.estadoLivro = estadoLivro;
    }
}

