import java.util.ArrayList;
import java.util.List;

public class C_EventManager extends Component {
    private List<EventListener> listeners = new ArrayList<>();
    
    public C_EventManager() {
        // Do nothing
    }
    public C_EventManager(EventListener listener) {
        addListener(listener);
    }

    public void addListener(EventListener listener) {
        // Check if the listener is already in the list
        for (EventListener existingListener : listeners) {
            if (existingListener.getClass() == listener.getClass()) {
                System.out.println("Listener of type " + listener.getClass() + " already exists");
                return;
            }
        }
        listeners.add(listener);
    }
    public void removeListener(EventListener listener) {
        listeners.remove(listener);
    }
    public void removeListener(Class<?> listenerType) {
        for (EventListener listener : listeners) {
            if (listener.getClass() == listenerType) {
                listeners.remove(listener);
                return;
            }
        }
    }
    public boolean isListenerAdded(Class<?> listenerType) {
        for (EventListener listener : listeners) {
            if (listenerType.isInstance(listener)) {
                return true;
            }
        }
        return false;
    }
    public void clearListeners() {
        listeners.clear();
    }

    public void dispatchEvent(Enumerations.Event event, Object data) {
        for (EventListener listener : listeners) {
            listener.onEvent(new S_GameEvent(event, owner, data));
        }
    }
    public void dispatchEvent(Enumerations.Event event, Object data, ActorComponent source) {
        for (EventListener listener : listeners) {
            listener.onEvent(new S_GameEvent(event, source, data));
        }
    }

    public <I> I dispatchEvent(Class<I> interfaceType) {
        for (EventListener listener : listeners) {
            if(interfaceType.isInstance(listener)) {
                return interfaceType.cast(listener);
            }
        }
        return null;
    }
    public <T> void safeDispatch(Class<T> clazz, java.util.function.Consumer<T> callback) {
        T listener = dispatchEvent(clazz);
        if (listener != null) {
            callback.accept(listener);
        } else {
            System.out.println("[EventManager] Warning: No listener found for " + clazz.getSimpleName() + " in " + owner.getClass().getSimpleName());
        }
    }
    
    @Override
    public void remove() {
        super.remove();
        //clearListeners();
    }
    
}