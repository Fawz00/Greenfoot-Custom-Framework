import greenfoot.GreenfootImage;

public abstract class TileEntity extends ActorBase {
    private C_AABBCollision2D collision;

    public TileEntity() {
        this(new GreenfootImage("tile_entities/default.png"), new C_AABBCollision2D(new Vector2D(1f)));
    }
    public TileEntity(GreenfootImage image, C_AABBCollision2D col) {
        super();
        setImage(image);

        this.collision = col;
        addComponent(collision);
    }

    public void act() {
        super.act();
        if(getWorld() == null) return;
    }
}
