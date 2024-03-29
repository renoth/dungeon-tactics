package de.renoth.dt.screen.game;

import com.badlogic.gdx.graphics.Texture;
import de.renoth.dt.actor.ActorWithDescription;
import de.renoth.dt.domain.Hero;
import de.renoth.dt.domain.IDescribable;


public class HeroActor extends ActorWithDescription {

    private Hero hero;

    public HeroActor(int x, int y, int width, int height, final GameWorld gameWorld, Texture tex, Hero hero) {
        super(x, y, width, height, gameWorld, tex);

        this.hero = hero;

        createDescriptionBox(hero);
    }

    @Override
    protected IDescribable getEntity() {
        return hero;
    }

    public Hero getHero() {
        return hero;
    }
}
