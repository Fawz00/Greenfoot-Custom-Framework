import java.awt.event.MouseEvent;
import javafx.scene.input.MouseButton;

public class GF_MouseButtonMapper {

    public static MouseButton mapSwingMouseButtonToFX(int swingButton) {
        switch (swingButton) {
            case MouseEvent.BUTTON1:
                return MouseButton.PRIMARY;  // Kiri
            case MouseEvent.BUTTON2:
                return MouseButton.MIDDLE;   // Tengah
            case MouseEvent.BUTTON3:
                return MouseButton.SECONDARY; // Kanan
            default:
                return null;  // Button tidak dikenali
        }
    }
}
