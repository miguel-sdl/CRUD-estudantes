package cadastroestudante.service;

import cadastroestudante.entity.Notas;
import cadastroestudante.repository.NotasRepository;

import java.util.Objects;
import java.util.Scanner;

/**
 * Classe de serviço para {@code Notas}. <br>
 * <p>Essa classe faz o intermédio entre o usuário e as operações CRUD no banco de dados,
 * que acontecem por meio da classe {@code NotasRepository}.
 * <p>Fornece alguns métodos como o método {@code showMenu()} que inicia a interação com o usuário
 * e também  outros métodos para operações CRUD.
 *
 * @see NotasRepository
 */
public class NotasService {
    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String MENU = "\nEscolha a opcao:\n1. Encontrar as notas de um estudante\n2. Atualizar as notas de um estudante\n3. Adicionar notas a um estudante\n4. Deletar as notas de um estudante\n5. Verificar se as notas de um estudante estao aprovadas\n0. Sair";

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
                case 5:
                    showVerification();
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
        System.out.println("Digite o id do estudante para atualizar sua nota");
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
        System.out.println("Digite o id do estudante para suas notas serem salvas");
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
        System.out.println("Digite o id do estudante para remover sua nota");
        int id = Integer.parseInt(SCANNER.nextLine());

        int rowsAffected = delete(id);

        if (rowsAffected == 0) {
            System.out.println("Nao foi encontrado estudante com id " + id);
        } else {
            System.out.println("Notas removidas com sucesso");
        }
    }

    private static void showVerification() {
        System.out.println("Digite o id do estudante para verificar sua nota");
        int id = Integer.parseInt(SCANNER.nextLine());

        Notas notas = findByStudentId(id);

        if (Objects.isNull(notas)) {
            System.out.println("Nao foi encontrado notas do estudante com id " + id);
            return;
        }

        if (isApproved(notas)) {
            System.out.println("O estudante foi aprovado com media " + notas.getMedia());
        } else {
            System.out.println("O estudante foi reprovado com media " + notas.getMedia());
        }

    }


    /**
     * Procura as notas do estudante com id fornecido.
     *
     * @param id Id do estudante
     * @return As notas do estudante ou {@code null} caso não encontre.
     * @throws IllegalArgumentException caso o {@code id} seja negativo.
     */
    public static Notas findByStudentId(int id) {
        idValidate(id);
        return NotasRepository.findByStudentId(id);
    }

    /**
     * Atualiza as Notas de um estudante no banco de dados.
     * <p>Retorna as linhas afetadas no banco, então caso não sejam encontradas Notas para atualizar o retorno é {@code 0}.</p>
     *
     * @param notas Notas para serem atualizadas no banco
     * @return As linhas afetadas no banco.
     * @throws RuntimeException         caso a nota seja inválida
     * @throws IllegalArgumentException caso o id do estudante seja negativo.
     */
    public static int update(Notas notas) {
        idValidate(notas.getIdEstudante());
        if (!notas.isValid()) throw new RuntimeException("Nota invalida");
        return NotasRepository.update(notas);
    }

    /**
     * Salva as Notas de um Estudante no banco de dados.
     * <p>Antes de atualizar no banco, faz as verificações das informações, então caso seja lançada alguma exceção as notas não são salvas.</p>
     *
     * @param notas Notas para serem salvas no banco
     * @return As linhas afetadas no banco.
     * @throws RuntimeException         caso a nota seja inválida
     * @throws RuntimeException         caso tente salvar Notas que já existem no banco.
     * @throws IllegalArgumentException caso o id do estudante seja negativo.
     */
    public static int save(Notas notas) {
        if (!notas.isValid()) throw new RuntimeException("Nota invalida");
        return NotasRepository.save(notas);
    }

    /**
     * Remove as Notas de um estudante do banco de dados.
     *
     * @param studentId Id do estudante que terá sua nota removida do banco.
     * @return As linhas afetadas no banco.
     * @throws IllegalArgumentException caso {@code studentId} seja negativo
     */
    public static int delete(int studentId) {
        idValidate(studentId);
        return NotasRepository.delete(studentId);
    }

    /**
     * Verifica se as Notas estão aprovadas.
     * <p>As notas são consideradas aprovadas quando a sua média é maior ou igual a 6.</p>
     *
     * @param notas Notas que serão verificadas.
     * @return {@code true} se está aprovado, caso contrário {@code false}.
     * @throws RuntimeException caso a nota seja inválida.
     */
    public static boolean isApproved(Notas notas) {
        if (!notas.isValid()) throw new RuntimeException("Nota invalida");
        return notas.getMedia() >= 6;
    }

    private static void idValidate(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID invalido");
        }
    }
}
