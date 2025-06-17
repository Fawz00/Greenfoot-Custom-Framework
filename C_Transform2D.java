
import greenfoot.GreenfootImage;

public class C_Transform2D extends Component {
    private volatile Transform2D transform;
    
    public C_Transform2D() {
        this(new Vector2D(), 0.0f, new Vector2D(1, 1));
    }
    public C_Transform2D(Vector2D position, float rotation, Vector2D scale) {
        this.transform = new Transform2D(position, rotation, scale);
    }

    public void start() {
        super.start();
        updateActorTransform();
    }

    public synchronized void updateActorTransform() {
        MainWorld currentWorld = owner.getCurrentWorld();
        if(currentWorld == null) return;

        Vector2D worldPosition = transform.location.mul(currentWorld.getTileSize()).add(new Vector2D(currentWorld.getWorldOffset()[0], currentWorld.getWorldOffset()[1]));
        owner.setLocation((int) worldPosition.x, (int) worldPosition.y);
        owner.setRotation((int)transform.rotation);
        GreenfootImage image = owner.getImage();
        image.scale((int)(image.getWidth()*transform.scale.x), (int)(image.getHeight()*transform.scale.y));
        transform.scale = new Vector2D(1, 1); // Reset scale
        owner.setImage(image);
    }

    public void setLocation(Vector2D location) {
        transform.location = location;
        testCollision();
        updateActorTransform();
    }
    public void setLocation(float x, float y) {
        setLocation(new Vector2D(x, y));
    }
    public void setRotation(float rotation) {
        transform.rotation = rotation;
        testCollision();
        updateActorTransform();
    }
    public void setScale(Vector2D scale) {
        transform.scale = scale;
        testCollision();
        updateActorTransform();
    }
    public void setScale(float x, float y) {
        setScale(new Vector2D(x, y));
    }
    public void setScale(float scale) {
        setScale(new Vector2D(scale, scale));
    }
    public void setTransform(Transform2D transform) {
        this.transform = transform;
        testCollision();
        updateActorTransform();
    }

    public void move(Vector2D velocity) {
        Vector2D newLocation = transform.location;

        I_Collision2D collisionComponent = owner.getComponentIfImplements(I_Collision2D.class);
        C_Physics2D physicsComponent = owner.getComponent(C_Physics2D.class);
        C_EventManager eventManager = owner.getComponent(C_EventManager.class);
    
        if (collisionComponent != null) {
            boolean hitX = false, hitY = false;
    
            // Coba gerakan pada sumbu X terlebih dahulu
            Vector2D testLocationX = newLocation.add(new Vector2D(velocity.x * owner.getDeltaTime(), 0));
            if (collisionComponent.detectWorldCollision(testLocationX)) {
                hitX = true; // Jika bertabrakan, cegah pergerakan di X
                velocity = new Vector2D(0, velocity.y); // Hentikan pergerakan di X
            } else {
                newLocation = testLocationX;
            }
    
            // Coba gerakan pada sumbu Y (untuk sliding)
            Vector2D testLocationY = newLocation.add(new Vector2D(0, velocity.y * owner.getDeltaTime()));
            if (collisionComponent.detectWorldCollision(testLocationY)) {
                hitY = true; // Jika bertabrakan, cegah pergerakan di Y
                velocity = new Vector2D(velocity.x, 0); // Hentikan pergerakan di Y
            } else {
                newLocation = testLocationY;
            }
    
            // Efek wall-sliding: Jika karakter menabrak dinding di X dan bergerak turun, kurangi kecepatan jatuh
            if (hitX && velocity.y > 0) {
                velocity = new Vector2D(velocity.x, velocity.y * 0.7f); // Meluncur lebih lambat di dinding
            }
    
            // Jika kedua sumbu terkena tabrakan, hentikan pergerakan sepenuhnya
            if (hitX && hitY) {
                velocity = velocity.zero();
                if(physicsComponent != null) {
                    physicsComponent.setVelocity(velocity);
                }
                return;
            }

            // Jika terjadi tabrakan, kirimkan event
            if (hitX || hitY) {
                if(physicsComponent != null) {
                    physicsComponent.setGrounded(true);
                }
                eventManager.dispatchEvent(Enumerations.Event.COLLIDED, new S_CollisionHit(newLocation, null, (float)velocity.length(), Enumerations.CollisionLayer.WORLD));
            } else {
                if(physicsComponent != null) {
                    physicsComponent.setGrounded(false);
                }
            }
        } else {
            if(physicsComponent != null) {
                physicsComponent.setGrounded(false);
            }
            newLocation = transform.location.add(velocity.mul(owner.getDeltaTime()));
        }

        // Update velocity
        if(physicsComponent != null) {
            physicsComponent.setVelocity(velocity);
        }
    
        transform.location = newLocation;
        updateActorTransform();
    }
    
    public void moveForward(float speed) {
        float radians = (float) Math.toRadians(transform.rotation);
        Vector2D direction = new Vector2D((float) Math.cos(radians), (float) Math.sin(radians)).normalize();
        Vector2D velocity = direction.mul(speed);
        move(velocity);
    }
    public void lookAt(Vector2D target) {
        transform.lookAt(target);
        updateActorTransform();
    }
    public void lookAt(ActorBase target) {
        transform.lookAt(target.getComponent(C_Transform2D.class).getLocation());
        updateActorTransform();
    }

    public Vector2D getLocation() {
        return transform.location;
    }
    public float getRotation() {
        return transform.rotation;
    }
    public Vector2D getScale() {
        return transform.scale;
    }
    public Transform2D getTransform() {
        return transform;
    }

    public Vector2D getForward() {
        float radians = (float) Math.toRadians(transform.rotation);
        return new Vector2D((float) Math.cos(radians), (float) Math.sin(radians));
    }

    private void testCollision() {
        if(owner != null) {
            I_Collision2D collisionComponent = owner.getComponentIfImplements(I_Collision2D.class);
            if (collisionComponent != null) {
                if(collisionComponent.detectWorldCollision()) {
                    owner.getComponent(C_EventManager.class).dispatchEvent(Enumerations.Event.COLLIDED, new S_CollisionHit(transform.location, null, 0.0f, Enumerations.CollisionLayer.WORLD));
                }
            }
        }
    }
    
}
