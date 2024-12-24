package cadastroestudante.repository;

import cadastroestudante.entity.Notas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class NotasRepositoryTest {
    private Notas notas;

    @BeforeEach
    void setUp() {
        notas = Notas.builder()
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
    }

    @Test
    @DisplayName("findByStudentId(3) deve retornar as notas do estudante com id 3")
    void findByStudentId() throws SQLException {
        Notas notasDB = NotasRepository.findByStudentId(3);
        Assertions.assertEquals(notas,notasDB);
    }
}