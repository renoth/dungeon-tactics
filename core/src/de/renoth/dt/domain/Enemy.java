package de.renoth.dt.domain;

import com.badlogic.gdx.graphics.Color;
import de.renoth.dt.actor.EnemyActor;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class Enemy implements IDescribable {

    private String name;
    int level;
    long xp;
    int health;
    int baseDefense;
    int baseDamage;
    EnemyType type;

    public Enemy(String name, EnemyType type) {
        this.name = name;
        this.type = type;
        this.health = type.getHealth();
        this.baseDamage = type.getBaseDamage();
        this.baseDefense = type.getBaseDefense();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public long getXp() {
        return xp;
    }

    public void setXp(long xp) {
        this.xp = xp;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBaseDefense() {
        return baseDefense;
    }

    public void setBaseDefense(int baseDefense) {
        this.baseDefense = baseDefense;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public void setBaseDamage(int baseDamage) {
        this.baseDamage = baseDamage;
    }

    public EnemyType getType() {
        return type;
    }

    public void setType(EnemyType type) {
        this.type = type;
    }

    @Override
    public List<StyledText> getDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText(name, Resources.mplus20, Color.WHITE));
        description.add(new StyledText("Level: " + level, Resources.mplus12, Color.WHITE));
        description.add(new StyledText("HP: " + health, Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Damage: " + baseDamage, Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Defense: " + baseDefense, Resources.mplus12, Color.WHITE));

        return description;
    }

    public void levelUp() {
        double random = Math.random();
        if (random < 0.55d) {
            health += 3;
        } else if (random < 0.8d) {
            baseDamage += 1;
        } else if (random < 0.95d) {
            baseDefense += 1;
        } else {
            //gnihihi
            health += 5;
            baseDamage += 1;
            baseDefense += 1;
        }
        level++;
    }

    public int takeDamage(Hero hero, EnemyActor victim) {
        int damage = hero.dealDamage();
        GameScreen.getGameWorld().getDamageLabelActor().animateDamage(victim, damage);
        health -= Math.max(0, (damage - baseDefense));
        return health;
    }

    public void attack(Hero hero) {
        int damage = getDamage() - hero.getDefense();
        hero.health -= damage;

        GameScreen.getGameWorld().getDamageLabelActor().animateDamage(GameScreen.getGameWorld().heroActor, damage);

        GameScreen.getGameWorld().heroActor.createDescriptionBox(hero);

        //TODO check for death
    }

    public int getDamage() {
        return baseDamage;
    }
}
