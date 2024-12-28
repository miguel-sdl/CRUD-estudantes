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
        System.out.println("Digite o id do estudante para encontrar sua nota");
        int id = Integer.parseInt(SCANNER.nextLine());

        Notas notas = findByStudentId(id);
        if (Objects.isNull(notas)) {
            System.out.println("Nao foram encontradas notas do estudante com id " + id);
            return;
        }

        System.out.println(notas);

    }

    private static void showUpdate() {
        System.out.println("Digite o id do estudante que tera suas notas atualizadas");
        int id = Integer.parseInt(SCANNER.nextLine());
        idValidate(id);

        System.out.println("Digite a nova nota de portugues");
        double portugues = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nova nota de matematica");
        double matematica = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nova nota de historia");
        double historia = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nova nota de geografia");
        double geografia = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nova nota de fisica");
        double fisica = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nova nota de quimica");
        double quimica = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nova nota de biologia");
        double biologia = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nova nota de ingles");
        double ingles = Double.parseDouble(SCANNER.nextLine());


        Notas notas = Notas.builder()
                .idEstudante(id)
                .portugues(portugues)
                .matematica(matematica)
                .historia(historia)
                .geografia(geografia)
                .fisica(fisica)
                .quimica(quimica)
                .biologia(biologia)
                .ingles(ingles)
                .build();


        int rowsAffected = update(notas);

        if (rowsAffected == 0) {
            System.out.println("Nao foi encontrado estudante para atualizar");
        } else {
            System.out.println("Notas do estudante atualizadas com sucesso");
        }


    }

    private static void showSave() {
        System.out.println("Digite o id do estudante que tera suas notas salvas");
        int id = Integer.parseInt(SCANNER.nextLine());
        idValidate(id);

        if (Objects.nonNull(findByStudentId(id))) {
            System.out.println("As notas do estudante com id " + id + " ja existem");
            return;
        }

        if (Objects.isNull(EstudanteService.findById(id))) {
            System.out.println("Nao existe estudante com id " + id);
            return;
        }

        System.out.println("Digite a nota de portugues");
        double portugues = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nota de matematica");
        double matematica = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nota de historia");
        double historia = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nota de geografia");
        double geografia = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nota de fisica");
        double fisica = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nota de quimica");
        double quimica = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nota de biologia");
        double biologia = Double.parseDouble(SCANNER.nextLine());
        System.out.println("Digite a nota de ingles");
        double ingles = Double.parseDouble(SCANNER.nextLine());


        Notas notas = Notas.builder()
                .idEstudante(id)
                .portugues(portugues)
                .matematica(matematica)
                .historia(historia)
                .geografia(geografia)
                .fisica(fisica)
                .quimica(quimica)
                .biologia(biologia)
                .ingles(ingles)
                .build();

        int rowsAffected = save(notas);

        if (rowsAffected == 1) {
            System.out.println("Notas salva com sucesso");
        }
    }

    private static void showDelete() {

    }


    public static Notas findByStudentId(int id) {
        idValidate(id);
        return NotasRepository.findByStudentId(id);
    }

    public static int update(Notas notas) {
        idValidate(notas.getIdEstudante());
        if (!notas.isValid()) throw new RuntimeException("Nota invalida");
        return NotasRepository.update(notas);
    }

    public static int save(Notas notas) {
        if (!notas.isValid()) throw new RuntimeException("Nota invalida");
        return NotasRepository.save(notas);
    }

    private static void idValidate(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invalido");
        }
    }
}
