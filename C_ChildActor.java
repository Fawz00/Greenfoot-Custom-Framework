import java.util.List;
import java.util.Vector;

public class C_ChildActor extends Component {
    private final List<S_ChildActor> childs = new Vector<S_ChildActor>();

    public C_ChildActor() {
        super();
    }

    public void start() {
        super.start();
        for (S_ChildActor child : childs) {
            ActorBase actor = child.getActor();
            if (actor != null && actor.getComponent(C_ChildActor.class) != null) {
                actor.getComponent(C_ChildActor.class).start();
            }
        }
    }

    public void update() {
        super.update();
        Transform2D parentTransform = this.getOwner().getComponent(C_Transform2D.class).getTransform();
        updateChildrenRecursive(parentTransform);
    }

    private void updateChildrenRecursive(Transform2D parentTransform) {
        for (S_ChildActor child : childs) {
            ActorBase actor = child.getActor();
            Transform2D localTransform = child.getLocalTransform();
            Transform2D globalTransform = composeTransform(parentTransform, localTransform);

            actor.transformComponent.setLocation(globalTransform.location);
            actor.transformComponent.setRotation(globalTransform.rotation);
            actor.transformComponent.setScale(globalTransform.scale);

            if (actor.getComponent(C_ChildActor.class) != null) {
                actor.getComponent(C_ChildActor.class).updateChildrenRecursive(globalTransform);
            }
        }
    }

    private Transform2D composeTransform(Transform2D parent, Transform2D local) {
        // Compose transforms: location, rotation, scale
        Transform2D result = new Transform2D();
        result.location = parent.location.add(local.location);
        result.rotation = parent.rotation + local.rotation;
        result.scale = parent.scale.mul(local.scale);
        return result;
    }

    public void addChild(ActorBase actor, Transform2D localTransform) {
        childs.add(new S_ChildActor(actor, localTransform));
    }

    public void addChild(ActorBase actor) {
        childs.add(new S_ChildActor(actor));
    }

    public void removeChild(ActorBase actor) {
        childs.removeIf(child -> child.getActor() == actor);
    }
}
