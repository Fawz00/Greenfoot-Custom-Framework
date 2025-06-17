import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Player extends Entity {
    private GreenfootImage[] idleAnimationLeft = new GreenfootImage[]{new GreenfootImage("char0/idle0.png"), new GreenfootImage("char0/idle1.png")};
    private GreenfootImage[] idleAnimationRight = new GreenfootImage[]{new GreenfootImage("char0/idle0.png"), new GreenfootImage("char0/idle1.png")};
    private GreenfootImage[] walkAnimationLeft = new GreenfootImage[]{new GreenfootImage("char0/walk0.png"), new GreenfootImage("char0/walk1.png"), new GreenfootImage("char0/walk2.png"), new GreenfootImage("char0/walk3.png")};
    private GreenfootImage[] walkAnimationRight = new GreenfootImage[]{new GreenfootImage("char0/walk0.png"), new GreenfootImage("char0/walk1.png"), new GreenfootImage("char0/walk2.png"), new GreenfootImage("char0/walk3.png")};

    private int fireCooldown = 0;
    private Enumerations.Direction lastMovement;
    private float speed = 4f;

    private C_LifeTime lifeTime = new C_LifeTime();
    private C_Animated animatedActor;
    private C_EventManager eventListener;
    private C_EntityStatus status;
    private C_AABBCollision2D collision;
    private C_Physics2D physics;

    public Player() {
        super();
        addComponent(lifeTime);

        animatedActor = new C_Animated(idleAnimationRight, false, 2);
        animatedActor.mirrorAnimation(idleAnimationLeft);
        animatedActor.mirrorAnimation(walkAnimationLeft);
        addComponent(animatedActor);

        eventListener = new C_EventManager(
            new EventListener() {
                @Override
                public void onEvent(S_GameEvent event) {
                    switch (event.getEvent()) {
                        case Enumerations.Event.DESTROY: {
                            destroy();
                            break;
                        }
                        case Enumerations.Event.COLLIDED: {
                            S_CollisionHit hit = (S_CollisionHit) event.getData();
                            System.out.println("Player collided with " + hit.getCollisionType() + " at " + hit.getLocation());
                            break;
                        }

                        default:
                        break;
                    }
                }
            }
        );
        eventListener.addListener(new E_EntityStatus() {
            @Override
            public void onDamaged(ActorComponent source, int damage, boolean isCritical) {
                System.out.println("Player damaged by " + source + " for " + damage + (isCritical ? " (Critical)" : ""));
            }

            @Override
            public void onHit(ActorComponent source) {
                System.out.println("Player hit by " + source);
                GlobalVariables.getSound("hit").play();
            }

            @Override
            public void onCritHit(ActorComponent source, ActorComponent target, int damage) {
                System.out.println("Player crit hit by " + source + " for " + damage);
            }

            @Override
            public void onHeal(ActorComponent source, int amount) {
                System.out.println("Player healed by " + source + " for " + amount);
            }

            @Override
            public void onDeath(ActorComponent source, Enumerations.DeathType deathType) {
                System.out.println("Player died due to " + deathType.toString().toLowerCase() + " by " + source);
                GlobalVariables.getSound("explode").play();
                getCurrentWorld().spawnActor(new Blast(), transformComponent.getTransform());

                Greenfoot.setWorld(new GameoverMenu());
                destroy();
            }

            @Override
            public void onRevive(ActorComponent source) {
                System.out.println("Player revived by " + source);
            }

            @Override
            public void onGainXP(ActorComponent owner, int amount) {
                System.out.println("Enemy gained " + amount + " XP from " + owner);
            }

            @Override
            public void onLevelUp(ActorComponent owner) {
                System.out.println("Enemy leveled up: " + owner);
            }

            @Override
            public void onEvent(S_GameEvent event) {
                // Handle events here if needed
            }
        });
        addComponent(eventListener);

        collision = new C_AABBCollision2D(new Vector2D(0.8));
        addComponent(collision);

        physics = new C_Physics2D();
        physics.setGravity(0f); // 0
        physics.setDrag(5f); // 5
        addComponent(physics);

        status = new C_EntityStatus(new S_EntityStatus(1200, 20, 10, 0.05f, 0.5f));
        addComponent(status);

        lastMovement = Enumerations.Direction.RIGHT;
    }

    public void addedToWorld(World world) {
        super.addedToWorld(world);
    }

    public void act() {
        super.act();

        controls();

        if( getCurrentWorld().getBlockAt(transformComponent.getTransform().location).identifier.equals("lava") ) {
            eventListener.safeDispatch(E_EntityStatus.class, ev -> ev.onDeath(this, Enumerations.DeathType.KILLED));
        }

        if (fireCooldown > 0) {
            fireCooldown--;
        }
    }

    private void controls() {
        speed = 4f;
        animatedActor.setAnimationSpeed(2);

        // Setup controls
        var mouse = Greenfoot.getMouseInfo();

        if (mouse != null) if(mouse.getButton() == 1 && mouse.getActor() != this) {
            if (fireCooldown == 0) {
                Bullet bullet = new Bullet();
                getCurrentWorld().spawnActor(bullet, new Transform2D(transformComponent.getTransform()));

                // Calculate the angle of the bullet from player to mouse
                var angle = Math.atan2(mouse.getY() - getY(), mouse.getX() - getX());
                bullet.transformComponent.setRotation((int) (angle * 180 / Math.PI));

                GlobalVariables.getSound("fire_ammo").play();
                fireCooldown = 20;
            }
        }

        if (lastMovement == Enumerations.Direction.RIGHT) {
            animatedActor.setAnimation(idleAnimationRight);
        } else {
            animatedActor.setAnimation(idleAnimationLeft);
        }

        // If key shift is pressed, player will move faster
        if (Greenfoot.isKeyDown("shift")) {
            speed = 6f;
        }

        if (Greenfoot.isKeyDown("left") || Greenfoot.isKeyDown("A")) {
            movePlayer(-1, 0);
            animatedActor.setAnimation(walkAnimationLeft);
            if (lastMovement == Enumerations.Direction.RIGHT) {
                lastMovement = Enumerations.Direction.LEFT;
            }
            if(speed > 4f)  animatedActor.setAnimationSpeed(8);
        } else if (Greenfoot.isKeyDown("right") || Greenfoot.isKeyDown("D")) {
            movePlayer(1, 0);
            animatedActor.setAnimation(walkAnimationRight);
            if (lastMovement == Enumerations.Direction.LEFT) {
                lastMovement = Enumerations.Direction.RIGHT;
            }
            if(speed > 4f)  animatedActor.setAnimationSpeed(8);
        }
        if (Greenfoot.isKeyDown("up") || Greenfoot.isKeyDown("W")) {
            if (lastMovement == Enumerations.Direction.RIGHT) {
                animatedActor.setAnimation(walkAnimationRight);
            } else {
                animatedActor.setAnimation(walkAnimationLeft);
            }
            if(speed > 4f)  animatedActor.setAnimationSpeed(8);
            movePlayer(0, -1);
        } else if (Greenfoot.isKeyDown("down") || Greenfoot.isKeyDown("S")) {
            if (lastMovement == Enumerations.Direction.RIGHT) {
                animatedActor.setAnimation(walkAnimationRight);
            } else {
                animatedActor.setAnimation(walkAnimationLeft);
            }
            if(speed > 4f)  animatedActor.setAnimationSpeed(8);
            movePlayer(0, 1);
        }
    }

    private void movePlayer(float x, float y) {
        transformComponent.move(new Vector2D(x, y).normalize().mul(speed));
    }
}
