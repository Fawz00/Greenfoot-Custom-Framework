import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Particle extends ActorBase {
    private C_LifeTime lifeTime;
    private C_Animated animated;

    public Particle(GreenfootImage image, float duration) {
        this(new GreenfootImage[]{image}, duration, 0);
    }
    public Particle(GreenfootImage[] images, float duration, float speed) {
        super();
        lifeTime = new C_LifeTime(duration);
        addComponent(lifeTime);

        animated = new C_Animated(images, false, speed);
        addComponent(animated);
    }

    public void act(){
        super.act();
    }
}
