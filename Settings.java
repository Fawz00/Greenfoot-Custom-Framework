public class Settings {
    public static final int SCREEN_WIDTH = 1080;
    public static final int SCREEN_HEIGHT = 720;
    public static final int TILE_SIZE = 64;
    public static final int WORLD_WIDTH = SCREEN_WIDTH / TILE_SIZE;
    public static final int WORLD_HEIGHT = SCREEN_HEIGHT / TILE_SIZE;

    public static final String WORLD_MAP_TEXTURE = "WorldMap";
    public static final String WORLD_MAP_TEXTURE_PATH = "resources/textures/" + WORLD_MAP_TEXTURE + ".png";

    public static final String TERRAIN_BLOCKS_PATH = "resources/terrain_blocks.json";    
}