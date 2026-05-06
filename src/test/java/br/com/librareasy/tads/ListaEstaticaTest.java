package br.com.librareasy.tads;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ListaEstaticaTest {

    @Test
    void testAddAndRemove() {
        ListaEstatica<String> lista = new ListaEstatica<>(3);
        assertTrue(lista.isEmpty());

        lista.add(0, "A");
        lista.add(1, "C");
        lista.add(1, "B"); // Deve empurrar C para a frente

        assertEquals(3, lista.size());
        assertEquals("A", lista.get(0));
        assertEquals("B", lista.get(1));
        assertEquals("C", lista.get(2));
        assertTrue(lista.isFull());

        assertEquals("B", lista.remove(1));
        assertEquals(2, lista.size());
        assertEquals("C", lista.get(1));
    }

    @Test
    void testSetAndGet() {
        ListaEstatica<Integer> lista = new ListaEstatica<>(5);
        lista.add(0, 10);
        lista.set(0, 20);
        assertEquals(20, lista.get(0));
    }

    @Test
    void testExceptions() {
        ListaEstatica<Integer> lista = new ListaEstatica<>(1);
        
        // Teste de lista vazia
        assertThrows(IllegalStateException.class, () -> lista.remove(0));
        
        lista.add(0, 1);
        
        // Teste de lista cheia
        assertThrows(IllegalStateException.class, () -> lista.add(1, 2));
        
        // Teste de índice inválido
        assertThrows(IndexOutOfBoundsException.class, () -> lista.get(5));
    }

    @Test
    void testToString() {
        ListaEstatica<Integer> lista = new ListaEstatica<>(3);
        assertEquals("[]", lista.toString());
        lista.add(0, 1);
        lista.add(1, 2);
        assertEquals("[1, 2]", lista.toString());
    }
}
