package cadastroestudante.service;

import cadastroestudante.entity.Estudante;
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

        printStudent(estudantes);

    }

    private static void showUpdateStudent() {

    }

    private static void showSaveStudent() {

    }

    private static void showDeleteStudent() {

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

    private static void idValidate(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invalido");
        }
    }

}
