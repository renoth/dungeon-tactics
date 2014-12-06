package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import de.renoth.dt.screen.game.GameWorld;

public class SimpleActor extends Actor {
    public final Texture tex;
    protected final GameWorld gameWorld;
    private int width, height;

    public SimpleActor(int x, int y, int width, int height, GameWorld gameWorld, Texture tex) {
        this.tex = tex;
        this.width = width;
        this.height = height;
        this.gameWorld = gameWorld;

        setPosition(x,y);

        setOrigin(x, y);
        setBounds(getX(), getY(), width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(tex, getX(), getY(), width, height);
    }
}
