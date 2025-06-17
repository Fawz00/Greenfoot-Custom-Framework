import java.util.List;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

// Maybe the game title should be 「星影に隠された真実よ、未来を拓く者に祝福を」.

public abstract class WorldBase extends World {
    private static final int[] screen = {Settings.SCREEN_WIDTH, Settings.SCREEN_HEIGHT};
    private static int[] worldOffset = {0, 0};
    private static final int tileSize = Settings.TILE_SIZE;

    private long startFrameTime;
    private double deltaTime;
    private float worldTime;
    private int frame = 0;
    private GreenfootImage bgImage;

    private WorldActor worldActor;
    private FollowCamera followCamera;
    private Player player;

    private C_EventManager eventManager;

    public WorldBase() {    
        super(screen[0], screen[1], 1, false);

        worldOffset = new int[]{0, 0};

        // Load sounds
        GlobalVariables.loadSound("background", "bgm.wav");
        GlobalVariables.loadSound("start_music", "gf_start.wav");
        GlobalVariables.loadSound("explode", "blast.wav");
        GlobalVariables.loadSound("hit", "hit.wav");
        GlobalVariables.loadSound("fire_ammo", "ammo.wav");

        GlobalVariables.loadTexture("WorldMap", "/terrain/map.png");

        // Load terrain blocks
        GlobalVariables.loadTileTexture("BlockTextures", "terrain/tilemap.png", tileSize);
        GlobalVariables.addTerrainBlock(0, new S_TarrainBlock(new int[]{255}, "void"));
        GlobalVariables.addTerrainBlock(0x00ff00ff, new S_TarrainBlock(new int[]{0}, "grass"));
        GlobalVariables.addTerrainBlock(0xffff00ff, new S_TarrainBlock(new int[]{1}, "sand"));
        GlobalVariables.addTerrainBlock(0x7f7f00ff, new S_TarrainBlock(new int[]{2}, "dirt"));
        GlobalVariables.addTerrainBlock(0x7f7f7fff, new S_TarrainBlock(new int[]{3}, "stone_brick", true));
        GlobalVariables.addTerrainBlock(0xff0000ff, new S_TarrainBlock(new int[]{4}, "lava"));
        GlobalVariables.addTerrainBlock(0x0000ffff, new S_TarrainBlock(new int[]{5}, "water"));
        GlobalVariables.addTerrainBlock(0x035820ff, new S_TarrainBlock(new int[]{12}, "tallgrass"));
        GlobalVariables.addTerrainBlock(0xff00f4ff, new S_TarrainBlock(new int[]{6}, "fence", true));
        GlobalVariables.addTerrainBlock(0x95008fff, new S_TarrainBlock(new int[]{8}, "fence", true));
        GlobalVariables.addTerrainBlock(0xe96fe4ff, new S_TarrainBlock(new int[]{7}, "fence", true));
        GlobalVariables.addTerrainBlock(0x42003fff, new S_TarrainBlock(new int[]{9}, "fence", true));
        GlobalVariables.addTerrainBlock(0xca00c1ff, new S_TarrainBlock(new int[]{10}, "fence", true));
        GlobalVariables.addTerrainBlock(0x7b0076ff, new S_TarrainBlock(new int[]{11}, "fence", true));

        worldActor = new WorldActor(this);
        addObject(worldActor, screen[0]/2, screen[1]/2);

        player = new Player();
        addObject(player, screen[0]/2, screen[1]/2);

        followCamera = new FollowCamera(player);
        addObject(followCamera, screen[0]/2, screen[1]/2);

        bgImage = new GreenfootImage(screen[0], screen[1]);
        bgImage.setColor(Color.MAGENTA);
        this.setBackground(bgImage);
        prepare();
    }

    public void started() {
        GreenfootSound bgm = GlobalVariables.getSound("background");
        bgm.playLoop();
        bgm.setVolume(30);

        eventManager = worldActor.getComponent(C_EventManager.class);
        if(eventManager == null) {
            eventManager = new C_EventManager();
            worldActor.addComponent(eventManager);
        }

        player.transformComponent.setLocation(24, 15);
        startFrameTime = System.currentTimeMillis();
    }

    public void act() {
        frame++;
        deltaTime = (System.currentTimeMillis() - startFrameTime) / 1000d;
        startFrameTime = System.currentTimeMillis();
        worldTime += deltaTime;

        // Draw background with infinite scrolling effect
        GreenfootImage textureMap = GlobalVariables.getTexture("WorldMap");

        // bgImage.setColor(Color.CYAN);
        // bgImage.fill();
        for(int x = -tileSize; x < screen[0]+tileSize; x += tileSize) {
            for(int y = -tileSize; y < screen[1]+tileSize; y += tileSize) {
                Vector2D screenLocation = new Vector2D(x + worldOffset[0]%tileSize, y + worldOffset[1]%tileSize);
                Vector2D worldLocation = screenLocationToWorldLocation(screenLocation);

                if(worldLocation.x >= 0 && worldLocation.y >= 0 && worldLocation.x < textureMap.getWidth() && worldLocation.y < textureMap.getHeight()) {
                    S_TarrainBlock currentBlock = getBlockAt(worldLocation);
                    if(currentBlock != null) bgImage.drawImage( GlobalVariables.getTileTexture("BlockTextures")[currentBlock.textureIndex[0]], (int)screenLocation.x, (int)screenLocation.y );
                }
                // bgImage.setColor(Color.BLACK);
                // bgImage.drawRect((int)screenLocation.x, (int)screenLocation.y, tileSize, tileSize);
                // bgImage.setColor(Color.RED);
                // bgImage.drawString("\n "+(int)worldLocation.x+"\n "+(int)worldLocation.y, x + worldOffset[0]%tileSize, y + worldOffset[1]%tileSize);
            }
        }

        // Add enemies
        if(frame % Math.max(40, (250 - ((int)(worldTime*2f)))) == 0) {
            for(int i = 0; i < 3+(1.06*player.getComponent(C_EntityStatus.class).getStatus().level); i++) {
                // Add enemy at the corner screen randomly
                int x = Greenfoot.getRandomNumber(screen[0]);
                int y = Greenfoot.getRandomNumber(screen[1]);
                int side = Greenfoot.getRandomNumber(4);
                if(side == 0) x = 0;
                if(side == 1) x = screen[0];
                if(side == 2) y = 0;
                if(side == 3) y = screen[1];
                Vector2D location = screenLocationToWorldLocation(x, y);

                Transform2D transform = new Transform2D(location, Greenfoot.getRandomNumber(360), new Vector2D(1, 1));
                Enemy enemy = new Enemy();
                spawnActor(enemy, transform);

                if(enemy.getComponent(C_AABBCollision2D.class).detectWorldCollision()) {
                    enemy.destroy();
                }
            }
        }
    }

