public class FollowCamera extends ActorComponent {
    private ActorBase target;
    private Vector2D offset;
    private float lerp = 0.5f; // For smooth camera movement

    private Vector2D shakeOffset;
    private double shakeDuration = 0.0;
    private int shakeIntensity = 0;
    
    public FollowCamera(ActorBase target) {
        this(target, new Vector2D());
    }
    public FollowCamera(ActorBase target, float offsetX, float offsetY) {
        this(target, new Vector2D(offsetX, offsetY));
    }
    public FollowCamera(ActorBase target, Vector2D offset) {
        super();
        this.target = target;
        this.offset = offset;
        shakeOffset = new Vector2D();
        setImage("empty.png");
    }
    
    public void act() {
        super.act();
        move();
        doCameraShake();
    }
    public void move() {
        if(target.isValid()) {
            float dx = target.getX() - getX() + (float)offset.x + (float)shakeOffset.x;
            float dy = target.getY() - getY() + (float)offset.y + (float)shakeOffset.y;
            Vector2D location = new Vector2D(dx * lerp, dy * lerp);
            target.getCurrentWorld().moveWorld(location.negate());
        }
    }
    private void doCameraShake() {
        if(shakeDuration <= 0 || shakeIntensity <= 0) {
            shakeOffset = new Vector2D();
            return;
        }

        shakeOffset.x = (float) (Math.random() * shakeIntensity - shakeIntensity / 2);
        shakeOffset.y = (float) (Math.random() * shakeIntensity - shakeIntensity / 2);
        
        shakeDuration -= getCurrentWorld().getDeltaTime();
    }

    public void shakeCamera(double duration, int intensity) {
        this.shakeDuration = duration;
        this.shakeIntensity = intensity;
    }
    
    public void setTarget(ActorBase target) {
        this.target = target;
    }
    
    public void setOffset(float offsetX, float offsetY) {
        this.offset.x = offsetX;
        this.offset.y = offsetY;
    }
    public void setOffset(Vector2D offset) {
        this.offset = offset;
    }
    
    public void setLerp(float lerp) {
        this.lerp = lerp;
    }
    
}
