public class S_ChildActor extends Structure {
    private ActorBase actor;
    private Transform2D localTransform;

    public S_ChildActor(ActorBase actor, Transform2D localTransform) {
        this.actor = actor;
        this.localTransform = localTransform;
    }
    public S_ChildActor(ActorBase actor) {
        this(actor, new Transform2D());
    }

    public ActorBase getActor() {
        return actor;
    }
    public Transform2D getLocalTransform() {
        return localTransform;
    }
}
