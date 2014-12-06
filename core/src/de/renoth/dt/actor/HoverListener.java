package de.renoth.dt.actor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;


public class HoverListener extends ClickListener {
    @Override
    public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
        super.enter(event, x, y, pointer, fromActor);

        System.out.println("Entering entity " + x + " " + y);
        isOver();
    }

    @Override
    public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
        super.exit(event, x, y, pointer, toActor);

        System.out.println("Exiting entity " + x + " " + y);
    }

    @Override
    public void clicked(InputEvent event, float x, float y) {
        super.clicked(event, x, y);

        System.out.println("Click " + x + " " + y);
    }

    @Override
    public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {


        System.out.println("Touch " + x + " " + y);

        return super.touchDown(event, x, y, pointer, button);
    }

    @Override
    public boolean isOver() {
        return super.isOver();
    }
}
