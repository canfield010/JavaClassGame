package com.canfield010.mygame;

import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.lowermapsquare.Grass;

import java.awt.*;

public class MapHolder<E extends Number> {
    Node head = new Node();

    /*public MapHolder() {
        E instance = new E();
        String myClassName = instance.getClass().getName();

        if (myClassName.equals(long.class.getName())) {
            currentNode = getNode((long)x, (long)y);
        } else if (myClassName.equals(int.class.getName())) {
            currentNode = getNode((int)x, (int)y);
        } else if (myClassName.equals(short.class.getName())) {
            currentNode = getNode((short)x, (short)y);
        } else if (myClassName.equals(byte.class.getName())) {
            currentNode = getNode((byte)x, (byte)y);
        } else {
            System.err.println("Fatal Error -- can't get map square because wrong type was used to create the MapHolder.");
        }
    }*/

    public MapSquare get(E x, E y) throws WrongClassException {
        // first bit acts as a sign indicator and a 1 means it's negative. First bit needs to be negated.
        //boolean firstTime = true;

        //long topLong = 0xF000_0000_0000_0000L;
        //int topInt = 0xF000_0000;
        //short topShort = (short)0xF000;
        //byte topByte = (byte)0xF0;

        String myClassName = x.getClass().getName();
        //Class<?> myClass = x.getClass();
        /*if (myClassName.equals(long.class.getName())) {
            return getSquare((long)x, (long)y);
        } else */if (myClassName.equals(int.class.getName())) {
            return getSquare((int)x, (int)y);
        } else if (myClassName.equals(short.class.getName())) {
            return getSquare((short)x, (short)y);
        } else if (myClassName.equals(byte.class.getName())) {
            return getSquare((byte)x, (byte)y);
        } else {
            System.err.println("Fatal Error -- can't get map square because wrong type was used to create the MapHolder.");
            throw new WrongClassException();
        }

        /*for (int i = 32; i>0; i--) {
            if (((myClass)x&0xF000_0000)==0) {
                if ((y&0xF000_0000)==0) {
                    currentNode = firstTime ? currentNode.nextUpRight:currentNode.nextDownLeft;
                } else {
                    currentNode = firstTime ? currentNode.nextDownRight:currentNode.nextDownLeft;
                }

            } else {
                if (((int)y&0xF000_0000)==0) {
                    currentNode = firstTime ? currentNode.nextUpLeft:currentNode.nextDownRight;
                } else {
                    currentNode = firstTime ? currentNode.nextDownLeft:currentNode.nextDownRight;
                }
            }
            x<<=1;
            y<<=1;
            firstTime = false;
        }*/

        // unforunately, I have no idea how to simplify this, though I really want to.
    }


    /*public MapSquare getSquare(long x, long y) {
        Node currentNode = head;
        boolean firstTime = true;
        for (int i = 64; i>0; i--) {
            if ((x&0xF000_0000_0000_0000L)==0) {
                if ((y&0xF000_0000_0000_0000L)==0) {
                    currentNode = firstTime ? currentNode.nextUpRight:currentNode.nextDownLeft;
                } else {
                    currentNode = firstTime ? currentNode.nextDownRight:currentNode.nextDownLeft;
                }

            } else {
                if ((y&0xF000_0000_0000_0000L)==0) {
                    currentNode = firstTime ? currentNode.nextUpLeft:currentNode.nextDownRight;
                } else {
                    currentNode = firstTime ? currentNode.nextDownLeft:currentNode.nextDownRight;
                }
            }
            x<<=1;
            y<<=1;
            firstTime = false;
        }
        if (currentNode.square==null) {
            currentNode.square = new MapSquare(null,null,null, new Point((int)x, (int)y));
            generateSquare(currentNode.square);
        }
        return currentNode.square;
    }*/
    public MapSquare getSquare(int x, int y) {
        Node currentNode = head;
        boolean firstTime = true;
        for (int i = 32; i>0; i--) {
            if ((x&0xF000_0000)==0) {
                if ((y&0xF000_0000)==0) {
                    currentNode = firstTime ? currentNode.nextUpRight:currentNode.nextDownLeft;
                } else {
                    currentNode = firstTime ? currentNode.nextDownRight:currentNode.nextDownLeft;
                }

            } else {
                if ((y&0xF000_0000)==0) {
                    currentNode = firstTime ? currentNode.nextUpLeft:currentNode.nextDownRight;
                } else {
                    currentNode = firstTime ? currentNode.nextDownLeft:currentNode.nextDownRight;
                }
            }
            x<<=1;
            y<<=1;
            firstTime = false;
        }
        if (currentNode.square==null) {

        }
        if (currentNode.square==null) {
            currentNode.square = new MapSquare(null,null,null, new Point(x, y));
            generateSquare(currentNode.square);
        }
        return currentNode.square;
    }
    public MapSquare getSquare(short x, short y) {
        Node currentNode = head;
        boolean firstTime = true;
        for (int i = 32; i>0; i--) {
            if ((x&(short)0xF000)==0) {
                if ((y&(short)0xF000)==0) {
                    currentNode = firstTime ? currentNode.nextUpRight:currentNode.nextDownLeft;
                } else {
                    currentNode = firstTime ? currentNode.nextDownRight:currentNode.nextDownLeft;
                }

            } else {
                if ((y&(short)0xF000)==0) {
                    currentNode = firstTime ? currentNode.nextUpLeft:currentNode.nextDownRight;
                } else {
                    currentNode = firstTime ? currentNode.nextDownLeft:currentNode.nextDownRight;
                }
            }
            x<<=1;
            y<<=1;
            firstTime = false;
        }
        if (currentNode.square==null) {
            currentNode.square = new MapSquare(null,null,null, new Point(x, y));
            generateSquare(currentNode.square);
        }
        return currentNode.square;
    }
    public MapSquare getSquare(byte x, byte y) {
        Node currentNode = head;
        boolean firstTime = true;
        for (int i = 32; i>0; i--) {
            if ((x&(byte)0xF0)==0) {
                if ((y&(byte)0xF0)==0) {
                    currentNode = firstTime ? currentNode.nextUpRight:currentNode.nextDownLeft;
                } else {
                    currentNode = firstTime ? currentNode.nextDownRight:currentNode.nextDownLeft;
                }

            } else {
                if ((y&(byte)0xF0)==0) {
                    currentNode = firstTime ? currentNode.nextUpLeft:currentNode.nextDownRight;
                } else {
                    currentNode = firstTime ? currentNode.nextDownLeft:currentNode.nextDownRight;
                }
            }
            x<<=1;
            y<<=1;
            firstTime = false;
        }
        if (currentNode.square==null) {
            currentNode.square = new MapSquare(null,null,null, new Point(x, y));
            generateSquare(currentNode.square);
        }
        return currentNode.square;
    }


    public void generateSquare(MapSquare square) {
        square.lowerMapSquare = new Grass();
    }


    private class Node {
        Node nextUpRight = null;
        Node nextUpLeft = null;
        Node nextDownRight = null;
        Node nextDownLeft = null;
        MapSquare square = null;
    }
}
