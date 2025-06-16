
public class AT_Tree extends Entity {
    private C_AABBCollision2D collision;

    public AT_Tree() {
        super();
        setImage("tile_entities/tree/tree0.png");

        collision = new C_AABBCollision2D(new Vector2D(2f));
        addComponent(collision);
    }

    public void act() {
        super.act();
        if(getWorld() == null) return;
    }
}
