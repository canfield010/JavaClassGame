package com.canfield010.mygame;

import com.canfield010.mygame.mapsquare.MapSquare;
import com.canfield010.mygame.mapsquare.lowermapsquare.Grass;

import java.awt.*;

public class MapHolder<T, E extends Number> {
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

    public void set(E x, E y, T square) {// throws WrongClassException {
        String myClassName = x.getClass().getName();
        if (myClassName.equals(int.class.getName())) {
            setSquare((int)x, (int)y, square);
        } else if (myClassName.equals(short.class.getName())) {
            setSquare((short)x, (short)y, square);
        } else if (myClassName.equals(byte.class.getName())) {
            setSquare((byte)x, (byte)y, square);
        } else {
            System.err.println("Can't get map square because wrong type was used to create the MapHolder.");
            //throw new WrongClassException();
        }
    }

    public T get(E x, E y) {// throws WrongClassException {
        // first bit acts as a sign indicator and a 1 means it's negative. First bit needs to be negated.
        //boolean firstTime = true;

        //long topLong = 0xF000_0000_0000_0000L;
        //int topInt = 0xF000_0000;
        //short topShort = (short)0xF000;
        //byte topByte = (byte)0xF0;

        String myClassName = x.getClass().getName();
        //Class<?> myClass = x.getClass();
        if (myClassName.equals(long.class.getName())) {
            //return getSquare((long)x, (long)y);
            return getSquare((long)x, (long)y, 0xF000_0000_0000_0000L, true, 64);
        } else if (myClassName.equals(int.class.getName())) {
            //return getSquare((int)x, (int)y);
            return getSquare((long)x, (long)y, 0xF000_0000L, false, 32);
        } else if (myClassName.equals(short.class.getName())) {
            //return getSquare((short)x, (short)y);
            return getSquare((long)x, (long)y, 0xF000L, false, 16);
        } else if (myClassName.equals(byte.class.getName())) {
            //return getSquare((byte)x, (byte)y);
            return getSquare((long)x, (long)y, 0xF0L, false, 8);
        } else {
            System.err.println("Can't get map square because wrong type was used to create the MapHolder.");
            return null;
            //throw new WrongClassException();
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

    private T getSquare(int x, int y) {
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
        //if (currentNode.square==null) {
            //currentNode.square = new MapSquare(null,null,null, new Point(x, y));
            //generateSquare(currentNode.square);
        //}
        return currentNode.square;
    }
    private T getSquare(long x, long y, long selector, boolean firstTime, int shiftCount) {
        Node currentNode = head;
        for (int i = shiftCount; i>0; i--) {
            if ((x&(1L<<selector))==0) {
                if ((y&(1L<<selector))==0) {
                    currentNode = firstTime ? currentNode.nextUpRight:currentNode.nextDownLeft;
                } else {
                    currentNode = firstTime ? currentNode.nextDownRight:currentNode.nextDownLeft;
                }

            } else {
                if ((y&(1L<<selector))==0) {
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
        //if (currentNode.square==null) {
        //currentNode.square = new MapSquare(null,null,null, new Point(x, y));
        //generateSquare(currentNode.square);
        //}
        return currentNode.square;
    }
    private T getSquare(short x, short y) {
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
        //if (currentNode.square==null) {
            //currentNode.square = new MapSquare(null,null,null, new Point(x, y));
            //generateSquare(currentNode.square);
        //}
        return currentNode.square;
    }
    private T getSquare(byte x, byte y) {
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
        //if (currentNode.square==null) {
            //currentNode.square = new MapSquare(null,null,null, new Point(x, y));
            //generateSquare(currentNode.square);
        //}
        return currentNode.square;
    }

    private void setSquare(int x, int y, T square) {
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
        currentNode.square = square;
    }
    private void setSquare(short x, short y, T square) {
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
        currentNode.square = square;
    }
    private void setSquare(byte x, byte y, T square) {
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
        currentNode.square = square;
    }


    //public void generateSquare(MapSquare square) {
        //square.lowerMapSquare = new Grass();
    //}

    private static int sizeof(Class dataType) {
        if (dataType == null) return 4;

        if (dataType == int.class    || dataType == Integer.class)   return 4;
        if (dataType == short.class  || dataType == Short.class)     return 2;
        if (dataType == byte.class   || dataType == Byte.class)      return 1;
        if (dataType == char.class   || dataType == Character.class) return 2;
        if (dataType == long.class   || dataType == Long.class)      return 8;
        if (dataType == float.class  || dataType == Float.class)     return 4;
        if (dataType == double.class || dataType == Double.class)    return 8;

        return 4; // 32-bit memory pointer...
        // (I'm not sure how this works on a 64-bit OS)
    }

    private class Node {
        Node nextUpRight = null;
        Node nextUpLeft = null;
        Node nextDownRight = null;
        Node nextDownLeft = null;
        T square = null;
    }
}
