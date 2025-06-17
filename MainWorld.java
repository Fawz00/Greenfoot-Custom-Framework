import greenfoot.Color;

public class MainWorld extends WorldBase {
    private UI_Progressbar barHP;
    private UI_Progressbar barXP;
    private UI_Label labelLevel;

    private AT_Tree tree;

    public MainWorld() {
        super();
        prepare();
    }

    private void prepare() {
        super.started();

        UI_Label label = new UI_Label("伊藤", 100);
        addObject(label, 130, 90);

        // HP bar
        barHP = new UI_Progressbar(1, 1, 250, 16, 16);
        barHP.setLineColor(Color.BLACK);
        barHP.setFillColor(Color.RED);
        barHP.setBackgroundColor(Color.WHITE);
        addObject(barHP, 130, 150);

        // XP bar
        barXP = new UI_Progressbar(1, 1, Settings.SCREEN_WIDTH, 20, 20);
        barXP.setLineColor(Color.BLACK);
        barXP.setFillColor(new Color(0, 128, 255, 172));
        barXP.setBackgroundColor(new Color(0, 0, 0, 64));
        addObject(barXP, Settings.SCREEN_WIDTH/2, 16);

        labelLevel = new UI_Label("Lv. 1", 50);
        addObject(labelLevel, Settings.SCREEN_WIDTH/2, 60);

        UI_Button button = new UI_Button("SKILL", 100, 50, 20);
        button.setLineColor(Color.BLACK);
        button.setBackgroundColor(Color.WHITE);
        button.handleEvent(new E_UIButton() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("Skill Button Clicked!");
            }

            @Override
            public void onEvent(S_GameEvent event) {}

            @Override
            public void onHover(int x, int y) {}

            @Override
            public void onRelease(int x, int y) {}
        });
        addObject(button, Settings.SCREEN_WIDTH-60, 60);

        tree = new AT_Tree();
        spawnActor(tree, new Transform2D(new Vector2D(7, 7), 0f, new Vector2D(1, 1)));
    }

    @Override
    public void act() {
        super.act();

        // Update the HP bar
        barHP.setMaxValue(getPlayer().getComponent(C_EntityStatus.class).getStatus().maxHP);
        barHP.setValue((int)getPlayer().getComponent(C_EntityStatus.class).getStatus().currentHP);

        // Update the XP bar
        barXP.setMaxValue(getPlayer().getComponent(C_EntityStatus.class).getStatus().maxXP);
        barXP.setValue(getPlayer().getComponent(C_EntityStatus.class).getStatus().currentXP);

        // Update the level label
        labelLevel.setValue("Lv. " + getPlayer().getComponent(C_EntityStatus.class).getStatus().level);
    }
}
