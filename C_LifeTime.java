
public class C_LifeTime extends Component {
    private C_EventManager eventManager;
    private long startTime;
    private float lifeTime = 0;
    private float maxLifeTime = -1;
    
    public C_LifeTime() {
        // Do nothing
    }
    public C_LifeTime(float maxLifeTime) {
        setMaxLifeTime(maxLifeTime);
    }
    
    public void setMaxLifeTime(float maxLifeTime) {
        this.maxLifeTime = maxLifeTime;
    }
    
    public void start() {
        super.start();
        eventManager = owner.getComponent(C_EventManager.class);
        startTime = System.currentTimeMillis();
        lifeTime = 0;
    }
    
    public void update() {
        super.update();
        lifeTime = (System.currentTimeMillis() - startTime) / 1000.0f;
        if(maxLifeTime > -1 && lifeTime >= maxLifeTime) {
            if(eventManager != null) {
                eventManager.dispatchEvent(Enumerations.Event.DESTROY, null);
                return;
            } else {
                owner.destroy();
                return;
            }
        }
    }
    
    public float getLifeTime() {
        return lifeTime;
    }
    public float getMaxLifeTime() {
        return maxLifeTime;
    }
    
}
