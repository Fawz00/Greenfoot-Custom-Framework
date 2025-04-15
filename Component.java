
public abstract class Component {
    protected volatile ActorComponent owner;
    
    public void setOwner(ActorComponent e) {
        this.owner = e;
    }
    
    public ActorComponent getOwner() {
        return owner;
    }
    
    public void start() {}
    public synchronized void update() {}
    public void remove() {}
}
