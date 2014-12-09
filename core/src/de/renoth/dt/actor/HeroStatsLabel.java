package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.renoth.dt.domain.StyledText;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.game.GameWorld;

import java.awt.*;
import java.util.List;

public class HeroStatsLabel extends DescriptionBox {

    public HeroStatsLabel(int x, int y, Texture tex, List<StyledText> description, GameWorld gameWorld, DescriptionHoverListener hoverListener) {
        super(x, y, tex, description, gameWorld, hoverListener);
        int maxHeight = ROW_HEIGHT * description.size();
        this.height = 200;
        setBounds(getX(), getY(), width, height);


        createLabels(description, gameWorld, maxHeight);
    }

    protected void createLabels(List<StyledText> description, GameWorld gameWorld, int maxHeight) {
        labels.clear();
        for (int i = 0; i < description.size(); i++) {
            StyledText st = description.get(i);

            //TODO draw color according to max percentage
            PositionedLabel label = new PositionedLabel(st.text, new Label.LabelStyle(Resources.mplus20, st.color), new Point(10, maxHeight - ROW_HEIGHT * i));
            label.setPosition(getX(), getY());
            labels.add(label);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tex, getX(), getY(), width, height);
        for (PositionedLabel l : labels) {
            l.getFont().draw(batch, l.getText().toString(), l.getX(), l.getY());
        }
    }

    public void updatePositions() {

    }

    public void setLabelsInivisbleAndDispose() {

    }

    public void destroy() {
        setLabelsInivisbleAndDispose();
        remove();
    }
}
