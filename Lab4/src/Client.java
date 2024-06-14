import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Random;

public class Client extends JFrame {

    private static final int PORT = 12345;
    private static final String SERVER_IP = "localhost"; // IP адреса сервера (можна використовувати localhost для локального тестування)

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private JButton circleButton;
    private JPanel drawPanel;

    public Client() {
        setTitle("Circle Client");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        circleButton = new JButton("Draw Circle");
        circleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendDrawCommand("circle");
            }
        });

        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Here we can draw circles if needed
            }
        };
        drawPanel.setBackground(Color.WHITE);

        setLayout(new BorderLayout());
        add(circleButton, BorderLayout.NORTH);
        add(drawPanel, BorderLayout.CENTER);

        connectToServer();
    }

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_IP, PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            Thread receiverThread = new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true) {
                            String command = (String) inputStream.readObject();
                            if (command.equals("rectangle")) {
                                // Handle rectangle command received from server
                                handleRectangle();
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

    private void sendDrawCommand(String command) {
        try {
            outputStream.writeObject(command);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRectangle() {
        // Handle the drawing of rectangles on the UI
        Graphics g = drawPanel.getGraphics();
        g.setColor(Color.BLUE);

        // Generate random coordinates
        Random random = new Random();
        int x = random.nextInt(drawPanel.getWidth() - 50); // -50 to ensure the shape stays within bounds
        int y = random.nextInt(drawPanel.getHeight() - 50); // -50 to ensure the shape stays within bounds

        g.fillRect(x, y, 50, 50); // Draw rectangle at random position
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Client().setVisible(true);
            }
        });
    }
}
