package br.com.librareasy.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

class DataTest {

    @Test
     void testConstrutorVazioPegaHoje() {
        Data hoje = new Data();
        LocalDate agora = LocalDate.now();
        
        assertEquals(agora.getDayOfMonth(), hoje.getDia());
        assertEquals(agora.getMonthValue(), hoje.getMes());
        assertEquals(agora.getYear(), hoje.getAno());
    }

    @Test
     void testAdicionarDias() {
        Data data = new Data(1, 1, 2026);
        Data novaData = data.adicionarDias();
        
        assertEquals(8, novaData.getDia());
        assertEquals(1, novaData.getMes());
        assertEquals(2026, novaData.getAno());
        
        // Verificar se a original não mudou (imutabilidade)
        assertEquals(1, data.getDia());
    }

    @Test
     void testDiferencaDiasAtraso() {
        Data prazo = new Data(10, 5, 2026);
        Data entrega = new Data(15, 5, 2026);
        
        long diferenca = prazo.calcularDiferencaEmDias(entrega);
        assertEquals(5, diferenca, "Deveria ter 5 dias de atraso");
    }

    @Test
     void testDiferencaDiasAdiantado() {
        Data prazo = new Data(10, 5, 2026);
        Data entrega = new Data(8, 5, 2026);
        
        long diferenca = prazo.calcularDiferencaEmDias(entrega);
        assertEquals(-2, diferenca, "Deveria estar 2 dias adiantado");
    }

    @Test
     void testDataInvalida() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Data(31, 2, 2026); // Fevereiro não tem 31
        });
    }

    @Test
     void testToStringFormatado() {
        Data data = new Data(5, 9, 2026);
        assertEquals("05/09/2026", data.toString());
    }
}
