package br.com.librareasy.app;
import br.com.librareasy.model.Data;
import br.com.librareasy.model.TipoUsuario;
import br.com.librareasy.model.Usuario;
import br.com.librareasy.service.Biblioteca;
import java.util.Scanner;
import static java.lang.System.out;

public class MenuPrincipal {
    private final Biblioteca biblioteca;
    private final Scanner scanner;

    public MenuPrincipal(Biblioteca biblioteca){
        this.biblioteca = biblioteca;
        this.scanner = new Scanner(System.in);
    }

    public void exibir(){
        int opcao;
        boolean rodando = true;

        while (rodando){
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
            opcao = scanner.nextInt();
            procesarOperacao(opcao);
        }
    }

    private void procesarOperacao(int opcao){
        switch (opcao){
            case 1:
                menuCadastrarExemplar();
                break;
            case 2:
                menuRemoverExemplar();
                break;
            case 3:
                menuExibirAcervo();
                break;
            case 4:
                menuCastrarUsuario();
                break;
            case 5:
                menuRemoverUsuario();
                break;
            case 6:

        }
    }

    /**
     * Leitura de tipo de usuário
     * @return tipo de usuário
     */
    private TipoUsuario lerTipo() {
        out.println("Tipo de Usuário: [1] Aluno | [2] Professor | [3] Admin");
        int op = scanner.nextInt();
        scanner.nextLine();
        return switch (op) {
            case 2 -> TipoUsuario.PROFESSOR;
            case 3 -> TipoUsuario.ADMINISTRADOR;
            default -> TipoUsuario.ALUNO;
        };
    }

    private void menuCastrarUsuario(){
        out.println("Nome usuário: ");
        String nome = scanner.nextLine();
        out.println("Tipo usuário: ");
        TipoUsuario tipo = lerTipo();
        try {
            biblioteca.cadastrarUsuario(nome, tipo);
            out.println("Usuário: " + nome + " cadastrado com sucesso");
        } catch (IllegalStateException | IllegalArgumentException e ){
            out.println(e.getMessage());
        }
    }

    private void menuExibirAcervo(){
        biblioteca.exibirAcervo();
    }

    private void menuRemoverExemplar(){
        out.println("Id do exemplar: ");
        int idExemplar = scanner.nextInt();
        try{
            biblioteca.removerExemplar(idExemplar);
            out.println("Exemplar: " + idExemplar + " removido");
        } catch (IllegalArgumentException | IllegalStateException e) {
            out.println(e.getMessage());
        }
    }

    private void menuCadastrarExemplar(){
        out.println("Título: ");
        String titulo = scanner.nextLine();
        out.println("Autor: ");
        String autor = scanner.nextLine();
        out.println("Editora: ");
        String editora = scanner.nextLine();
        out.println("Ano de publicação: ");
        String anoPubli = scanner.nextLine();
        out.println("ISBN: ");
        String isbn = scanner.nextLine();
        try{
            biblioteca.cadastrarExemplar(titulo, autor, editora, anoPubli, isbn);
            out.println("Exemplar: " + titulo + " adicionado");
        } catch (IllegalStateException | IllegalArgumentException e){
            out.println(e.getMessage());
        }
    }

    private void menuRemoverUsuario() {
        out.println("Id usuário: ");
        int idUsuario = scanner.nextInt();
        scanner.nextLine();
        try{
            biblioteca.removerUsuario(idUsuario);
            out.println("Usuário: " + idUsuario + " removido com sucesso");
        } catch (IllegalArgumentException | IllegalStateException e){
            out.println(e.getMessage());
        }
    }

    private void menuRealizarEmprestimo(){
        out.println("Usuário: ");
        String nomeUsuario = scanner.nextLine();
        out.println("Titulo: ");
        String tituloLivro = scanner.nextLine();
        out.println("Dia: ");
        int dia = scanner.nextInt();
        out.println("Mês: ");
        int mes = scanner.nextInt();
        out.println("Ano: ");
        int ano = scanner.nextInt();

        try{
            Data data = new Data(dia, mes, ano);
        } catch (IllegalArgumentException e){
            out.println(e.getMessage());
        }

        try {
            biblioteca.realizarEmprestimo(nomeUsuario, tituloLivro, data);
        }
    }
}
