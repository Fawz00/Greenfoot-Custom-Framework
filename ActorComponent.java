import java.util.List;
import java.util.Vector;

import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public abstract class ActorComponent extends Actor {
    // Must be volatile to ensure that the value is always read from memory and not from a thread cache
    private volatile boolean valid = true;
    private volatile List<Component> components = new Vector<Component>();

    public ActorComponent() {
        super();
        setImage(new GreenfootImage("engine/unknown_actor.png"));
    }

    // Must be synchronized to ensure that the method is not called by multiple threads at the same time
    public synchronized void act() {
        for (Component component : new Vector<>(components)) {
            component.update();
            if(!isValid()) return;
        }
    }

    public void addComponent(Component component) {
        Class<?> componentType = component.getClass();
        for (Component existingComponent : components) {
            if (existingComponent.getClass() == componentType) {
                System.out.println("Component of type " + componentType + " already exists");
                return;
            }
        }
        component.setOwner(this);
        components.add(component);
        component.start();
    }
    public <T extends Component> T getComponent(Class<T> type) {
        for (Component component : components) {
            if (type.isInstance(component)) {
                return type.cast(component);
            }
        }
        return null;
    }
    public <I> I getComponentIfImplements(Class<I> interfaceType) {
        for (Component component : components) {
            if(interfaceType.isInstance(component)) {
                return interfaceType.cast(component);
            }
        }
        return null;
    }
    public <T extends Component> void removeComponent(Class<T> type) {
        for (Component component : components) {
            if (type.isInstance(component)) {
                component.remove();
                components.remove(component);
                return;
            }
        }
    }
    public <T extends Component> void removeComponents() {
        for (Component component : components) {
            component.remove();
            components.remove(component);
            return;
        }
    }
    public synchronized void destroy() {
        if(getWorld() == null) {
            return;
        }
        removeComponents();
        valid = false;
        getWorld().removeObject(this);
    }
    public MainWorld getCurrentWorld() {
        return (MainWorld) getWorld();
    }
    protected double getDeltaTime() {
        if (getCurrentWorld() == null) return 0;
        return getCurrentWorld().getDeltaTime();
    }
    public synchronized boolean isValid() {
        return getWorld() != null && valid;
    }
}
