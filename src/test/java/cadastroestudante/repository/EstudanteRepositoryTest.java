package cadastroestudante.repository;

import cadastroestudante.entity.Estudante;
import cadastroestudante.entity.Notas;
import org.junit.jupiter.api.*;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EstudanteRepositoryTest {
    private Estudante carlos;
    private Notas notasCarlos;

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
    }

    @Test
    @DisplayName("findByName('Carlos') deve retornar o estudante Carlos Pereira do db")
    void findByName() throws SQLException {
        List<Estudante> estudantes = EstudanteRepository.findByName("Carlos");
        Estudante carlosDB = estudantes.getFirst();
        Assertions.assertEquals(carlos, carlosDB);
    }
}