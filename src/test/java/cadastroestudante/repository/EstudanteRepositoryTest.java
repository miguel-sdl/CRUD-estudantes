package cadastroestudante.repository;

import cadastroestudante.entity.Estudante;
import cadastroestudante.entity.Notas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class EstudanteRepositoryTest {
    private Estudante carlos;
    private Notas notasCarlos;
    private Estudante estudante;
    private Notas notas;

    @BeforeEach
    void setUp() {
        notasCarlos = Notas.builder()
                .idEstudante(3)
                .portugues(6.5)
                .matematica(9)
                .historia(8.8)
                .geografia(7.5)
                .fisica(8.0)
                .quimica(6.2)
                .biologia(9)
                .ingles(9.2)
                .build();

        carlos = Estudante.builder()
                .nome("Carlos Pereira")
                .idade(15)
                .serie(1)
                .id(3)
                .notas(notasCarlos)
                .build();

        notas = Notas.builder()
                .idEstudante(1000)
                .portugues(6)
                .matematica(6)
                .historia(6)
                .geografia(6)
                .fisica(6)
                .quimica(6)
                .biologia(6)
                .ingles(6)
                .build();

        estudante = Estudante.builder()
                .nome("José")
                .idade(17)
                .serie(2)
                .notas(notas)
                .build();


    }

    @Test
    @DisplayName("findByName('Carlos Pereira') deve retornar o estudante Carlos Pereira do db")
    void findByName_CarlosPereira() {
        List<Estudante> estudantes = EstudanteRepository.findByName("Carlos Pereira");
        Estudante carlosDB = estudantes.getFirst();
        Assertions.assertEquals(carlos, carlosDB);
    }

    @Test
    @DisplayName("findByName('Maria') deve encontrar toda Maria no db")
    void findByName_Maria() {
        List<Estudante> estudantes = EstudanteRepository.findByName("Maria");
        Assertions.assertTrue(() -> estudantes.size() >= 2);
    }

    @Test
    @DisplayName("findByName deve retornar uma lista vazia quando encontrar nenhum estudante")
    void findByName_NomeNaoEncontrado() {
        List<Estudante> estudantes = EstudanteRepository.findByName("nsanifobf");
        Assertions.assertTrue(estudantes.isEmpty());
    }

    @Test
    @DisplayName("findById(3) deve encontar o estudante com id 3")
    void findById() {
        Estudante estudante = EstudanteRepository.findById(3);
        Assertions.assertEquals(carlos, estudante);
    }

    @Test
    @DisplayName("save() deve retornar 1 quando um estudante e salvo no db")
    void save() {
        int rowAffected = EstudanteRepository.save(estudante);
        Assertions.assertEquals(1, rowAffected);
    }

    @Test
    @DisplayName("update() deve retornar 1 quando um estudante e atualizado no db")
    void update() {
        carlos.setIdade(15);
        int rowsAffected = EstudanteRepository.update(carlos);
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    @DisplayName("update() deve retornar 0 quando nao encontrar estudante para atualizar")
    void update_estudanteNaoEncontrado() {
        estudante.setId(1000);
        int rowsAffected = EstudanteRepository.update(estudante);
        Assertions.assertEquals(0, rowsAffected);
    }

    @Test
    @DisplayName("delete() deve retornar 1 quando um estudante e deletado do db")
    void delete() {
        int rowsAffected = EstudanteRepository.delete(7);
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    @DisplayName("delete() deve retornar 0 quando não encontrar estudante para deletar")
    void delete_estudanteNaoEncontrado() {
        int rowsAffected = EstudanteRepository.delete(1000);
        Assertions.assertEquals(0, rowsAffected);
    }
}