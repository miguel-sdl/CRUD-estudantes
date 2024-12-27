package cadastroestudante.service;

import cadastroestudante.entity.Estudante;
import cadastroestudante.entity.Notas;
import cadastroestudante.repository.EstudanteRepository;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class EstudanteService {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MENU = "Escolha a operacao:\n1. Encontrar um estudante\n2. Atualizar as informacoes do estudante\n3. Adicionar um novo estudante\n4.Deletar um estudan\n0. Sair";


    public static void showMenu() {
        while (true) {
            System.out.println(MENU);
            int escolha = Integer.parseInt(SCANNER.nextLine());
            if (escolha == 0) break;

            switch (escolha) {
                case 1:
                    showFindStudent();
                    break;
                case 2:
                    showUpdateStudent();
                    break;
                case 3:
                    showSaveStudent();
                    break;
                case 4:
                    showDeleteStudent();
                    break;
            }
        }
    }

    private static void showFindStudent() {
        System.out.println("Digite o nome para ser procurado ou pressione enter para encontrar todos estudantes");
        String nome = SCANNER.nextLine();
        List<Estudante> estudantes = findByName(nome);

        if (estudantes.isEmpty()) {
            System.out.println("Nao foi encontrado nenhum estudante com nome " + nome);
            return;
        }

        for (Estudante estudante : estudantes) {
            Notas notas = NotasService.findByStudentId(estudante.getId());
            estudante.setNotas(notas);
        }

        printStudent(estudantes);

    }

    private static void showUpdateStudent() {
        printStudent(findAll());
        System.out.println("Digite o id do estudante que sera atualizado");
        int id = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Digite o novo nome:");
        String nome = SCANNER.nextLine();
        System.out.println("Digite a nova idade");
        int idade = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Digite a nova serie");
        int serie = Integer.parseInt(SCANNER.nextLine());

        Estudante estudante = Estudante.builder()
                .id(id)
                .nome(nome)
                .serie(serie)
                .idade(idade)
                .build();

        int rowsAffected = update(estudante);

        if (rowsAffected == 0) {
            System.out.println("Nao foi encontrado estudante para atualizar");
        } else {
            System.out.println("Estudante atualizado com sucesso");
        }

    }

    private static void showSaveStudent() {
        System.out.println("Digite o nome do estudante para ser cadastrado");
        String nome = SCANNER.nextLine();
        System.out.println("Digite a idade do estudante");
        int idade = Integer.parseInt(SCANNER.nextLine());
        System.out.println("Digite a serie do estudante");
        int serie = Integer.parseInt(SCANNER.nextLine());

        Estudante estudante = Estudante.builder()
                .nome(nome)
                .idade(idade)
                .serie(serie)
                .build();

        int rowsAffected = save(estudante);

        if (rowsAffected == 1) {
            System.out.println("Estudante cadastrado");
        }
    }

    private static void showDeleteStudent() {
        printStudent(findAll());

        System.out.println("Digite o id do estudante que deve ser removido");
        int id = Integer.parseInt(SCANNER.nextLine());

        Estudante estudante = findById(id);

        if (Objects.isNull(estudante)) {
            System.out.println("Estudante nao encontrado");
        } else {
            delete(id);
            printStudent(estudante);
            System.out.println("Estudante acima removido com sucesso");
        }


    }

    private static void printStudent(List<Estudante> estudantes) {
        for (Estudante estudante : estudantes) {
            printStudent(estudante);
        }
    }

    private static void printStudent(Estudante estudante) {
        System.out.printf("[%d] Nome: %s, idade %d, serie: %d%n", estudante.getId(), estudante.getNome(), estudante.getIdade(), estudante.getSerie());
        if (Objects.nonNull(estudante.getNotas())) {
            System.out.println(estudante.getNotas());
        }
    }


    public static List<Estudante> findByName(String name) {
        return EstudanteRepository.findByName(name);
    }

    public static List<Estudante> findAll() {
        return EstudanteRepository.findByName("");
    }

    public static Estudante findById(int id) {
        idValidate(id);
        return EstudanteRepository.findById(id);
    }

    public static int update(Estudante estudante) {
        idValidate(estudante.getId());
        serieValidate(estudante.getSerie());
        idadeValidate(estudante.getIdade());
        return EstudanteRepository.update(estudante);
    }

    public static int save(Estudante estudante) {
        serieValidate(estudante.getSerie());
        idadeValidate(estudante.getIdade());
        return EstudanteRepository.save(estudante);
    }

    public static int delete(int id) {
        idValidate(id);
        return EstudanteRepository.delete(id);
    }

    private static void idValidate(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invalido");
        }
    }

    private static void serieValidate(int serie) {
        if (serie <= 0 || serie > 9) {
            throw new IllegalArgumentException("Serie invalida");
        }
    }

    private static void idadeValidate(int idade) {
        if (idade <= 0 || idade > 100) {
            throw new IllegalArgumentException("Idade invalida");
        }
    }

}
