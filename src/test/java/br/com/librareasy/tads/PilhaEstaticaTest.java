package br.com.librareasy.tads;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PilhaEstaticaTest {

    @Test
    void testPushAndPop() {
        PilhaEstatica<Integer> pilha = new PilhaEstatica<>(3);
        assertTrue(pilha.isEmpty());
        assertFalse(pilha.isFull());

        pilha.push(10);
        pilha.push(20);
        pilha.push(30);

        assertEquals(3, pilha.size());
        assertTrue(pilha.isFull());
        assertEquals(30, pilha.show());

        assertEquals(30, pilha.pop());
        assertEquals(20, pilha.pop());
        assertEquals(10, pilha.pop());
        assertTrue(pilha.isEmpty());
    }

    @Test
    void testStackOverflow() {
        PilhaEstatica<Integer> pilha = new PilhaEstatica<>(1);
        pilha.push(1);
        assertThrows(IllegalStateException.class, () -> pilha.push(2));
    }

    @Test
    void testStackUnderflow() {
        PilhaEstatica<Integer> pilha = new PilhaEstatica<>(1);
        assertThrows(IllegalStateException.class, pilha::pop);
    }

    @Test
    void testShowOnEmpty() {
        PilhaEstatica<Integer> pilha = new PilhaEstatica<>(1);
        assertThrows(IllegalStateException.class, pilha::show);
    }
}
