import greenfoot.Color;

public class MainWorld extends WorldBase {
    private UI_Progressbar barHP;
    private UI_Progressbar barXP;
    private AT_Tree tree;

    public MainWorld() {
        super();
        prepare();
    }

    @Override
    public void started() {
        super.started();

        UI_Label label = new UI_Label("伊藤", 100);
        addObject(label, 130, 90);

        barHP = new UI_Progressbar(1, 1, 250, 16, 16);
        barHP.setLineColor(Color.BLACK);
        barHP.setFillColor(Color.RED);
        barHP.setBackgroundColor(Color.WHITE);
        addObject(barHP, 130, 150);

        barXP = new UI_Progressbar(1, 1, Settings.SCREEN_WIDTH, 20, 20);
        barXP.setLineColor(Color.BLACK);
        barXP.setFillColor(new Color(0, 128, 255, 172));
        barXP.setBackgroundColor(new Color(0, 0, 0, 64));
        addObject(barXP, Settings.SCREEN_WIDTH/2, 16);

        tree = new AT_Tree();
        spawnActor(tree, new Transform2D(new Vector2D(7, 7), 0f, new Vector2D(1, 1)));
    }

    @Override
    public void act() {
        super.act();
        barHP.setMaxValue(getPlayer().getComponent(C_EntityStatus.class).getStatus().maxHP);
        barHP.setValue((int)getPlayer().getComponent(C_EntityStatus.class).getStatus().currentHP);
    }
    
    private void prepare()
    {

    }
}
