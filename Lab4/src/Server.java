import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Server extends JFrame {

    private static final int PORT = 12345;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private JPanel drawPanel;
    private List<String> messagesReceived = new ArrayList<>();

    public Server() throws IOException {
        setTitle("Server");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        drawPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);

        JButton rectangleButton = new JButton("Draw Rectangle");
        rectangleButton.addActionListener(e -> sendDrawCommand("rectangle"));
        add(rectangleButton, BorderLayout.NORTH);

        setupServer();
    }

    private void setupServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            System.out.println("Server waiting for connections on port " + PORT);

            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket.getInetAddress().getHostName());

            outputStream = new ObjectOutputStream(clientSocket.getOutputStream());
            inputStream = new ObjectInputStream(clientSocket.getInputStream());

            Thread receiverThread = new Thread(() -> {
                try {
                    while (true) {
                        String command = (String) inputStream.readObject();
                        if (command.equals("circle")) {
                            handleCircle();
                        } else {
                            messagesReceived.add(command);
                            JOptionPane.showMessageDialog(Server.this, command, "Message from Client", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            });
            receiverThread.start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendDrawCommand(String command) {
        try {
            outputStream.writeObject(command);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleCircle() {
        Graphics g = drawPanel.getGraphics();
        g.setColor(Color.RED);

        Random random = new Random();
        int x = random.nextInt(drawPanel.getWidth() - 50);
        int y = random.nextInt(drawPanel.getHeight() - 50);

        g.fillOval(x, y, 50, 50);
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
