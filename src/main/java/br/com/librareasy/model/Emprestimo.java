package br.com.librareasy.model;

import br.com.librareasy.tad.Data;

public class Emprestimo {
    private Livro livro;
    private Usuario usuario;
    private Data dataInicio;
    private Data prazoDevolucao;
    private boolean statusEmprestimo;

    public Emprestimo(Livro livro, Usuario usuario, Data dataInicio, Data prazoDevolucao) {
        this.livro = livro;
        this.usuario = usuario;
        this.dataInicio = dataInicio;
        this.prazoDevolucao = prazoDevolucao;
        this.statusEmprestimo = true;
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

    public boolean isStatusEmprestimo() {
        return statusEmprestimo;
    }

    public void setStatusEmprestimo(boolean statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }
}
