package cadastroestudante.service;

import cadastroestudante.entity.Estudante;
import cadastroestudante.entity.Notas;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class EstudanteServiceTest {
    private static Estudante estudante;
    private static Notas notas;
    private static Notas notasAprovadas;
    private static Estudante estudanteAprovado;
    private static List<Estudante> estudantes;

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

        estudante = Estudante.builder()
                .nome("José")
                .idade(17)
                .serie(2)
                .notas(notas)
                .build();


        notasAprovadas = Notas.builder()
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

        estudanteAprovado = Estudante.builder()
                .notas(notasAprovadas).build();

        estudantes = List.of(estudante, estudanteAprovado);
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

    @Test
    @DisplayName("getApprovedStudents() deve retornar todos estudantes aprovados na lista")
    void getApprovedStudents() {
        List<Estudante> approvedStudents = EstudanteService.getApprovedStudents(estudantes);
        Assertions.assertEquals(2, approvedStudents.size());
    }

    @Test
    @DisplayName("getApprovedStudents() deve lancar IllegalArgumentException para uma lista vazia")
    void getApprovedStudents_EmptyList() {
        List<Estudante> empty = new ArrayList<>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> EstudanteService.getApprovedStudents(empty));
    }

    @Test
    @DisplayName("getApprovedStudents deve ignorar estudantes com notas null")
    void getApprovedStudents_notasNull() {
        estudante.setNotas(null);
        estudanteAprovado.setNotas(null);

        List<Estudante> approvedStudents = EstudanteService.getApprovedStudents(estudantes);
        Assertions.assertEquals(0, approvedStudents.size());
    }


    @Test
    @DisplayName("getApprovedStudents() deve retornar todos estudantes reprovados na lista")
    void getNotApprovedStudents() {
        notas.setBiologia(0);
        estudante.setNotas(notas);

        List<Estudante> notApprovedStudents = EstudanteService.getNotApprovedStudents(estudantes);
        Assertions.assertEquals(1, notApprovedStudents.size());
    }
}