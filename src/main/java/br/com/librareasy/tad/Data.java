package br.com.librareasy.tad;

public class Data {
    int dia;
    int mes;
    int ano;

    public Data(int dia, int mes, int ano) {
        if(dia < 1 || dia > 30)
            throw new IllegalArgumentException("Dia inválido.");
        else
            this.dia = dia;
        if(mes < 1 || mes > 12)
            throw new IllegalArgumentException("Mês inválido");
        else
            this.mes = mes;
        if(ano < 2026 || ano > 2050)
            throw new IllegalArgumentException("Ano inválido");
        else
            this.ano = ano;
    }

    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }

    public boolean isBissexto(){
        return (ano % 4 == 0 && ano % 100 != 0) || (ano % 400 == 0);
    }

    public int calcularDiferencaEmDias(Data dataEscolhida){
        int totalDiasData1 = (ano * 360) + (mes * 30) + dia;
        int totalDiasData2 = (dataEscolhida.getAno() * 360) + (dataEscolhida.getMes() * 30) + dataEscolhida.getDia();

        int diferenca = totalDiasData2 - totalDiasData1;

        return (diferenca < 0) ? -diferenca : diferenca;
    }
}
