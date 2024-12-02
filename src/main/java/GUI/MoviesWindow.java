package GUI;

import Funciones.DatabaseConnection;
import Funciones.Tickets;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;
import java.util.List;
import java.util.Arrays;

public class MoviesWindow extends JFrame {

    private String pelicula;
    private String asientos;
    private double costo;

    private static final Color BACKGROUND_COLOR = new Color(30, 30, 30);
    private static final Color BORDER_COLOR = new Color(139, 0, 0);
    private static final Color TEXT_COLOR = Color.WHITE;
    private static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 28);
    private static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 14);

    private JPanel mainPanel; // Panel principal para cambiar contenido
    private JPanel moviesPanel; // Panel de películas
    private JPanel seatsPanel; // Panel de asientos

    private Map<String, Set<String>> occupiedSeats = new HashMap<>(); // Asientos ocupados por película
    private String currentMovie; // Película seleccionada

    public MoviesWindow() {
        setTitle("Cinema Store");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);
        setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Login.jpeg"));

        // Crear el menú principal
        createMenuBar();

        // Crear el panel principal
        mainPanel = new JPanel(new CardLayout());
        getContentPane().add(mainPanel, BorderLayout.CENTER);

        // Crear paneles para películas y asientos
        createMoviesPanel();
        createSeatsPanel();

        // Mostrar panel de películas al inicio
        showMoviesPanel();
    }

    private void filterMoviesBySearch(String query) {
        moviesPanel.removeAll();

        // Películas y sus respectivas imágenes
        List<String> movies = Arrays.asList("Rapidos y Furiosos X", "Donden estan las rubias", "La infiltrada", "Andrea",
                "Blade Runner 2049", "Moana", "Infiltrados en la universidad", "La lista de Schindler");
        List<String> images = Arrays.asList(
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Rapido y Furiosos accion.jpeg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Donde estan las rubias comedia.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\La infiltrada drama.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Andrea.jpeg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Blade Runner 2049.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Moana animacion.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Infiltrados en la universidad comedia.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\La lista de Schindler drama.jpg");

        // Filtrar películas que contienen el texto buscado (ignorando mayúsculas)
        for (int i = 0; i < movies.size(); i++) {
            if (movies.get(i).toLowerCase().contains(query.toLowerCase())) {
                addMovieCard(movies.get(i), images.get(i));
            }
        }

        moviesPanel.revalidate();
        moviesPanel.repaint();

        if (moviesPanel.getComponentCount() == 0) {
            JLabel noResultsLabel = new JLabel("No se encontraron películas.", SwingConstants.CENTER);
            noResultsLabel.setForeground(Color.WHITE);
            noResultsLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
            moviesPanel.add(noResultsLabel);
        }
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(0, 0, 0)); // Fondo negro
        menuBar.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        // Menú Inicio
        JMenu menuInicio = new JMenu("Inicio");
        styleMenu(menuInicio);

        menuInicio.add(new JMenuItem(new AbstractAction("Volver al Inicio") {
            @Override
            public void actionPerformed(ActionEvent e) {
                showMoviesPanel();
            }
        }));

        // Menú Categorías
        JMenu menuCategorias = new JMenu("Categorías");
        styleMenu(menuCategorias);

        // Lista de categorías
        List<String> categories = Arrays.asList("Todas", "Acción", "Comedia", "Drama", "Terror", "Animación");
        for (String category : categories) {
            JMenuItem categoryItem = new JMenuItem(category);
            categoryItem.setFont(BUTTON_FONT);
            categoryItem.setBackground(new Color(230, 230, 250)); // Lavanda claro
            categoryItem.setForeground(new Color(60, 60, 60)); // Texto gris oscuro
            categoryItem.addActionListener(e -> filterMoviesByCategory(category));
            menuCategorias.add(categoryItem);
        }

        // Menú Salir
        JMenu menuSalir = new JMenu("Salir");
        styleMenu(menuSalir);

        menuSalir.add(new JMenuItem(new AbstractAction("Cerrar Aplicación") {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        }));

        // Buscador
        JTextField searchField = new JTextField(20);
        searchField.setFont(BUTTON_FONT);
        searchField.setBackground(Color.WHITE);
        searchField.setForeground(Color.BLACK);
        searchField.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255))); // Azul Dodger
        JButton searchButton = new JButton("🔍 Buscar");
        searchButton.setFont(BUTTON_FONT);
        searchButton.setBackground(new Color(141, 31, 18)); // Azul
        searchButton.setForeground(Color.WHITE);
        searchButton.addActionListener(e -> {
            String query = searchField.getText().trim();
            if (!query.isEmpty()) {
                filterMoviesBySearch(query);
            } else {
                filterMoviesByCategory("Todas");
            }
        });

        //limpia el campo de texto
        searchField.addActionListener(e -> searchButton.doClick());

        // Agregar componentes al menú
        menuBar.add(menuInicio);
        menuBar.add(menuCategorias);
        menuBar.add(Box.createHorizontalGlue()); // Separador para alinear a la derecha
        menuBar.add(searchField);
        menuBar.add(searchButton);
        menuBar.add(menuSalir);

        setJMenuBar(menuBar);
    }

    private void addMovieCard(String movie, String imagePath) {
        JPanel movieCard = new JPanel();
        movieCard.setLayout(new BorderLayout());
        movieCard.setBackground(BACKGROUND_COLOR);
        movieCard.setBorder(BorderFactory.createLineBorder(BORDER_COLOR, 2));

        JLabel imageLabel = new JLabel();
        ImageIcon movieImage = new ImageIcon(imagePath);
        Image scaledImage = movieImage.getImage().getScaledInstance(350, 500, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(scaledImage));
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        movieCard.add(imageLabel, BorderLayout.CENTER);

        JPanel infoPanel = new JPanel();
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBackground(new Color(0, 0, 0, 0.8f));

        JLabel titleLabel = new JLabel(movie);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton viewSeatsButton = new JButton("Reservar");
        viewSeatsButton.setFont(new Font("SansSerif", Font.BOLD, 12));
        viewSeatsButton.setBackground(new Color(178, 34, 34));
        viewSeatsButton.setForeground(Color.WHITE);
        viewSeatsButton.setFocusPainted(false);
        viewSeatsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        viewSeatsButton.addActionListener(e -> {
            currentMovie = movie;
            showSeatsPanel(movie);
        });

        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(titleLabel);
        infoPanel.add(Box.createVerticalStrut(10));
        infoPanel.add(viewSeatsButton);
        infoPanel.add(Box.createVerticalStrut(10));

        movieCard.add(infoPanel, BorderLayout.SOUTH);
        moviesPanel.add(movieCard);
    }

    private void filterMoviesByCategory(String category) {
        moviesPanel.removeAll();

        // Películas y categorías
        List<String> movies = Arrays.asList("Rapidos y Furiosos X", "Donden estan las rubias", "La infiltrada", "Andrea",
                "Blade Runner 2049", "Moana", "Infiltrados en la universidad", "La lista de Schindler");
        List<String> images = Arrays.asList(
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Rapido y Furiosos accion.jpeg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Donde estan las rubias comedia.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\La infiltrada drama.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Andrea.jpeg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Blade Runner 2049.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Moana animacion.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Infiltrados en la universidad comedia.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\La lista de Schindler drama.jpg");
        List<String> categories = Arrays.asList("Acción", "Comedia", "Drama", "Terror",
                "Acción", "Animación", "Comedia", "Drama");

        // Filtrar películas
        for (int i = 0; i < movies.size(); i++) {
            if (category.equals("Todas") || categories.get(i).equals(category)) {
                addMovieCard(movies.get(i), images.get(i));
            }
        }

        moviesPanel.revalidate();
        moviesPanel.repaint();
    }

    private void styleMenu(JMenu menu) {
        menu.setForeground(new Color(139, 0, 0)); // Rojo oscuro (BORDER_COLOR)
        menu.setFont(BUTTON_FONT);
        menu.setBorder(BorderFactory.createEmptyBorder(2, 5, 2, 5));
    }

    private void createMoviesPanel() {
        moviesPanel = new JPanel();
        moviesPanel.setBackground(BACKGROUND_COLOR);
        moviesPanel.setLayout(new GridLayout(0, 4, 15, 15)); // Por defecto 4 columnas y 15px de separación

        mainPanel.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                int width = mainPanel.getWidth();
                int columns = Math.max(1, width / 300); // Calcula columnas: cada tarjeta ocupa 300px
                ((GridLayout) moviesPanel.getLayout()).setColumns(columns);
                moviesPanel.revalidate();
            }
        });

        List<String> movies = Arrays.asList("Rapidos y Furiosos X", "Donden estan las rubias", "La infiltrada", "Andrea",
                "Blade Runner 2049", "Moana", "Infiltrados en la universidad", "La lista de Schindler");
        List<String> images = Arrays.asList(
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Rapido y Furiosos accion.jpeg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Donde estan las rubias comedia.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\La infiltrada drama.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Andrea.jpeg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Blade Runner 2049.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Moana animacion.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\Infiltrados en la universidad comedia.jpg",
                "C:\\Users\\pc\\OneDrive\\Escritorio\\Imagenes\\La lista de Schindler drama.jpg");

        for (int i = 0; i < movies.size(); i++) {
            addMovieCard(movies.get(i), images.get(i));
        }

        mainPanel.add(moviesPanel, "Movies");
    }

    // Crear el panel de asientos
    private void createSeatsPanel() {
        seatsPanel = new JPanel(new BorderLayout());
        seatsPanel.setBackground(BACKGROUND_COLOR);

        JPanel seatsGrid = new JPanel(new GridLayout(5, 5, 10, 10));
        seatsGrid.setBackground(BACKGROUND_COLOR);

        for (int i = 1; i <= 25; i++) {
            String seat = "S" + i;
            JButton seatButton = new JButton(seat);
            seatButton.setFont(BUTTON_FONT);
            seatButton.setBackground(Color.GREEN);
            seatButton.setForeground(Color.BLACK);
            seatButton.addActionListener(e -> toggleSeatSelection(seatButton, seat));
            seatsGrid.add(seatButton);
        }

        seatsPanel.add(seatsGrid, BorderLayout.CENTER);

        // Botones para regresar y confirmar
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        actionPanel.setBackground(BACKGROUND_COLOR);

        JButton backButton = new JButton("Regresar");
        backButton.setFont(BUTTON_FONT);
        backButton.setBackground(BORDER_COLOR);
        backButton.setForeground(TEXT_COLOR);
        backButton.addActionListener(e -> showMoviesPanel());
        actionPanel.add(backButton);

        JButton confirmButton = new JButton("Confirmar");
        confirmButton.setFont(BUTTON_FONT);
        confirmButton.setBackground(Color.BLUE);
        confirmButton.setForeground(TEXT_COLOR);
        confirmButton.addActionListener(e -> confirmSeats());
        actionPanel.add(confirmButton);

        seatsPanel.add(actionPanel, BorderLayout.SOUTH);

        mainPanel.add(seatsPanel, "Seats");
    }

    // Mostrar el panel de películas
    private void showMoviesPanel() {
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "Movies");
    }

    // Mostrar el panel de asientos
    private void showSeatsPanel(String movie) {
        updateSeatStatus(movie);
        CardLayout cl = (CardLayout) mainPanel.getLayout();
        cl.show(mainPanel, "Seats");
    }

    // Actualizar el estado de los asientos según la película
    private void updateSeatStatus(String movie) {
        Set<String> occupied = occupiedSeats.getOrDefault(movie, new HashSet<>());

        for (Component component : ((JPanel) seatsPanel.getComponent(0)).getComponents()) {
            if (component instanceof JButton seatButton) {
                String seat = seatButton.getText();
                if (occupied.contains(seat)) {
                    seatButton.setBackground(Color.RED);
                    seatButton.setEnabled(false);
                } else {
                    seatButton.setBackground(Color.GREEN);
                    seatButton.setEnabled(true);
                }
            }
        }
    }

    // Alternar selección de asientos
    private void toggleSeatSelection(JButton seatButton, String seat) {
        if (seatButton.getBackground() == Color.GREEN) {
            seatButton.setBackground(Color.YELLOW);
        } else {
            seatButton.setBackground(Color.GREEN);
        }
    }

    // Confirmar los asientos seleccionados
    private void confirmSeats() {
        // Aquí puedes agregar la lógica para confirmar los asientos, como lo has hecho antes
        Set<String> occupied = occupiedSeats.getOrDefault(currentMovie, new HashSet<>());
        List<String> selectedSeats = new ArrayList<>();

        // Aquí se pasa la lógica de selección de asientos y costos
        for (Component component : ((JPanel) seatsPanel.getComponent(0)).getComponents()) {
            if (component instanceof JButton seatButton) {
                if (seatButton.getBackground() == Color.YELLOW) { // Si está seleccionado
                    occupied.add(seatButton.getText()); // Añadir a la lista de ocupados
                    selectedSeats.add(seatButton.getText());
                    seatButton.setBackground(Color.RED); // Cambiar a rojo
                    seatButton.setEnabled(false); // Desactivar el botón
                }
            }
        }

        // Actualizar el mapa de asientos ocupados
        occupiedSeats.put(currentMovie, occupied);

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT costo FROM peliculas WHERE Pelicula = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, currentMovie);
            // Obtener los datos ingresados o seleccionados por el usuario
            pelicula = currentMovie; // Asegúrate de tener un JComboBox
            asientos = String.join(", ", selectedSeats); // Reemplaza con el campo adecuado
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                double precioUnitario = rs.getDouble("costo"); // Obtiene el precio unitario de la película
                costo = selectedSeats.size() * precioUnitario; // Calcula el costo total
            } else {
                System.out.println("No se encontró la película en la base de datos.");
            }

            // Crear un objeto de la clase Ticket
            Tickets ticket = new Tickets(pelicula, asientos, costo);

            // Guardar el ticket en la base de datos
            ticket.guardarEnBaseDeDatos();

            // Mostrar un mensaje al usuario con los detalles
            JOptionPane.showMessageDialog(this, "¡Ticket generado con éxito!\n"
                    + "Número de Ticket: " + ticket.getNumeroTicket() + "\n"
                    + "Película: " + ticket.getPelicula() + "\n"
                    + "Asientos: " + ticket.getAsientos() + "\n"
                    + "Costo: $" + ticket.getCosto(),
                    "Ticket Confirmado", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al generar el ticket. Inténtelo de nuevo.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginApp().setVisible(true));
    }
}