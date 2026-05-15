package br.com.librareasy.model;

public class Emprestimo {
    private static int cont;
    private final int idEmprestimo;
    private Exemplar exemplarEmprestado;
    private Usuario usuario;
    private Data dataInicio;
    private Data prazoDevolucao;
    private StatusEmprestimo statusEmprestimo;

    public Emprestimo(Exemplar exemplarEmprestado, Usuario usuario, Data hoje) {
        this.idEmprestimo = cont++;
        this.exemplarEmprestado = exemplarEmprestado;
        this.usuario = usuario;
        this.dataInicio = hoje;
        this.prazoDevolucao = hoje.adicionarDias();
        this.statusEmprestimo = StatusEmprestimo.Ativo;
    }

    /**
     * Calculo de multa
     * @return
     */

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
