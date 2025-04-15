public class FollowCamera extends ActorComponent {
    private ActorBase target;
    private Vector2D offset;
    private float lerp = 0.5f; // For smooth camera movement
    
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
        setImage("empty.png");
    }
    
    public void act() {
        super.act();
        move();
    }
    public void move() {
        if(target.isValid()) {
            float dx = target.getX() - getX() + (float)offset.x;
            float dy = target.getY() - getY() + (float)offset.y;
            Vector2D location = new Vector2D(dx * lerp, dy * lerp);
            target.getCurrentWorld().moveWorld(location.negate());
        }
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
