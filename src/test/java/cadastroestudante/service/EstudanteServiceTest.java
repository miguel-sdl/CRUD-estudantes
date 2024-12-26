package cadastroestudante.service;

import cadastroestudante.entity.Estudante;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class EstudanteServiceTest {

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
}