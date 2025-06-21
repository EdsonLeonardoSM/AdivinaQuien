package pruebas.adivinaquien;

import pruebas.adivinaquien.conexXxion;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class TablaPartidas extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtJugador;
    private JButton btnFiltrar, btnOrdenar;
    private JPanel panelFiltros;

    public TablaPartidas() {
        setTitle("🌸 Historial de Partidas");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // 🌸 Panel superior con filtros
        panelFiltros = new JPanel();
        panelFiltros.setBackground(new Color(255, 245, 245));
        panelFiltros.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        txtJugador = new JTextField(15);
        txtJugador.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        txtJugador.setToolTipText("Nombre del jugador");

        btnFiltrar = crearBotonConEstilo("Filtrar por  nombre", "resources/iconos/filtro.png");
        btnOrdenar = crearBotonConEstilo("Ordenar por duración", "resources/iconos/reloj.png");

        panelFiltros.add(new JLabel("Buscar jugador:"));
        panelFiltros.add(txtJugador);
        panelFiltros.add(btnFiltrar);
        panelFiltros.add(btnOrdenar);
        add(panelFiltros, BorderLayout.NORTH);

        // 🌸 Tabla
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(24);
        tabla.setBackground(new Color(255, 250, 255));
        tabla.setGridColor(new Color(230, 200, 220));

        JTableHeaderEstilizado();
        JScrollPane scroll = new JScrollPane(tabla);
        add(scroll, BorderLayout.CENTER);

        cargarDatos();

        // 🧠 Acciones de los botones
        btnFiltrar.addActionListener(e -> filtrarPorJugador(txtJugador.getText().trim()));
        btnOrdenar.addActionListener(e -> ordenarPorDuracion());
    }

    private void JTableHeaderEstilizado() {
        tabla.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 14));
        tabla.getTableHeader().setBackground(new Color(248, 200, 220));  
        tabla.getTableHeader().setForeground(Color.DARK_GRAY);
    }

    private JButton crearBotonConEstilo(String texto, String rutaIcono) {
        JButton btn = new JButton(texto);
        btn.setFocusPainted(false);
        btn.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        btn.setBackground(new Color(255, 230, 240));
        btn.setBorder(BorderFactory.createLineBorder(new Color(240, 180, 200), 2));

        try {
            ImageIcon icono = new ImageIcon(rutaIcono);
            Image img = icono.getImage().getScaledInstance(18, 18, Image.SCALE_SMOOTH);
            btn.setIcon(new ImageIcon(img));
        } catch (Exception e) {
            System.out.println("No se pudo cargar icono: " + rutaIcono);
        }

        return btn;
    }

    public void cargarDatos() {
        modelo.setRowCount(0);
        modelo.setColumnIdentifiers(new Object[]{"Jugador 1", "Jugador 2", "Ganador", "Fecha", "Duración", "Objeto Ganador"});
        try (Connection conn = conexXxion.conectar();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM partidas");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vector<Object> fila = new Vector<>();
                for (int i = 1; i <= 6; i++) {
                    fila.add(rs.getObject(i));
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void filtrarPorJugador(String nombre) {
        modelo.setRowCount(0);
        try (Connection conn = conexXxion.conectar();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM partidas WHERE jugador1 LIKE ? OR jugador2 LIKE ?")) {

            ps.setString(1, "%" + nombre + "%");
            ps.setString(2, "%" + nombre + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Vector<Object> fila = new Vector<>();
                for (int i = 1; i <= 6; i++) {
                    fila.add(rs.getObject(i));
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    private void ordenarPorDuracion() {
        modelo.setRowCount(0);
        try (Connection conn = conexXxion.conectar();
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM partidas ORDER BY duracion ASC");
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Vector<Object> fila = new Vector<>();
                for (int i = 1; i <= 6; i++) {
                    fila.add(rs.getObject(i));
                }
                modelo.addRow(fila);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TablaPartidas().setVisible(true));
    }
}
