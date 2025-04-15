import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class C_Animated extends Component {
    private C_LifeTime lifeTime;
    private GreenfootImage[] animation;
    private float animationSpeed = 0;

    public C_Animated() {
        // Do nothing
    }
    public C_Animated(GreenfootImage[] images, boolean mirror, float speed) {
        setAnimation(images);
        setAnimationSpeed(speed);
        if (mirror) {
            mirrorAnimation();
        }
    }

    public void setAnimation(GreenfootImage[] images) {
        animation = images;
    }
    public void mirrorAnimation() {
        for (int i = 0; i < animation.length; i++) {
            animation[i].mirrorHorizontally();
        }
    }
    public void mirrorAnimation(GreenfootImage[] images) {
        for (int i = 0; i < images.length; i++) {
            images[i].mirrorHorizontally();
        }
    }
    public void setAnimationSpeed(float speed) {
        animationSpeed = speed;
    }

    public void start() {
        super.start();

        if(animation == null || animation.length == 0) {
            System.out.println("Entity " + owner.getClass().toString() + " does not have an animation. Adding one...");
            setAnimation(new GreenfootImage[]{owner.getImage()});
        }

        lifeTime = owner.getComponent(C_LifeTime.class);
        if(lifeTime == null) {
            System.out.println("Entity " + owner.getClass().toString() + " does not have an lifeTime component. Adding one...");
            owner.addComponent(new C_LifeTime());
            lifeTime = owner.getComponent(C_LifeTime.class);
        }
    }

    public void update() {
        super.update();
        applyAnimation(animationSpeed);
    }

    private void applyAnimation(float animationFPS) {
        int frame = ((int)(lifeTime.getLifeTime() * animationFPS)) % animation.length;
        if(frame < 0 || frame >= animation.length) {
            frame = 0;
        }
        owner.setImage(animation[frame]);
    }

    public float getAnimationSpeed() {
        return animationSpeed;
    }
}
