package main;
import javax.swing.JFrame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import asciiPanel.AsciiFont;
import asciiPanel.AsciiPanel;
import main.ApplicationMain;
import screen.PlayScreen;
import screen.Screen;

public class ApplicationMain extends JFrame implements KeyListener{
    private AsciiPanel terminal;
    private Screen screen;
    
    public ApplicationMain() {
        super();
        Timer timer = new Timer(this);
        terminal = new AsciiPanel(60, 60, AsciiFont.TALRYTH_15_15);
        add(terminal);
        pack();
        screen = new PlayScreen(60, 60, this);
        addKeyListener(this);
        repaint();
    }

    @Override
    public void repaint() {
        terminal.clear();
        screen.displayOutput(terminal);
        super.repaint();
    }

    public void keyPressed(KeyEvent e) {
        screen.respondToUserInput(e);
    }
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e) {}

    public static void main(String[] args) {
        ApplicationMain app = new ApplicationMain();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        app.setVisible(true);
    }

    public void changeScreen(Screen screen) {
        this.screen = screen;
    }
}