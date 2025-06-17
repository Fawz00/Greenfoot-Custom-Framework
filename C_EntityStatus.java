
public class C_EntityStatus extends Component {
    private S_EntityStatus status;
    private C_EventManager eventManager;

    public C_EntityStatus() {
        this(new S_EntityStatus());
    }
    public C_EntityStatus(S_EntityStatus status) {
        this.status = status;
    }

    public void start() {
        super.start();
        eventManager = owner.getComponent(C_EventManager.class);
        if(eventManager == null) {
            // Add a default event manager if not present
            eventManager = new C_EventManager();
            owner.addComponent(eventManager);
        }
    }

    public void setStatus(S_EntityStatus status) {
        this.status = status;
    }
    public S_EntityStatus getStatus() {
        return status;
    }

    public void applyDamage(int damage, ActorComponent source, boolean isCritical) {
        int reducedDamage = 0;
        if (!status.isImmortal) {
            reducedDamage = Math.max(damage - status.defense, 1); // At least 1 damage
            status.currentHP -= reducedDamage;
        }

        eventManager.safeDispatch(E_EntityStatus.class, ev -> ev.onHit(source));
        if (reducedDamage > 0) {
            final int damageToDispatch = reducedDamage;
            eventManager.safeDispatch(E_EntityStatus.class, ev -> ev.onDamaged(source, damageToDispatch, isCritical));
        }
        
        if (status.currentHP <= 0 && !status.isImmortal) {
            status.currentHP = 0;
            eventManager.safeDispatch(E_EntityStatus.class, ev -> ev.onDeath(source, Enumerations.DeathType.KILLED));
        }
    }

    public void attack(Entity target) {
        boolean isCritical = Math.random() < status.critRate;
        int damage = status.attack;
        if (isCritical) {
            damage += damage * status.critDamage;

            final int dmgTemp = damage;
            eventManager.safeDispatch(E_EntityStatus.class, ev -> ev.onCritHit(owner, target, dmgTemp));
        }
        C_EntityStatus other = target.getComponent(C_EntityStatus.class);
        if(other != null) other.applyDamage(damage, owner, isCritical);
    }

    public void healSelf(int amount) {
        status.currentHP = Math.min(status.currentHP + amount, status.maxHP);
        eventManager.safeDispatch(E_EntityStatus.class, ev -> ev.onHeal(owner, amount));
    }

    public void addXP(int amount) {
        status.currentXP += amount;
        if (status.currentXP >= status.maxXP) {
            levelUp();
        }
        eventManager.safeDispatch(E_EntityStatus.class, ev -> ev.onGainXP(owner, amount));
    }
    private void levelUp() {
        status.currentXP -= status.maxXP;
        status.maxXP = (int)(status.maxXP * 2f); // Increase max XP by 200%
        status.attack += 1; // Increase attack
        status.maxHP += 2; // Increase max HP
        status.currentHP = Math.min(status.currentHP + 60, status.maxHP); // Heal a bit on level up
        status.level++;

        eventManager.safeDispatch(E_EntityStatus.class, ev -> ev.onLevelUp(owner));
    }
    
}
