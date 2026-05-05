package br.com.librareasy.model;

public class Usuario {
    private String cpf;
    private String nomeCompleto;
    private String numTel;
    private TipoUsuario tipo;
    private double multaAcumulada;
    private Emprestimo[] emprestimosAtuais;

    public Usuario(String cpf, String nomeCompleto, String numTel, TipoUsuario tipo, double multaAcumulada) {
        this.cpf = cpf;
        this.nomeCompleto = nomeCompleto;
        this.numTel = numTel;
        this.multaAcumulada = multaAcumulada;
        switch (tipo){
            case Aluno:
                this.tipo = tipo;
                this.emprestimosAtuais = new Emprestimo[3];
                break;
            case Professor:
                this.tipo = tipo;
                this.emprestimosAtuais = new Emprestimo[10];
                break;
            case Administrador:
                this.tipo = tipo;
                this.emprestimosAtuais = new Emprestimo[10];
                break;
            default:
                throw new IllegalArgumentException("Tipo de usuário inválido.");
        }
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public void setTipo(TipoUsuario tipo) {
        this.tipo = tipo;
    }

    public double getMultaAcumulada() {
        return multaAcumulada;
    }

    public void setMultaAcumulada(double multaAcumulada) {
        this.multaAcumulada = multaAcumulada;
    }

    public Emprestimo[] getEmprestimosAtuais() {
        return emprestimosAtuais;
    }

    public void setEmprestimosAtuais(Emprestimo[] emprestimosAtuais) {
        this.emprestimosAtuais = emprestimosAtuais;
    }
}