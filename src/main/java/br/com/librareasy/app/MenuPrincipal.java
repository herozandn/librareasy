package br.com.librareasy.app;

import br.com.librareasy.model.Data;
import br.com.librareasy.model.TipoUsuario;
import br.com.librareasy.service.Biblioteca;
import java.util.Scanner;
import static java.lang.System.out;

public class MenuPrincipal {
    private final Biblioteca biblioteca;
    private final Scanner scanner;
    private boolean rodando = true;

    public MenuPrincipal(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
        this.scanner = new Scanner(System.in);
    }

    public void exibir(){
        while (rodando){
            out.println("\n=== SISTEMA LIBRAEASY ===");
            out.println("[1] - Cadastrar exemplar");
            out.println("[2] - Remover exemplar");
            out.println("[3] - Exibir acervo");
            out.println("[4] - Cadastrar usuário");
            out.println("[5] - Remover usuário");
            out.println("[6] - Realizar empréstimo");
            out.println("[7] - Realizar Devolução");
            out.println("[8] - Renovar empréstimo");
            out.println("[9] - Realizar reserva");
            out.println("[0] - Sair");
            out.print("Opção: ");
            
            if (scanner.hasNextInt()) {
                int opcao = scanner.nextInt();
                scanner.nextLine();
                procesarOperacao(opcao);
            } else {
                out.println("Opção inválida.");
                scanner.nextLine();
            }
        }
    }

    private void procesarOperacao(int opcao){
        switch (opcao){
            case 1: menuCadastrarExemplar(); break;
            case 2: menuRemoverExemplar(); break;
            case 3: menuExibirAcervo(); break;
            case 4: menuCastrarUsuario(); break;
            case 5: menuRemoverUsuario(); break;
            case 6: menuRealizarEmprestimo(); break;
            case 7: menuRealizarDevolucao(); break;
            case 8: menuRenovarEmprestimo(); break;
            case 9: menuRealizarReserva(); break;
            case 0: 
                out.println("Saindo...");
                this.rodando = false; 
                break;
            default: out.println("Opção não encontrada"); break;
        }
    }

    private TipoUsuario lerTipo() {
        out.println("Tipo: [1] Aluno | [2] Professor | [3] Admin");
        int op = scanner.nextInt();
        scanner.nextLine();
        if (op == 2) return TipoUsuario.PROFESSOR;
        if (op == 3) return TipoUsuario.ADMINISTRADOR;
        return TipoUsuario.ALUNO;
    }

    private void menuCastrarUsuario(){
        out.print("Nome: ");
        String nome = scanner.nextLine();
        TipoUsuario tipo = lerTipo();
        try {
            biblioteca.cadastrarUsuario(nome, tipo);
            out.println("Sucesso!");
        } catch (Exception e ){
            out.println("Erro: " + e.getMessage());
        }
    }

    private void menuExibirAcervo(){
        biblioteca.exibirAcervo();
    }

    private void menuRemoverExemplar(){
        out.print("ID Exemplar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            biblioteca.removerExemplar(id);
            out.println("Removido!");
        } catch (Exception e) {
            out.println("Erro: " + e.getMessage());
        }
    }

    private void menuCadastrarExemplar(){
        out.print("Título: "); String tit = scanner.nextLine();
        out.print("Autor: "); String aut = scanner.nextLine();
        out.print("Editora: "); String edi = scanner.nextLine();
        out.print("Ano: "); String ano = scanner.nextLine();
        out.print("ISBN: "); String isbn = scanner.nextLine();
        try{
            biblioteca.cadastrarExemplar(tit, aut, edi, ano, isbn);
            out.println("Adicionado!");
        } catch (Exception e){
            out.println("Erro: " + e.getMessage());
        }
    }

    private void menuRemoverUsuario() {
        out.print("ID Usuário: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            biblioteca.removerUsuario(id);
            out.println("Removido!");
        } catch (Exception e){
            out.println("Erro: " + e.getMessage());
        }
    }

    private void menuRealizarEmprestimo(){
        out.print("Usuário: "); String user = scanner.nextLine();
        out.print("Livro: "); String livro = scanner.nextLine();
        out.print("Dia: "); int d = scanner.nextInt();
        out.print("Mês: "); int m = scanner.nextInt();
        out.print("Ano: "); int a = scanner.nextInt();
        scanner.nextLine();
        try {
            biblioteca.realizarEmprestimo(user, livro, new Data(d, m, a));
            out.println("Empréstimo concluído");
        } catch (Exception e){
            out.println("Erro: " + e.getMessage());
        }
    }

    public void menuRealizarDevolucao(){
        out.print("ID Exemplar: "); int id = scanner.nextInt();
        out.print("Dia: "); int d = scanner.nextInt();
        out.print("Mês: "); int m = scanner.nextInt();
        out.print("Ano: "); int a = scanner.nextInt();
        scanner.nextLine();
        try {
            biblioteca.realizarDevolucao(id, new Data(d, m, a));
            out.println("Devolução realizada");
        } catch (Exception e){
            out.println("Erro: " + e.getMessage());
        }
    }

    public void menuRenovarEmprestimo(){
        out.print("ID Exemplar: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        try{
            biblioteca.renovarEmprestimo(id);
            out.println("Renovação concluída");
        }catch (Exception e){
            out.println("Erro: " + e.getMessage());
        }
    }

    public void menuRealizarReserva(){
        out.print("Usuário: "); String user = scanner.nextLine();
        out.print("Livro: "); String livro = scanner.nextLine();
        out.print("Dia: "); int d = scanner.nextInt();
        out.print("Mês: "); int m = scanner.nextInt();
        out.print("Ano: "); int a = scanner.nextInt();
        scanner.nextLine();
        try {
            biblioteca.realizarReserva(user, livro, new Data(d, m, a));
            out.println("Reserva realizada");
        } catch (Exception e){
            out.println("Erro: " + e.getMessage());
        }
    }
}
