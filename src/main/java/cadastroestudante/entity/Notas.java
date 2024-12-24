package cadastroestudante.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Notas {
    int idEstudante;
    double portugues;
    double matematica;
    double historia;
    double geografia;
    double fisica;
    double quimica;
    double biologia;
    double ingles;
}
