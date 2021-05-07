package com.canfield010.mygame.gui;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.MapHolder;
import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.actors.Villager;
import com.canfield010.mygame.item.Log;
import com.canfield010.mygame.item.tool.Axe;
import com.canfield010.mygame.item.weapon.IronSword;
import com.canfield010.mygame.mapsquare.FinalPoint;
import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare;
import com.canfield010.mygame.mapsquare.uppermapsquare.plant.OakTree;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;

public class Gui extends JFrame {

    public boolean onMenu = true;

    public static MapHolder<MapSquare, Integer> mapSquares = Main.backgroundSquares;
    //public static ActionListener actionListener;
    public Border btnBorder = BorderFactory.createLineBorder(Color.GREEN, 2);

    public static final int STARTING_SCREEN_WIDTH = 856;
    public static final int STARTING_SCREEN_HEIGHT = 482;
    private int cols = 32;
    private int rows = 32;
    private double rowSize = 32;
    private double colSize = 32;
    public static final boolean DEBUG = false;
    private JLayeredPane layeredPane = new JLayeredPane();
    JButton[] myBtns = new JButton[1089];
    JPanel btnPanel = new JPanel();

    JButton playButton = new JButton("Play");
    JButton settingsButton = new JButton("Settings");
    JPanel panel = new JPanel();
    JPanel buttonPanel = new JPanel();
    JPanel inventoryPanel = new JPanel();
    JPanel hud = new JPanel();
    JLabel healthBar = new JLabel();
    //JOptionPane inventoryPane = new JOptionPane();


    private Gui(String name) {
        super(name);
    }

    //enum InitialSquares {
        //GRASS,
        //TREE,
        //WATER,
        //STONE,
        //STONE_WALL
    //}

