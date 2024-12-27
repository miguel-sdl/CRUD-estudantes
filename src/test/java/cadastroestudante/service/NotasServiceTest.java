package cadastroestudante.service;

import cadastroestudante.entity.Notas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class NotasServiceTest {
    private static Notas notas;

    @BeforeEach
    void setUp() {
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
    @DisplayName("findByStudentId() deve lancar uma RuntimeException quando o id e negativo")
    void findByStudentId() {
        Assertions.assertThrows(RuntimeException.class, () -> NotasService.findByStudentId(-1));
    }

    @Test
    @DisplayName("update() deve lancar uma RuntimeException quando alguma nota e negativa")
    void update_algumaNotaNegativa() {
        notas.setFisica(-1);
        Assertions.assertThrows(RuntimeException.class, () -> NotasService.update(notas));
    }

    @Test
    @DisplayName("update() deve lancar uma RuntimeException quando alguma nota e maior que 10")
    void update_algumaNotaMaior10() {
        notas.setHistoria(10.1);
        Assertions.assertThrows(RuntimeException.class, () -> NotasService.update(notas));
    }
}