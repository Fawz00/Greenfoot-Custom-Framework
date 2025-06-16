import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class Blast extends Particle {

    public Blast() {
        super(new GreenfootImage("blast.png"), 0.5f);
    }

    @Override
    public void addedToWorld(World world) {
        super.addedToWorld(world);
        getCurrentWorld().getFollowCamera().shakeCamera(1, 16);
    }

    public void act() {
        super.act();
    }
}
