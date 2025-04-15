public class C_AABBCollision2D extends Component implements I_Collision2D {
    private Vector2D size;
    private Vector2D offset;
    
    public C_AABBCollision2D() {
        this(new Vector2D(1.0f, 1.0f), new Vector2D());
    }
    public C_AABBCollision2D(Vector2D size) {
        this(size, new Vector2D());
    }
    public C_AABBCollision2D(Vector2D size, Vector2D offset) {
        this.size = size;
        this.offset = offset;
    }

    public boolean detectCollision(C_AABBCollision2D other) {
        Vector2D pos1 = owner.getComponent(C_Transform2D.class).getTransform().location.add(offset);
        Vector2D pos2 = other.owner.getComponent(C_Transform2D.class).getTransform().location.add(other.offset);
        
        double left1 = pos1.x - size.x / 2;
        double right1 = pos1.x + size.x / 2;
        double top1 = pos1.y - size.y / 2;
        double bottom1 = pos1.y + size.y / 2;
        
        double left2 = pos2.x - other.size.x / 2;
        double right2 = pos2.x + other.size.x / 2;
        double top2 = pos2.y - other.size.y / 2;
        double bottom2 = pos2.y + other.size.y / 2;
        
        return (left1 < right2 && right1 > left2 && top1 < bottom2 && bottom1 > top2) || detectWorldCollision();
    }

    @Override
    public boolean detectWorldCollision(Vector2D location) {
        MainWorld world = owner.getCurrentWorld();
        if(world == null) {
            return false;
        }

        Vector2D pos = location.add(offset);
        Vector2D start = pos.sub(size.mul(0.5f));
        Vector2D end = pos.add(size.mul(0.5f));

        for(int x = (int)start.x; x <= (int)end.x; x++) {
            for(int y = (int)start.y; y <= (int)end.y; y++) {
                S_TarrainBlock currentBlock = world.getBlockAt(new Vector2D(x, y));
                if(currentBlock.solid) {
                    return true;
                }
            }
        }
        return false;
    }
    @Override
    public boolean detectWorldCollision() {
        return detectWorldCollision(owner.getComponent(C_Transform2D.class).getTransform().location);
    }
}
