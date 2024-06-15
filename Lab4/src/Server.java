import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

public class Server extends JFrame {

    private static final int PORT = 12345;

    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private JPanel drawPanel;
    private List<String> messages = new ArrayList<>();

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

        setupHotkeys();
        setupServer();
    }

    private void setupHotkeys() {
        setupKeyStroke(KeyEvent.VK_H, InputEvent.CTRL_DOWN_MASK, "sendMessage1", "Hello!");
        setupKeyStroke(KeyEvent.VK_A, InputEvent.CTRL_DOWN_MASK, "sendMessage2", "How are you?");
        setupKeyStroke(KeyEvent.VK_G, InputEvent.CTRL_DOWN_MASK, "sendMessage3", "Goodbye!");

        setupKeyStroke(KeyEvent.VK_R, InputEvent.ALT_DOWN_MASK, "showMessages", this::showMessages);
        setupKeyStroke(KeyEvent.VK_D, InputEvent.ALT_DOWN_MASK, "clearMessages", this::clearMessages);
    }

    private void setupKeyStroke(int keyCode, int modifiers, String actionKey, String message) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
        drawPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionKey);
        drawPanel.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sendMessage(message);
            }
        });
    }

    private void setupKeyStroke(int keyCode, int modifiers, String actionKey, Runnable action) {
        KeyStroke keyStroke = KeyStroke.getKeyStroke(keyCode, modifiers);
        drawPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, actionKey);
        drawPanel.getActionMap().put(actionKey, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                action.run();
            }
        });
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
                        if (command.equals("rectangle")) {
                            handleCircle();
                        } else {
                            messages.add(command);
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

        int x = (int) (Math.random() * drawPanel.getWidth());
        int y = (int) (Math.random() * drawPanel.getHeight());

        g.fillOval(x, y, 50, 50);
    }

    private void sendMessage(String message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
            messages.add(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMessages() {
        StringBuilder sb = new StringBuilder("Messages:\n");

        for (int i = 0; i < messages.size(); i++) {
            sb.append(i + 1).append(": ").append(messages.get(i)).append("\n");
        }

        JOptionPane.showMessageDialog(this, sb.toString(), "All Messages", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearMessages() {
        messages.clear();
        JOptionPane.showMessageDialog(this, "Messages cleared.", "Messages Cleared", JOptionPane.INFORMATION_MESSAGE);
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
