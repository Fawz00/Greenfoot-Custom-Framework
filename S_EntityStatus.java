public class S_EntityStatus extends Structure {
    public int maxHP;
    public int currentHP;
    public int attack;
    public int defense;
    public float critRate;
    public float critDamage;
    public boolean isImmortal;

    public S_EntityStatus() {
        this(100, 10, 5, 10, 150, false);
    }
    public S_EntityStatus(int maxHP, int attack, int defense, float critRate, float critDamage) {
        this(maxHP, attack, defense, critRate, critDamage, false);
    }
    public S_EntityStatus(boolean isImmortal) {
        this(100, 10, 5, 10, 150, true);
    }
    public S_EntityStatus(int maxHP, int attack, int defense, float critRate, float critDamage, boolean isImmortal) {
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.attack = attack;
        this.defense = defense;
        this.critRate = critRate;
        this.critDamage = critDamage;
        this.isImmortal = isImmortal;
    }
}
