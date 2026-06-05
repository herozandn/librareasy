package br.com.librareasy.model;

public class LogOperacao {
    private final Data data;
    private final String descricao;
    private final String tipoOperacao;

    public LogOperacao(String tipoOperacao, String descricao) {
        this.data = new Data();
        this.tipoOperacao = tipoOperacao;
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return String.format("[%s] %-12s: %s", data, tipoOperacao, descricao);
    }
}
