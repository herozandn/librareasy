package br.com.librareasy.model;

public class Reserva {
    private Livro livro;
    private Usuario usuario;
    private Data dataSolicitacao;

    public Reserva(Livro livro, Usuario usuario, Data dataSolicitacao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataSolicitacao = dataSolicitacao;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Data getDataSolicitacao() {
        return dataSolicitacao;
    }

    public void setDataSolicitacao(Data dataSolicitacao) {
        this.dataSolicitacao = dataSolicitacao;
    }

}
