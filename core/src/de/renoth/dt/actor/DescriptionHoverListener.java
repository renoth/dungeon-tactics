package de.renoth.dt.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class DescriptionHoverListener extends ClickListener {

    private DescriptionBox descriptionBox;

    public DescriptionHoverListener() {
    }

    public void setDescriptionBox(DescriptionBox descriptionBox) {
        this.descriptionBox = descriptionBox;
    }

    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);
        descriptionBox.setVisible(true);
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);
        descriptionBox.setVisible(false);
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
