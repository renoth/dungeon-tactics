package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.GameScreen;
import de.renoth.dt.screen.game.GameWorld;
import javafx.geometry.Point2D;

public class DamageLabelActor extends Actor {
    private PositionedLabel label;

    public DamageLabelActor(GameWorld gameWorld) {
        gameWorld.stage.fg.addActor(this);

        label = new PositionedLabel("0", new Label.LabelStyle(Resources.mplus36, Color.BLACK), new Point2D(10, 10));
        label.setPosition(getX() + 10, getY() + 30);

        setVisible(false);
    }

    @Override
    public void setPosition(float x, float y) {
        super.setPosition(x, y);
        label.setPosition(getX() + 10, getY() + 30);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(1, 1, 1, getColor().a);
        batch.draw(Resources.explosion, getX(), getY(), 64, 64);

        label.getFont().setColor(label.getStyleColor());

        label.getFont().draw(batch, label.getText().toString(), label.getX(), label.getY());

        label.getFont().setColor(Color.WHITE);
        batch.setColor(1, 1, 1, 1);
    }

    public void animateDamage(Actor actor, int damage) {
        DamageLabelActor newActor = new DamageLabelActor(GameScreen.getGameWorld());
        newActor.setVisible(true);
        newActor.setColor(1, 1, 1, 1);
        newActor.label.setText(damage + "");
        newActor.setPosition(actor.getX(), actor.getY());
        newActor.addAction(Actions.moveTo(newActor.getX(), newActor.getY() + 64, 0.5f));
        newActor.addAction(Actions.sequence(Actions.delay(0.3f), Actions.alpha(0f, 0.3f), Actions.removeActor(newActor)));
    }
}
