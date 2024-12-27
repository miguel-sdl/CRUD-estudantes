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


    public boolean isValid() {
        if (!isValid(portugues)) return false;
        if (!isValid(matematica)) return false;
        if (!isValid(historia)) return false;
        if (!isValid(geografia)) return false;
        if (!isValid(fisica)) return false;
        if (!isValid(quimica)) return false;
        if (!isValid(biologia)) return false;
        if (!isValid(ingles)) return false;

        return true;

    }

    private boolean isValid(double nota) {
        if (nota<=0 || nota>10) return false;

        return true;
    }

}
