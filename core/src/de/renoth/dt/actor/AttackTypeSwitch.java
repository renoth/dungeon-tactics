package de.renoth.dt.actor;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import de.renoth.dt.domain.IDescribable;
import de.renoth.dt.domain.StyledText;
import de.renoth.dt.domain.enums.AttackType;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.game.GameWorld;

import java.util.ArrayList;
import java.util.List;

public class AttackTypeSwitch extends ActorWithDescription {

    private AttackType attackType;

    public AttackTypeSwitch(int x, int y, int width, int height, final GameWorld gameWorld, Texture tex, final AttackType attackType) {
        super(x, y, width, height, gameWorld, tex);
        this.attackType = attackType;

        addListener(new InputListener(){
            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button) {
                gameWorld.hero.setAttackType(attackType);
                return false;
            }
        });

        createDescriptionBox(getEntity());
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {

        super.draw(batch, parentAlpha);
        if (attackType == gameWorld.hero.getAttackType()) {
            batch.draw(Resources.selectedFrame, getX(), getY());
        }
        batch.setColor(Color.WHITE);
    }

    @Override
    protected IDescribable getEntity() {
        return new AttackTypeDescription(attackType);
    }

    private class AttackTypeDescription implements IDescribable {
        public AttackTypeDescription(AttackType attackType) {
        }

        @Override
        public List<StyledText> getDescription() {
            ArrayList<StyledText> description = new ArrayList<>();

            description.add(new StyledText("Attack Type: " + attackType.toString(), Resources.mplus20, Color.WHITE));
            return description;
        }
    }
}