    private void initComponents() {
        this.setPreferredSize(new Dimension(STARTING_SCREEN_WIDTH, STARTING_SCREEN_HEIGHT));
        this.setLayout(new BorderLayout());
        btnPanel.setLayout(null);
        for (int i = 0; i<myBtns.length; i++) {
            myBtns[i] = makeButton(i);
            btnPanel.add(myBtns[i]);
        }




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
        //inventoryPanel.setOpaque(false);
        hud.setOpaque(false);
        hud.setLayout(new BoxLayout(hud, BoxLayout.PAGE_AXIS));
        hud.add(healthBar);
        healthBar.setText(Main.player.health+" health");
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        //inventoryPanel.setLayout(new GridLayout(3,8));

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
            Main.initializeMap();
            mapSquares = Main.mapSquares;
            resetSizes(); // cuz I'm lazy and don't want to redraw here;
            onMenu = false;
            layeredPane.remove(panel);
            layeredPane.moveToFront(btnPanel);
            layeredPane.moveToFront(hud);
            healthBar.setText(Main.player.health+" health");
            if (Main.player.health<=0) {
                hud.setOpaque(true);
                hud.setBackground(Color.RED);
                hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
            }
            getMovableSquares();
            //System.out.println("Click 1");
            //System.out.println(mapSquares.get(0,0).occupant);
        });
        settingsButton.addActionListener(e -> {
            System.out.println("Click 2");
        });
        addButtonListeners();
    }
    public void addButtonListeners() {
        /*for (int index = 0; index<myBtns.length; index++) {
            int finalIndex = index;
            myBtns[index].addActionListener(e -> {
                System.out.println((finalIndex/rows)+Main.playerPosition.x-(rows/2) +", "+((finalIndex%rows)+Main.playerPosition.y-(cols/2))+", "+rows);
                System.out.println(mapSquares.get((finalIndex/rows)+Main.playerPosition.x-(rows/2), (finalIndex%rows)+Main.playerPosition.x-(cols/2)).lowerMapSquare.name);
                System.out.println(Main.playerPosition.x+", "+Main.playerPosition.y);
                //System.out.println(Main.mapSquares.get(finalIndex/rows, finalIndex%rows).lowerMapSquare);
                FinalPoint pos = mapSquares.get((finalIndex/rows)+Main.playerPosition.x-(rows/2), (finalIndex%rows)+Main.playerPosition.x-(cols/2)).coordinates;
                //Main.playerPosition = new Point(pos.x, pos.y);
                for (JButton btn: myBtns) {
                    btn.removeActionListener(ex->{});
                }
                movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), mapSquares.get(Main.playerPosition.x, Main.playerPosition.y).coordinates);
            });*/
        /*for (int index = 0; index<myBtns.length; index++) {
            int finalIndex = index;
            myBtns[index].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println((finalIndex/rows)+Main.playerPosition.x-(rows/2) +", "+((finalIndex%rows)+Main.playerPosition.y-(cols/2))+", "+rows);
                    System.out.println(mapSquares.get((finalIndex/rows)+Main.playerPosition.x-(rows/2), (finalIndex%rows)+Main.playerPosition.x-(cols/2)).lowerMapSquare.name);
                    System.out.println(Main.playerPosition.x+", "+Main.playerPosition.y);
                    movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), new FinalPoint((finalIndex/rows)+Main.playerPosition.x-(rows/2), (finalIndex%rows)+Main.playerPosition.x-(cols/2)));
                }
            });
        }*/
        for (int index = 0; index<myBtns.length; index++) {
            int finalIndex = index;
            /*actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    //if (((JButton)e.getSource()).getBorder().equals(btnBorder)) {
                    if (btnBorder.equals(((JButton)e.getSource()).getBorder())) {
                         //System.out.println((finalIndex / rows) + Main.playerPosition.x - (cols / 2) + ", " + ((finalIndex % rows) + Main.playerPosition.y - (rows / 2)) + ", " + rows);
                        //System.out.println(mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.x - (rows / 2)).lowerMapSquare.name);
                        movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), new FinalPoint((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                        //getMovableSquares();
                        resetMovableSquares();
                        //System.out.println(Main.playerPosition.x + ", " + Main.playerPosition.y);
                    }
                }
            };*/
            MouseAdapter mouseAdapter = new MouseAdapter() {
                public void mouseClicked(MouseEvent e) {
                    layeredPane.remove(inventoryPanel);
                    if (e.getButton()==MouseEvent.BUTTON3) {
                        JPopupMenu popupMenu = new JPopupMenu();
                        if (btnBorder.equals(((JButton)e.getSource()).getBorder())) {
                            popupMenu.add(new JMenuItem("Move")).addActionListener(event -> {
                                if (btnBorder.equals(((JButton) e.getSource()).getBorder())) {
                                    movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), new FinalPoint((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                                    resetMovableSquares();
                                    Main.tickActors();
                                }
                                healthBar.setText(Main.player.health+" health");
                                if (Main.player.health<=0) {
                                    hud.setOpaque(true);
                                    hud.setBackground(Color.RED);
                                    hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
                                }
                            });

                        }
                        if (Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).getButtons()!=null) {
                            for (UpperMapSquare.Button btn : Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).getButtons()) {
                                //System.out.println(btn);
                                switch (btn) {
                                    /*case DESTROY -> {
                                        if (Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).upperMapSquare instanceof OakTree && Main.player.inventory.axe != null) {
                                            FinalPoint point = null;
                                            if (!(myBtns[finalIndex + 1].getBorder()==null)) {
                                                point = new FinalPoint((finalIndex / rows) + 1 + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2));
                                            }
                                            if (!(myBtns[finalIndex - 1].getBorder() == null)) {
                                                point = new FinalPoint((finalIndex / rows) - 1 + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2));
                                            }
                                            if (!(myBtns[finalIndex + cols].getBorder() == null)) {
                                                point = new FinalPoint((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + 1 + Main.playerPosition.y - (rows / 2));
                                            }
                                            if (!(myBtns[finalIndex - cols].getBorder() == null)) {
                                                point = new FinalPoint((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) - 1 + Main.playerPosition.y - (rows / 2));
                                            }
                                            //if (point != null) {
                                                //System.out.println(point.x + ", " + point.y);
                                            //} else {
                                                //System.out.println("null");
                                            //}
                                            if (point != null) {
                                                int finalX = point.x;
                                                int finalY = point.y;
                                                popupMenu.add(new JMenuItem("Destroy")).addActionListener(event -> {
                                                    movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), new FinalPoint(finalX, finalY));
                                                    resetMovableSquares();
                                                    //Main.player.inventory.axe.use(Main.mapSquares.get(finalX, finalY));
                                                    Main.player.inventory.axe.use(Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                                                    resetSizes();
                                                });
                                            }
                                        }
                                    }*/
                                    case ATTACK -> popupMenu.add(new JMenuItem("Attack")).addActionListener(event -> {
                                        //System.out.println("attacking");
                                        if (Main.player.inventory.meleeWeapon != null) {
                                            Main.player.inventory.meleeWeapon.use(Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                                        } else {
                                            Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).occupant.damage(Math.random() > 0.5 ? 1 : 2);
                                        }
                                        Main.tickActors();
                                        healthBar.setText(Main.player.health+" health");
                                        if (Main.player.health<=0) {
                                            hud.setOpaque(true);
                                            hud.setBackground(Color.RED);
                                            hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
                                        }
                                    });
                                    case USE_ITEM -> popupMenu.add(new JMenuItem("Use Item")).addActionListener(event -> {
                                        loadInventory(Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                                        layeredPane.add(inventoryPanel);
                                        layeredPane.moveToFront(inventoryPanel);
                                        healthBar.setText(Main.player.health+" health");
                                        if (Main.player.health<=0) {
                                            hud.setOpaque(true);
                                            hud.setBackground(Color.RED);
                                            hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
                                        }
                                    });
                                    case SHOOT -> {
                                        if (Main.player.inventory.rangedWeapon!=null) {
                                            popupMenu.add(new JMenuItem("Shoot")).addActionListener(event -> {
                                                Main.player.inventory.rangedWeapon.use(Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                                                Main.tickActors();
                                                healthBar.setText(Main.player.health+" health");
                                                if (Main.player.health<=0) {
                                                    hud.setOpaque(true);
                                                    hud.setBackground(Color.RED);
                                                    hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
                                                }
                                            });
                                        }
                                    }
                                    case TRADE -> popupMenu.add(new JMenuItem("Trade")).addActionListener(event -> {
                                        loadInventoryTrades(Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                                        layeredPane.add(inventoryPanel);
                                        layeredPane.moveToFront(inventoryPanel);
                                        healthBar.setText(Main.player.health+" health");
                                        if (Main.player.health<=0) {
                                            hud.setOpaque(true);
                                            hud.setBackground(Color.RED);
                                            hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
                                        }
                                    });
                                }
                            }
                        }
                        /*if (Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).upperMapSquare!=null) {
                            if (Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).upperMapSquare.buttons!=null) {
                                for (com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare.Button btn : Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).upperMapSquare.buttons) {
                                    switch (btn) {
                                        case USE_ITEM -> popupMenu.add(new JMenuItem("Use Item")).addActionListener(event -> {
                                            //if (btnBorder.equals(((JButton) e.getSource()).getBorder())) {
                                                //System.out.println("usingItem");
                                                loadInventory(Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                                                //inventoryPanel.setBackground(Color.RED);
                                                layeredPane.add(inventoryPanel);
                                                layeredPane.moveToFront(inventoryPanel);
                                                //for (int index = 0; index<24; index++) {
                                                    //switch (index) {
                                                        //case 0 ->
                                                    //}
                                                //}
                                            //inventoryPanel.setVisible(true);
                                            // inventoryPanel.setVisible(true);
                                            //}
                                        });
                                        case DESTROY -> popupMenu.add(new JMenuItem("Destroy")).addActionListener(event -> {
                                            if (btnBorder.equals(((JButton) e.getSource()).getBorder())) {
                                                movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), new FinalPoint((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                                                resetMovableSquares();
                                            }
                                        });
                                    }
                                }
                            }
                        }*/
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    } else {
                        if (btnBorder.equals(((JButton)e.getSource()).getBorder())) {
                            movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), new FinalPoint((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                            resetMovableSquares();
                            Main.tickActors();
                        }
                    }
                    healthBar.setText(Main.player.health+" health");
                    if (Main.player.health<=0) {
                        hud.setOpaque(true);
                        hud.setBackground(Color.RED);
                        hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
                    }
                }
            };
            myBtns[index].addMouseListener(mouseAdapter);
        }
    }

    private void addComponentsToPane() {
        //inventoryPanel.add(inventoryPanel);
        buttonPanel.add(playButton);
        buttonPanel.add(settingsButton);
        panel.add(buttonPanel);
        layeredPane.add(btnPanel, 0, 2);
        layeredPane.add(panel, 0, 0);
        layeredPane.add(hud, 0, 1);
        this.add(layeredPane, BorderLayout.CENTER);
    }

    private JButton makeButton(int i){
        return new JButton();
    }
    private void resetSizes() {
        btnPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        panel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        hud.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        inventoryPanel.setBounds(layeredPane.getWidth()/10, layeredPane.getHeight()/10, layeredPane.getWidth()-(layeredPane.getWidth()/5), layeredPane.getHeight()-(layeredPane.getHeight()/5));
        double rowPerColumn = (double)layeredPane.getWidth()/(double)layeredPane.getHeight();
        double columnsPerRow = (double)layeredPane.getHeight()/(double)layeredPane.getWidth();
        int cols1 = (int)Math.floor(Math.sqrt(1089/rowPerColumn));
        int rows1 = (int)Math.floor(1089D/(double)cols1);

        int rows2 = (int)Math.floor(Math.sqrt(1089/columnsPerRow));
        int cols2 = (int)Math.floor(1089D/(double)rows2);
        // choosing minimum values to keep more zoomed in.
        if (rows1*cols1>rows2*cols2) {
            cols = cols2;
            rows = rows2;
        } else {
            cols = cols1;
            rows = rows1;
        }
        //cols = Math.min(cols1, cols2);
        //rows = Math.min(rows1, rows2);
        //double rowSize;
        //double colSize;
        if (layeredPane.getWidth()==0 || layeredPane.getHeight()==0) {
            rowSize = 1;
            colSize = 1;
        } else {
            rowSize = layeredPane.getWidth()/(rows-1);
            colSize = layeredPane.getHeight()/(cols-1);
        }
        //MapSquare.initalizeImages();
        for (int index = 0; index<myBtns.length; index++) {
            myBtns[index].setBounds((int)(Math.floor(((double)index)%((double)rows))*colSize), (int)(Math.floor(((double)index)/((double)rows))*rowSize), (int)rowSize+1, (int)colSize+1);
            if (rows==0) {
                rows = 32;
            }
            myBtns[index].setIcon(mapSquares.get((index/rows)+Main.playerPosition.x-(cols/2), (index%rows)+Main.playerPosition.y-(rows/2)).getImage((int)rowSize, (int)rowSize));
        }
        if (!onMenu) {
            resetMovableSquares();
        }

        healthBar.setText(Main.player.health+" health");
        if (Main.player.health<=0) {
            hud.setOpaque(true);
            hud.setBackground(Color.RED);
            hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
        }

        layeredPane.moveToFront(hud);
        layeredPane.moveToFront(panel);
    }

    public void movePlayer(FinalPoint start, FinalPoint end) {

        //final int timing = 1000;
        //final long t = System.currentTimeMillis();
        //long currentTime = System.currentTimeMillis();
        /*while (currentTime<(t+timing)) {
            btnPanel.setLocation((end.x-start.x)*(int)(((double)(currentTime-t+timing))/timing), (end.y-start.y)*(int)(((double)(currentTime-t+timing))/timing));
            currentTime = System.currentTimeMillis();
        }
        btnPanel.setLocation(0, 0);*/

        /*double playerX;// = start.x;
        double playerY;// = start.y;
        double distance = Math.sqrt((start.x*start.x)+(end.y*end.y));
        //System.out.println(distance);
        long t = System.currentTimeMillis();
        long currentTime = System.currentTimeMillis();
        //System.out.println("got here");
        final int timing = 1000;
        while (currentTime<(t+(distance*timing))) {
            //System.out.println("animating");
            playerX = ((end.x-start.x)*((currentTime-t)/(distance*timing)))+start.x;
            playerY = ((end.y-start.y)*((currentTime-t)/(distance*timing)))+start.y;
            //System.out.println(playerX+", "+playerY);
            //boolean changingPos = false;
            boolean hasChanged = false;
            if (end.x>start.x) {
                while (playerX > Main.playerPosition.x) {
                    Main.playerPosition.x++;
                    hasChanged = true;
                }
                //for (int i = rows; i > 0; i--) {
                    //if (i * cols > myBtns.length) break;
                    //for (int index = 0; index < myBtns.length; index++) {
                        //myBtns[index].setIcon(mapSquares.get((index / rows) + Main.playerPosition.x - (rows / 2), (index % rows) + Main.playerPosition.y - (cols / 2)).getImage((int) rowSize, (int) rowSize));
                        //addButtonListeners();
                    //}
                //}
            } else {
                while (playerX < Main.playerPosition.x) {
                    Main.playerPosition.x--;
                    hasChanged = true;
                }
                //for (int i = 0; i < rows; i++) {
                    //if (i * cols > myBtns.length) break;
                    //for (int index = 0; index < myBtns.length; index++) {
                        //myBtns[index].setIcon(mapSquares.get((index / rows) + Main.playerPosition.x - (rows / 2), (index % rows) + Main.playerPosition.y - (cols / 2)).getImage((int) rowSize, (int) rowSize));
                        //addButtonListeners();
                    //}
                //}
            }
            if (end.y>start.y) {
                while (playerY > Main.playerPosition.y) {
                    Main.playerPosition.y++;
                    hasChanged = true;
                }
                //for (int i = cols; i > 0; i--) {
                    //if (i * rows > myBtns.length) break;
                    //for (int index = 0; index < myBtns.length; index++) {
                        //myBtns[index].setIcon(mapSquares.get((index / rows) + Main.playerPosition.x - (rows / 2), (index % rows) + Main.playerPosition.y - (cols / 2)).getImage((int) rowSize, (int) rowSize));
                        //addButtonListeners();
                    //}
                //}
            } else {
                while (playerY < Main.playerPosition.y) {
                    Main.playerPosition.y--;
                    hasChanged = true;
                }
                //for (int i = 0; i < cols; i++) {
                    //if (i * rows > myBtns.length) break;
                    //for (int index = 0; index < myBtns.length; index++) {
                        //myBtns[index].setIcon(mapSquares.get((index / rows) + Main.playerPosition.x - (rows / 2), (index % rows) + Main.playerPosition.y - (cols / 2)).getImage((int) rowSize, (int) rowSize));
                        //addButtonListeners();
                    //}
                //}
            }
            if (hasChanged) {
                for (int index = 0; index<myBtns.length; index++) {
                    myBtns[index].setIcon(mapSquares.get((index/rows)+Main.playerPosition.x-(rows/2), (index%rows)+Main.playerPosition.y-cols/2).getImage((int)rowSize, (int)rowSize));
                }
                addButtonListeners();
            }


            currentTime = System.currentTimeMillis();
        }
        System.out.println("done");
        playerX = ((start.x-end.x)*((currentTime-t)/(distance*1000)))+start.x;
        playerY = ((start.y-end.y)*((currentTime-t)/(distance*1000)))+start.y;*/
        Main.playerPosition.x = end.x;
        Main.playerPosition.y = end.y;
        //Main.player.squareOn = mapSquares.get(end.x, end.y);
        Main.player.move(mapSquares.get(end.x, end.y));
        //mapSquares.get(end.x, end.y).occupant = Main.player;
        //mapSquares.get(end.x, end.y).setActor(Main.player);
        //mapSquares.get(start.x, start.y).occupant = null;
        //mapSquares.get(start.x, start.y).setActor(null);

        /*for (int index = 0; index<myBtns.length; index++) {
            int finalIndex = index;
            myBtns[index].setIcon(mapSquares.get((index/rows)+Main.playerPosition.x-(rows/2), (index%rows)+Main.playerPosition.y-cols/2).getImage((int)rowSize, (int)rowSize));
            myBtns[index].getActionListeners()[0] = e -> {
                System.out.println((finalIndex/rows)+Main.playerPosition.x-(rows/2) +", "+((finalIndex%rows)+Main.playerPosition.y-(cols/2))+", "+rows);
                System.out.println(mapSquares.get((finalIndex/rows)+Main.playerPosition.x-(rows/2), (finalIndex%rows)+Main.playerPosition.x-(cols/2)).lowerMapSquare.name);
                System.out.println(Main.playerPosition.x+", "+Main.playerPosition.y);
                //movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), new FinalPoint((finalIndex/rows)+Main.playerPosition.x-(rows/2), (finalIndex%rows)+Main.playerPosition.x-(cols/2)));
            };
        }*/
        for (int index = 0; index<myBtns.length; index++) {
            //int finalIndex = index;
            myBtns[index].setIcon(mapSquares.get((index/rows)+Main.playerPosition.x-(cols/2), (index%rows)+Main.playerPosition.y-(rows/2)).getImage((int)rowSize, (int)rowSize));
            /*myBtns[index].removeActionListener(actionListener);
            actionListener = new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (((JButton)e.getSource()).getBorder().equals(btnBorder)) {
                        myBtns[finalIndex].addActionListener(actionListener);
                        System.out.println((finalIndex / rows) + Main.playerPosition.x - (cols / 2) + ", " + ((finalIndex % rows) + Main.playerPosition.y - (rows / 2)) + ", " + rows);
                        System.out.println(mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.x - (rows / 2)).lowerMapSquare.name);
                        movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), new FinalPoint((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.x - (rows / 2)));
                        System.out.println(Main.playerPosition.x + ", " + Main.playerPosition.y);
                    }
                }
            };*/
            //myBtns[index].addActionListener(actionListener);
        }
        //addButtonListeners();
        //System.out.println("done");

        //boolean changingPos = false;
        /*if (end.x>start.x) {
            while (playerX > Main.playerPosition.x) {
                Main.playerPosition.x++;
                for (int i = rows; i > 0; i--) {
                    if (i * cols > myBtns.length) break;
                    for (int index = 0; index < myBtns.length; index++) {
                        myBtns[index].setIcon(mapSquares.get((index / rows) + Main.playerPosition.x - (rows / 2), (index % rows) + Main.playerPosition.y - (cols / 2)).getImage((int) rowSize, (int) rowSize));
                        addButtonListeners();
                    }
                }
            }
        } else {
            while (playerX > Main.playerPosition.x) {
                Main.playerPosition.x--;
                for (int i = 0; i < rows; i++) {
                    if (i * cols > myBtns.length) break;
                    for (int index = 0; index < myBtns.length; index++) {
                        myBtns[index].setIcon(mapSquares.get((index / rows) + Main.playerPosition.x - (rows / 2), (index % rows) + Main.playerPosition.y - (cols / 2)).getImage((int) rowSize, (int) rowSize));
                        addButtonListeners();
                    }
                }
            }
        }
        if (end.y>start.y) {
            while (playerY > Main.playerPosition.y) {
                Main.playerPosition.y++;
                for (int i = cols; i > 0; i--) {
                    if (i * rows > myBtns.length) break;
                    for (int index = 0; index < myBtns.length; index++) {
                        myBtns[index].setIcon(mapSquares.get((index / rows) + Main.playerPosition.x - (rows / 2), (index % rows) + Main.playerPosition.y - (cols / 2)).getImage((int) rowSize, (int) rowSize));
                        addButtonListeners();
                    }
                }
            }
        } else {
            while (playerY > Main.playerPosition.y) {
                Main.playerPosition.y--;
                for (int i = 0; i < cols; i++) {
                    if (i * rows > myBtns.length) break;
                    for (int index = 0; index < myBtns.length; index++) {
                        myBtns[index].setIcon(mapSquares.get((index / rows) + Main.playerPosition.x - (rows / 2), (index % rows) + Main.playerPosition.y - (cols / 2)).getImage((int) rowSize, (int) rowSize));
                        addButtonListeners();
                    }
                }
            }
        }*/
    }

    public void getMovableSquares() {
        //resetMovableSquares();
        MapHolder<Boolean, Byte> availableSquares = Main.player.getSquaresToMoveTo();
        /*for (byte x = -6; x<7; x++) {
            String string = "";
            for (byte y = -6; y<7;y++) {
                string += availableSquares.get(x, y)+", ";
            }
            System.out.println(string);
        }*/
        for (byte x = (byte)-63; x<64; x++) {
            for (byte y = (byte)-63; y<64; y++) {
                //if (availableSquares.get(x, y)!=null) {
                    //System.out.println("got one!");
                //}
                if (availableSquares.get(x, y)!=null && availableSquares.get(x, y)) {
                    //System.out.println(x+", "+y);
                    //if (x<rows && y<cols) {
                    int theX = x+(cols/2);
                    int theY = y+(rows/2);
                    if (((theX*rows) + (theY%rows))<myBtns.length && ((theX*rows) + (theY%rows))>=0) {
                        myBtns[(theX * rows) + (theY % rows)].setBorder(btnBorder);
                    }
                    //}
                }
            }
        }
    }
    public void resetMovableSquares() {
        for (JButton btn: myBtns) {
            btn.setBorder(null);
        }
        getMovableSquares();
    }

    public void loadInventory(MapSquare square) {
        inventoryPanel = new JPanel();
        inventoryPanel.setOpaque(false);
        inventoryPanel.setLayout(new GridLayout(3, 9));
        inventoryPanel.setBounds(layeredPane.getWidth()/10, layeredPane.getHeight()/10, layeredPane.getWidth()-(layeredPane.getWidth()/5), layeredPane.getHeight()-(layeredPane.getHeight()/5));

        JButton btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.axe!=null) {
            if (Main.player.inventory.axe.isUseful(square)) {
                btn.addActionListener(e -> {
                    Main.player.inventory.axe.use(square);
                    layeredPane.remove(inventoryPanel);
                    for (Component jBtn: inventoryPanel.getComponents()) {
                        if (jBtn!=null) {
                            if (((JButton) jBtn).getBorder().equals(btnBorder)) {
                                ((JButton) jBtn).setBorder(null);
                            }
                        }
                    }
                    //layeredPane.moveToFront(btnPanel);
                    resetSizes();
                    Main.tickActors();
                });
                btn.setBorder(btnBorder);
            }
            btn.setIcon(new ImageIcon(Main.player.inventory.axe.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);

        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.meleeWeapon!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.meleeWeapon.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.rangedWeapon!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.rangedWeapon.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.armor[0]!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.armor[0].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.armor[1]!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.armor[1].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.armor[2]!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.armor[2].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.armor[3]!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.armor[3].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.pickaxe!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.pickaxe.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);


        for (int index = 11; index < 27; index++) {
            if (index == 17) {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                BufferedImage image = new BufferedImage(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, BufferedImage.TYPE_INT_ARGB);//(BufferedImage)((Image)Main.player.inventory.storage[index-11].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST));
                Graphics2D graphics = (Graphics2D) image.getGraphics();
                graphics.setColor(Color.BLACK);
                try {
                    graphics.drawImage(ImageIO.read(new File("img/coin.png")), 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 3, null);
                } catch (Exception ignored) {}
                graphics.drawString(String.valueOf(Main.player.inventory.coins), (inventoryPanel.getWidth()/9)-((inventoryPanel.getWidth()/9)/2), (inventoryPanel.getHeight()/3)-((inventoryPanel.getHeight()/3)/5));

                graphics.dispose();
                btn.setIcon(new ImageIcon(image));
            } else if (index == 18) {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                if (Main.player.inventory.backpack!=null) {
                    btn.setIcon(new ImageIcon(Main.player.inventory.backpack.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
                }
            } else if (index == 19) {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                btn.setVisible(false);
            } else if (index == 26) {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                btn.addActionListener(e -> {
                    layeredPane.remove(inventoryPanel);
                    for (Component jBtn: inventoryPanel.getComponents()) {
                        if (jBtn!=null) {
                            if (((JButton) jBtn).getBorder().equals(btnBorder)) {
                                ((JButton) jBtn).setBorder(null);
                            }
                        }
                    }
                    layeredPane.moveToFront(btnPanel);
                    layeredPane.moveToFront(hud);
                    healthBar.setText(Main.player.health+" health");
                    if (Main.player.health<=0) {
                        hud.setOpaque(true);
                        hud.setBackground(Color.RED);
                        hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
                    }
                    Main.tickActors();
                });
                try {
                    btn.setIcon(new ImageIcon(ImageIO.read(new File("img/bigX.png")).getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
                    } catch (Exception e) {
                    System.out.println(e);
                    }
            } else if (index < 16) {
                /*if (Main.player.inventory.storage!=null) {
                    if (Main.player.inventory.storage[index-11]!=null) {
                        btn = new JButton(String.valueOf(Main.player.inventory.storage[index - 11].count));
                    }
                } else {
                    btn = new JButton();
                }*/
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                if (Main.player.inventory.storage!=null) {
                    if (Main.player.inventory.storage[index-11]!=null) {
                        BufferedImage image = new BufferedImage(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, BufferedImage.TYPE_INT_ARGB);//(BufferedImage)((Image)Main.player.inventory.storage[index-11].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST));
                        Graphics2D graphics = (Graphics2D) image.getGraphics();
                        graphics.setColor(Color.BLACK);
                        if (Main.player.inventory.storage[index-11]!=null) {
                            graphics.drawImage(Main.player.inventory.storage[index - 11].getImage(), 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 3, null);
                            graphics.drawString(String.valueOf(Main.player.inventory.storage[index - 11].count), (inventoryPanel.getWidth() / 9) - ((inventoryPanel.getWidth() / 9) / 5), (inventoryPanel.getHeight() / 3) - ((inventoryPanel.getHeight() / 3) / 5));
                        }

                        graphics.dispose();
                        btn.setIcon(new ImageIcon(image));
                    }
                }
            } else {
                /*if (Main.player.inventory.storage!=null) {
                    if (Main.player.inventory.storage[index-14]!=null) {
                        btn = new JButton(String.valueOf(Main.player.inventory.storage[index - 14].count));
                    }
                } else {
                    btn = new JButton();
                }*/
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                //btn.setVerticalAlignment(SwingConstants.BOTTOM);
                if (Main.player.inventory.storage!=null) {
                    if (Main.player.inventory.storage[index-14]!=null) {
                        //BufferedImage image = (BufferedImage)((Image)Main.player.inventory.storage[index-14].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST));
                        //image.getGraphics().drawString(String.valueOf(Main.player.inventory.storage[index-14].count), btn.getWidth()-(btn.getWidth()/5), btn.getHeight()-(btn.getHeight()/5));
                        //btn.setIcon(new ImageIcon(image));
                        //btn.setIcon(new ImageIcon(Main.player.inventory.storage[index-14].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST)));
                        BufferedImage image = new BufferedImage(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, BufferedImage.TYPE_INT_ARGB);//(BufferedImage)((Image)Main.player.inventory.storage[index-11].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST));
                        Graphics2D graphics = (Graphics2D) image.getGraphics();
                        graphics.setColor(Color.BLACK);
                        if (Main.player.inventory.storage[index-11]!=null) {
                            graphics.drawImage(Main.player.inventory.storage[index - 11].getImage(), 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 3, null);
                            graphics.drawString(String.valueOf(Main.player.inventory.storage[index-11].count), (inventoryPanel.getWidth()/9)-((inventoryPanel.getWidth()/9)/5), (inventoryPanel.getHeight()/3)-((inventoryPanel.getHeight()/3)/5));
                        }
                        //graphics.drawString(String.valueOf(Main.player.inventory.storage[index-11].count), (inventoryPanel.getWidth()/9)-((inventoryPanel.getWidth()/9)/5), (inventoryPanel.getHeight()/3)-((inventoryPanel.getHeight()/3)/5));

                        graphics.dispose();
                        btn.setIcon(new ImageIcon(image));
                    }
                }
            }

            //btn.setIcon((Icon)Main.player.inventory.storage[index-12].getImage());
            //try {
                //btn.setIcon(new ImageIcon(ImageIO.read(new File("img/bigX.png"))));
            //} catch (Exception e) {
                //System.out.println(e);
            //}
        }
        /*if (Main.player.inventory.storage!=null) {
            for (int index = 10; index < 24; index++) {
                if (index == 16 || index == 17) continue;
                if (index < 16) {
                    if (Main.player.inventory.storage[index-10]!=null) {
                        JButton btn = new JButton();
                        btn.setIcon((Icon)Main.player.inventory.storage[index-10].getImage());
                        inventoryPanel.add(btn, index);
                    }
                } else {
                    if (Main.player.inventory.storage[index-12]!=null) {
                        JButton btn = new JButton();
                        btn.setIcon((Icon)Main.player.inventory.storage[index-12].getImage());
                        inventoryPanel.add(btn, index);
                    }
                }
            }
        }*/
    }

    public void loadInventoryTrades(MapSquare square) {
        inventoryPanel = new JPanel();
        inventoryPanel.setOpaque(false);
        inventoryPanel.setLayout(new GridLayout(5, 9));
        inventoryPanel.setBounds(layeredPane.getWidth()/10, layeredPane.getHeight()/10, layeredPane.getWidth()-(layeredPane.getWidth()/5), layeredPane.getHeight()-(layeredPane.getHeight()/5));

        JButton btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);


        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        BufferedImage myImage = new BufferedImage(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, BufferedImage.TYPE_INT_ARGB);//(BufferedImage)((Image)Main.player.inventory.storage[index-11].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST));
        Graphics2D myGraphics = (Graphics2D) myImage.getGraphics();
        myGraphics.setColor(Color.BLACK);
        myGraphics.drawImage(((Villager)square.occupant).itemToSell.getImage(), 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 8, null);
        myGraphics.drawString(((Villager)square.occupant).cost + " coins", (int)((inventoryPanel.getWidth()/9)-((inventoryPanel.getWidth()/9)/1.5)), (inventoryPanel.getHeight()/5)-((inventoryPanel.getHeight()/5)/5));

        myGraphics.dispose();
        btn.setIcon(new ImageIcon(myImage));
        //System.out.println(Main.player.inventory.axe + ", " + Main.player.inventory.coins + ", " + ((Villager)square.occupant).cost);
        if (Main.player.inventory.axe==null && Main.player.inventory.coins>=((Villager)square.occupant).cost) {
            btn.setBorder(btnBorder);
            btn.addActionListener(e -> {
                //System.out.println("buying");
                Main.player.inventory.coins-=((Villager)square.occupant).cost;
                Main.player.inventory.axe = new Axe();
                layeredPane.remove(inventoryPanel);
                loadInventoryTrades(square);
                layeredPane.add(inventoryPanel);
                layeredPane.moveToFront(inventoryPanel);

            });
        }


        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);

        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        myImage = new BufferedImage(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, BufferedImage.TYPE_INT_ARGB);//(BufferedImage)((Image)Main.player.inventory.storage[index-11].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST));
        myGraphics = (Graphics2D) myImage.getGraphics();
        myGraphics.setColor(Color.BLACK);
        //myGraphics.drawImage(((Villager)square.occupant).itemToSell.getImage(), 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 5, null);
        try {
            myGraphics.drawImage(ImageIO.read(new File("img/coin.png")), 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 5, null);
        } catch (Exception ignored) {}
        myGraphics.drawString(1 + " log", (int)((inventoryPanel.getWidth()/9)-((inventoryPanel.getWidth()/9)/1.5)), (inventoryPanel.getHeight()/5)-((inventoryPanel.getHeight()/5)/5));

        myGraphics.dispose();
        btn.setIcon(new ImageIcon(myImage));
        boolean hasWood = false;
        for (int i = 0; i<12; i++) {
            if (Main.player.inventory.storage[i] instanceof Log) {
                if (Main.player.inventory.storage[i].count>0) {
                    hasWood = true;
                    break;
                }
            }
        }
        if (hasWood) {
            btn.setBorder(btnBorder);
            btn.addActionListener(e -> {
                //System.out.println("buying");
                for (int i = 0; i<12; i++) {
                    if (Main.player.inventory.storage[i] instanceof Log) {
                        Main.player.inventory.storage[i].count--;
                        if (Main.player.inventory.storage[i].count==0) {
                            Main.player.inventory.storage[i] = null;
                        }
                        Main.player.inventory.coins++;
                        break;
                    }
                }
                //Main.player.inventory.coins-=((Villager)square.occupant).cost;
                //Main.player.inventory.axe = new Axe();
                layeredPane.remove(inventoryPanel);
                loadInventoryTrades(square);
                layeredPane.add(inventoryPanel);
                layeredPane.moveToFront(inventoryPanel);

            });
        }

        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        myImage = new BufferedImage(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, BufferedImage.TYPE_INT_ARGB);//(BufferedImage)((Image)Main.player.inventory.storage[index-11].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST));
        myGraphics = (Graphics2D) myImage.getGraphics();
        myGraphics.setColor(Color.BLACK);
        myGraphics.drawImage(IronSword.image, 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 8, null);
        myGraphics.drawString(((Villager)square.occupant).swordCost + " coins", (int)((inventoryPanel.getWidth()/9)-((inventoryPanel.getWidth()/9)/1.5)), (inventoryPanel.getHeight()/5)-((inventoryPanel.getHeight()/5)/5));

        myGraphics.dispose();
        btn.setIcon(new ImageIcon(myImage));
        //System.out.println(Main.player.inventory.axe + ", " + Main.player.inventory.coins + ", " + ((Villager)square.occupant).cost);
        if (Main.player.inventory.meleeWeapon==null && Main.player.inventory.coins>=((Villager)square.occupant).swordCost) {
            btn.setBorder(btnBorder);
            btn.addActionListener(e -> {
                //System.out.println("buying");
                Main.player.inventory.coins-=((Villager)square.occupant).swordCost;
                Main.player.inventory.meleeWeapon = new IronSword();
                layeredPane.remove(inventoryPanel);
                loadInventoryTrades(square);
                layeredPane.add(inventoryPanel);
                layeredPane.moveToFront(inventoryPanel);

            });
        }

        for (int i = 0; i<9; i++) {
            btn = new JButton();
            inventoryPanel.add(btn);
            btn.setOpaque(false);
            btn.setVisible(false);
        }


        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.axe!=null) {
            /*if (Main.player.inventory.axe.isUseful(square)) {
                btn.addActionListener(e -> {
                    Main.player.inventory.axe.use(square);
                    layeredPane.remove(inventoryPanel);
                    for (Component jBtn: inventoryPanel.getComponents()) {
                        if (jBtn!=null) {
                            if (((JButton) jBtn).getBorder().equals(btnBorder)) {
                                ((JButton) jBtn).setBorder(null);
                            }
                        }
                    }
                    resetSizes();
                });
                btn.setBorder(btnBorder);
            }*/
            btn.setIcon(new ImageIcon(Main.player.inventory.axe.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);

        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.meleeWeapon!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.meleeWeapon.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.rangedWeapon!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.rangedWeapon.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.armor[0]!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.armor[0].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.armor[1]!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.armor[1].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.armor[2]!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.armor[2].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.armor[3]!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.armor[3].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        if (Main.player.inventory.pickaxe!=null) {
            btn.setIcon(new ImageIcon(Main.player.inventory.pickaxe.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
        }
        btn = new JButton();
        inventoryPanel.add(btn);
        btn.setOpaque(false);
        btn.setVisible(false);


        for (int index = 11; index < 27; index++) {
            if (index == 17) {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                BufferedImage image = new BufferedImage(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, BufferedImage.TYPE_INT_ARGB);//(BufferedImage)((Image)Main.player.inventory.storage[index-11].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST));
                Graphics2D graphics = (Graphics2D) image.getGraphics();
                graphics.setColor(Color.BLACK);
                try {
                    graphics.drawImage(ImageIO.read(new File("img/coin.png")), 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 5, null);
                } catch (Exception ignored) {}
                graphics.drawString(String.valueOf(Main.player.inventory.coins), (inventoryPanel.getWidth()/9)-((inventoryPanel.getWidth()/9)/2), (inventoryPanel.getHeight()/5)-((inventoryPanel.getHeight()/5)/5));

                graphics.dispose();
                btn.setIcon(new ImageIcon(image));
            } else if (index == 18) {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                if (Main.player.inventory.backpack!=null) {
                    btn.setIcon(new ImageIcon(Main.player.inventory.backpack.getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
                }
            } else if (index == 19) {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                btn.setVisible(false);
            } else if (index == 26) {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                btn.addActionListener(e -> {
                    layeredPane.remove(inventoryPanel);
                    for (Component jBtn: inventoryPanel.getComponents()) {
                        if (jBtn!=null) {
                            if (((JButton) jBtn).getBorder().equals(btnBorder)) {
                                ((JButton) jBtn).setBorder(null);
                            }
                        }
                    }
                    layeredPane.moveToFront(btnPanel);
                    layeredPane.moveToFront(hud);
                    healthBar.setText(Main.player.health+" health");
                    if (Main.player.health<=0) {
                        hud.setOpaque(true);
                        hud.setBackground(Color.RED);
                        hud.add(new JLabel("YOU DIED"), BoxLayout.Y_AXIS);
                    }
                    Main.tickActors();
                });
                try {
                    btn.setIcon(new ImageIcon(ImageIO.read(new File("img/bigX.png")).getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, Image.SCALE_FAST)));
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else if (index < 16) {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                if (Main.player.inventory.storage!=null) {
                    if (Main.player.inventory.storage[index-11]!=null) {
                        BufferedImage image = new BufferedImage(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, BufferedImage.TYPE_INT_ARGB);//(BufferedImage)((Image)Main.player.inventory.storage[index-11].getImage().getScaledInstance(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/3, Image.SCALE_FAST));
                        Graphics2D graphics = (Graphics2D) image.getGraphics();
                        graphics.setColor(Color.BLACK);
                        if (Main.player.inventory.storage[index-11]!=null) {
                            graphics.drawImage(Main.player.inventory.storage[index - 11].getImage(), 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 5, null);
                            graphics.drawString(String.valueOf(Main.player.inventory.storage[index - 11].count), (inventoryPanel.getWidth() / 9) - ((inventoryPanel.getWidth() / 9) / 5), (inventoryPanel.getHeight() / 5) - ((inventoryPanel.getHeight() / 5) / 5));
                        }

                        graphics.dispose();
                        btn.setIcon(new ImageIcon(image));
                    }
                }
            } else {
                btn = new JButton();
                inventoryPanel.add(btn);
                btn.setOpaque(false);
                if (Main.player.inventory.storage!=null) {
                    if (Main.player.inventory.storage[index-14]!=null) {
                        BufferedImage image = new BufferedImage(inventoryPanel.getWidth()/9, inventoryPanel.getHeight()/5, BufferedImage.TYPE_INT_ARGB);
                        Graphics2D graphics = (Graphics2D) image.getGraphics();
                        graphics.setColor(Color.BLACK);
                        if (Main.player.inventory.storage[index-11]!=null) {
                            graphics.drawImage(Main.player.inventory.storage[index - 11].getImage(), 0, 0, inventoryPanel.getWidth() / 9, inventoryPanel.getHeight() / 5, null);
                            graphics.drawString(String.valueOf(Main.player.inventory.storage[index - 11].count), (inventoryPanel.getWidth() / 9) - ((inventoryPanel.getWidth() / 9) / 5), (inventoryPanel.getHeight() / 5) - ((inventoryPanel.getHeight() / 5) / 5));
                        }

                        graphics.dispose();
                        btn.setIcon(new ImageIcon(image));
                    }
                }
            }
        }
    }


    public static void createAndShowGui(String name) {
        Gui frame = new Gui(name);



        frame.initComponents();
        MapSquare.initalizeImages();
        frame.addListeners();
        frame.addComponentsToPane();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //frame.getMovableSquares();
    }

}
