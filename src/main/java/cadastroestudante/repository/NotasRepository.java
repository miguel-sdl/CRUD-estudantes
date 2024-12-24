package cadastroestudante.repository;

import cadastroestudante.conn.ConnectionFactory;
import cadastroestudante.entity.Notas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotasRepository {

    public static Notas findByStudentId(int studentId) throws SQLException {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindByStudentId(conn, studentId);) {
            ResultSet rs = ps.executeQuery();

            rs.next();
            Notas notas = Notas.builder()
                    .idEstudante(rs.getInt("id_estudante"))
                    .portugues(rs.getDouble("portugues"))
                    .matematica(rs.getDouble("matematica"))
                    .historia(rs.getDouble("historia"))
                    .geografia(rs.getDouble("geografia"))
                    .fisica(rs.getDouble("fisica"))
                    .quimica(rs.getDouble("quimica"))
                    .biologia(rs.getDouble("biologia"))
                    .ingles(rs.getDouble("ingles"))
                    .build();

            return notas;
        }
    }

    private static PreparedStatement createPreparedStatementFindByStudentId(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM `cadastro_estudante`.`notas` WHERE (id_estudante = ?);";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        return preparedStatement;
    }
}
