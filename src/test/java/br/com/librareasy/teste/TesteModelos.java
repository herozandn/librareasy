/**
package br.com.librareasy.teste;

import br.com.librareasy.model.*;

public class TesteModelos {

    public static void main(String[] args) {
        System.out.println("=== INICIANDO BATERIA DE TESTES: LIBRAREASY ===\n");

        testarTadData();
        System.out.println("-------------------------------------------------");
        testarModelosERelacionamentos();
    }

    private static void testarTadData() {
        System.out.println("[TESTE 1] Validando o TAD Data...");

        try {
            Data dataValida = new Data(15, 5, 2026);
            System.out.println("✅ Data válida criada com sucesso: " + dataValida.getDia() + "/" + dataValida.getMes() + "/" + dataValida.getAno());
        } catch (Exception e) {
            System.out.println("❌ Erro inesperado ao criar data válida: " + e.getMessage());
        }

        try {
            System.out.println("❌ Falha de segurança: O sistema aceitou um ano fora do limite!");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Bloqueio de ano inválido funcionou: " + e.getMessage());
        }

        try {
            System.out.println("❌ Falha de segurança: O sistema aceitou o dia 31 de fevereiro!");
        } catch (IllegalArgumentException e) {
            System.out.println("✅ Bloqueio de data inexistente funcionou: " + e.getMessage());
        }

        try {
            Data retirada = new Data(28, 2, 2026); // Ano não bissexto
            Data devolucao = new Data(1, 3, 2026);
            long diferenca = retirada.calcularDiferencaEmDias(devolucao);

            if (diferenca == 1) {
                System.out.println("✅ Cálculo de dias exato! A diferença entre 28/02/2026 e 01/03/2026 é de " + diferenca + " dia.");
            } else {
                System.out.println("❌ Cálculo de dias falhou. Retornou: " + diferenca);
            }
        } catch (Exception e) {
            System.out.println("❌ Erro no cálculo de dias: " + e.getMessage());
        }
    }

    private static void testarModelosERelacionamentos() {
        System.out.println("[TESTE 2] Validando Modelos (Catálogo vs Físico) e Usuários...");

        try {
            Livro livroSenhorAneis = new Livro("O Senhor dos Anéis", "J.R.R. Tolkien", "HarperCollins", 1954);
            System.out.println("✅ Livro (Catálogo) criado: " + livroSenhorAneis.getTitulo());

            Exemplar exemplar101 = new Exemplar(livroSenhorAneis, StatusLivro.Disponivel, EstadoConservacao.BOM, "9859698657845");
            System.out.println("✅ Exemplar criado. ID do Exemplar: " + exemplar101.getIdExemplar() + " | Obra vinculada: " + exemplar101.getLivro().getTitulo());

            Usuario aluno = new Usuario("João Aluno", TipoUsuario.ALUNO);
            Usuario professor = new Usuario("Maria Professora", TipoUsuario.PROFESSOR);

            Data dataInicio = new Data(10, 5, 2026);
            Data prazo = new Data(17, 5, 2026);
            Emprestimo emprestimo = new Emprestimo(exemplar101, aluno, dataInicio, prazo, StatusLivro.Emprestado);
            System.out.println("✅ Empréstimo registrado com sucesso!");
            System.out.println("   -> Quem pegou: " + emprestimo.getUsuario().getNome());
            System.out.println("   -> Código do objeto físico levado: " + emprestimo.getExemplarEmprestado().getIdExemplar());

            Reserva reserva = new Reserva(livroSenhorAneis, professor, new Data(12, 5, 2026));
            System.out.println("✅ Reserva registrada com sucesso!");
            System.out.println("   -> Quem reservou: " + reserva.getUsuario().getNome());
            System.out.println("   -> O que quer ler: " + reserva.getLivro().getTitulo() + " (Qualquer exemplar serve)");

            System.out.println("\n--- Validando Regras Financeiras ---");
        } catch (Exception e) {
            System.out.println("❌ Erro catastrófico ao criar relacionamentos: " + e.getMessage());
        }
    }
}
 */