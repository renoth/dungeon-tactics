package de.renoth.dt.domain;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import de.renoth.dt.actor.EnemyActor;
import de.renoth.dt.actor.SimpleActor;
import de.renoth.dt.common.GameStats;
import de.renoth.dt.domain.enums.AttackType;
import de.renoth.dt.domain.enums.EnemyType;
import de.renoth.dt.res.Resources;
import de.renoth.dt.res.SoundResources;
import de.renoth.dt.screen.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class Enemy implements IDescribable, IKillable {

    int level;
    long xp;
    int health;
    int baseDefense;
    int baseDamage;
    EnemyType type;
    List<AttackType> resistances;
    AttackType weakness;
    private String name;

    public Enemy(String name, EnemyType type) {
        this.name = name;
        this.type = type;
        this.health = type.getHealth();
        this.baseDamage = type.getBaseDamage();
        this.baseDefense = type.getBaseDefense();
    }

    @Override
    public List<StyledText> getDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText(name, Resources.mplus20, Color.WHITE));
        description.add(new StyledText("Level  : " + level, Resources.mplus12, Color.WHITE));
        description.add(new StyledText("XP     : " + xp, Resources.mplus12, Color.WHITE));
        description.add(new StyledText("HP     : " + health, Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Damage : " + baseDamage, Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Defense: " + baseDefense, Resources.mplus12, Color.WHITE));

        return description;
    }

    public void levelUp() {
        double random = Math.random();
        if (random < 0.55d) {
            health += 5;
        } else if (random < 0.8d) {
            baseDamage += 2;
        } else if (random < 0.95d) {
            baseDefense += 2;
            xp++;
        } else {
            //gnihihi
            health += 10;
            baseDamage += 2;
            baseDefense += 2;
            xp++;
            xp++;
        }
        level++;
        xp += level;
    }

    public int takeDamage(Hero hero, EnemyActor victim) {
        //the enemy gets attacked
        int damage = (int) Math.max(0, Math.round(hero.dealDamage() - baseDefense) * applyResistance(hero) * applyWeakness(hero));
        GameScreen.getGameWorld().getDamageLabelActor().animateDamage(victim, damage);
        GameStats.damageDealt += damage;
        health -= damage;
        switch (hero.getAttackType()) {
            case SLICE:
                SoundResources.sword.play();
                break;
            case STAB:
                SoundResources.knife.play();
                break;
            case BLUDGEON:
                SoundResources.club.play();
                break;
        }

        return health;
    }

    private int applyWeakness(Hero hero) {
        return hero.attackType == weakness ? 2 : 1;
    }

    private float applyResistance(Hero hero) {
        return resistances.contains(hero.getAttackType()) ? 0.5f : 1f;
    }

    public void attack(Hero hero) {
        //the hero gets attacked
        int damage = Math.max(getDamage() - hero.getDefense(), 0);
        GameStats.damageTaken += damage;
        hero.health.setBaseValue(hero.health.getBaseValue() - damage);

        GameScreen.getGameWorld().getDamageLabelActor().animateDamage(GameScreen.getGameWorld().heroActor, damage);

        GameScreen.getGameWorld().heroActor.createDescriptionBox(hero);

        if (hero.health.getValue() <= 0) {
            GameStats.writeScoreToDisk();
            GameStats.reset();

            SoundResources.gameOver.play();
            //SoundResources.gitarrenmusik.stop();

            GameScreen.getGameWorld().heroDied = true;
            GameScreen.getGameWorld().stage.addActor(new SimpleActor(0, 0, 1280, 800, GameScreen.getGameWorld(), Resources.deathBanner));
            Label label = new Label("Press N to start a new game", new Label.LabelStyle(Resources.mplus20, Color.WHITE));
            label.setPosition(500, 100);
            GameScreen.getGameWorld().stage.addActor(label);
        } else {
            SoundResources.takeDamage.play();
        }
    }

    public int getDamage() {
        return baseDamage;
    }

    @Override
    public void die() {

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

    public List<AttackType> getResistances() {
        return resistances;
    }

    public void setResistances(List<AttackType> resistances) {
        this.resistances = resistances;
    }

    public void setWeakness(AttackType at) {
        this.weakness = at;
    }
}
