package misClases;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

/**
 *
 * @author Alonso
 */
public class TablaPartidas extends JFrame{
    
    private JTable tabla;
    private DefaultTableModel modelo;
    
    public TablaPartidas(){
        setTitle("historial de las mediocres partidas");
        setSize(960,600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        modelo = new DefaultTableModel(
                new String[]{"Jugdor 1", "Jugador 2", "Ganador", "Fecha", "Duracion", "Objeto Ganador"}, 0);
        tabla = new JTable(modelo);
        add(new JScrollPane(tabla));
        
        cargarDatos();
    }
    
    private void cargarDatos(){
        String sql = "SELECT * FROM partidas";
        
        try(Connection conn = conexXxion.conectar();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){
           
            while(rs.next()){
                String j1 = rs.getString("jugador1");
                String j2 = rs.getString("jugador2");
                String ganador = rs.getString("ganador");
                Timestamp fecha = rs.getTimestamp("fecha");
                Time duracion = rs.getTime("duracion");
                String objeto = rs.getString("objeto_ganador");
                
                modelo.addRow(new Object[]{j1, j2, ganador, fecha, duracion, objeto});
            }
            
        }catch(SQLException e){
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> new TablaPartidas().setVisible(true));
    }
}

