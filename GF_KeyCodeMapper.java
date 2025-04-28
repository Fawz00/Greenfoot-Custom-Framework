import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.input.KeyCode;

public class GF_KeyCodeMapper {

    private static final Map<Integer, KeyCode> keyMap = new HashMap<>();

    static {
        // Huruf A-Z
        keyMap.put(KeyEvent.VK_A, KeyCode.A);
        keyMap.put(KeyEvent.VK_B, KeyCode.B);
        keyMap.put(KeyEvent.VK_C, KeyCode.C);
        keyMap.put(KeyEvent.VK_D, KeyCode.D);
        keyMap.put(KeyEvent.VK_E, KeyCode.E);
        keyMap.put(KeyEvent.VK_F, KeyCode.F);
        keyMap.put(KeyEvent.VK_G, KeyCode.G);
        keyMap.put(KeyEvent.VK_H, KeyCode.H);
        keyMap.put(KeyEvent.VK_I, KeyCode.I);
        keyMap.put(KeyEvent.VK_J, KeyCode.J);
        keyMap.put(KeyEvent.VK_K, KeyCode.K);
        keyMap.put(KeyEvent.VK_L, KeyCode.L);
        keyMap.put(KeyEvent.VK_M, KeyCode.M);
        keyMap.put(KeyEvent.VK_N, KeyCode.N);
        keyMap.put(KeyEvent.VK_O, KeyCode.O);
        keyMap.put(KeyEvent.VK_P, KeyCode.P);
        keyMap.put(KeyEvent.VK_Q, KeyCode.Q);
        keyMap.put(KeyEvent.VK_R, KeyCode.R);
        keyMap.put(KeyEvent.VK_S, KeyCode.S);
        keyMap.put(KeyEvent.VK_T, KeyCode.T);
        keyMap.put(KeyEvent.VK_U, KeyCode.U);
        keyMap.put(KeyEvent.VK_V, KeyCode.V);
        keyMap.put(KeyEvent.VK_W, KeyCode.W);
        keyMap.put(KeyEvent.VK_X, KeyCode.X);
        keyMap.put(KeyEvent.VK_Y, KeyCode.Y);
        keyMap.put(KeyEvent.VK_Z, KeyCode.Z);

        // Angka 0-9
        keyMap.put(KeyEvent.VK_0, KeyCode.DIGIT0);
        keyMap.put(KeyEvent.VK_1, KeyCode.DIGIT1);
        keyMap.put(KeyEvent.VK_2, KeyCode.DIGIT2);
        keyMap.put(KeyEvent.VK_3, KeyCode.DIGIT3);
        keyMap.put(KeyEvent.VK_4, KeyCode.DIGIT4);
        keyMap.put(KeyEvent.VK_5, KeyCode.DIGIT5);
        keyMap.put(KeyEvent.VK_6, KeyCode.DIGIT6);
        keyMap.put(KeyEvent.VK_7, KeyCode.DIGIT7);
        keyMap.put(KeyEvent.VK_8, KeyCode.DIGIT8);
        keyMap.put(KeyEvent.VK_9, KeyCode.DIGIT9);

        // Tombol umum
        keyMap.put(KeyEvent.VK_ENTER, KeyCode.ENTER);
        keyMap.put(KeyEvent.VK_ESCAPE, KeyCode.ESCAPE);
        keyMap.put(KeyEvent.VK_SPACE, KeyCode.SPACE);
        keyMap.put(KeyEvent.VK_SHIFT, KeyCode.SHIFT);
        keyMap.put(KeyEvent.VK_CONTROL, KeyCode.CONTROL);
        keyMap.put(KeyEvent.VK_ALT, KeyCode.ALT);
        keyMap.put(KeyEvent.VK_BACK_SPACE, KeyCode.BACK_SPACE);
        keyMap.put(KeyEvent.VK_TAB, KeyCode.TAB);
        keyMap.put(KeyEvent.VK_UP, KeyCode.UP);
        keyMap.put(KeyEvent.VK_DOWN, KeyCode.DOWN);
        keyMap.put(KeyEvent.VK_LEFT, KeyCode.LEFT);
        keyMap.put(KeyEvent.VK_RIGHT, KeyCode.RIGHT);

        // Function keys (F1 - F12)
        keyMap.put(KeyEvent.VK_F1, KeyCode.F1);
        keyMap.put(KeyEvent.VK_F2, KeyCode.F2);
        keyMap.put(KeyEvent.VK_F3, KeyCode.F3);
        keyMap.put(KeyEvent.VK_F4, KeyCode.F4);
        keyMap.put(KeyEvent.VK_F5, KeyCode.F5);
        keyMap.put(KeyEvent.VK_F6, KeyCode.F6);
        keyMap.put(KeyEvent.VK_F7, KeyCode.F7);
        keyMap.put(KeyEvent.VK_F8, KeyCode.F8);
        keyMap.put(KeyEvent.VK_F9, KeyCode.F9);
        keyMap.put(KeyEvent.VK_F10, KeyCode.F10);
        keyMap.put(KeyEvent.VK_F11, KeyCode.F11);
        keyMap.put(KeyEvent.VK_F12, KeyCode.F12);

        // Numpad keys
        keyMap.put(KeyEvent.VK_NUMPAD0, KeyCode.NUMPAD0);
        keyMap.put(KeyEvent.VK_NUMPAD1, KeyCode.NUMPAD1);
        keyMap.put(KeyEvent.VK_NUMPAD2, KeyCode.NUMPAD2);
        keyMap.put(KeyEvent.VK_NUMPAD3, KeyCode.NUMPAD3);
        keyMap.put(KeyEvent.VK_NUMPAD4, KeyCode.NUMPAD4);
        keyMap.put(KeyEvent.VK_NUMPAD5, KeyCode.NUMPAD5);
        keyMap.put(KeyEvent.VK_NUMPAD6, KeyCode.NUMPAD6);
        keyMap.put(KeyEvent.VK_NUMPAD7, KeyCode.NUMPAD7);
        keyMap.put(KeyEvent.VK_NUMPAD8, KeyCode.NUMPAD8);
        keyMap.put(KeyEvent.VK_NUMPAD9, KeyCode.NUMPAD9);
        keyMap.put(KeyEvent.VK_ADD, KeyCode.ADD);
        keyMap.put(KeyEvent.VK_SUBTRACT, KeyCode.SUBTRACT);
        keyMap.put(KeyEvent.VK_MULTIPLY, KeyCode.MULTIPLY);
        keyMap.put(KeyEvent.VK_DIVIDE, KeyCode.DIVIDE);
        keyMap.put(KeyEvent.VK_DECIMAL, KeyCode.DECIMAL);
        keyMap.put(KeyEvent.VK_NUM_LOCK, KeyCode.NUM_LOCK);
        keyMap.put(KeyEvent.VK_COMMA, KeyCode.COMMA);
        keyMap.put(KeyEvent.VK_PERIOD, KeyCode.PERIOD);

        // Simbol umum
        keyMap.put(KeyEvent.VK_MINUS, KeyCode.MINUS);
        keyMap.put(KeyEvent.VK_EQUALS, KeyCode.EQUALS);
        keyMap.put(KeyEvent.VK_OPEN_BRACKET, KeyCode.BRACELEFT);
        keyMap.put(KeyEvent.VK_CLOSE_BRACKET, KeyCode.BRACERIGHT);
        keyMap.put(KeyEvent.VK_SEMICOLON, KeyCode.SEMICOLON);
        keyMap.put(KeyEvent.VK_QUOTE, KeyCode.QUOTE);
        keyMap.put(KeyEvent.VK_BACK_SLASH, KeyCode.BACK_SLASH);
        keyMap.put(KeyEvent.VK_SLASH, KeyCode.SLASH);
        keyMap.put(KeyEvent.VK_PERIOD, KeyCode.PERIOD);
        keyMap.put(KeyEvent.VK_COMMA, KeyCode.COMMA);
    }

    public static KeyCode mapAWTKeyCodeToFX(int awtKeyCode) {
        return keyMap.get(awtKeyCode);
    }
}
