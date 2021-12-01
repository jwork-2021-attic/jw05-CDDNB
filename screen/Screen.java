package screen;

import asciiPanel.AsciiPanel;
import java.awt.event.KeyEvent;
//import com.apple.eawt.Application;

public interface Screen {
    public void displayOutput(AsciiPanel terminal);

    public void respondToUserInput(KeyEvent e);

    public void changeScreen();
}
