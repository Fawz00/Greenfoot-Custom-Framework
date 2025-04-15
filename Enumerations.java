public class Enumerations {
    public static enum Event {
        NULL,
        COLLIDED,
        DESTROY,
        FIRE,
        HIT,
        DEAD,
        HEALED,
        ATTACK,
        DAMAGED,
        CRIT_HIT,

        // UI Events
        UI_HOVER,
        UI_CLICK,
        UI_DRAG,
        UI_DROP,
        UI_SCROLL,
        UI_FOCUS,
        UI_UNFOCUS,
    }

    public static enum CollisionLayer {
        UNKNOWN,
        ENTITY,
        WORLD,
        CAMERA,
        UI
    }

    public static enum Tag {
        PLAYER,
        CAMERA,
        WALL,
        BACKGROUND,
        UI
    }

    public static enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }

    public static enum AnimationState {
        IDLE,
        WALK,
        RUN,
        ATTACK,
        SKILL,
        ULTIMATE,
        MAGIC,
        DEAD
    }

    public static enum DeathType {
        UNKNOWN,
        KILLED,
        POISONED,
        BURN,
        DROWN,
        STARVE,
        FALL,
        CRUSH, // Crushed by a block
        SUFFOCATE, // Suffocated in a wall
        EXPLODE,
        VOID, // Fall into the void
        DISINTEGRATE, // Disintegrated by a laser
        ELECTROCUTE, // Electrocuted by a wire
        MELT, // Melted by a heat source
        MAGIC, // Killed by magic
        CURSE // Cursed to death
    }
}