    public void moveWorld(Vector2D location) {
        moveWorld((int)location.x, (int)location.y);
    }

    public void moveWorld(int x, int y) {
        worldOffset[0] += x;
        worldOffset[1] += y;
        for(ActorBase actor : getObjects(ActorBase.class)) {
            actor.setLocation(actor.getX() + x, actor.getY() + y);
        }
    }

    @Override
    public void addObject(Actor actor, int x, int y) {
        super.addObject(actor, x + worldOffset[0], y + worldOffset[1]);
        if(eventManager != null) eventManager.safeDispatch(E_WorldEvent.class, ev -> ev.onActorAdded(actor));
    }

    public void spawnActor(ActorBase actor, Vector2D location, float rotation, Vector2D scale) {
        spawnActor(actor, new Transform2D(location, rotation, scale));
    }
    public void spawnActor(ActorBase actor, Transform2D transform) {
        addObject(actor, 0,0);
        actor.getComponent(C_Transform2D.class).setTransform(transform);
    }

    /**
     * Reorders the actors in the world so that their z-order matches the order of the given classes array.
     * The first class in the array will be rendered at the back, the last at the front.
     * Only actors of the specified classes are reordered.
     * 
     * @param classOrder Array of Actor classes in desired z-order.
     */
    public void reorderActors(Class<? extends Actor>[] classOrder) {
        for (int i = 0; i < classOrder.length; i++) {
            List<? extends Actor> actors = getObjects(classOrder[i]);
            for (Actor actor : actors) {
                setPaintOrder(classOrder[i]);
            }
        }
        // Set the paint order for all classes at once, in the given order
        setPaintOrder(classOrder);
    }

    public float getFPS() {
        return (float) (1f / deltaTime);
    }

    public double getDeltaTime() {
        return deltaTime;
    }

    public Player getPlayer() {
        return player;
    }

    public FollowCamera getFollowCamera() {
        return followCamera;
    }

    public float getWorldTime() {
        return worldTime;
    }

    public int[] getWorldOffset() {
        return worldOffset;
    }

    public int getTileSize() {
        return tileSize;
    }

    public Vector2D screenLocationToWorldLocation(Vector2D screenLocation) {
        return screenLocation.sub(new Vector2D(worldOffset[0], worldOffset[1])).div(tileSize);
    }

    public Vector2D screenLocationToWorldLocation(int x, int y) {
        return screenLocationToWorldLocation(new Vector2D(x, y));
    }

    public Vector2D worldLocationToScreenLocation(Vector2D worldLocation) {
        return worldLocation.mul(tileSize).add(new Vector2D(worldOffset[0], worldOffset[1]));
    }

    public S_TarrainBlock getBlockAt(Vector2D worldLocation) {
        GreenfootImage textureMap = GlobalVariables.getTexture("WorldMap");
        int x = (int)worldLocation.x;
        int y = (int)worldLocation.y;
        if(x < 0 || y < 0 || x >= textureMap.getWidth() || y >= textureMap.getHeight()) {
            return GlobalVariables.getTerrainBlock(0);
        }
        S_TarrainBlock block = GlobalVariables.getTerrainBlock( GlobalVariables.colorToInt(textureMap.getColorAt(x, y)) );
        if(block == null) {
            return GlobalVariables.getTerrainBlock(0);
        }
        return block;
    }

    public S_TarrainBlock getBlockAt(int x, int y) {
        return getBlockAt(new Vector2D(x, y));
    }

    public void setBlockAt(Vector2D worldLocation, S_TarrainBlock block) {
        GreenfootImage textureMap = GlobalVariables.getTexture("WorldMap");
        int x = (int)worldLocation.x;
        int y = (int)worldLocation.y;
        if(x < 0 || y < 0 || x >= textureMap.getWidth() || y >= textureMap.getHeight()) {
            return;
        }
        textureMap.setColorAt(x, y, GlobalVariables.intToColor(GlobalVariables.getTerrainBlockID(block)));
    }

    public void setBlockAt(int x, int y, S_TarrainBlock block) {
        setBlockAt(new Vector2D(x, y), block);
    }

    public void setBlockAt(Vector2D worldLocation, int id) {
        setBlockAt(worldLocation, GlobalVariables.getTerrainBlock(id));
    }

    public void setBlockAt(int x, int y, int id) {
        setBlockAt(new Vector2D(x, y), id);
    }
    
    /**
     * Prepare the world for the start of the program.
     * That is: create the initial objects and add them to the world.
     */
    private void prepare()
    {
    }
}
