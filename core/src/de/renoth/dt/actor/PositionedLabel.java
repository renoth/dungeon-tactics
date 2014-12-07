package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import javafx.geometry.Point2D;

public class PositionedLabel extends Label {

    private final LabelStyle labelStyle;
    private Point2D relativePosition;

    public PositionedLabel(CharSequence text, LabelStyle style, Point2D relativePosition) {
        super(text, style);
        this.labelStyle = style;
        this.relativePosition = relativePosition;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x + (float) relativePosition.getX(), y + (float) relativePosition.getY());
    }

    public BitmapFont getFont() {
        return labelStyle.font;
    }

    Color getStyleColor() {
        return labelStyle.fontColor;
    }
}
