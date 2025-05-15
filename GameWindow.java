import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public JButton buttons[] = new JButton[9];

    GameWindow() {
        super("Tik-Tac-Move!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setLayout(new BorderLayout());
        setResizable(false);

        // Top panel holding Panel 1 and Panel 2
        JPanel topPanel = new JPanel(new GridLayout(1, 2));

        JLabel label1 = new JLabel("Panel 1", SwingConstants.CENTER);
        label1.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        topPanel.add(label1);

        JLabel label2 = new JLabel("Panel 2", SwingConstants.CENTER);
        label2.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        topPanel.add(label2);

        topPanel.setPreferredSize(new Dimension(500, 50));
        topPanel.setBackground(new Color(149, 82, 81));
        // JLabel.setFont(new Font("Courier New", Font.ITALIC, 20));

        // Center panel with 3x3 buttons
        JPanel gridPanel = new JPanel(new GridLayout(3, 3, 0, 0));
       // gridPanel.setPreferredSize(new Dimension(500, 700));

        for (int i = 0; i < 9; i++) {
            buttons[i] = new JButton();
            buttons[i].setBackground(new Color(255, 229, 180));
            //            button.setBackground(Color.MAGENTA);       // Background color
            buttons[i].setForeground(Color.BLACK);      // Text color
//            button.setOpaque(true);                 // Needed on some systems
//            buttons[i].setBorderPainted(true);         // Optional: cleaner look
            buttons[i].setFocusPainted(true);
            buttons[i].setBorderPainted(true);
            buttons[i].setContentAreaFilled(true);
            buttons[i].setOpaque(true);
            //  button.setBackground(new Color(240, 240, 240)); // Light gray
            buttons[i].setForeground(Color.BLACK);
            buttons[i].setFont(new Font("Arial", Font.BOLD, 14));
            buttons[i].setBorder(BorderFactory.createLineBorder(new Color(149, 82, 81), 4));
            buttons[i].setPreferredSize(new Dimension(100, 40));
            buttons[i].setFont(new Font("Courier New", Font.BOLD, 20));

            gridPanel.add(buttons[i]);
        }

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        setVisible(true);
    }


}
