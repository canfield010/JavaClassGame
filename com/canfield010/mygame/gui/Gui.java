package com.canfield010.mygame.gui;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.lowermapsquare.LowerMapSquare;

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
    private int cols = 32;
    private int rows = 32;
    public static final boolean DEBUG = false;
    private JLayeredPane layeredPane = new JLayeredPane();
    JButton[] myBtns = new JButton[1089];
    //Point[] correspondingPoints = new Point[1089];
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
            myBtns[i] = makeButton(i);
            //if (rows == 0) {
            //correspondingPoints[i] = new Point(i/32, i%32);
            //} else {
            //correspondingPoints[i] = new Point(i/rows, i%rows);
            //}
            btnPanel.add(myBtns[i]);
        }




        playButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //playButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        //playButton.setVerticalAlignment(JButton.CENTER);
        //playButton.setLocation(buttonPanel.getWidth(), buttonPanel.getHeight());
        playButton.setFont(new Font("Arial", Font.PLAIN, Math.min((STARTING_SCREEN_WIDTH /4)/6, (STARTING_SCREEN_HEIGHT /10))));
        if (!DEBUG) {
            playButton.setContentAreaFilled(false);
        }
        playButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //settingsButton.setAlignmentY(Component.CENTER_ALIGNMENT);
        //settingsButton.setVerticalAlignment(JButton.CENTER);
        //settingsButton.setLocation(buttonPanel.getWidth(), buttonPanel.getHeight());
        settingsButton.setFont(new Font("Arial", Font.PLAIN, Math.min((STARTING_SCREEN_WIDTH /4)/6, (STARTING_SCREEN_HEIGHT /10))));
        if (!DEBUG) {
            settingsButton.setContentAreaFilled(false);
        }
        settingsButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        //buttonPanel.setLayout(new GridBagLayout());

        // OMG it took me SO LONG to find this!!! Here's the solution:
        buttonPanel.setOpaque(false);
        panel.setOpaque(false);

        //panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setLayout(new GridBagLayout());

        resetSizes();
    }

    public void addListeners() {
        this.addComponentListener(new ComponentAdapter() {
            public void componentResized(ComponentEvent componentEvent) {
                resetSizes();
                //playButton.setLocation(buttonPanel.getWidth()/2, buttonPanel.getHeight()/2);
                //settingsButton.setLocation(buttonPanel.getWidth()/2, buttonPanel.getHeight()/2);
            }
        });this.addWindowStateListener(event -> {
            resetSizes();
            //playButton.setLocation(buttonPanel.getWidth()/2, buttonPanel.getHeight()/2);
            //settingsButton.setLocation(buttonPanel.getWidth()/2, buttonPanel.getHeight()/2);
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
        panel.add(buttonPanel);
        layeredPane.add(btnPanel, 0, 1);
        layeredPane.add(panel, 0, 0);
        this.add(layeredPane, BorderLayout.CENTER);
    }

    private JButton makeButton(int i){
        //System.out.println(Integer.toString(i%rows) + ", " + Integer.toString(i/rows));
        //mapSquare.setPreferredSize(new Dimension(35,35));
        //mapSquare.setBorder(BorderFactory.createMatteBorder(1, 1, 1, 1, Color.BLACK));
        //ImageIcon img = new ImageIcon(getScaledImage(filePath, 45,40));
        //btn.setIcon(img);
        //mapSquare.setIcon(mapSquare.icon);
        //if (rows == 0) {
            //return Main.mapSquares.get(i/32, i%32);
        //}
        //return Main.mapSquares.get(i/rows, i%rows);
        return new JButton();
    }
    /*private Image getScaledImage(String imgPath, int w, int h){

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
    }*/
    private void resetSizes() {
        btnPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        panel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        //playButton.setLocation((buttonPanel.getWidth()/2)-(playButton.getWidth()/2), (int)(buttonPanel.getHeight()/2.1));
        //settingsButton.setLocation((buttonPanel.getWidth()/2)-(settingsButton.getWidth()/2), (int)(buttonPanel.getHeight()/2.5));
        double rowPerColumn = (double)layeredPane.getWidth()/(double)layeredPane.getHeight();
        double columnsPerRow = (double)layeredPane.getHeight()/(double)layeredPane.getWidth();
        int cols1 = (int)Math.floor(Math.sqrt(1089/rowPerColumn));
        int rows1 = (int)Math.floor(1089D/(double)cols1);

        int rows2 = (int)Math.floor(Math.sqrt(1089/columnsPerRow));
        int cols2 = (int)Math.floor(1089D/(double)rows2);
        // choosing minimum values to keep more zoomed in.
        cols = Math.min(cols1, cols2);
        rows = Math.min(rows1, rows2);
        double rowSize;
        double colSize;
        if (layeredPane.getWidth()==0 || layeredPane.getHeight()==0) {
            rowSize = 1;
            colSize = 1;
        } else {
            rowSize = layeredPane.getWidth()/(rows-1);
            colSize = layeredPane.getHeight()/(cols-1);
        }
        //for (int i = 0; i<myBtns.length; i++) {
            //myBtns[i] = makeButton(i);
            //btnPanel.add(myBtns[i]);
        //}
        MapSquare.resetImages((int)rowSize, (int)colSize);
        for (int index = 0; index<myBtns.length; index++) {
            //myBtns[index] = makeButton(index);

            //if (rows == 0) {
                //correspondingPoints[index] = new Point(index/32, index%32);
            //} else {
                //correspondingPoints[index] = new Point(index / rows, index % rows);
            //}
            myBtns[index].setBounds((int)(Math.floor(((double)index)%((double)rows))*colSize), (int)(Math.floor(((double)index)/((double)rows))*rowSize), (int)rowSize+1, (int)colSize+1);
            //myBtns[index].setIcon(new ImageIcon(getScaledImage("img/stoneFloor.png", (int)(rowSize+1),(int)(colSize+1))));
            //myBtns[index].getGraphics().drawImage((Image)myBtns[index].icon, 0, 0, (int)rowSize, (int)colSize, null);
            //myBtns[index].setIcon((Icon)myBtns[index].icon); 
            //myBtns[index].setIcon(new ImageIcon(((ImageIcon)myBtns[index].icon).getImage().getScaledInstance((int)(rowSize+1), (int)(colSize+1), Image.SCALE_DEFAULT)));//Image.SCALE_DEFAULT)));
            if (rows==0) {
                rows = 32;
            }
            //MapSquare.resetImages((int)rowSize, (int)colSize);
            //myBtns[index].setIcon(new ImageIcon(((ImageIcon)Main.mapSquares.get(index/rows, index%rows).icon).getImage().getScaledInstance((int)(rowSize+1), (int)(colSize+1), Image.SCALE_FAST)));//Image.SCALE_DEFAULT)));
            //myBtns[index].setIcon(new ImageIcon(((ImageIcon)myBtns[index].icon).getImage().getScaledInstance((int)(rowSize+1), (int)(colSize+1), Image.SCALE_FAST)));//Image.SCALE_DEFAULT)));
            try {
                myBtns[index].setIcon((Icon)Main.mapSquares.get(index/rows, index%rows).lowerMapSquare.icon);//Image.SCALE_DEFAULT)));
            } catch (Exception ignored){
                System.out.println(ignored);
            }
        }

        layeredPane.moveToFront(panel);
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
