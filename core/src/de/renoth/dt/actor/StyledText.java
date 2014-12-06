package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class StyledText {
    public String text;
    public BitmapFont font;
    public Color color;

    public StyledText(String text, BitmapFont font, Color color) {
        this.text = text;
        this.font = font;
        this.color = color;
    }
}
