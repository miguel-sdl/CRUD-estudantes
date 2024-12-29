package cadastroestudante.service;

import cadastroestudante.entity.Estudante;
import cadastroestudante.entity.Notas;
import cadastroestudante.repository.EstudanteRepository;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Classe de serviço para {@code Estudante}. <br>
 * <p>Essa classe faz o intermédio entre o usuário e as operações CRUD no banco de dados,
 * que acontecem por meio da classe {@code EstudanteRepository}.
 * <p>Fornece alguns métodos como o método {@code showMenu()} que inicia a interação com o usuário
 * e também  outros métodos para operações CRUD.
 *
 * @see EstudanteRepository
 */
public class EstudanteService {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MENU = "\nEscolha a operação:\n1. Encontrar um estudante\n2. Atualizar as informações do estudante\n3. Adicionar um novo estudante\n4. Remover um estudante\n5. Encontrar todos estudantes aprovados\n6. Encontrar todos estudantes reprovados\n0. Sair";


    /**
     * Mostra o menu de operações e contém a lógica para capturar a entrada do usuário
     * e fazer a operação desejada ou retornar para o menu anterior.
     */
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
                case 5:
                    showAllApprovedStudents();
                    break;
                case 6:
                    showAllNotApprovedStudents();
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

    private static void showAllApprovedStudents() {
        List<Estudante> estudantes = findAll();

        List<Estudante> approvedStudents = getApprovedStudents(estudantes);

        if (approvedStudents.isEmpty()) {
            System.out.println("Nenhum estudante aprovado");
            return;
        }
        System.out.println("Estudantes aprovados:");
        printStudent(approvedStudents);
    }

    private static void showAllNotApprovedStudents() {
        List<Estudante> estudantes = findAll();

        List<Estudante> notApprovedStudents = getNotApprovedStudents(estudantes);

        if (notApprovedStudents.isEmpty()) {
            System.out.println("Nenhum estudante reprovado");
        }
        System.out.println("Estudantes reprovados:");
        printStudent(notApprovedStudents);
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


    /**
     * Procura no banco de dados todos os estudantes com semelhança com o nome fornecido.
     * <p>A lista retornada é de estudantes que contém no seu nome a {@code String} {@code name}, portanto caso {@code name} seja {@code ""}, o método retorna todos estudantes do banco de dados.</p>
     *
     * @param name Nome para ser procurado no banco de dados.
     * @return Uma lista com todos os estudantes encontrados com o nome dado como argumento. Ou uma lista vazia caso não encontre.
     */
    public static List<Estudante> findByName(String name) {
        List<Estudante> estudantes = EstudanteRepository.findByName(name);

        for (Estudante estudante : estudantes) {
            Notas notas = NotasService.findByStudentId(estudante.getId());
            estudante.setNotas(notas);
        }

        return estudantes;

    }

    /**
     * Procura por todos os estudantes do banco de dados.
     *
     * @return Uma lista com todos os estudantes do banco de dados.
     */
    public static List<Estudante> findAll() {
        return EstudanteRepository.findByName("");
    }

    /**
     * Procura no banco de dados o estudante com id fornecido.
     *
     * @param id Id do estudante para ser procurado no banco de dados
     * @return O estudante com id fornecido ou {@code null} caso não exista
     * @throws IllegalArgumentException caso o id seja negativo.
     */
    public static Estudante findById(int id) {
        idValidate(id);
        Estudante estudante = EstudanteRepository.findById(id);
        estudante.setNotas(NotasService.findByStudentId(id));

        return estudante;
    }

    /**
     * Atualiza as informações pessoais de um estudante no banco de dados.
     * <p>Antes de atualizar no banco, faz as verificações das informações, então caso algum dos atributos seja considerado invalido é lançada uma {@code IllegalArgumentException} e o estudante não é atualizado.</p>
     * <p>Retorna as linhas afetadas no banco, então caso não seja encontrado estudante para atualizar o retorno é {@code 0}.</p>
     *
     * @param estudante Estudante para ser atualizado no banco de dados.
     * @return As linhas afetadas no banco
     * @throws IllegalArgumentException caso algum atributo do estudante seja inválido.
     */
    public static int update(Estudante estudante) {
        idValidate(estudante.getId());
        serieValidate(estudante.getSerie());
        idadeValidate(estudante.getIdade());
        return EstudanteRepository.update(estudante);
    }

    /**
     * Salva as informações pessoais de um estudante no banco de dados.
     * <p>Antes de salvar no banco, faz as verificações das informações, então caso algum dos atributos seja considerado invalido é lançada uma {@code IllegalArgumentException} e o estudante não é salvo.</p>
     *
     * @param estudante Estudante para ser salvo no banco de dados.
     * @return As linhas afetadas no banco.
     * @throws IllegalArgumentException caso algum atributo do estudante seja inválido.
     */
    public static int save(Estudante estudante) {
        serieValidate(estudante.getSerie());
        idadeValidate(estudante.getIdade());
        return EstudanteRepository.save(estudante);
    }

    /**
     * Remove um estudante do banco de dados.
     * <p>Ao remover um estudante as suas notas também são removidas do banco de dados.</p>
     *
     * @param id Id do estudante que será removido.
     * @return As linhas afetadas no banco.
     * @throws IllegalArgumentException quando o id é negativo.
     */
    public static int delete(int id) {
        idValidate(id);
        NotasService.delete(id);
        return EstudanteRepository.delete(id);
    }

    /**
     * Verifica uma lista de estudantes e retorna os estudantes aprovados com média das notas maior ou igual a 6.
     * <p>Caso algum estudante tenha notas {@code null}, suas notas não são verificadas.</p>
     *
     * @param estudantes Estudantes que serão verificados.
     * @return Os estudantes aprovados.
     * @throws IllegalArgumentException caso {@code estudantes} esteja vazio.
     */
    public static List<Estudante> getApprovedStudents(List<Estudante> estudantes) {
        if (estudantes.isEmpty()) {
            throw new IllegalArgumentException("A lista de estudantes nao pode ser vazia");
        }

        return estudantes.stream()
                .filter((e) -> Objects.nonNull(e.getNotas()))
                .filter((e) -> NotasService.isApproved(e.getNotas()))
                .toList();
    }

    /**
     * Verifica uma lista de estudantes e retorna os estudantes reprovados com média das notas menor ou igual a 6.
     * <p>Caso algum estudante tenha notas {@code null}, suas notas não são verificadas.</p>
     *
     * @param estudantes Estudantes que serão verificados.
     * @return Os estudantes reprovados.
     * @throws IllegalArgumentException caso {@code estudantes} esteja vazio.
     */
    public static List<Estudante> getNotApprovedStudents(List<Estudante> estudantes) {
        if (estudantes.isEmpty()) {
            throw new IllegalArgumentException("A lista de estudantes nao pode ser vazia");
        }

        return estudantes.stream()
                .filter((e) -> Objects.nonNull(e.getNotas()))
                .filter((e) -> !NotasService.isApproved(e.getNotas()))
                .toList();
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