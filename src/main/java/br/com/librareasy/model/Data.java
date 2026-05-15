package br.com.librareasy.model;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Data {
    private final int dia;
    private final int mes;
    private final int ano;

    /*
    * Construtor manual
    * Para testes
     */
    public Data(int dia, int mes, int ano) {
        validarData(dia, mes, ano);
        this.dia = dia;
        this.mes = mes;
        this.ano = ano;
    }

    /*
    * Construtor automático
    * Usado efetivamente no sistema
     */
    public Data() {
        this(LocalDate.now().getDayOfMonth(),
                LocalDate.now().getMonthValue(),
                LocalDate.now().getYear());
    }

    /**
     * Valida a data entrada
     * @param dia, mes, ano são os dias entrados
     * @throws IllegalArgumentException se data inválida
     */
    private static void validarData(int dia, int mes, int ano) {
        int anoAtual = LocalDate.now().getYear();
        if (ano < anoAtual || ano > anoAtual + 20) {
            throw new IllegalArgumentException("Ano inválido. Deve estar entre " + anoAtual + " e " + (anoAtual + 20));
        }
        try {
            LocalDate.of(ano, mes, dia);
        } catch (DateTimeException e) {
            throw new IllegalArgumentException("A data informada (" + dia + "/" + mes + "/" + ano + ") não existe no calendário.");
        }
    }

    /**
     * @return Data atual do sistema
     */
    public static Data hoje() {
        return new Data();
    }

    /**
     * Calcula o prazo de devolução (por padrão 7 sete dias após a data de retirada)
     * @return Data
     */
    public Data adicionarDias() {
        LocalDate dataAtual = LocalDate.of(this.ano, this.mes, this.dia);
        LocalDate novaData = dataAtual.plusDays(7);
        return new Data(novaData.getDayOfMonth(), novaData.getMonthValue(), novaData.getYear());
    }

    public int getDia() {return dia;}
    public int getMes() {return mes;}
    public int getAno() {return ano;}

    /**
     * Calcula a diferença entre a data de retirada e de devolução
     * @param dataDevolucaoReal Data de devolução esperada
     * @return diferenca
     */
    public long calcularDiferencaEmDias(Data dataDevolucaoReal) {
        LocalDate dataEsperada = LocalDate.of(this.ano, this.mes, this.dia);
        LocalDate dataReal = LocalDate.of(dataDevolucaoReal.getAno(), dataDevolucaoReal.getMes(), dataDevolucaoReal.getDia());

        return ChronoUnit.DAYS.between(dataEsperada, dataReal);
    }

    @Override
    public String toString() {
        return String.format("%02d/%02d/%04d", dia, mes, ano);
    }
}
