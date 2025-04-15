import java.util.List;
import java.util.Vector;

public class C_ChildActor extends Component {
    private volatile List<S_ChildActor> childs = new Vector<S_ChildActor>();
    
    public C_ChildActor() {
        super();
    }
    
    public void start() {
        super.start();
    }
    
    public void update() {
        super.update();
        
        // Apply parent transform to child actors
        for(S_ChildActor child : childs) {
            ActorBase actor = child.getActor();
            Transform2D localTransform = child.getLocalTransform();
            Transform2D globalTransform = actor.transformComponent.getTransform();
            
            child.getActor().transformComponent.setLocation(globalTransform.location.add(localTransform.location));
            child.getActor().transformComponent.setRotation(globalTransform.rotation + localTransform.rotation);
            child.getActor().transformComponent.setScale(globalTransform.scale.mul(localTransform.scale));
        }
    }

    public void addChild(ActorBase actor, Transform2D localTransform) {
        childs.add(new S_ChildActor(actor, localTransform));
    }
    public void addChild(ActorBase actor) {
        childs.add(new S_ChildActor(actor));
    }
    public void removeChild(ActorBase actor) {
        for(S_ChildActor child : childs) {
            if(child.getActor() == actor) {
                childs.remove(child);
                return;
            }
        }
    }
    
}
