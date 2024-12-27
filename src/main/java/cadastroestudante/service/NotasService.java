package cadastroestudante.service;

import cadastroestudante.entity.Notas;
import cadastroestudante.repository.NotasRepository;

import java.util.Objects;
import java.util.Scanner;

public class NotasService {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MENU = "Escolha a opcao:\n1. Encontrar as notas de um estudante\n2. Atualizar as notas de um estudante\n3. Adicionar notas a um estudante\n4. Deletar as notas de um estudante\n0. Sair";

    public static void showMenu() {
        while (true) {
            System.out.println(MENU);
            int escolha = Integer.parseInt(SCANNER.nextLine());
            if (escolha == 0) break;

            switch (escolha) {
                case 1:
                    showFind();
                    break;
                case 2:
                    showUpdate();
                    break;
                case 3:
                    showSave();
                    break;
                case 4:
                    showDelete();
                    break;
            }
        }
    }

    private static void showFind() {
        System.out.println("Digite o id do estudante para encontrar susa nota");
        int id = Integer.parseInt(SCANNER.nextLine());

        Notas notas = findByStudentId(id);
        if (Objects.isNull(notas)) {
            System.out.println("Nao foram encontradas notas do estudante com id " + id);
            return;
        }

        System.out.println(notas);

    }

    public static Notas findByStudentId(int id) {
        idValidate(id);
        return NotasRepository.findByStudentId(id);
    }

    private static void showUpdate() {

    }

    private static void showSave() {

    }

    private static void showDelete() {

    }

    private static void idValidate(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invalido");
        }
    }
}
