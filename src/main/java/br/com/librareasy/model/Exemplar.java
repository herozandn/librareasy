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

    public String getAnoPubli() {
        return anoPubli;
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

    //Faz apenas: if(e.estaDisponivel())
    public boolean estaDisponivel(){
        return this.statusLivro == StatusLivro.Disponivel;
    }

    public void marcarEmprestado(){
        if(!estaDisponivel()) throw new IllegalStateException("Livro não está disponível");
        this.statusLivro = StatusLivro.Emprestado;
    }

    public String getEditora() {
        return editora;
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

    public EstadoConservacao getEstadoConservacao() {
        return estadoConservacao;
    }

    public void setEstadoConservacao(EstadoConservacao estadoConservacao) {
        this.estadoConservacao = Objects.requireNonNull(estadoConservacao, "Estado de conservação não pode ser nulo.");
    }

    public String toString(){
        return String.format("ID: %d | Livro: %-20s | Status: %-12s | Editora: %s",
        idExemplar, livro.getTitulo(), statusLivro, editora);
    }
}
