import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;

public class Server extends JFrame {

    private static final int PORT = 12345;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    ServerSocket serverSocket = new ServerSocket(PORT);

    private final JPanel drawPanel;

    public Server() throws IOException {
        setTitle("Rectangle Server");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton rectangleButton = new JButton("Draw Rectangle");
        rectangleButton.addActionListener(e -> sendDrawCommand());

        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Here we can draw rectangles if needed
            }
        };
        drawPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        add(rectangleButton, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        setupServer();
    }

    private void setupServer() {
        try {
            System.out.println("Server waiting for connections on port " + PORT);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());

            Thread receiverThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String command = (String) inputStream.readObject();
                            if (command.equals("circle")) {
                                // Handle circle command received from client
                                handleCircle();
                            }
                        }
                    } catch (IOException | ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            });
            receiverThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendDrawCommand() {
        try {
            outputStream.writeObject("rectangle");
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCircle() {
        // Handle the drawing of circles on the UI
        Graphics g = drawPanel.getGraphics();
        g.setColor(Color.RED);

        // Generate random coordinates
        Random random = new Random();
        int x = random.nextInt(drawPanel.getWidth() - 50); // -50 to ensure the shape stays within bounds
        int y = random.nextInt(drawPanel.getHeight() - 50); // -50 to ensure the shape stays within bounds

        g.fillOval(x, y, 50, 50); // Draw circle at random position
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new Server().setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

