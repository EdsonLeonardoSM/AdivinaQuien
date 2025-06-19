
package misClases;

/**
 *
 * @author Alonso
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexXxion {
    private static final String URL = "jdbc:mysql://localhost:3306/juego";
    private static final String USER = "root";
    private static final String PASSWORD = " "; // Reemplaza esto

    public static Connection conectar() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
