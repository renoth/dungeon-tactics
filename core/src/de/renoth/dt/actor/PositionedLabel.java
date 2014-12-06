package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import javafx.geometry.Point2D;

import java.awt.geom.Dimension2D;

/**
 * User: hans
 * Date: 12/6/14
 */
public class PositionedLabel extends Label {

    private Point2D relativePosition;

    public PositionedLabel(CharSequence text, LabelStyle style, Point2D relativePosition) {
        super(text, style);
        this.relativePosition = relativePosition;
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x + (float) relativePosition.getX(), y + (float) relativePosition.getY());
    }
}
