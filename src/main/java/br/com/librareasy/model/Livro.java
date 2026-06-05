package br.com.librareasy.model;

public class Livro {
    private static int contadorId=1;
    private final int idLivro;
    private String titulo;
    private String autor;
    private String isbn;

    public Livro(String titulo, String autor, String isbn) {
        this.idLivro = contadorId++;
        setTitulo(titulo);
        setAutor(autor);
        setIsbn(isbn);
    }

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

    public void setIsbn(String isbn){
        //Remove possíveis hífens ou espaços que o usuário digitou
        String cleanIsbn = isbn.replace("-", "").replace(" ", "");
        //Verifica se tem exatamente 13 dígitos numéricos
        if (!cleanIsbn.matches("\\d{13}")) {
            throw new IllegalArgumentException("O ISBN deve conter exatamente 13 dígitos.");
        }
        this.isbn = isbn;
    }

    public String getIsbn() {
        return isbn;
    }
}

