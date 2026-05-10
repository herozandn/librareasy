package br.com.librareasy.model;

public class Usuario {
    private int idUsuario;
    private String nome;
    private TipoUsuario tipo;
    private double multaAcumulada;
    private Emprestimo[] emprestimosAtuais;

    public Usuario(int idUsuario, String nome, TipoUsuario tipo, double multaAcumulada) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.multaAcumulada = multaAcumulada;
        this.tipo = tipo;
        this.emprestimosAtuais = new Emprestimo[tipo.getLimiteLivros()];
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public TipoUsuario getTipo() {
        return tipo;
    }

    public double getMultaAcumulada() {
        return multaAcumulada;
    }

    public void adicionarMulta(double valor){
        if(valor > 0){
            this.multaAcumulada += valor;
        }
    }

    public void pagarMulta(double valorPago) {
        if (valorPago <= 0){
            return;
        }
        if(valorPago >= this.multaAcumulada){
            this.multaAcumulada = 0.0;
        } else {
            throw new IllegalArgumentException("Pagamentos parciais não são aceitos.");
        }
    }

    public Emprestimo[] getEmprestimosAtuais() {
        return emprestimosAtuais;
    }
}