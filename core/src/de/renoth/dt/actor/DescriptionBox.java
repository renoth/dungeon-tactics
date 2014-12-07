package de.renoth.dt.actor;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.renoth.dt.domain.StyledText;
import de.renoth.dt.screen.game.GameWorld;
import javafx.geometry.Point2D;

import java.util.ArrayList;
import java.util.List;

public class DescriptionBox extends Actor {

    public static final int ROW_HEIGHT = 30;
    public static final int SMALL_ROW_HEIGHT = 16;

    final Texture tex;
    public List<PositionedLabel> labels;
    int width;
    int height;
    private DescriptionHoverListener hoverlistener;

    public DescriptionBox(int x, int y, Texture tex, List<StyledText> description, GameWorld gameWorld, DescriptionHoverListener hoverListener) {
        int maxHeight = ROW_HEIGHT + (description.size() - 1) * SMALL_ROW_HEIGHT;
        this.tex = tex;
        this.width = 280;
        this.height = maxHeight + 5;
        this.hoverlistener = hoverListener;

        setPosition(x,y);

        setOrigin(x, y);
        setBounds(getX(), getY(), width, height);

        labels = new ArrayList<>();

        gameWorld.stage.fg.addActor(this);

        createLabels(description, gameWorld, maxHeight);
    }

    protected void createLabels(List<StyledText> description, GameWorld gameWorld, int maxHeight) {
        for (int i = 0; i < description.size(); i++) {
            StyledText st = description.get(i);
            PositionedLabel label = new PositionedLabel(st.text, new Label.LabelStyle(st.font, st.color), new Point2D(10, maxHeight - (i > 0 ? 10 : 0) - SMALL_ROW_HEIGHT * i));
            label.setPosition(Gdx.input.getX(), 800 - Gdx.input.getY());
            label.setVisible(false);

            labels.add(label);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(hoverlistener != null && hoverlistener.isOver() && !labels.isEmpty()) {
            updatePositions();
            batch.draw(tex, getX(), getY(), width, height);
            for (PositionedLabel l : labels) {
                l.getFont().setColor(l.getStyleColor());
                l.getFont().draw(batch, l.getText().toString(), l.getX(), l.getY());
                l.getFont().setColor(Color.WHITE);
            }
        }
    }

    public void updatePositions() {
        setPosition(Gdx.input.getX() + 5, 800 - Gdx.input.getY() + 5);
        for (Label l : labels) {
            l.setPosition(Gdx.input.getX(), 800 - Gdx.input.getY());
        }
    }

    public void setLabelsInivisbleAndDispose() {
        for (Label l : labels) {
            l.setVisible(false);
            l.remove();
        }
    }

    public void destroy() {
        setLabelsInivisbleAndDispose();
        remove();
    }
}
