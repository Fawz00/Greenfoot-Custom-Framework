public interface E_EntityStatus extends EventListener {
    public void onDamaged(ActorComponent source, int damage, boolean isCritical);
    public void onHit(ActorComponent source);
    public void onCritHit(ActorComponent  source, ActorComponent target, int damage);
    public void onHeal(ActorComponent  source, int amount);
    public void onDeath(ActorComponent source, Enumerations.DeathType deathType);
    public void onRevive(ActorComponent source);
}
