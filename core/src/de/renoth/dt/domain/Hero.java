package de.renoth.dt.domain;


import com.badlogic.gdx.graphics.Color;
import de.renoth.dt.domain.enums.AttackType;
import de.renoth.dt.domain.enums.StatType;
import de.renoth.dt.domain.stats.BaseStat;
import de.renoth.dt.domain.stats.Damage;
import de.renoth.dt.domain.stats.Defense;
import de.renoth.dt.domain.stats.Health;
import de.renoth.dt.domain.stats.modifier.StatModifier;
import de.renoth.dt.res.Resources;
import de.renoth.dt.screen.GameScreen;

import java.util.ArrayList;
import java.util.List;

public class Hero implements IDescribable, IKillable {
    private final String name;
    int level;
    long xp;

    Health health;
    Defense defense;
    Damage damage;

    AttackType attackType;

    List<BaseStat> baseStatList;

    public Hero(String name) {
        this.name = name;
        level = 1;
        xp = 0;

        baseStatList = new ArrayList<>();
        attackType = AttackType.SLICE;

        baseStatList.add(health = new Health(100, StatType.HEALTH, this));
        baseStatList.add(defense = new Defense(0,StatType.DEFENSE));
        baseStatList.add(damage = new Damage(4,StatType.DAMAGE));
    }

    @Override
    public List<StyledText> getDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText(name, Resources.mplus20, Color.WHITE));
        description.add(new StyledText("Health: " + health.getValue(), Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Damage: " + damage.getValue(), Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Defense: " + defense.getValue(), Resources.mplus12, Color.WHITE));

        return description;
    }

    public int dealDamage() {
        //TODO add bonuses
        return damage.getValue();
    }

    public int getDefense() {
        return defense.getValue();
    }

    @Override
    public void die() {
        //TODO game ends
    }

    public void applyModifiers() {
        for (BaseStat bs : baseStatList) {
            bs.modifiers.clear();
        }
        for (Item item : GameScreen.getGameWorld().inventory.getAllEquippedItems()) {
            for (StatModifier statModifier : item.modifiers) {
                switch (statModifier.getStatType()) {
                    case HEALTH:
                        health.modifiers.add(statModifier);
                        break;
                    case DAMAGE:
                        damage.modifiers.add(statModifier);
                        break;
                    case DEFENSE:
                        defense.modifiers.add(statModifier);
                        break;
                }
            }
        }
    }

    public Item getWeapon() {
        return GameScreen.getGameWorld().inventory.weaponSlot.getItem();
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public AttackType getAttackType() {
        return attackType;
    }
}
