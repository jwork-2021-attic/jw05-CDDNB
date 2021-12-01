package world;

import java.awt.event.KeyEvent;
import java.awt.Color;

public class Player extends Creature{
    private int cheat_rest;
    public Player(World world, char glyph, Color color, int hp, int attack) {
        super(world, glyph, color);
        this.hp = hp;
        this.attack = attack;
        this.cheat_rest = 9999;
        this.start();
    }

    public void respondToUserInput(int keyCode) {
        if(this.isAlive()) {
        switch(keyCode) {
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_A:
                this.moveBy(0);
                break;
            case KeyEvent.VK_RIGHT:
            case KeyEvent.VK_D:
                this.moveBy(1);
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_W:
                this.moveBy(2);
                break;
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_S:
                this.moveBy(3);
                break;
            case KeyEvent.VK_G:
            case KeyEvent.VK_MINUS:
                this.attack();
                break;
        }
    }
    }

    @Override
    public void run() {
        while(hp > 0) {
            try {
            Thread.sleep(100);
            }catch(Exception e) {}
        }
        this.world.endGame();
        this.setX(-1);
        this.setY(-1);
    }

    public void attack() {
        super.attack();
        if (this.cheat_rest > 0) {
            Node node = next_position();
            this.world.ruin(node.x, node.y);
            cheat_rest--;
        }
    }
    /*@Override
    public void attack() {
        int dx = x, dy = y;
        switch(direction) {
            case 0:
                dx += -1;
                break;
            case 1:
                dx += 1;
                break;
            case 2:
                dy += -1;
                break;
            case 3:
                dy += 1;
                break;
        }
        //get_target & handle
        List<Creature> targets_c = new ArrayList<>();//for futher aoe
        List<Tile> targets_t = new ArrayList<>();
        this.lock.lock();
        try {
            Creature t = this.world.creature(dx, dy);
            Tile tt = this.world.tile(dx, dy);
            if (t != null) {
                targets_c.add(t);
            }
            else if (tt == Tile.WALL) {
                targets_t.add(tt);
            }
        
            for (Creature c : targets_c) {
                c.hurt(this.attack);
            }
            for (Tile tile : targets_t) {
                tile = Tile.FLOOR;
            }
        } finally {this.lock.unlock();}
    }*/
}
