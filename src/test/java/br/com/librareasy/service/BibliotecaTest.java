package br.com.librareasy.service;

import br.com.librareasy.model.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BibliotecaTest {
    private Biblioteca biblioteca;
    private Usuario aluno;
    private Data hoje;

    @BeforeEach
    void setUp() {
        // Capacidades: 10 livros, 10 usuários, 20 exemplares, 50 histórico, 20 reservas, 100 logs
        biblioteca = new Biblioteca(10, 10, 20, 50, 20, 100);
        biblioteca.cadastrarUsuario("Joao", TipoUsuario.ALUNO);
        hoje = new Data();
    }

    @Test
    void testEmprestimoESucesso() {
        biblioteca.cadastrarExemplar("Java Clean Code", "Robert Martin", "Prentice Hall", "2008", "12345");
        
        // Realiza empréstimo
        biblioteca.realizarEmprestimo("João", "Java Clean Code", hoje);
        
        // Verifica se o status do exemplar mudou

        // Verifica se o usuário tem o empréstimo na lista dele
        assertFalse(aluno.getEmprestimosAtuais().isEmpty());
    }

    @Test
    void testDevolucaoEReserva() {
        biblioteca.cadastrarExemplar("Design Patterns", "GoF", "Addison-Wesley", "1994", "67890");
        Usuario maria = new Usuario("Maria", TipoUsuario.ALUNO);
        
        // João pega o livro
        biblioteca.realizarEmprestimo("João", "Design Patterns", hoje);
        
        // Maria tenta pegar, mas não tem disponível, então ela reserva
        // biblioteca.realizarReserva(maria, "Design Patterns", hoje);
        
        // João devolve
        // biblioteca.realizarDevolucao(1, hoje); // Supondo ID 1
        
        // Verifica se a reserva da Maria virou um empréstimo automático (conforme sua lógica)
        // ...
    }
}
