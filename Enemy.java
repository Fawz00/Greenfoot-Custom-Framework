import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Enemy extends Entity {
    private C_LifeTime lifeTime = new C_LifeTime(30f);
    private C_EventManager eventManager;
    private C_EntityStatus status;
    private C_CoolDown cooldown;
    private C_AABBCollision2D collision;

    public Enemy() {
        super();
        eventManager = new C_EventManager(
            new EventListener() {
                @Override
                public void onEvent(S_GameEvent event) {
                    switch (event.getEvent()) {
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
        eventManager.addListener(new E_EntityStatus() {
            @Override
            public void onDamaged(ActorComponent source, int damage, boolean isCritical) {
                System.out.println("Enemy damaged by " + source + " for " + damage + (isCritical ? " (Critical)" : ""));
            }

            @Override
            public void onHit(ActorComponent source) {
                System.out.println("Enemy hit by " + source);
            }

            @Override
            public void onCritHit(ActorComponent source, ActorComponent target, int damage) {
                System.out.println("Enemy crit hit by " + source + " for " + damage);
            }

            @Override
            public void onHeal(ActorComponent source, int amount) {
                System.out.println("Enemy healed by " + source + " for " + amount);
            }

            @Override
            public void onDeath(ActorComponent source, Enumerations.DeathType deathType) {
                System.out.println("Enemy died due to " + deathType.toString().toLowerCase() + " by " + source);

                GlobalVariables.getSound("explode").play();
                getCurrentWorld().spawnActor(new Blast(), transformComponent.getTransform());
                destroy();
            }

            @Override
            public void onRevive(ActorComponent source) {
                System.out.println("Enemy revived by " + source);
            }

            @Override
            public void onEvent(S_GameEvent event) {
                // Handle events here if needed
            }
        });
        addComponent(eventManager);

        collision = new C_AABBCollision2D(new Vector2D(0.75));
        addComponent(collision);

        addComponent(lifeTime);

        cooldown = new C_CoolDown("attack", 0.5f);
        addComponent(cooldown);

        status = new C_EntityStatus(new S_EntityStatus(100, 40, 5, 0.05f, 0.5f));
        addComponent(status);
    }

    public void addedToWorld(World world) {
        super.addedToWorld(world);
        transformComponent.setRotation(60);
    }

    public void act() {
        super.act();
        if(getWorld() == null) return;
        
        // Add movement to player
        Player player = getCurrentWorld().getPlayer();
        if(player.isValid()) {
            transformComponent.lookAt(player);
            transformComponent.moveForward(2f);
        } else {
            transformComponent.moveForward(2f);
        }

        // Set collision with the player
        if(cooldown.isCoolDownActive("attack")) {
            Entity detected = (Entity)getOneIntersectingObject(Player.class);
            if (detected != null) {
                if(detected.isValid()) {
                    status.attack(detected);
                    cooldown.resetCoolDown("attack");
                }
            }
        }

        // Remove the object if far from the screen
        int offset = 720;
        if(getX() < -offset || getX() > getWorld().getWidth()+offset || getY() < -offset || getY() > getWorld().getHeight()+offset) {
            eventManager.dispatchEvent(Enumerations.Event.DESTROY, null);
        }
    }
}
