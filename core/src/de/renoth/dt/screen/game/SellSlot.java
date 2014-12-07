package de.renoth.dt.screen.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class SellSlot extends Actor {
    private Texture texture;

    public SellSlot(int x, int y, int widtg, int height, final GameWorld gameWorld, Texture texture) {
        this.texture = texture;
        setPosition(x, y);
        setSize(widtg, height);

        addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                if (gameWorld.selectedItem != null) {
                    gameWorld.hero.sellItem(gameWorld.selectedItem);
                    gameWorld.setSelectedItem(null);

                    gameWorld.heroActor.createDescriptionBox(gameWorld.hero);
                }

                return false;
            }
        });
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), 40, 40);
    }
}
