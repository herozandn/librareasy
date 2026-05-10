package br.com.librareasy.tad;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Data {
    private int dia;
    private int mes;
    private int ano;

    public Data(int dia, int mes, int ano) {
        int anoAtual = LocalDate.now().getYear();
        if(ano < anoAtual || ano > anoAtual + 20){
            throw new IllegalArgumentException("Ano inválido. Deve estar entre " + anoAtual + " e " + (anoAtual + 20));
        }

        try{
            LocalDate.of(ano, mes, dia);
        } catch (DateTimeException e){
            throw new IllegalArgumentException("A data informada (" + dia + "/" + mes + "/" + ano + ") não existe no calendário.");
        }
        this.dia = dia;
        this.mes = mes;
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

    public long calcularDiferencaEmDias(Data dataDevolucaoReal){
        LocalDate dataEsperada = LocalDate.of(this.ano, this.mes, this.dia);
        LocalDate dataReal = LocalDate.of(dataDevolucaoReal.getAno(), dataDevolucaoReal.getMes(), dataDevolucaoReal.getDia());

        long diferenca = ChronoUnit.DAYS.between(dataEsperada, dataReal);

        if(diferenca < 0){
            throw new IllegalArgumentException("Data de devolução não pode ser anterior à data de retirada!");
        }

        return diferenca;
    }
}
