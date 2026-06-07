package br.com.librareasy.service;

import br.com.librareasy.model.Data;
import br.com.librareasy.model.TipoUsuario;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

public class BibliotecaSeedDataTest {
    private Biblioteca biblioteca;
    private Data hoje;

    @BeforeEach
    void setUp() {
        // Inicializa a biblioteca com capacidades confortáveis
        biblioteca = new Biblioteca(50, 50, 100, 200, 50, 1000);
        hoje = new Data();
    }

    @Test
    @DisplayName("Abastecer biblioteca com dados iniciais para testes de integração")
    void abastecerBiblioteca() {
        assertDoesNotThrow(() -> {
            // 1. Cadastrar Usuários de diferentes tipos
            biblioteca.cadastrarUsuario("Ana Silva", TipoUsuario.ALUNO);
            biblioteca.cadastrarUsuario("Prof. Ricardo", TipoUsuario.PROFESSOR);
            biblioteca.cadastrarUsuario("Admin Master", TipoUsuario.ADMINISTRADOR);
            biblioteca.cadastrarUsuario("Bruno Souza", TipoUsuario.ALUNO);

            // 2. Cadastrar Livros e Exemplares (Acervo)
            // Livro 1 com 2 exemplares
            biblioteca.cadastrarExemplar("O Codificador Limpo", "Robert C. Martin", "Alta Books", "2012", "978-8576086475");
            biblioteca.cadastrarExemplar("O Codificador Limpo", "Robert C. Martin", "Alta Books", "2012", "978-8576086475");

            // Livro 2 com 1 exemplar
            biblioteca.cadastrarExemplar("Arquitetura Limpa", "Robert C. Martin", "Alta Books", "2018", "978-8550804606");

            // Livro 3 com 1 exemplar
            biblioteca.cadastrarExemplar("Design Patterns", "Erich Gamma", "Addison-Wesley", "1994", "978-0201633610");

            // 3. Simular algumas operações iniciais
            // Ana Silva pega "O Codificador Limpo"
            biblioteca.realizarEmprestimo("Ana Silva", "O Codificador Limpo", hoje);

            // Prof. Ricardo pega "Arquitetura Limpa"
            biblioteca.realizarEmprestimo("Prof. Ricardo", "Arquitetura Limpa", hoje);

            // Bruno Souza tenta pegar "Arquitetura Limpa" (não disponível), então reserva
            biblioteca.realizarReserva("Bruno Souza", "Arquitetura Limpa", hoje);

            System.out.println("Biblioteca abastecida com sucesso para o cenário de teste!");
            biblioteca.exibirAcervo();
        });
    }
}
