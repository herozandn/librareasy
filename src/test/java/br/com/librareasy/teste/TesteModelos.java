package br.com.librareasy.teste;

import br.com.librareasy.model.*;
import br.com.librareasy.tad.Data;

public class TesteModelos {
    public static void main(String[] args) {
        // --- CENÁRIO 1: TESTE DE EMPRÉSTIMO ---
        System.out.println("--- INICIANDO TESTE DE EMPRÉSTIMO ---");
        Data dataHoje = new Data(2, 5, 2026);
        Data dataDevolucao = new Data(15, 5, 2026);

        Usuario aluno = new Usuario("111.222.333-44", "Gabriel da Silva", "11999999999", TipoUsuario.Aluno, 0.0);
        Livro livroPOO = new Livro(1, "Java - Como Programar", "Deitel", "Pearson", 2016);

        System.out.println("Livro 1: " + livroPOO.getTitulo());
        System.out.println("Status inicial: " + livroPOO.getEstadoLivro() + "\n");

        Emprestimo novoEmprestimo = new Emprestimo(livroPOO, aluno, dataHoje, dataDevolucao);
        livroPOO.setEstadoLivro(StatusLivro.Emprestado);
        int diasPrazo = dataHoje.calcularDiferencaEmDias(dataDevolucao);

        System.out.println("Livro 1 (Pós-operação): " + novoEmprestimo.getLivro().getTitulo());
        System.out.println("Status atual: " + novoEmprestimo.getLivro().getEstadoLivro());
        System.out.println("Prazo do " + aluno.getTipo() + ": " + diasPrazo + " dias.\n");


        // --- CENÁRIO 2: TESTE DE RESERVA ---
        System.out.println("--- INICIANDO TESTE DE RESERVA ---");

        // Criando um novo usuário (Professor) e um novo Livro
        Usuario professor = new Usuario("555.666.777-88", "Sávio da Costa", "11988888888", TipoUsuario.Professor, 0.0);
        Livro livroBD = new Livro(2, "Sistemas de Banco de Dados", "Elmasri", "Pearson", 2011);

        System.out.println("Livro 2: " + livroBD.getTitulo());
        System.out.println("Status inicial: " + livroBD.getEstadoLivro() + "\n");

        // Registrando a reserva
        Reserva novaReserva = new Reserva(livroBD, professor, dataHoje);

        // Atualizando o status do livro para refletir a reserva
        livroBD.setEstadoLivro(StatusLivro.Reservado);

        // Validando os dados
        System.out.println("Reserva feita por: " + novaReserva.getUsuario().getTipo() + " (Nome: " + novaReserva.getUsuario().getNomeCompleto() + ")");
        System.out.println("Livro 2 (Pós-operação): " + novaReserva.getLivro().getTitulo());
        System.out.println("Status atual: " + novaReserva.getLivro().getEstadoLivro());
    }
}