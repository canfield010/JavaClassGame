package com.canfield010.mygame.gui;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Gui extends JFrame {

    public static final int STARTING_SCREEN_WIDTH = 856;
    public static final int STARTING_SCREEN_HEIGHT = 482;
    public static final boolean DEBUG = false;
    private JLayeredPane layeredPane = new JLayeredPane();
    JButton[] myBtns = new JButton[1024];
    JPanel btnPanel = new JPanel();

    JButton playButton = new JButton("Play");
    JButton settingsButton = new JButton("Settings");
    JPanel panel = new JPanel();
    JPanel buttonPanel = new JPanel();


    private Gui(String name) {
        super(name);
    }

    private void initComponents() {
        this.setPreferredSize(new Dimension(STARTING_SCREEN_WIDTH, STARTING_SCREEN_HEIGHT));
        this.setLayout(new BorderLayout());
        btnPanel.setLayout(null);
        for (int i = 0; i<myBtns.length; i++) {
            myBtns[i] = makeButton("img/stoneFloor.png");
            btnPanel.add(myBtns[i]);
        }
        resetSizes();




        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        playButton.setFont(new Font("Arial", Font.PLAIN, Math.min((STARTING_SCREEN_WIDTH /4)/6, (STARTING_SCREEN_HEIGHT /10))));
        if (!DEBUG) {
            playButton.setContentAreaFilled(false);
        }
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.setFont(new Font("Arial", Font.PLAIN, Math.min((STARTING_SCREEN_WIDTH /4)/6, (STARTING_SCREEN_HEIGHT /10))));
        if (!DEBUG) {
            settingsButton.setContentAreaFilled(false);
        }
        settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        // OMG it took me SO LONG to find this!!! Here's the solution:
        buttonPanel.setOpaque(false);
    }

    public void addListeners() {
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resetSizes();
            }
        });this.addWindowStateListener(event -> {

            resetSizes();
        });

        playButton.addActionListener(e -> {
            System.out.println("Click 1");
        });
        settingsButton.addActionListener(e -> {
            System.out.println("Click 2");
        });
    }

    private void addComponentsToPane() {
        buttonPanel.add(playButton);
        buttonPanel.add(settingsButton);
        layeredPane.add(btnPanel, 0, 1);
        layeredPane.add(buttonPanel, 0, 0);
        this.add(layeredPane, BorderLayout.CENTER);
    }

    private JButton makeButton(String filePath){
        JButton btn = new JButton();
        btn.setPreferredSize(new Dimension(35,35));
        btn.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        ImageIcon img = new ImageIcon(getScaledImage(filePath, 45,40));
        btn.setIcon(img);
        return btn;
    }
    private Image getScaledImage(String imgPath, int w, int h){

        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = resizedImg.createGraphics();

        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        try {
            g2.drawImage(ImageIO.read(new File(imgPath)), 0, 0, w, h, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
        g2.dispose();

        return resizedImg;
    }
    private void resetSizes() {
        btnPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        buttonPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        double rowPerColumn = (double)layeredPane.getWidth()/(double)layeredPane.getHeight();
        double columnsPerRow = (double)layeredPane.getHeight()/(double)layeredPane.getWidth();
        int cols1 = (int)Math.floor(Math.sqrt(961/rowPerColumn));
        int rows1 = (int)Math.floor(961D/(double)cols1);

        int rows2 = (int)Math.floor(Math.sqrt(961/columnsPerRow));
        int cols2 = (int)Math.floor(961D/(double)rows2);
        // choosing minimum values to keep more zoomed in.
        int cols = Math.min(cols1, cols2);
        int rows = Math.min(rows1, rows2);
        double rowSize;
        double colSize;
        if (layeredPane.getWidth()==0 || layeredPane.getHeight()==0) {
            rowSize = 1;
            colSize = 1;
        } else {
            rowSize = layeredPane.getWidth()/rows;
            colSize = layeredPane.getHeight()/cols;
        }
        for (int index = 0; index<myBtns.length; index++) {
            myBtns[index].setBounds((int)(Math.floor((double)index%(double)rows)*colSize), (int)(Math.floor((double)index/(double)rows)*rowSize), (int)rowSize+1, (int)colSize+1);
        }
    }


    public static void createAndShowGui(String name) {
        Gui frame = new Gui(name);

        frame.initComponents();
        frame.addListeners();
        frame.addComponentsToPane();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
