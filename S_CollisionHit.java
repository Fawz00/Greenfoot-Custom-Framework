
public class S_CollisionHit extends Structure {
    private Vector2D location;
    private ActorBase actorSource;
    private Enumerations.CollisionLayer collisionType;
    private float velocity;

    public S_CollisionHit(Vector2D location, ActorBase actorSource, float velocity, Enumerations.CollisionLayer collisionType) {
        this.location = location;
        this.actorSource = actorSource;
        this.velocity = velocity;
        this.collisionType = collisionType;
    }
    public S_CollisionHit(Vector2D location, ActorBase actorSource) {
        this(location, actorSource, 0.0f, Enumerations.CollisionLayer.ENTITY);
    }

    public Vector2D getLocation() {
        return location;
    }
    public ActorBase getActorSource() {
        return actorSource;
    }
    public float getVelocity() {
        return velocity;
    }
    public Enumerations.CollisionLayer getCollisionType() {
        return collisionType;
    }
}
