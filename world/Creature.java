package world;

import java.awt.Color;
import java.util.concurrent.locks.*;
import java.util.ArrayList;
import java.util.List;

public class Creature extends Thread {
    protected World world;
    protected int x, y, direction;
    private char glyph;
    private Color color;
    protected int hp;
    protected int attack;
    //protected Creature target;
    protected Lock lock;
    
    public Creature(World world, char glyph, Color color) {
        this.world = world;
        this.glyph = glyph;
        this.color = color;
        this.lock = new ReentrantLock();
        this.hp = 100;
    }

    public void run(){}
    public void attack(){
        Node node = next_position();
        //get_target & handle
        //this.lock.lock();
        //try {
            Creature c = this.world.creature(node.x, node.y);
            if (c != null) {
                c.hurt(attack);
            }
        //} finally {this.lock.unlock();}
    }
    public synchronized void hurt(int attack) {
        this.hp -= attack;
    }

    public synchronized void moveBy(int direction) {
        this.direction = direction;
        Node node = next_position();
        if(world.tile(node.x, node.y) == Tile.FLOOR && world.creature(node.x, node.y) == null) {
            x = node.x;
            y = node.y;
        }
    }

    public void setX(int x) {this.x = x;}
    public void setY(int y) {this.y = y;}
    public int x() {return x;}
    public int y() {return y;}
    public char glyph() {return glyph;}
    public Color color() {return color;}
    public Node next_position() {
        switch(direction) {//左右下上
            case 0:
                return new Node(x-1, y);
            case 1:
                return new Node(x+1, y);
            case 2:
                return new Node(x, y-1);
            case 3:
            default:
                return new Node(x, y+1);
        }
    }
}
