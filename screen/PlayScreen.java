package screen;

import asciiPanel.AsciiPanel;
import world.Creature;
import world.World;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import main.Timer;

import main.ApplicationMain;

public class PlayScreen implements Screen {
    private int width, height;
    private World world;
    private Timer timer;
    private ApplicationMain app;

    public PlayScreen(int w, int h, ApplicationMain app) {
        this.width = w;
        this.height = h;
        this.timer = new Timer(app);
        this.app = app;
        this.world = new World(width, height, this);
    }

    public void displayOutput(AsciiPanel terminal) {
        //map
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                terminal.write(world.glyph(x, y), x, y, world.color(x, y));
            }
        }

        //creature
        for (Creature c : world.getCreatures()) {
            if (c != null && c.isAlive())
                terminal.write(c.glyph(), c.x(), c.y(), c.color());
        }
    }

    public void respondToUserInput(KeyEvent e) {
        world.respondToUserInput(e);
    }

    public void changeScreen() {
        timer.off();
        app.changeScreen(new EndScreen());
    }
}
