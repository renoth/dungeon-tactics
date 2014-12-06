package de.renoth.dt.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.game.GameWorld;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class DescriptionBox extends Actor {

    public static final int ROW_HEIGHT = 28;

    private final Texture tex;
    public List<PositionedLabel> labels;
    private int width, height;

    public DescriptionBox(int x, int y, Texture tex, List<StyledText> description, GameWorld gameWorld) {
        int maxHeight = description.size() * ROW_HEIGHT;
        this.tex = tex;
        this.width = 400;
        this.height = maxHeight + 5;

        setPosition(x,y);

        setOrigin(x, y);
        setBounds(getX(), getY(), width, height);

        labels = new ArrayList<>();

        for (int i = 0; i < description.size(); i++) {
            StyledText st = description.get(i);
            PositionedLabel label = new PositionedLabel(st.text, new Label.LabelStyle(st.font, st.color), new Point2D(10, (maxHeight - 20) - ROW_HEIGHT * i));
            label.setPosition(Gdx.input.getX(), 800 - Gdx.input.getY());
            label.setVisible(false);

            label.setZIndex(1000);

            gameWorld.stage.addActor(label);
            labels.add(label);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tex, getX(), getY(), width, height);
    }

    public void updatePositions() {
        setPosition(Gdx.input.getX() + 5, 800 - Gdx.input.getY() + 5);
        for (Label l : labels) {
            l.setZIndex(1000);
            l.setPosition(Gdx.input.getX(), 800 - Gdx.input.getY());
        }
    }
}
