import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import greenfoot.gui.input.KeyboardManager;
import javafx.scene.input.KeyCode;

public class GF_KeyListener implements KeyListener {
    private static GF_KeyListener instance = null;
    private static KeyboardManager gfKeyboardManager = null;

    public GF_KeyListener(KeyboardManager keyMan) {
        gfKeyboardManager = keyMan;
    }

    public static GF_KeyListener getInstance() {
        if (instance == null) {
            instance = new GF_KeyListener(gfKeyboardManager);
        }
        return instance;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        KeyCode keyCode = GF_KeyCodeMapper.mapAWTKeyCodeToFX(e.getKeyCode());
        if (keyCode != null) {
            gfKeyboardManager.keyTyped(keyCode, keyCode.getName());
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        KeyCode keyCode = GF_KeyCodeMapper.mapAWTKeyCodeToFX(e.getKeyCode());
        if (keyCode != null) {
            gfKeyboardManager.keyPressed(keyCode, keyCode.getName());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        KeyCode keyCode = GF_KeyCodeMapper.mapAWTKeyCodeToFX(e.getKeyCode());
        if (keyCode != null) {
            gfKeyboardManager.keyReleased(keyCode, keyCode.getName());
        }
    }
}
