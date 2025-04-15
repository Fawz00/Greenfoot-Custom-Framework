public class Transform2D {
    public Vector2D location;
    public float rotation;
    public Vector2D scale;
    
    public Transform2D(Transform2D transform) {
        this(transform.location, transform.rotation, transform.scale);
    }
    public Transform2D() {
        this(new Vector2D(), 0.0f, new Vector2D(1.0f, 1.0f));
    }
    public Transform2D(Vector2D location, float rotation, Vector2D scale) {
        this.location = location;
        this.rotation = rotation;
        this.scale = scale;
    }

    public void lookAt(Vector2D target) {
        Vector2D direction = target.sub(location);
        rotation = (float) Math.toDegrees(Math.atan2(direction.y, direction.x));
    }

    public Transform2D add(Transform2D other) {
        return new Transform2D(
            this.location.add(other.location),
            this.rotation + other.rotation,
            this.scale.add(other.scale)
        );
    }

    public Vector2D getForward() {
        float radians = (float) Math.toRadians(rotation);
        return new Vector2D((float) Math.cos(radians), (float) Math.sin(radians));
    }
    public Vector2D getRight() {
        float radians = (float) Math.toRadians(rotation + 90);
        return new Vector2D((float) Math.cos(radians), (float) Math.sin(radians));
    }
}
