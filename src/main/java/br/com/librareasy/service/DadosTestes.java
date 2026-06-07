package br.com.librareasy.service;

import br.com.librareasy.model.TipoUsuario;

import static java.lang.System.out;

public class DadosTestes {

  public static void popular(Biblioteca b) {
  // --- USUÁRIOS ---
  String[] nomes = {"Ana Silva", "Ricardo Oliveira", "Beatriz Santos", "Carlos Ferreira",
          "Mariana Costa", "João Pereira", "Fernanda Lima", "Gabriel Souza"};

   for (String nome : nomes) {
           b.cadastrarUsuario(nome, TipoUsuario.ALUNO);
       }
   b.cadastrarUsuario("Dr. Alberto (Coord)", TipoUsuario.ADMINISTRADOR);
   b.cadastrarUsuario("Profa. Helena", TipoUsuario.PROFESSOR);

   // --- ACERVO TÉCNICO E LITERÁRIO ---
     // Formato: Título, Autor, Editora, Ano, ISBN (13 dígitos)
     String[][] obras = {
                         {"Código Limpo", "Robert Martin", "Alta Books", "2009", "9788576082675"},
                         {"Arquitetura Limpa", "Robert Martin", "Alta Books", "2018", "9788550804606"},
                         {"Design Patterns", "GoF", "Addison-Wesley", "1994", "9780201633610"},
                         {"Refatoração", "Martin Fowler", "Novatec", "2019", "9788575227244"},
                         {"Algoritmos", "Thomas Cormen", "Campus", "2012", "9788535236996"},
                         {"Domain-Driven Design", "Eric Evans", "Alta Books", "2003", "9788550814391"},
                         {"O Hobbit", "J.R.R. Tolkien", "HarperCollins", "1937", "9788595084742"},
                         {"1984", "George Orwell", "Companhia das Letras", "1949", "9788535914849"},
                         {"Dom Casmurro", "Machado de Assis", "Principis", "1899", "9788594318602"},
                         {"A Hora da Estrela", "Clarice Lispector", "Rocco", "1977", "9788532530189"}
                     };

         for (String[] o : obras) {
                 // Adiciona 2 exemplares de cada obra para ter estoque
                 b.cadastrarExemplar(o[0], o[1], o[2], o[3], o[4]);
                 b.cadastrarExemplar(o[0], o[1], o[2], o[3], o[4]);
             }

         out.println(">>> Sistema abastecido com " + (obras.length * 2) + " exemplares");
     }
}