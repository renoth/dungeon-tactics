package de.renoth.dt.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class DescriptionHoverListener extends ClickListener {

    private DescriptionBox descriptionBox;

    public DescriptionHoverListener(DescriptionBox descriptionBox) {
        this.descriptionBox = descriptionBox;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);

        for (Label label : descriptionBox.labels) {
            label.setVisible(true);
        }
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);

        for (Label label : descriptionBox.labels) {
            label.setVisible(false);
        }
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public boolean isOver() {
        return super.isOver();
    }
}