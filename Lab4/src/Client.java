import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Client extends JFrame {

    private static final int PORT = 12345;
    private static final String SERVER_IP = "localhost";

    private Socket socket;
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;

    private JPanel drawPanel;
    private List<String> messagesSent = new ArrayList<>();

    public Client() {
        setTitle("Client");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        drawPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
            }
        };
        drawPanel.setBackground(Color.WHITE);
        drawPanel.setFocusable(true);
        drawPanel.requestFocusInWindow();

        setLayout(new BorderLayout());
        add(drawPanel, BorderLayout.CENTER);

        JButton circleButton = new JButton("Draw Circle");
        circleButton.addActionListener(e -> sendDrawCommand("circle"));
        add(circleButton, BorderLayout.NORTH);

        setupHotkeys();
        connectToServer();
    }

    private void setupHotkeys() {
        setupKeyStroke(KeyEvent.VK_P, InputEvent.CTRL_DOWN_MASK, "sendMessage1", "Привіт!");
        setupKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_DOWN_MASK, "sendMessage2", "Як справи?");
        setupKeyStroke(KeyEvent.VK_R, InputEvent.CTRL_DOWN_MASK, "sendMessage3", "До побачення!");

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

    private void connectToServer() {
        try {
            socket = new Socket(SERVER_IP, PORT);
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            inputStream = new ObjectInputStream(socket.getInputStream());

            Thread receiverThread = new Thread(() -> {
                try {
                    while (true) {
                        String command = (String) inputStream.readObject();
                        if (command.equals("rectangle")) {
                            handleRectangle();
                        } else {
                            JOptionPane.showMessageDialog(Client.this, command, "Message from Server", JOptionPane.INFORMATION_MESSAGE);
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

    private void sendMessage(String message) {
        try {
            outputStream.writeObject(message);
            outputStream.flush();
            messagesSent.add(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleRectangle() {
        Graphics g = drawPanel.getGraphics();
        g.setColor(Color.BLUE);

        Random random = new Random();
        int x = random.nextInt(drawPanel.getWidth() - 50);
        int y = random.nextInt(drawPanel.getHeight() - 50);

        g.fillRect(x, y, 50, 50);
    }

    private void showMessages() {
        StringBuilder sb = new StringBuilder("Messages sent to server:\n");
        for (int i = 0; i < messagesSent.size(); i++) {
            sb.append(i + 1).append(": ").append(messagesSent.get(i)).append("\n");
        }
        JOptionPane.showMessageDialog(this, sb.toString(), "Sent Messages", JOptionPane.INFORMATION_MESSAGE);
    }

    private void clearMessages() {
        messagesSent.clear();
        JOptionPane.showMessageDialog(this, "Sent messages cleared.", "Messages Cleared", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Client().setVisible(true));
    }
}
