package GUI;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

import javax.swing.border.SoftBevelBorder;
import javax.swing.border.BevelBorder;
import javax.swing.border.EtchedBorder;

public class Signin extends JFrame {
	private JTextField Email;
    // Constructor del JFrame
    public Signin() {
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
        lblNewLabel_1.setIcon(new ImageIcon("C:\\Users\\User\\Desktop\\OIG1.jpg"));
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
        userField.setBorder(BorderFactory.createTitledBorder("Usuario"));
        loginPanel.add(userField);

        // Campo de texto para la contraseña
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(111, 204, 314, 42);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 18));
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setBorder(BorderFactory.createTitledBorder("Contraseña"));
        loginPanel.add(passwordField);

        // Botón de inicio de sesión
        JButton loginButton = new JButton("Registrarse");
        loginButton.setBounds(111, 263, 134, 34);
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
                JLabel titleLabel = new JLabel("Registrarse", SwingConstants.CENTER);
                titleLabel.setBounds(0, 10, 551, 52);
                loginPanel.add(titleLabel);
                titleLabel.setFont(new Font("SansSerif", Font.BOLD, 28));
                titleLabel.setForeground(new Color(0, 0, 0));
                
                JPanel panel_2 = new JPanel();
                panel_2.setBackground(new Color(255, 255, 255));
                panel_2.setBounds(111, 329, 314, 52);
                loginPanel.add(panel_2);
                
                JLabel errorLabel = new JLabel("", SwingConstants.CENTER);
                errorLabel.setForeground(Color.BLACK);
                errorLabel.setFont(new Font("SansSerif", Font.PLAIN, 20));
                panel_2.add(errorLabel);
                
                Email = new JTextField();
                Email.setForeground(Color.BLACK);
                Email.setFont(new Font("SansSerif", Font.PLAIN, 18));
                Email.setBorder(BorderFactory.createTitledBorder("Correo"));
                Email.setBackground(Color.WHITE);
                Email.setBounds(111, 136, 314, 42);
                loginPanel.add(Email);

        // Acción para el botón de login
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = userField.getText();
                String password = new String(passwordField.getPassword());
                String email = Email.getText();

                // Validación simple
                if (username.equals("user") && password.equals("1234")) {
                    errorLabel.setText("Usuario creado exitosamente");
                    errorLabel.setForeground(Color.GREEN);
                    // Aquí abres la ventana de películas
                    LoginApp login = new LoginApp();
                    login.setVisible(true);
                    setVisible(false); // Cierra la ventana de login
                } else {
                    errorLabel.setText("Revise de nuevo");
                }
                
               
            }
        });
    }

   
}

