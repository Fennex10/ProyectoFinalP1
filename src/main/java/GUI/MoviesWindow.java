package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.*;
import java.util.List;
import java.util.Arrays;

public class MoviesWindow extends JFrame {

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
        setTitle("Películas - CineApp");
        setSize(900, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setLayout(new BorderLayout());
        getContentPane().setBackground(BACKGROUND_COLOR);

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
            String query = searchField.getText();
            JOptionPane.showMessageDialog(this, "Buscando: " + query);
        });

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
        List<String> movies = Arrays.asList("Avengers", "It", "Película 3", "Película 4",
                "Película 5", "Película 6", "Película 7", "Película 8");
        List<String> images = Arrays.asList(
                "path/to/image1.jpg", "C:\\Users\\User\\Desktop\\download.jpg", "path/to/image3.jpg", "path/to/image4.jpg",
                "path/to/image5.jpg", "path/to/image6.jpg", "path/to/image7.jpg", "path/to/image8.jpg");
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

        List<String> movies = Arrays.asList("Película 1", "Película 2", "Película 3", "Película 4",
                "Película 5", "Película 6", "Película 7", "Película 8");
        List<String> images = Arrays.asList(
                "C:\\Users\\User\\Desktop\\Sin título.png", "C:\\Users\\User\\Desktop\\download.jpg",
                "C:\\Users\\User\\Desktop\\image3.jpg", "C:\\Users\\User\\Desktop\\image4.jpg",
                "C:\\Users\\User\\Desktop\\image5.jpg", "C:\\Users\\User\\Desktop\\image6.jpg",
                "C:\\Users\\User\\Desktop\\image7.jpg", "C:\\Users\\User\\Desktop\\image8.jpg");

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
        Set<String> occupied = occupiedSeats.getOrDefault(currentMovie, new HashSet<>());

        for (Component component : ((JPanel) seatsPanel.getComponent(0)).getComponents()) {
            if (component instanceof JButton seatButton) {
                if (seatButton.getBackground() == Color.YELLOW) {
                    occupied.add(seatButton.getText());
                    seatButton.setBackground(Color.RED);
                    seatButton.setEnabled(false);
                }
            }
        }

        occupiedSeats.put(currentMovie, occupied);
        JOptionPane.showMessageDialog(this, "Asientos confirmados para " + currentMovie);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new LoginApp().setVisible(true));
    }}