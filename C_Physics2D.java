public class C_Physics2D extends Component {
    private Vector2D velocity;
    private Vector2D acceleration;
    private float friction;
    private float mass;
    private float drag;
    private float gravity;
    private boolean isGrounded;
    
    public C_Physics2D(Vector2D velocity, Vector2D acceleration, float friction, float mass, float drag, float gravity) {
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = Math.max(mass, 0.01f);
        this.drag = Math.max(drag, 0.0f);
        this.gravity = gravity;
        this.friction = Math.max(friction, 0.0f);
    }
    
    public C_Physics2D() {
        this(new Vector2D(), new Vector2D(), 0.12f, 1.0f, 0.25f, 9.8f);
    }

    @Override
    public void update() {
        super.update();
        applyGravity();
        applyDrag();
        if(isGrounded) applyFriction();
        applyMovement();
    }

    private void applyGravity() {
        acceleration = acceleration.add(new Vector2D(0, gravity));
    }

    private void applyDrag() {
        if (drag > 0) {
            float deltaTime = (float) owner.getDeltaTime();

            if (velocity.mag() < 0.0001f) {
                velocity = new Vector2D();
            } else {
                Vector2D dragForce = velocity.mul(-drag);
                Vector2D dragAcceleration = dragForce.div(mass);
                velocity = velocity.add(dragAcceleration.mul(deltaTime));
            }
        }
    }

    private void applyFriction() {
        float deltaTime = (float) owner.getDeltaTime();
        Vector2D frictionForce = getFrictionForce();
        Vector2D accelerationDueToFriction = frictionForce.div(mass); // a = F / m

        // Update kecepatan
        velocity = velocity.add(accelerationDueToFriction.mul(deltaTime));

        // Mencegah kecepatan negatif (agar tidak berosilasi bolak-balik)
        if (velocity.mag() < 0.01) velocity = new Vector2D(0, 0);
    }
    private Vector2D getFrictionForce() {
        float frictionMagnitude = friction * getNormalForce(); // F_friction = μ * N
        if (velocity.mag() < 0.0001f) {
            return new Vector2D(0, 0);
        }
        Vector2D frictionDirection = velocity.normalize().mul(-1);
        return frictionDirection.mul(frictionMagnitude);
    }

    private void applyMovement() {
        float deltaTime = (float) owner.getDeltaTime();
        velocity = velocity.add(acceleration.mul(deltaTime));

        C_Transform2D transform = owner.getComponent(C_Transform2D.class);
        if (transform != null) {
            transform.move(velocity);
        }

        acceleration = new Vector2D();
    }

    // Public methods
    public void addForce(Vector2D force) {
        acceleration = acceleration.add(force.div(mass));
    }

    public float getNormalForce() {
        return mass * gravity;
    }

    public void setGrounded(boolean grounded) {
        isGrounded = grounded;
    }

    // Getters
    public Vector2D getVelocity() {
        return velocity;
    }
    public Vector2D getAcceleration() {
        return acceleration;
    }
    public float getMass() {
        return mass;
    }
    public float getDrag() {
        return drag;
    }
    public float getGravity() {
        return gravity;
    }
    public float getFriction() {
        return friction;
    }

    // Setters
    public void setVelocity(Vector2D velocity) {
        this.velocity = velocity;
    }
    public void setAcceleration(Vector2D acceleration) {
        this.acceleration = acceleration;
    }
    public void setMass(float mass) {
        this.mass = Math.max(mass, 0.01f);
    }
    public void setDrag(float drag) {
        this.drag = drag;
    }
    public void setGravity(float gravity) {
        this.gravity = gravity;
    }
    public void setFriction(float friction) {
        this.friction = friction;
    }
}
