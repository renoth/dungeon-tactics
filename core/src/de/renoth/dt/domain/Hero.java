package de.renoth.dt.domain;


import com.badlogic.gdx.graphics.Color;
import de.renoth.dt.domain.enums.StatType;
import de.renoth.dt.domain.stats.Damage;
import de.renoth.dt.domain.stats.Defense;
import de.renoth.dt.domain.stats.Health;
import de.renoth.dt.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class Hero implements IDescribable, IKillable {
    private final String name;
    int level;
    long xp;

    Health health;
    Defense baseDefense;
    Damage baseDamage;

    public Hero(String name) {
        this.name = name;
        level = 1;
        xp = 0;

        health = new Health(100, StatType.HEALTH, this);
        baseDefense = new Defense(0,StatType.DEFENSE);
        baseDamage = new Damage(2,StatType.DAMAGE);
    }

    @Override
    public List<StyledText> getDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText(name, Resources.mplus20, Color.WHITE));
        description.add(new StyledText("Health: " + health.getValue(), Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Damage: " + baseDamage.getValue(), Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Defense: " + baseDefense.getValue(), Resources.mplus12, Color.WHITE));

        return description;
    }

    public int dealDamage() {
        //TODO add bonuses
        return baseDamage.getValue();
    }

    public int getDefense() {
        return baseDefense.getValue();
    }

    @Override
    public void die() {
        //TODO game ends
    }
}
