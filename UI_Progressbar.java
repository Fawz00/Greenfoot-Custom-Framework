import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

public class UI_Progressbar extends UIBase
{
    private int value;
    private int maxValue;
    private int width;
    private int height;
    private int fontSize;
    private Color lineColor = Color.BLACK;
    private Color fillColor = Color.GREEN;
    private Color backgroundColor = Color.WHITE;
    private static final Color transparent = new Color(0,0,0,0);

    public UI_Progressbar(int value, int maxValue, int width, int height, int fontSize)
    {
        this.value = value;
        this.maxValue = maxValue;
        this.width = width;
        this.height = height;
        this.fontSize = fontSize;
        updateImage();
    }

    public void setValue(int value)
    {
        this.value = Math.max(0, Math.min(value, maxValue));
        updateImage();
    }

    public void setMaxValue(int maxValue)
    {
        this.maxValue = maxValue;
        if (value > maxValue) value = maxValue;
        updateImage();
    }

    public void setLineColor(Color lineColor)
    {
        this.lineColor = lineColor;
        updateImage();
    }

    public void setFillColor(Color fillColor)
    {
        this.fillColor = fillColor;
        updateImage();
    }

    public void setBackgroundColor(Color backgroundColor)
    {
        this.backgroundColor = backgroundColor;
        updateImage();
    }

    private void updateImage()
    {
        GreenfootImage img = new GreenfootImage(width, height);
        img.setColor(backgroundColor);
        img.fillRect(0, 0, width, height);

        int barWidth = (int)((double)value / maxValue * (width - 2));
        img.setColor(fillColor);
        img.fillRect(1, 1, barWidth, height - 2);

        img.setColor(lineColor);
        img.drawRect(0, 0, width - 1, height - 1);

        // Draw value as text (optional)
        String text = value + " / " + maxValue;
        GreenfootImage textImg = new GreenfootImage(text, fontSize, lineColor, transparent);
        img.drawImage(textImg, (width - textImg.getWidth()) / 2, (height - textImg.getHeight()) / 2);

        setImage(img);
    }
}