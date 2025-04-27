import greenfoot.Greenfoot;
import greenfoot.World;
import greenfoot.core.Simulation;
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
        Simulation simulation = GF_Simulation.forceInitializeSimulation(worldHandler);

        // Buat dunia utama
        World world = new MainWorld();
        world.started();
        Greenfoot.setWorld(world);
        Greenfoot.setSpeed(60);
        Greenfoot.start();

        simulation.start();

        // Buat window setelah inisialisasi selesai
        System.out.println("Konfigurasi selesai, membuat window...");

        JFrame frame = new JFrame("Greenfoot Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel untuk menggambar dunia
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);

                WorldRenderer worldRenderer = new WorldRenderer();

                int worldWidth = world.getWidth();
                int worldHeight = world.getHeight();

                if (worldWidth <= 0 || worldHeight <= 0) {
                    System.err.println("Dunia ukuran invalid: " + worldWidth + "x" + worldHeight);
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
        frame.setVisible(true);
        System.out.println("Frame visible: " + frame.isVisible());

        // Thread untuk menjalankan game loop
        Thread gameLoopThread = new Thread(() -> {
            while (true) {
                world.act();

                // Render ulang dunia ke JFrame
                panel.repaint();
                try {
                    Thread.sleep(16); // Sekitar 60 FPS
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        // Mulai game loop
        gameLoopThread.start();
        System.out.println("Greenfoot started");
    }
}
