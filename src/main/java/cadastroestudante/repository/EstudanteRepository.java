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
    public static List<Estudante> findByName(String name) {
        List<Estudante> estudantes = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementFindByName(conn, name)) {
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
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private static PreparedStatement createPreparedStatementFindByName(Connection conn, String name) throws SQLException {
        String sql = "SELECT * FROM `cadastro_estudante`.`estudante` WHERE nome like ?;";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, String.format("%%%s%%", name));

        return preparedStatement;
    }

    public static int save(Estudante estudante) {

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = createPreparedStatementSave(conn, estudante)) {
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private static PreparedStatement createPreparedStatementSave(Connection conn, Estudante estudante) throws SQLException {
        String sql = "INSERT INTO `cadastro_estudante`.`estudante`" +
                "(`nome`, `idade`, `serie`)" +
                "VALUES (?, ?, ?);";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, estudante.getNome());
        preparedStatement.setInt(2, estudante.getIdade());
        preparedStatement.setInt(3, estudante.getSerie());

        return preparedStatement;
    }

    public static int update(Estudante estudante) {
        try (Connection conn = ConnectionFactory.getConnection();
        PreparedStatement ps = createPreparedStatementUpdate(conn, estudante)) {
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    private static PreparedStatement createPreparedStatementUpdate(Connection conn, Estudante estudante) throws SQLException {
        String sql = "UPDATE `cadastro_estudante`.`estudante`" +
                "SET `nome` = ?, `idade` = ?, `serie` = ? " +
                "WHERE `idestudante` = ?;";

        PreparedStatement preparedStatement = conn.prepareStatement(sql);
        preparedStatement.setString(1, estudante.getNome());
        preparedStatement.setInt(2, estudante.getIdade());
        preparedStatement.setInt(3, estudante.getSerie());
        preparedStatement.setInt(4, estudante.getId());

        return preparedStatement;

    }
}
