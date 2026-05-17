package br.com.librareasy.model;

import br.com.librareasy.tads.FilaEstatica;

public class Livro {
    private static int contadorId=1;
    private final int idLivro;
    private String titulo;
    private String autor;
    private FilaEstatica<Usuario> filaEspera;
    private FilaEstatica<Reserva> filaReserva;

    public Livro(String titulo, String autor) {
        this.idLivro = contadorId++;
        setTitulo(titulo);
        setAutor(autor);
        this.filaEspera = new FilaEstatica<>(10);
        this.filaReserva = new FilaEstatica<>(10);
    }

    public boolean temFilaDeEspera() {
        return !filaEspera.isEmpty();
    }

    public boolean temFilaDeReserva() {
        return !filaEspera.isEmpty();
    }

    /**
     * Entrar na fila de espera usuário
     * @param usuario o usuário que entrará na espera
     */
    public void adicionarUsuarioEspera(Usuario usuario){
        if(usuario==null) throw new IllegalArgumentException("Usuário inválido");
        if(filaEspera.isFull()) throw new IllegalStateException("Fila cheia");
        this.filaEspera.enqueue(usuario);
    }

    /**
     * Entrar na fila de espera reserva
     * @param reserva o usuário que entrará na espera
     */
    public void adicionarReserva(Reserva reserva){
        if(reserva==null) throw new IllegalArgumentException("Reserva inválida");
        if(filaReserva.isFull()) throw new IllegalStateException("Fila cheia");
        this.filaReserva.enqueue(reserva);
    }

    /**
     * Retira o último usuário da fila
     */
    public Usuario chamarProximoUsuario(){
        if(filaEspera.isEmpty()) return null;
        return this.filaEspera.dequeue();
    }

    /**
     * Remove a última reserva
     */
    public Reserva chamarProximaReserva(){
        if(filaReserva.isEmpty()) return null;
        return this.filaReserva.dequeue();
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
}

