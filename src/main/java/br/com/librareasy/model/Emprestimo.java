package br.com.librareasy.model;

public class Emprestimo {
    private Exemplar exemplarEmprestado;
    private Usuario usuario;
    private Data dataInicio;
    private Data prazoDevolucao;
    private StatusLivro statusEmprestimo;

    public Emprestimo(Exemplar exemplarEmprestado, Usuario usuario, Data dataInicio, Data prazoDevolucao, StatusLivro statusEmprestimo) {
        this.exemplarEmprestado = exemplarEmprestado;
        this.usuario = usuario;
        this.dataInicio = dataInicio;
        this.prazoDevolucao = prazoDevolucao;
        this.statusEmprestimo = statusEmprestimo;
    }

    public Exemplar getExemplarEmprestado() {
        return exemplarEmprestado;
    }

    public void setExemplarEmprestado(Exemplar exemplarEmprestado) {
        this.exemplarEmprestado = exemplarEmprestado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Data getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Data dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Data getPrazoDevolucao() {
        return prazoDevolucao;
    }

    public void setPrazoDevolucao(Data prazoDevolucao) {
        this.prazoDevolucao = prazoDevolucao;
    }

    public StatusLivro getStatusEmprestimo() {
        return statusEmprestimo;
    }

    public void setStatusEmprestimo(StatusLivro statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }
}
