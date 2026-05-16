package br.com.librareasy.model;

import java.time.LocalDate;

public class Emprestimo {
    private static int cont=1;
    private final int idEmprestimo;
    private Exemplar exemplarEmprestado;
    private Usuario usuario;
    private Data dataInicio;
    private Data prazoDevolucao;
    private StatusEmprestimo statusEmprestimo;
    private Data dataDevolucao;

    public Emprestimo(Exemplar exemplarEmprestado, Usuario usuario, Data hoje) {
        this.idEmprestimo = cont++;
        this.exemplarEmprestado = exemplarEmprestado;
        this.usuario = usuario;
        this.dataInicio = hoje;
        this.prazoDevolucao = hoje.adicionarDias();
        this.statusEmprestimo = StatusEmprestimo.Ativo;
    }

    /**
     * Registra a devolução
     * @param dataDevolucao data REAL de devolução do exemplar
     */
    public void registrarDevolucao(Data dataDevolucao){
        this.dataDevolucao = dataDevolucao;
        this.statusEmprestimo = StatusEmprestimo.Finalizado;
    }

    /**
     * Calcula a multa de atraso
     * Se houver atrasp a multa é de R$4,00 por dia
     */
    public double multa(){
        if(dataDevolucao==null) return 0.0;
        long dias = prazoDevolucao.calcularDiferencaEmDias(dataDevolucao);
        return (dias>0) ? dias*4.0 : 0.0;
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

    public StatusEmprestimo getStatusEmprestimo() {
        return statusEmprestimo;
    }

    public void setStatusEmprestimo(StatusEmprestimo statusEmprestimo) {
        this.statusEmprestimo = statusEmprestimo;
    }
}
