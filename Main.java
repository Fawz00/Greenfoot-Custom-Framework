import greenfoot.Actor;
import greenfoot.Greenfoot;
import greenfoot.core.WorldHandler;
import greenfoot.gui.WorldRenderer;
import greenfoot.util.GreenfootUtil;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class Main {
    public static void main(String[] args) {
        // Inisialisasi GreenfootUtil
        GreenfootUtil.initialise(new GF_UtilDelegate());

        GF_BlueJ_Config.forceInitializeConfig();
        WorldHandler worldHandler = GF_WorldHandler.forceInitializeWorldHandler();
        GF_Simulation.forceInitializeSimulation(worldHandler);

        // Buat dunia utama
        WorldBase world = new MainWorld();
        world.started();
        Greenfoot.setWorld(world);
        Greenfoot.start();
        Greenfoot.setSpeed(60);

        WorldRenderer worldRenderer = new WorldRenderer();

        // Buat window setelah inisialisasi selesai
        System.out.println("Konfigurasi selesai, membuat window...");

        JFrame frame = new JFrame("Greenfoot Standalone Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        GF_KeyListener keyListener = new GF_KeyListener(worldHandler.getKeyboardManager());
        GF_MouseListener mouseListener = new GF_MouseListener(worldHandler.getMouseManager());
        frame.addKeyListener(keyListener);
        frame.addMouseListener(mouseListener);
        //frame.addMouseMotionListener(mouseListener);

        // Panel untuk menggambar dunia
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                int worldWidth = world.getWidth();
                int worldHeight = world.getHeight();

                if (worldWidth <= 0 || worldHeight <= 0) {
                    System.err.println("Invalid world size: " + worldWidth + "x" + worldHeight);
                    return;
                }

                BufferedImage worldImage = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
                worldRenderer.renderWorld(world, worldImage);
                g.drawImage(worldImage, 0, 0, null);
            }
        };

        panel.setPreferredSize(new Dimension(world.getWidth(), world.getHeight()));
        frame.add(panel, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setFocusable(true);
        frame.setVisible(true);
        System.out.println("Frame visible: " + frame.isVisible());

        // Thread untuk menjalankan game loop
        Thread gameLoopThread = new Thread(() -> {
            while (Thread.currentThread().isInterrupted() == false) {
                worldHandler.getMouseManager().newActStarted();
                world.act();
                world.getObjects(Actor.class).forEach(Actor::act);

                // Render ulang dunia ke JFrame
                panel.repaint();

                try {
                    Thread.sleep(17); // Limit to ~60 FPS
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt(); // Restore interrupted status
                }
            }
        });

        // Mulai game loop
        gameLoopThread.start();
        System.out.println("Greenfoot started");
    }
}
