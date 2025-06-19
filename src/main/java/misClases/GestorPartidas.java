
package misClases;

/**
 *
 * @author Alonso
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class GestorPartidas {

    public void guardarPartida(String jugador1, String jugador2, String ganador,
                               LocalDateTime fecha, LocalTime duracion, String objetoGanador) {
        String sql = "INSERT INTO partidas (jugador1, jugador2, ganador, fecha, duracion, objeto_ganador) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = conexXxion.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, jugador1);
            stmt.setString(2, jugador2);
            stmt.setString(3, ganador);
            stmt.setObject(4, fecha);
            stmt.setObject(5, duracion);
            stmt.setString(6, objetoGanador);

            stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
