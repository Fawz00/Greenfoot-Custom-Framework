import greenfoot.Actor;
import greenfoot.World;

public class WorldActor extends ActorComponent {
    private WorldBase world;
    private C_EventManager eventManager;

    public WorldActor(WorldBase Iworld) {
        super();
        setImage("empty.png");
        world = Iworld;

        eventManager = new C_EventManager(
            new E_WorldEvent() {
                @SuppressWarnings("unchecked")
                @Override
                public void onActorAdded(Actor actor) {
                    world.reorderActors(new Class[] { UIBase.class, TileEntity.class, Entity.class });
                }

                @Override
                public void onActorRemoved(Actor actor) {
                    // Handle actor removed event
                }

                @Override
                public void onEvent(S_GameEvent event) {
                    // TODO Auto-generated method stub
                }
            }
        );
        addComponent(eventManager);
    }

    public void act() {
        super.act();
    }
}
