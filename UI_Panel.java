import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)
import greenfoot.collision.ibsp.Rect;

public class UI_Panel extends UIBase
{
    private String text;
    private int width;
    private int height;
    private int fontSize;
    private Color lineColor = Color.BLACK;
    private Color backgroundColor = Color.WHITE;
    private GreenfootImage image;
    private int borderThickness = 1;
    private static final Color transparent = new Color(0,0,0,0);

    public UI_Panel(String text, int width, int height, int fontSize)
    {
        this.text = text;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
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

    public void setBackgroundImage(GreenfootImage backgroundImage)
    {
        if (backgroundImage != null) {
            this.backgroundColor = transparent; // Set to transparent if an image is used
            this.image = backgroundImage;
            this.width = backgroundImage.getWidth();
            this.height = backgroundImage.getHeight();
        } else {
            this.image = null;
        }
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

        if (image != null) {
            img.drawImage(image, 0, 0);
        }

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
    }
}