package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;

public class DescriptionBox extends Actor {
    private final Texture tex;
    private int width, height;
    List<StyledText> description;

    public DescriptionBox(int x, int y, Texture tex) {
        this.tex = tex;
        this.width = 200;
        this.height = 200;

        setPosition(x,y);

        setOrigin(x, y);
        setBounds(getX(), getY(), width, height);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if (true) batch.draw(tex, getX(), getY(), width, height);
    }
}
