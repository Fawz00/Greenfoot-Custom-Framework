import greenfoot.Color;
import greenfoot.GreenfootImage;
import greenfoot.GreenfootSound;
import java.util.Map;
import java.util.Vector;
import java.util.HashMap;

public class GlobalVariables {
    private static Map<String, GreenfootSound> sounds = new HashMap<>();
    private static Map<String, GreenfootImage> textures = new HashMap<>();
    private static Map<String, GreenfootImage[]> tileTextures = new HashMap<>();
    private static Map<Integer, S_TarrainBlock> blocks = new HashMap<>();

    public static int colorToInt(Color color) {
        return colorToInt(color.getRed(), color.getGreen(), color.getBlue(), color.getAlpha());
    }
    public static int colorToInt(int r, int g, int b, int a) {
        return (r << 24) | (g << 16) | (b << 8) | a;
    }
    public static Color intToColor(int color) {
        return new Color((color >> 24) & 0xff, (color >> 16) & 0xff, (color >> 8) & 0xff, color & 0xff);
    }

    // SOUNDS
    public static void loadSound(String name, String path) {
        sounds.put(name, new GreenfootSound(path));
    }
    public static GreenfootSound getSound(String name) {
        GreenfootSound sound = sounds.get(name);
        if(sound == null) {
            throw new IllegalArgumentException("Sound not found: " + name);
        }
        return sound;
    }
    
    // TEXTURES
    public static void loadTexture(String name, String path) {
        textures.put(name, new GreenfootImage(path));
    }
    public static GreenfootImage getTexture(String name) {
        GreenfootImage texture = textures.get(name);
        if(texture == null) {
            throw new IllegalArgumentException("Texture not found: " + name);
        }
        return texture;
    }

    // TERRAIN BLOCKS
    public static void addTerrainBlock(int id, S_TarrainBlock block) {
        blocks.put(id, block);
    }
    public static S_TarrainBlock getTerrainBlock(int id) {
        S_TarrainBlock block = blocks.get(id);
        if(block == null) {
            return blocks.get(0);
        }
        return block;
    }
    public static int getTerrainBlockID(S_TarrainBlock block) {
        for(Map.Entry<Integer, S_TarrainBlock> entry : blocks.entrySet()) {
            if(entry.getValue().equals(block)) {
                return entry.getKey();
            }
        }
        return 0;
    }

    // TILE TEXTURES
    public static void loadTileTexture(String name, String path, int tileSize) {
        GreenfootImage texture = new GreenfootImage(path);
        Vector<GreenfootImage> tiles = new Vector<>();

        for(int y = 0; y < texture.getHeight(); y += tileSize) {
            for(int x = 0; x < texture.getWidth(); x += tileSize) {
                GreenfootImage tile = new GreenfootImage(tileSize, tileSize);
                tile.drawImage(texture, -x, -y);
                tiles.add(tile);
            }
        }
        tileTextures.put(name, tiles.toArray(new GreenfootImage[tiles.size()]));
    }
    public static GreenfootImage[] getTileTexture(String name) {
        GreenfootImage[] tiles = tileTextures.get(name);
        if(tiles == null) {
            throw new IllegalArgumentException("Tile texture not found: " + name);
        }
        return tiles;
    }

    public static void clear() {
        sounds.clear();
        textures.clear();
        tileTextures.clear();
        blocks.clear();
    }
}