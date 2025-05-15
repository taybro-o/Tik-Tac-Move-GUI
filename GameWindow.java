import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {

    public JButton buttons[] = new JButton[9];

    //creates
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

        //create 9 buttons
        for (int i = 0; i < 9; i++) {
            buttons[i] = createButton();
            gridPanel.add(buttons[i]);
        }

        // Add panels to frame
        add(topPanel, BorderLayout.NORTH);
        add(gridPanel, BorderLayout.CENTER);

        setVisible(true);
    }

    private JButton createButton() {
        JButton tempButton = new JButton();
        tempButton.setBackground(new Color(255, 229, 180));
        //            button.setBackground(Color.MAGENTA);       // Background color
        tempButton.setForeground(Color.BLACK);      // Text color
//            button.setOpaque(true);                 // Needed on some systems
//            tempButton.setBorderPainted(true);         // Optional: cleaner look
        tempButton.setFocusPainted(true);
        tempButton.setBorderPainted(true);
        tempButton.setContentAreaFilled(true);
//            tempButton.setOpaque(true);
        //  button.setBackground(new Color(240, 240, 240)); // Light gray
        tempButton.setForeground(Color.BLACK);
        tempButton.setFont(new Font("Arial", Font.BOLD, 14));
        tempButton.setBorder(BorderFactory.createLineBorder(new Color(149, 82, 81), 4));
        tempButton.setPreferredSize(new Dimension(100, 40));
        tempButton.setFont(new Font("Courier New", Font.BOLD, 20));

        return tempButton;
    }

    //creates titleScreen
    GameWindow(int i) {
        super("Tik-Tac-Move!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(500, 500);
        setBackground(new Color(255, 229, 180));

        setLayout(null);
        setResizable(false);

        ImageIcon logoImage = new ImageIcon("newLogo.png");

        JLabel logoLabel = new JLabel(logoImage);
        logoLabel.setBounds(0,0, 500, 250);

        add(logoLabel);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setSize(500, 500);
        startButtonPanel.setLayout(new BorderLayout(0,0));
        startButtonPanel.setBorder(null);

        JButton startButton = createButton();
        startButton.setBorder(null);
        startButton.setText("Start Game!");

        startButtonPanel.setBounds(0, 250 , 500, 500);
        startButtonPanel.setBackground(new Color(255, 229, 180));
        startButtonPanel.add(startButton, BorderLayout.NORTH);

        add(startButtonPanel);


        setVisible(true);
        System.out.println(getWidth() + " " + getHeight());

    }


}
