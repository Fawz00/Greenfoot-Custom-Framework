
public class S_TarrainBlock extends Structure {
    public int[] textureIndex;
    public String identifier;
    public boolean solid;

    public S_TarrainBlock(int[] textureIndex, String identifier) {
        this(textureIndex, identifier, false);
    }
    public S_TarrainBlock(int[] textureIndex, String identifier, boolean solid) {
        this.textureIndex = textureIndex;
        this.solid = solid;
        this.identifier = identifier;
    }
}
