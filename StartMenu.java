import greenfoot.Color;
import greenfoot.Greenfoot;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import greenfoot.World;

public class StartMenu extends World {
    private UI_Progressbar barHP;
    private UI_Progressbar barXP;
    private AT_Tree tree;

    GreenfootSound bgm;

    public StartMenu() {
        super(Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT, 1, true);
        GlobalVariables.loadSound("start_music", "gf_start.wav");
        prepare();
    }

    public void started() {
        super.started();
        bgm = GlobalVariables.getSound("start_music");
        bgm.playLoop();
        bgm.setVolume(70);
    }
    
    private void prepare() {
        super.started();

        setBackground(new GreenfootImage("background/05.jpg"));

        UI_Label title = new UI_Label("伊藤の旅", 120);
        addObject(title, Settings.SCREEN_WIDTH / 2, 150);

        UI_Button startButton = new UI_Button("Start Game", 350, 60, 32);
        startButton.setLineColor(Color.BLACK);
        startButton.setBackgroundColor(new Color(200, 255, 200, 192));
        startButton.setBorderThickness(3);
        startButton.handleEvent(new E_UIButton() {
            @Override
            public void onClick(int x, int y) {
                startButton.setBackgroundColor(new Color(120, 192, 120, 255));
                startButton.setLineColor(Color.GREEN);
            }

            @Override
            public void onEvent(S_GameEvent event) {}

            @Override
            public void onHover(int x, int y) {}

            @Override
            public void onRelease(int x, int y) {
                if(bgm != null) {
                    bgm.stop();
                }

                startButton.setBackgroundColor(new Color(200, 255, 200));
                startButton.setLineColor(Color.BLACK);
                Greenfoot.setWorld(new MainWorld());
            }
        });
        addObject(startButton, Settings.SCREEN_WIDTH / 2, 300);

        UI_Button exitButton = new UI_Button("Exit", 350, 60, 32);
        exitButton.setLineColor(Color.BLACK);
        exitButton.setBackgroundColor(new Color(255, 200, 200, 192));
        exitButton.setBorderThickness(3);
        exitButton.handleEvent(new E_UIButton() {
            @Override
            public void onClick(int x, int y) {
                if(bgm != null) {
                    bgm.stop();
                }
                Greenfoot.stop();
            }

            @Override
            public void onEvent(S_GameEvent event) {}

            @Override
            public void onHover(int x, int y) {}

            @Override
            public void onRelease(int x, int y) {}
        });
        addObject(exitButton, Settings.SCREEN_WIDTH / 2, 380);
    }

    @Override
    public void act() {
        super.act();
    }
}
