package br.com.librareasy.model;

public class Reserva {
    private final Livro livro;
    private final Usuario usuario;
    private final Data dataSolicitacao; //data solicitação é definida na criação

    public Reserva(Livro livro, Usuario usuario, Data dataSolicitacao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataSolicitacao = dataSolicitacao;
    }

    public Livro getLivro() {
        return livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }
}
