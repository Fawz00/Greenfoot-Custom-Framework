import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.collision.ibsp.Rect;

public class UI_Button extends UIBase
{
    private String text;
    private int width;
    private int height;
    private int fontSize;
    private Color lineColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;
    private int borderThickness = 1; // Added border thickness
    private static final Color transparent = new Color(0,0,0,0);
    private C_EventManager eventManager;

    public UI_Button(String text, int width, int height, int fontSize)
    {
        this.text = text;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        updateImage();

        eventManager = new C_EventManager();
        addComponent(eventManager);
    }

    public void handleEvent(E_UIButton event)
    {
        eventManager.addListener(event);
        updateImage();
    }

    public void setText(String value)
    {
        this.text = value;
        updateImage();
    }

    public void setLineColor(Color lineColor)
    {
        this.lineColor = lineColor;
        updateImage();
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
        updateImage();
    }

    public void setBorderThickness(int thickness) {
        this.borderThickness = Math.max(1, thickness);
        updateImage();
    }

    private void updateImage()
    {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(backgroundColor);
        img.fillRect(0, 0, width, height);

        img.setColor(lineColor);
        // Draw border with thickness
        for (int i = 0; i < borderThickness; i++) {
            img.drawRect(i, i, width - 1 - 2*i, height - 1 - 2*i);
        }

        GreenfootImage textImg = new GreenfootImage(text, fontSize, lineColor, transparent);
        img.drawImage(textImg, (width - textImg.getWidth()) / 2, (height - textImg.getHeight()) / 2);

        setImage(img);
    }

    @Override
    public void act()
    {
        super.act();
        var mouse = Greenfoot.getMouseInfo();

        if (mouse != null) if(mouse.getButton() == 1 && mouse.getActor() == this && Greenfoot.mousePressed(this)) {
            eventManager.safeDispatch(E_UIButton.class, ev -> ev.onClick(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY()));
        }
        if (mouse != null) if(mouse.getButton() == 1 && mouse.getActor() == this && !Greenfoot.mousePressed(this)) {
            eventManager.safeDispatch(E_UIButton.class, ev -> ev.onRelease(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY()));
        }
        if (mouse != null) if(mouse.getActor() == this) {
            eventManager.safeDispatch(E_UIButton.class, ev -> ev.onHover(Greenfoot.getMouseInfo().getX(), Greenfoot.getMouseInfo().getY()));
        }
    }
}