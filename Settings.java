import greenfoot.World;

public abstract class Settings {
    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;
    public static final int TILE_SIZE = 64;

    public static final Class<? extends World> WORLD_CLASS = StartMenu.class;

    public static final String TERRAIN_BLOCKS_PATH = "resources/terrain_blocks.json";
}