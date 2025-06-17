import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.World;

public class GameoverMenu extends World {
    private UI_Label labelGameOver;
    private UI_Button buttonRetry;
    private UI_Button buttonExit;


    public GameoverMenu() {
        super(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, 1, true);
        prepare();
    }
    
    private void prepare() {
        super.started();

        setBackground(new GreenfootImage("background/03.jpg"));

        labelGameOver = new UI_Label("GAME OVER", 100);
        addObject(labelGameOver, Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HEIGHT / 2 - 100);

        buttonRetry = new UI_Button("Main Lagi", 200, 60, 20);
        buttonRetry.setLineColor(Color.BLACK);
        buttonRetry.setBackgroundColor(Color.WHITE);
        buttonRetry.handleEvent(new E_UIButton() {
            @Override
            public void onClick(int x, int y) {
                Greenfoot.setWorld(new StartMenu()); // restart world
            }

            @Override
            public void onEvent(S_GameEvent event) {}

            @Override
            public void onHover(int x, int y) {}

            @Override
            public void onRelease(int x, int y) {}
        });
        addObject(buttonRetry, Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HEIGHT / 2);

        buttonExit = new UI_Button("Keluar", 200, 60, 20);
        buttonExit.setLineColor(Color.BLACK);
        buttonExit.setBackgroundColor(Color.WHITE);
        buttonExit.handleEvent(new E_UIButton() {
            @Override
            public void onClick(int x, int y) {
                Greenfoot.stop();
            }

            @Override
            public void onEvent(S_GameEvent event) {}

            @Override
            public void onHover(int x, int y) {}

            @Override
            public void onRelease(int x, int y) {}
        });
        addObject(buttonExit, Settings.SCREEN_WIDTH / 2, Settings.SCREEN_HEIGHT / 2 + 80);
    }

    @Override
    public void act() {
        super.act();
    }
}
