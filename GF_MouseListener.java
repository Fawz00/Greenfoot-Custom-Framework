import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import greenfoot.gui.input.mouse.MousePollingManager;
import javafx.scene.input.MouseButton;

public class GF_MouseListener implements MouseListener, MouseMotionListener {
    private static GF_MouseListener instance = null;
    private static MousePollingManager gfMouseInfo = null;

    public GF_MouseListener(MousePollingManager mouseInfo) {
        gfMouseInfo = mouseInfo;
    }

    public static GF_MouseListener getInstance() {
        if (instance == null) {
            instance = new GF_MouseListener(gfMouseInfo);
        }
        return instance;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        MouseButton fxButton = GF_MouseButtonMapper.mapSwingMouseButtonToFX(e.getButton());
        if (fxButton != null) {
            gfMouseInfo.mouseClicked(e.getX(), e.getY(), fxButton, e.getClickCount());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        MouseButton fxButton = GF_MouseButtonMapper.mapSwingMouseButtonToFX(e.getButton());
        if (fxButton != null) {
            gfMouseInfo.mousePressed(e.getX(), e.getY(), fxButton);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        MouseButton fxButton = GF_MouseButtonMapper.mapSwingMouseButtonToFX(e.getButton());
        if (fxButton != null) {
            gfMouseInfo.mouseReleased(e.getX(), e.getY(), fxButton);
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        // Handle mouse entered event
    }

    @Override
    public void mouseExited(MouseEvent e) {
        MouseButton fxButton = GF_MouseButtonMapper.mapSwingMouseButtonToFX(e.getButton());
        if (fxButton != null) {
            gfMouseInfo.mouseExited();
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        MouseButton fxButton = GF_MouseButtonMapper.mapSwingMouseButtonToFX(e.getButton());
        if (fxButton != null) {
            gfMouseInfo.mouseDragged(e.getX(), e.getY(), fxButton);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        gfMouseInfo.mouseMoved(e.getX(), e.getY());
    }
}
