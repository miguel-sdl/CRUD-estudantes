package cadastroestudante.conn;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionFactoryTest {

    @Test
    void getConnection() throws SQLException {
        ConnectionFactory.getConnection();
    }
}