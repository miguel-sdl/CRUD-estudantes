package cadastroestudante.repository;

import cadastroestudante.conn.ConnectionFactory;
import cadastroestudante.entity.Estudante;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EstudanteRepository {
    public static List<Estudante> findByName(String name) throws SQLException {
        List<Estudante> estudantes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = createPreparedStatementFindByName(conn, name)){
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Estudante estudante = Estudante.builder()
                        .id(rs.getInt("idestudante"))
                        .nome(rs.getString("nome"))
                        .idade(rs.getInt("idade"))
                        .serie(rs.getInt("serie"))
                        .notas(NotasRepository.findByStudentId(rs.getInt("idestudante")))
                        .build();

                estudantes.add(estudante);
            }

            return estudantes;
        }
    }

    private static PreparedStatement createPreparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM `cadastro_estudante`.`estudante` WHERE nome like ?;";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, String.format("%%%s%%", name));

        return preparedStatement;
    }
}
