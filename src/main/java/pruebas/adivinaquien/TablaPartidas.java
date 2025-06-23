package pruebas.adivinaquien;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.Vector;

public class TablaPartidas extends JFrame {

    private JTable tabla;
    private DefaultTableModel modelo;
    private JTextField txtJugador;
    private JButton btnFiltrar, btnOrdenar, btnMenu;

    public TablaPartidas() {
        setTitle("Historial de Partidas");
        setSize(900, 550);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        getContentPane().setBackground(Color.DARK_GRAY);

        // Panel superior de filtros
        JPanel panelFiltros = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 10));
        panelFiltros.setBackground(Color.DARK_GRAY);
        panelFiltros.setBorder(new EmptyBorder(10, 20, 10, 20));

        JLabel lblBuscar = new JLabel("Buscar jugador:");
        lblBuscar.setFont(new Font("Old English Text MT", Font.PLAIN, 20));
        lblBuscar.setForeground(Color.WHITE);
        panelFiltros.add(lblBuscar);

        txtJugador = new JTextField(15);
        txtJugador.setFont(new Font("Old English Text MT", Font.PLAIN, 18));
        panelFiltros.add(txtJugador);

        btnFiltrar = crearBotonConEstilo("Filtrar Nombre");
        btnOrdenar = crearBotonConEstilo("Ordenar Duracion");
        btnMenu = crearBotonConEstilo("Menú");

        panelFiltros.add(btnFiltrar);
        panelFiltros.add(btnOrdenar);
        panelFiltros.add(btnMenu);
        add(panelFiltros, BorderLayout.NORTH);

        // Tabla de datos
        modelo = new DefaultTableModel();
        tabla = new JTable(modelo);
        tabla.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        tabla.setRowHeight(26);
        tabla.setBackground(new Color(40, 40, 40));
        tabla.setForeground(Color.WHITE);
        tabla.setGridColor(Color.GRAY);
        tabla.setShowVerticalLines(false);
        tabla.setShowHorizontalLines(true);

        JTableHeader header = tabla.getTableHeader();
        header.setFont(new Font("Segoe UI", Font.BOLD, 14));
        header.setBackground(Color.BLACK);
        header.setForeground(Color.YELLOW);

        JScrollPane scroll = new JScrollPane(tabla);
        scroll.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        add(scroll, BorderLayout.CENTER);

        cargarDatos();

        // Acciones
        btnFiltrar.addActionListener(e -> filtrarPorJugador(txtJugador.getText().trim()));
        btnOrdenar.addActionListener(e -> ordenarPorDuracion());
        btnMenu.addActionListener(e -> {
            dispose();
            new menuPrueba().setVisible(true);
        });
    }

    private JButton crearBotonConEstilo(String texto) {
        JButton btn = new JButton(texto);
        btn.setFont(new Font("Old English Text MT", Font.BOLD, 13));
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.setFocusPainted(false);
        btn.setBorderPainted(false);
        btn.setPreferredSize(new Dimension(140, 40));

        // Hover
        btn.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent evt) {
                btn.setBackground(new Color(50, 50, 50));
                btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            public void mouseExited(MouseEvent evt) {
                btn.setBackground(Color.BLACK);
                btn.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });

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
