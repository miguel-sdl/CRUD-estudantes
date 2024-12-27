package cadastroestudante.service;

import cadastroestudante.entity.Estudante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class EstudanteServiceTest {
    private static Estudante estudante;

    @BeforeEach
    void setUp() {
        estudante = Estudante.builder()
                .nome("José")
                .idade(17)
                .serie(2)
                .build();
    }


    @Test
    @DisplayName("findAll() deve encontrar todos estudantes do db")
    void findAll() {
        List<Estudante> estudantes = EstudanteService.findAll();
        Assertions.assertTrue(() -> estudantes.size() > 3);
    }

    @Test
    @DisplayName("findById deve lancar IllegalArgumentException para um id negativo")
    void findById() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> EstudanteService.findById(-1));
    }

    @Test
    @DisplayName("update() deve lancar IllegalArgumentException quando a idade e negativa")
    void update_idadeNegativa() {
        estudante.setIdade(-1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> EstudanteService.update(estudante));
    }

    @Test
    @DisplayName("update() deve lancar IllegalArgumentException quando a idade e maior que 100")
    void update_idadeMaior100() {
        estudante.setIdade(101);
        Assertions.assertThrows(IllegalArgumentException.class, () -> EstudanteService.update(estudante));
    }

    @Test
    @DisplayName("update() deve lancar IllegalArgumentException quando a serie e negativa")
    void update_serieNegativa() {
        estudante.setSerie(-1);
        Assertions.assertThrows(IllegalArgumentException.class, () -> EstudanteService.update(estudante));
    }

    @Test
    @DisplayName("update() deve lancar IllegalArgumentException quando a serie e maior que 9")
    void update_serieMaior9() {
        estudante.setSerie(10);
        Assertions.assertThrows(IllegalArgumentException.class, () -> EstudanteService.update(estudante));
    }
}