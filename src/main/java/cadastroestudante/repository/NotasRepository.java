package cadastroestudante.repository;

import cadastroestudante.conn.ConnectionFactory;
import cadastroestudante.entity.Notas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class NotasRepository {

    public static Notas findByStudentId(int studentId) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindByStudentId(conn, studentId);) {
            ResultSet rs = ps.executeQuery();

            if (!rs.next()) return null;
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement createPreparedStatementFindByStudentId(Connection conn, int id) throws SQLException {
        String sql = "SELECT * FROM `cadastro_estudante`.`notas` WHERE (id_estudante = ?);";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        return preparedStatement;
    }


    public static int save(Notas notas) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementSave(conn, notas)) {
            return ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static PreparedStatement createPreparedStatementSave(Connection conn, Notas notas) throws SQLException {
        String sql = "INSERT INTO `cadastro_estudante`.`notas`\n" +
                "(`id_estudante`, `portugues`, `matematica`, `historia`, `geografia`, `fisica`, `quimica`, `biologia`, `ingles`)\n" +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, notas.getIdEstudante());
        preparedStatement.setDouble(2, notas.getPortugues());
        preparedStatement.setDouble(3, notas.getMatematica());
        preparedStatement.setDouble(4, notas.getHistoria());
        preparedStatement.setDouble(5, notas.getGeografia());
        preparedStatement.setDouble(6, notas.getFisica());
        preparedStatement.setDouble(7, notas.getQuimica());
        preparedStatement.setDouble(8, notas.getBiologia());
        preparedStatement.setDouble(9, notas.getIngles());

        return preparedStatement;

    }

    public static int update(Notas notas) {
        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementUpdate(conn, notas)) {
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement createPreparedStatementUpdate(Connection conn, Notas notas) throws SQLException {
        String sql = "UPDATE `cadastro_estudante`.`notas`\n" +
                "SET `portugues` = ?, `matematica` = ?, `historia` = ?,`geografia` = ?, `fisica` = ?, `quimica` = ?, `biologia` = ?, `ingles` = ?\n" +
                "WHERE `id` = ?\n";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setDouble(1, notas.getPortugues());
        preparedStatement.setDouble(2, notas.getMatematica());
        preparedStatement.setDouble(3, notas.getHistoria());
        preparedStatement.setDouble(4, notas.getGeografia());
        preparedStatement.setDouble(5, notas.getFisica());
        preparedStatement.setDouble(6, notas.getQuimica());
        preparedStatement.setDouble(7, notas.getBiologia());
        preparedStatement.setDouble(8, notas.getIngles());
        preparedStatement.setInt(9, notas.getIdEstudante());

        return preparedStatement;
    }

    public static int delete(int studentId) {
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = createPreparedStatementDelete(conn, studentId)) {
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement createPreparedStatementDelete(Connection conn, int id) throws SQLException {
        String sql = "DELETE FROM `cadastro_estudante`.`notas` WHERE id_estudante = ?;";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setInt(1, id);

        return preparedStatement;
    }
}
