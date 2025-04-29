
public class Bullet extends Entity {
    private C_LifeTime lifeTime = new C_LifeTime(10f);
    private C_EventManager eventManager;
    private C_EntityStatus entityStatus;

    public Bullet() {
        super();
        setImage("bullet.png");

        eventManager = new C_EventManager(
            new EventListener() {
                public void onEvent(S_GameEvent e) {
                    switch (e.getEvent()) {
                        case Enumerations.Event.DEAD: {
                            destroy();
                            break;
                        }
                        case Enumerations.Event.DESTROY: {
                            destroy();
                            break;
                        }
                    
                        default:
                            break;
                    }
                }
            }
        );
        addComponent(eventManager);

        addComponent(lifeTime);

        entityStatus = new C_EntityStatus(new S_EntityStatus(10, 75, 1, 0.5f, 1.5f));
        addComponent(entityStatus);
    }

    public void act() {
        super.act();
        if(getWorld() == null) return;

        transformComponent.moveForward(10);

        // Set collision with the Entity
        Entity other = (Entity)getOneIntersectingObject(Entity.class);
        if(other != null && other != this && other.getClass() != Player.class) {
            entityStatus.attack(other);
            eventManager.dispatchEvent(Enumerations.Event.DESTROY, null);
        }
    }
}
