package br.com.librareasy.tads;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class FilaEstaticaTest {

    @Test
    void testEnqueueAndDequeue() {
        FilaEstatica<Integer> fila = new FilaEstatica<>(3);
        assertTrue(fila.isEmpty());

        fila.enqueue(10);
        fila.enqueue(20);
        fila.enqueue(30);

        assertTrue(fila.isFull());
        assertEquals(10, fila.peek());

        assertEquals(10, fila.dequeue());
        assertEquals(20, fila.peek());
        assertEquals(20, fila.dequeue());
        assertEquals(30, fila.dequeue());
        assertTrue(fila.isEmpty());
    }

    @Test
    void testFullQueueException() {
        FilaEstatica<Integer> fila = new FilaEstatica<>(1);
        fila.enqueue(1);
        assertThrows(IllegalStateException.class, () -> fila.enqueue(2));
    }

    @Test
    void testEmptyQueueException() {
        FilaEstatica<Integer> fila = new FilaEstatica<>(1);
        assertThrows(IllegalStateException.class, () -> fila.dequeue());
        assertThrows(IllegalStateException.class, () -> fila.peek()); // Atual nota: sua implementação atual usa IllegalArgumentException para peek
    }

    @Test
    void testToString() {
        FilaEstatica<String> fila = new FilaEstatica<>(3);
        fila.enqueue("Primeiro");
        fila.enqueue("Segundo");
        // Nota: O seu toString atual tem o sufixo "<- topo", vamos testar como está
        assertEquals("[Primeiro, Segundo] <- fim", fila.toString());
    }
}
