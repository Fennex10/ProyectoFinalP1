package GUI;

import Funciones.DatabaseConnection;
import Funciones.Usuario;
import Funciones.UsuarioConcreto;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class LoginApp extends JFrame {
    
    private Usuario usuario = new UsuarioConcreto(0, 0, 0, "", "", "") {
        @Override
        public void agregarSaldo() {
            throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
        }
    };

    
    // Constructor del JFrame
    public LoginApp() {
    	setResizable(false);
        setTitle("Login - CineApp");
        setSize(961, 566);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());

        // Panel principal
        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(new Color(255, 255, 255));
        getContentPane().add(mainPanel, BorderLayout.CENTER);
        mainPanel.setLayout(null);
        
        JPanel panel_1 = new JPanel();
        panel_1.setBounds(0, 0, 402, 529);
        panel_1.setBackground(new Color(0, 0, 0));
        mainPanel.add(panel_1);
        panel_1.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("\"¡Tu asiento, tu película, tu momento!\"");
        lblNewLabel_2.setForeground(new Color(255, 255, 255));
        lblNewLabel_2.setFont(new Font("SansSerif", Font.BOLD, 14));
        lblNewLabel_2.setBounds(60, 360, 297, 50);
        panel_1.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Cinema store");
        lblNewLabel_2_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_2_1.setForeground(Color.WHITE);
        lblNewLabel_2_1.setFont(new Font("Rockwell Extra Bold", Font.BOLD, 27));
        lblNewLabel_2_1.setBounds(38, 69, 319, 62);
        panel_1.add(lblNewLabel_2_1);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Login.jpeg"));
        lblNewLabel_1.setBounds(41, 113, 297, 297);
        panel_1.add(lblNewLabel_1);

        // Panel central para los campos de login
        JPanel loginPanel = new JPanel();
        loginPanel.setBounds(383, 95, 536, 490);
        loginPanel.setBackground(new Color(255, 255, 255));
        mainPanel.add(loginPanel);
        loginPanel.setLayout(null);

        // Campo de texto para el usuario
        JTextField userField = new JTextField();
        userField.setBounds(111, 72, 314, 42);
        userField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        userField.setBackground(Color.WHITE);
        userField.setForeground(Color.BLACK);
        userField.setBorder(BorderFactory.createTitledBorder("Email"));
        loginPanel.add(userField);

        // Campo de texto para la contraseña
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(111, 140, 314, 42);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(BorderFactory.createTitledBorder("Contraseña"));
        loginPanel.add(passwordField);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Iniciar Sesión");
        loginButton.setBounds(111, 219, 134, 34);
        loginButton.setBackground(new Color(151, 19, 19));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        loginButton.setFocusPainted(false);
        loginButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        loginPanel.add(loginButton);
        class RoundedPanel extends JPanel {
            private int cornerRadius; // Radio de las esquinas

            public RoundedPanel(int cornerRadius) {
                this.cornerRadius = cornerRadius;
                setOpaque(false); // Para que sea transparente
            }

            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(225, 225, 225, 50)); // Color con transparencia (RGBA)
                g2.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));
            }
        }
        
        JPanel panel = new RoundedPanel(30); // Esquinas redondeadas con radio 30
        panel.setBounds(87, 40, 360, 276);
        loginPanel.add(panel);
        
                // Panel de título
                JLabel titleLabel = new JLabel("Iniciar sesion", SwingConstants.CENTER);
                titleLabel.setBounds(0, 10, 551, 52);
                loginPanel.add(titleLabel);
                titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
                titleLabel.setForeground(new Color(0, 0, 0));
                
                JPanel panel_2 = new JPanel();
                panel_2.setBackground(new Color(255, 255, 255));
                panel_2.setBounds(111, 287, 314, 52);
                loginPanel.add(panel_2);
                
                JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
                errorLabel.setForeground(Color.BLACK);
                errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
                panel_2.add(errorLabel);

    // Acción para el botón de login
    loginButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        
            String emailInput = userField.getText().trim();
            String contrasenaInput = new String(passwordField.getPassword()).trim();

    // Validación de campos vacíos antes de proceder
    if (emailInput.isEmpty() || contrasenaInput.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Email y contraseña no pueden estar vacíos.");
        return;
    }

    try (Connection conn = DatabaseConnection.getConnection()) {
        String query = "SELECT * FROM admin WHERE Email = ? AND Contraseña = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setString(1, emailInput);
        stmt.setString(2, contrasenaInput);
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            usuario.setEmail(rs.getString("Email"));
            usuario.setContraseña(rs.getString("Contraseña"));
            errorLabel.setForeground(Color.GREEN);
            JOptionPane.showMessageDialog(null, "Login exitoso!");
            // Aquí abres la ventana de películas
            MoviesWindow moviesWindow = new MoviesWindow();
            moviesWindow.setVisible(true);
            setVisible(false); // Cierra la ventana de login
        } else {
            JOptionPane.showMessageDialog(null, "Credenciales incorrectas.");
        }
    } catch (SQLException ex) {
        ex.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error al acceder a la base de datos.");
    }
}
});

        
// Cierre de la clase LoginApp
// Clase SeatSelectionWindow (fuera de LoginApp)
class SeatSelectionWindow extends JFrame {

    public SeatSelectionWindow(String movieName) {
        setTitle("Seleccionar Asientos - " + movieName);
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Título
        JLabel titleLabel = new JLabel("Selecciona los asientos para " + movieName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 24));
        titleLabel.setForeground(Color.RED);
        add(titleLabel, BorderLayout.NORTH);

        // Panel de selección de asientos
        JPanel seatPanel = new JPanel();
        seatPanel.setLayout(new GridLayout(5, 5, 5, 5)); // 5 filas, 5 columnas de asientos
        seatPanel.setBackground(Color.BLACK);
        add(seatPanel, BorderLayout.CENTER);

        // Crear los asientos como botones
        for (int i = 0; i < 25; i++) { // 25 asientos
            final int seatNumber = i + 1; // Captura el número de asiento actual

            JButton seatButton = new JButton("A" + seatNumber);
            seatButton.setFont(new Font("SansSerif", Font.PLAIN, 12));
            seatButton.setBackground(Color.GREEN);
            seatButton.setForeground(Color.WHITE);

            // Acción de selección de asiento
            seatButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    // Cambiar el color del asiento si ya está seleccionado
                    if (seatButton.getBackground() == Color.GREEN) {
                        seatButton.setBackground(Color.RED); // Ocupado
                        seatButton.setText("Ocupado");
                    } else {
                        seatButton.setBackground(Color.GREEN); // Disponible
                        seatButton.setText("A" + seatNumber);
                    }
                }
            });

            seatPanel.add(seatButton);
        }

        // Botón para confirmar la reserva
        JButton confirmButton = new JButton("Confirmar Reserva");
        confirmButton.setBackground(Color.RED);
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        confirmButton.setFocusPainted(false);
        confirmButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Confirmar la reserva
                JOptionPane.showMessageDialog(SeatSelectionWindow.this, "Reserva confirmada para " + movieName, "Éxito", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false); // Cierra la ventana actual
            }
        });

        // Botón de retroceder para regresar a la selección de película
        JButton backButton = new JButton("Regresar");
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.setFont(new Font("SansSerif", Font.BOLD, 16));
        backButton.setFocusPainted(false);
        backButton.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MoviesWindow moviesWindow = new MoviesWindow();
                moviesWindow.setVisible(true);
                setVisible(false); // Cierra la ventana actual
            }
        });

        // Añadir botón de retroceder al pie
        add(backButton, BorderLayout.SOUTH);
    }
}

        }}