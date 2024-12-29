package cadastroestudante.application;


import cadastroestudante.service.EstudanteService;
import cadastroestudante.service.NotasService;

import java.util.Scanner;

public class App {
    private static final String MENU = "Esse é o sistema de cadastro de estudantes e suas notas\nEscolha a opção desejada:\n1. Estudante\n2. Notas\n0. Sair";
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println(MENU);
            int escolha = Integer.parseInt(SCANNER.nextLine());

            if (escolha == 0) {
                System.out.println("Saindo...");
                break;
            }

            switch (escolha) {
                case 1:
                    EstudanteService.showMenu();
                    break;
                case 2:
                    NotasService.showMenu();
                    break;
            }
        }
    }
}
