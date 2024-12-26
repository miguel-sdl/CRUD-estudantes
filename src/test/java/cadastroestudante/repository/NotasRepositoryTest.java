package cadastroestudante.repository;

import cadastroestudante.entity.Notas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotasRepositoryTest {
    private Notas notas;
    private Notas notasId3;

    @BeforeEach
    void setUp() {
        notasId3 = Notas.builder()
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
        notas = Notas.builder()
                .idEstudante(6)
                .portugues(6)
                .matematica(6)
                .historia(6)
                .geografia(6)
                .fisica(6)
                .quimica(6)
                .biologia(6)
                .ingles(6)
                .build();
    }

    @Test
    @DisplayName("findByStudentId(3) deve retornar as notas do estudante com id 3")
    void findByStudentId() {
        Notas notasDB = NotasRepository.findByStudentId(3);
        Assertions.assertEquals(notasId3, notasDB);
    }

    @Test
    @DisplayName("findByStudentId deve retornar null quando nao encontrar nota")
    void findByStudentId_NaoEncontraNota() {
        Notas notasDB = NotasRepository.findByStudentId(1000);
        Assertions.assertNull(notasDB);
    }

    @Test
    @DisplayName("save() deve retornar 1 quando uma nota e salva no db")
    void save() {
        int rowsAffected = NotasRepository.save(notas);
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    @DisplayName("save() deve lancar excecao ao tentar salvar a nota de um estudante que ja possui nota no db")
    void save_notaJaExiste() {
        Assertions.assertThrows(RuntimeException.class, () -> NotasRepository.save(notasId3));
    }

    @Test
    @DisplayName("update() deve retornar 1 quando 1 nota e atualizada no db")
    void update() {
        notas.setIdEstudante(5);
        int rowsAffected = NotasRepository.update(notas);
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    @DisplayName("update() deve retornar 0 quando nao encontrar nota para atualizar")
    void update_NaoEncontraNota() {
        notas.setIdEstudante(1000);
        int rowsAffected = NotasRepository.update(notas);
        Assertions.assertEquals(0, rowsAffected);
    }

    @Test
    @DisplayName("delete() deve retornar 1 quando uma nota e deletada do db")
    void delete() {
        int rowsAffected = NotasRepository.delete(2);
        Assertions.assertEquals(1, rowsAffected);
    }

    @Test
    @DisplayName("delete() deve retornar 0 quando nao encotrar nota para deletar")
    void delete_NaoEncontraNota() {
        int rowsAffected = NotasRepository.delete(1000);
        Assertions.assertEquals(0, rowsAffected);
    }
}