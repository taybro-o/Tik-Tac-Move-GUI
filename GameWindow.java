import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameWindow extends JFrame {

    private CardLayout cardLayout = new CardLayout();
    private JPanel MAIN_PANEL = new JPanel(cardLayout);

    public final String TITLE_SCREEN_CONST = "TITLE";
    public final String BOARD_SCREEN_CONST = "BOARD";
    public final String WIN_SCREEN_CONST = "WIN";

    private JPanel TITLE_SCREEN;
    private JPanel BOARD_SCREEN;
    private JPanel WIN_SCREEN;

    public JButton buttons[] = new JButton[9];

    //creates GameWindow and TITLE_SCREEN
    GameWindow() {
        super("Tik-Tac-Move!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(500, 500);

        MAIN_PANEL.setSize(500, 500);
        MAIN_PANEL.setBackground(new Color(255, 229, 180));

        setContentPane(MAIN_PANEL);

        createTITLE_SCREEN();
        MAIN_PANEL.add(TITLE_SCREEN, TITLE_SCREEN_CONST);
        //setContentPane(null);

        createBOARD_SCREEN();
        MAIN_PANEL.add(BOARD_SCREEN, BOARD_SCREEN_CONST);

        setVisible(true);

    }

    private void createTITLE_SCREEN() {
        TITLE_SCREEN = new JPanel(null);
        ImageIcon logoImage = new ImageIcon("newLogo.png");

        JLabel logoLabel = new JLabel(logoImage);
        logoLabel.setBounds(0, 0, 500, 250);

        TITLE_SCREEN.add(logoLabel);

        JPanel startButtonPanel = new JPanel();
        startButtonPanel.setSize(500, 500);
        startButtonPanel.setLayout(new BorderLayout(0, 0));
        startButtonPanel.setBorder(null);

        JButton startButton = createButton();
        startButton.setBorder(null);
        startButton.setText("Start Game!");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switchScreen(BOARD_SCREEN_CONST);
            }
        });

        startButtonPanel.setBounds(0, 250, 500, 500);
        startButtonPanel.setBackground(new Color(255, 229, 180));
        startButtonPanel.add(startButton, BorderLayout.NORTH);

        TITLE_SCREEN.add(startButtonPanel);
    }

    private void createBOARD_SCREEN() {

        BOARD_SCREEN = new JPanel(new BorderLayout());

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
        BOARD_SCREEN.add(topPanel, BorderLayout.NORTH);
        BOARD_SCREEN.add(gridPanel, BorderLayout.CENTER);

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

    public void switchScreen(String choice) {
        switch (choice) {
            case TITLE_SCREEN_CONST:
                cardLayout.show(MAIN_PANEL, TITLE_SCREEN_CONST);
                break;

            case BOARD_SCREEN_CONST:
                cardLayout.show(MAIN_PANEL, BOARD_SCREEN_CONST);
                break;

            //would add case 2 of win screen here

            default:
                throw new IllegalStateException("Unexpected value: " + choice);
        }
    }


}
