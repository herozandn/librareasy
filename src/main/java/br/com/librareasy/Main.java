package br.com.librareasy;

import br.com.librareasy.app.MenuPrincipal;
import br.com.librareasy.service.*;

public class Main {
    public static void main(String[] args) {
        // Inicializa a biblioteca com as capacidades definidas
        Biblioteca biblioteca = new Biblioteca();
        
        // Popula com dados iniciais
        DadosTestes.popular(biblioteca);
        
        // Inicia o menu principal
        MenuPrincipal menu = new MenuPrincipal(biblioteca);
        menu.exibir();
    }
}
