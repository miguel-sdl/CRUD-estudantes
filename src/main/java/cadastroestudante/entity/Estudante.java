package cadastroestudante.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Estudante {
    int id;
    String nome;
    int idade;
    int serie;
    Notas notas;
}
