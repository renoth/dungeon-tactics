package de.renoth.dt.domain;


import com.badlogic.gdx.graphics.Color;
import de.renoth.dt.res.Resources;

import java.util.ArrayList;
import java.util.List;

public class Hero implements IDescribable {
    private final String name;
    int level;
    long xp;
    int health;
    int baseDefense;
    int baseDamage;

    public Hero(String name) {
        this.name = name;
        level = 1;
        xp = 0;
        health = 100;
        baseDefense = 0;
        baseDamage = 5;
    }

    @Override
    public List<StyledText> getDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText(name, Resources.mplus20, Color.WHITE));

        return description;
    }
}
