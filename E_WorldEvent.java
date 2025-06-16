import greenfoot.Actor;

public interface E_WorldEvent extends EventListener {
    public void onActorAdded(Actor actor);
    public void onActorRemoved(Actor actor);
}
