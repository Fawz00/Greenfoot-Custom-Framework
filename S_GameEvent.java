public class S_GameEvent extends Structure {
    private Enumerations.Event event;
    private ActorComponent source;
    private Object data;
    
    public S_GameEvent(Enumerations.Event event, ActorComponent source, Object data) {
        this.event = event;
        this.source = source;
        this.data = data;
    }
    
    public Enumerations.Event getEvent() {
        return event;
    }

    public ActorComponent getSource() {
        return source;
    }
    
    public Object getData() {
        return data;
    }
}
