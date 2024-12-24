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
    @DisplayName("save() deve retornar 1 quando uma nota e salva no db")
    void save() {
        int rowsAffected = NotasRepository.save(notas);
        Assertions.assertEquals(1, rowsAffected);
    }
}