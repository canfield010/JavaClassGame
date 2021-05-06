package com.canfield010.mygame.gui;

import com.canfield010.mygame.Main;
import com.canfield010.mygame.MapHolder;
import com.canfield010.mygame.actors.Actor;
import com.canfield010.mygame.mapsquare.FinalPoint;
import com.canfield010.mygame.mapsquare.MapSquare;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

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
    JOptionPane inventoryPane = new JOptionPane();


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
        inventoryPanel.setOpaque(false);
        panel.setOpaque(false);
        panel.setLayout(new GridBagLayout());
        inventoryPanel.setLayout(new GridLayout(3,8));

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
                                }
                            });
                        }
                        if (Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).upperMapSquare!=null) {
                            if (Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).upperMapSquare.buttons!=null) {
                                for (com.canfield010.mygame.mapsquare.uppermapsquare.UpperMapSquare.Button btn : Main.mapSquares.get((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)).upperMapSquare.buttons) {
                                    switch (btn) {
                                        case USE_ITEM -> popupMenu.add(new JMenuItem("Use Item")).addActionListener(event -> {
                                            //if (btnBorder.equals(((JButton) e.getSource()).getBorder())) {
                                                //System.out.println("usingItem");
                                                loadInventory();
                                                //inventoryPanel.setBackground(Color.RED);
                                                layeredPane.add(inventoryPanel);
                                                layeredPane.moveToFront(inventoryPanel);
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
                        }
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    } else {
                        if (btnBorder.equals(((JButton)e.getSource()).getBorder())) {
                            movePlayer(new FinalPoint(Main.playerPosition.x, Main.playerPosition.y), new FinalPoint((finalIndex / rows) + Main.playerPosition.x - (cols / 2), (finalIndex % rows) + Main.playerPosition.y - (rows / 2)));
                            resetMovableSquares();
                        }
                    }
                }
            };
            myBtns[index].addMouseListener(mouseAdapter);
        }
    }

    private void addComponentsToPane() {
        //inventoryPane.add(inventoryPanel);
        buttonPanel.add(playButton);
        buttonPanel.add(settingsButton);
        panel.add(buttonPanel);
        layeredPane.add(btnPanel, 0, 1);
        layeredPane.add(panel, 0, 0);
        this.add(layeredPane, BorderLayout.CENTER);
    }

    private JButton makeButton(int i){
        return new JButton();
    }
    private void resetSizes() {
        btnPanel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        panel.setBounds(0, 0, layeredPane.getWidth(), layeredPane.getHeight());
        inventoryPanel.setBounds(layeredPane.getWidth()/10, layeredPane.getHeight()/10, layeredPane.getWidth()-(layeredPane.getWidth()/10), layeredPane.getHeight()-(layeredPane.getHeight()/10));
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

    public void loadInventory() {
        if (Main.player.inventory.axe!=null) {
            JButton btn = new JButton();
            btn.setIcon(new ImageIcon(Main.player.inventory.axe.getImage()));
            inventoryPanel.add(btn, 0);
        }
        if (Main.player.inventory.pickaxe!=null) {
            JButton btn = new JButton();
            btn.setIcon((Icon)Main.player.inventory.pickaxe.getImage());
            inventoryPanel.add(btn, 8);
        }
        if (Main.player.inventory.backpack!=null) {
            JButton btn = new JButton();
            btn.setIcon((Icon)Main.player.inventory.backpack.getImage());
            inventoryPanel.add(btn, 16);
        }
        if (Main.player.inventory.meleeWeapon!=null) {
            JButton btn = new JButton();
            btn.setIcon((Icon)Main.player.inventory.meleeWeapon.getImage());
            inventoryPanel.add(btn, 2);
        }
        if (Main.player.inventory.rangedWeapon!=null) {
            JButton btn = new JButton();
            btn.setIcon((Icon)Main.player.inventory.rangedWeapon.getImage());
            inventoryPanel.add(btn, 3);
        }
        if (Main.player.inventory.armor[0]!=null) {
            JButton btn = new JButton();
            btn.setIcon((Icon)Main.player.inventory.armor[0].getImage());
            inventoryPanel.add(btn, 4);
        }
        if (Main.player.inventory.armor[1]!=null) {
            JButton btn = new JButton();
            btn.setIcon((Icon)Main.player.inventory.armor[1].getImage());
            inventoryPanel.add(btn, 5);
        }
        if (Main.player.inventory.armor[2]!=null) {
            JButton btn = new JButton();
            btn.setIcon((Icon)Main.player.inventory.armor[2].getImage());
            inventoryPanel.add(btn, 6);
        }
        if (Main.player.inventory.armor[3]!=null) {
            JButton btn = new JButton();
            btn.setIcon((Icon)Main.player.inventory.armor[3].getImage());
            inventoryPanel.add(btn, 7);
        }
        if (Main.player.inventory.storage!=null) {
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
