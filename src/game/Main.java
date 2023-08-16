package game;


import game.Controllers.FieldController;
import game.Controllers.SnakeController;
import game.Models.*;


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

import static game.Params.*;

public class Main extends JFrame {
    private static Field myField;
    private static Snake mySnake;
    CardLayout layoutManager = new CardLayout();
    JPanel containerPanel = new JPanel();
    private static boolean gameState = false;
    private static int gameSpeed = 200;
    private static JLabel scoreLabel;
    private static JLabel currScoreLabel;
    private static SnakeController mySnakeController;
    private static FieldController myFieldController;
    Main(){

        setTitle("Wise snake devouring golden apples in the magic world");
        //setLocationRelativeTo(null);
        setSize(Params.WIDTH,Params.HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        containerPanel.setLayout(layoutManager);
        var gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        var menuPanel = new JPanel();
        var restartPanel = new JPanel();

        var mJPanel = new Canvas();
        mJPanel.setBackground(Color.BLACK);

        var startButton = new JButton("New Game");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                layoutManager.next(containerPanel);
                myField = new Field(numberOfRows,numberOfColumns,FIELD_DEFAULT_COLOR);
                mySnake = new Snake(SNAKE_BEGIN_POS_X,SNAKE_BEGIN_POS_Y,SNAKE_DEFAULT_DIRECTION,myField,SNAKE_LENGTH);
                mySnakeController = new SnakeController(mySnake);
                myFieldController = new FieldController(myField);
                myFieldController.setFieldApple(new Apple(0,APPLE_DEFAULT_COLOR,myField));
                gameState = true;
            }
        });
        var menuButton = new JButton("Main menu");
        menuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                layoutManager.first(containerPanel);
                gameState = false;
            }
        });
        JDialog pauseMenu = new JDialog(this);
        pauseMenu.getRootPane().setOpaque(false);
        pauseMenu.setUndecorated(true);
        pauseMenu.setBackground(new Color(0,0,0,0));


        JPanel pausePanel = new JPanel();
        var pExitButton = new JButton("Exit");
        pExitButton.addActionListener(e->{System.exit(0);});
        pExitButton.setFont(BASIC_FONT);
        pExitButton.setAlignmentX(CENTER_ALIGNMENT);
        pExitButton.setFocusPainted(false);
        pExitButton.setForeground(Color.WHITE);
        pExitButton.setBorderPainted(false);
        pExitButton.setContentAreaFilled(false);

        var pMenuButton = new JButton("Main menu");
        pMenuButton.addActionListener(e->{ layoutManager.first(containerPanel);pauseMenu.setVisible(false);});
        pMenuButton.setFont(BASIC_FONT);
        pMenuButton.setAlignmentX(CENTER_ALIGNMENT);
        pMenuButton.setFocusPainted(false);
        pMenuButton.setForeground(Color.WHITE);
        pMenuButton.setBorderPainted(false);
        pMenuButton.setContentAreaFilled(false);

        var pauseButton = new JButton("Resume game");
        pausePanel.setBackground(new Color(0,0,0,64));
        pauseButton.addActionListener(e->{gameState = true;pauseMenu.setVisible(false);this.setFocusable(true);});
        pauseButton.setFont(BASIC_FONT);
        pauseButton.setAlignmentX(CENTER_ALIGNMENT);
        pauseButton.setFocusPainted(false);
        pauseButton.setForeground(Color.WHITE);
        pauseButton.setBorderPainted(false);
        pauseButton.setContentAreaFilled(false);

        var pauseLabel = new JLabel("Game paused");
        pauseLabel.setFont(new Font(Font.SANS_SERIF,Font.BOLD,45));
        pauseLabel.setForeground(Color.RED);
        pausePanel.setLayout(new GridBagLayout());
        pausePanel.add(pauseLabel,gbc);
        pausePanel.add(pauseButton,gbc);
        pausePanel.add(pMenuButton,gbc);
        pausePanel.add(pExitButton,gbc);


        pauseMenu.add(pausePanel);
        pauseMenu.setVisible(false);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                pauseMenu.pack();
                pauseMenu.setLocation((getWidth()/2-pauseMenu.getWidth()/2),(getHeight()/2-pauseMenu.getHeight()/2));
            }

        });


        pauseMenu.setLocationRelativeTo(this);
        pauseMenu.pack();

        pauseMenu.setLocation((getWidth()/2-pauseMenu.getWidth()/2),(getHeight()/2-pauseMenu.getHeight()/2));



        this.setFocusable(true);

        mJPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("UP"),"up");
        mJPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("DOWN"),"down");
        mJPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("RIGHT"),"right");
        mJPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("LEFT"),"left");
        mJPanel.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke("ESCAPE"),"pause");
        mJPanel.getActionMap().put("pause", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pauseMenu.setVisible(true);
                gameState = false;
            }
        });
        mJPanel.getActionMap().put("up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameState){
                    try{
                        mySnakeController.setSnakeDirection("up");
                    }
                    catch (IllegalStateException ex){
                        System.err.println(ex.getMessage());
                    }
                }
            }
        });
        mJPanel.getActionMap().put("down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameState){
                    try{
                        mySnakeController.setSnakeDirection("down");
                    }
                    catch (IllegalStateException ex){
                        System.err.println(ex.getMessage());
                    }
                }
            }
        });
        mJPanel.getActionMap().put("right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameState){
                    try{
                        mySnakeController.setSnakeDirection("right");
                    }
                    catch (IllegalStateException ex){
                        System.err.println(ex.getMessage());
                    }
                }
            }
        });
        mJPanel.getActionMap().put("left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(gameState){
                    try{
                        mySnakeController.setSnakeDirection("left");
                    }
                    catch (IllegalStateException ex){
                        System.err.println(ex.getMessage());
                    }
                }
            }
        });



        currScoreLabel = new JLabel("Wisdom: 0");
        currScoreLabel.setFont(BASIC_FONT);
        currScoreLabel.setForeground(Color.RED);
        mJPanel.add(currScoreLabel,BorderLayout.LINE_START);

        menuPanel.setBackground(Color.BLACK);
        menuPanel.setLayout(new GridBagLayout());

        var buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBackground(Color.BLACK);

        startButton.setFont(BASIC_FONT);
        startButton.setAlignmentX(CENTER_ALIGNMENT);
        startButton.setBackground(Color.BLACK);
        startButton.setFocusPainted(false);
        startButton.setForeground(Color.WHITE);
        startButton.setContentAreaFilled(false);
        startButton.setBorderPainted(false);

        var Title = new JLabel("Wise snake devouring golden apples in the magic world");
        Title.setFont(new Font(Font.SANS_SERIF,Font.BOLD,45));
        Title.setForeground(Color.RED);



        menuPanel.add(Title,gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        var exitButton = new JButton("Exit");
        exitButton.addActionListener(e->{System.exit(0);});
        exitButton.setFont(BASIC_FONT);
        exitButton.setAlignmentX(CENTER_ALIGNMENT);
        exitButton.setBackground(Color.BLACK);
        exitButton.setFocusPainted(false);
        exitButton.setForeground(Color.WHITE);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);

        buttonPanel.add(startButton,gbc);
        buttonPanel.add(exitButton,gbc);
        menuPanel.add(buttonPanel,gbc);

        restartPanel.setBackground(Color.BLACK);
        restartPanel.setLayout(new GridBagLayout());
        menuButton.setFont(BASIC_FONT);

        var endButtonPanel = new JPanel();
        endButtonPanel.setLayout(new GridBagLayout());
        endButtonPanel.setBackground(Color.BLACK);
        endButtonPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        var lostLabel = new JLabel("You Lost");
        lostLabel.setFont(new Font(Font.DIALOG,Font.BOLD,45));
        lostLabel.setForeground(Color.RED);
        lostLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        scoreLabel = new JLabel("Your Wisdom is : 0");
        scoreLabel.setForeground(Color.GREEN);
        scoreLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        menuButton.setBorderPainted(false);
        menuButton.setContentAreaFilled(false);
        menuButton.setBackground(Color.BLACK);
        menuButton.setForeground(Color.WHITE);
        menuButton.setFocusPainted(false);
        menuButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        var secExitButton =  new JButton("Exit");
        secExitButton.addActionListener(e->{System.exit(0);});
        secExitButton.setFont(BASIC_FONT);
        secExitButton.setAlignmentX(CENTER_ALIGNMENT);
        secExitButton.setBackground(Color.BLACK);
        secExitButton.setFocusPainted(false);
        secExitButton.setForeground(Color.WHITE);
        secExitButton.setBorderPainted(false);
        secExitButton.setContentAreaFilled(false);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.CENTER;
        restartPanel.add(lostLabel,gbc);
        restartPanel.add(scoreLabel,gbc);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        restartPanel.add(menuButton,gbc);
        restartPanel.add(secExitButton,gbc);

        containerPanel.add(menuPanel,BorderLayout.CENTER);
        containerPanel.add(mJPanel);
        containerPanel.add(restartPanel);


        add(containerPanel);

    }
    class Canvas extends JPanel{

        Canvas(){
            var timer = new Timer(gameSpeed,e->{

                if(gameState){
                    if(mySnakeController.checkSnakeState()){
                        layoutManager.last(containerPanel);
                        gameState = false;
                    }
                    else{
                        mySnakeController.moveSnake();
                        scoreLabel.setText("Your Wisdom is : " + mySnakeController.getSnakeScore());
                        currScoreLabel.setText("Wisdom: " + mySnakeController.getSnakeScore());
                        repaint();
                    }
                }
            });
            timer.start();
        }
        @Override
        protected void paintComponent(Graphics g){
            super.paintComponent(g);
            Graphics2D g2D = (Graphics2D) g;
            myFieldController.drawField(g2D,this);
            mySnakeController.drawSnake(g2D);
            myFieldController.drawApple(g2D);

        }
    }



    public static void main(String[] args) {
        new Main().setVisible(true);
    }
}