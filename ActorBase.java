import greenfoot.World;

public abstract class ActorBase extends ActorComponent {
    protected C_Transform2D transformComponent;

    ActorBase() {
        this(new Vector2D(), 0.0f, new Vector2D(1.0f, 1.0f));
    }
    ActorBase(Vector2D position) {
        this(position, 0.0f, new Vector2D(1.0f, 1.0f));
    }
    ActorBase(Vector2D position, float rotation, Vector2D scale) {
        super();
        transformComponent = new C_Transform2D( position, rotation, scale );
    }

    @Override
    public void addedToWorld(World world) {
        super.addedToWorld(world);
        addComponent(transformComponent);
    }

    public void act() {
        super.act();
    }
}
