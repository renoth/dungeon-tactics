package de.renoth.dt.domain;


import com.badlogic.gdx.graphics.Color;
import de.renoth.dt.common.Constants;
import de.renoth.dt.domain.enums.AttackType;
import de.renoth.dt.domain.enums.StatType;
import de.renoth.dt.domain.stats.*;
import de.renoth.dt.domain.stats.modifier.StatModifier;
import de.renoth.dt.res.Resources;
import de.renoth.dt.res.SoundResources;
import de.renoth.dt.screen.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Hero implements IDescribable, IKillable {
    private String name;
    int level;
    Experience xp;

    int maxHealth;

    Health health;
    Defense defense;
    Damage damage;

    Random random;

    AttackType attackType;

    List<BaseStat> baseStatList;

    public Hero(String name) {
        this.name = name;
        level = 1;

        random = new Random();

        baseStatList = new ArrayList<>();
        attackType = AttackType.SLICE;

        maxHealth = Constants.INITIAL_HEALTH;

        baseStatList.add(health = new Health(Constants.INITIAL_HEALTH, StatType.HEALTH, this));
        baseStatList.add(defense = new Defense(0,StatType.DEFENSE));
        baseStatList.add(damage = new Damage(4,StatType.DAMAGE));
        baseStatList.add(xp = new Experience(0,StatType.EXPERIENCE));
    }

    @Override
    public List<StyledText> getDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText(name, Resources.mplus20, Color.WHITE));
        description.add(new StyledText("XP     : " + xp.getBaseValue() + " / " + xpNeededForLevelUp(), Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Health : " + health.getValue() + " / " + (maxHealth + health.getBonus()), Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Damage : " + damage.getBaseValue() + " (+ " + damage.getBonus() + ")", Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Defense: " + defense.getBaseValue() + " (+ " + defense.getBonus() + ")", Resources.mplus12, Color.WHITE));

        return description;
    }

    public int dealDamage() {
        return damage.getValue();
    }

    public int getDefense() {
        return defense.getValue();
    }

    @Override
    public void die() {
        //legacy
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

    public List<BaseStat> getBaseStats() {
        return baseStatList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Experience getXp() {
        return xp;
    }

    public void addXP(int xp) {
        this.xp.setBaseValue(this.xp.getValue() + xp);

        if (this.xp.getBaseValue() > xpNeededForLevelUp()) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;

        health.setBaseValue(health.getMaxValue());

        for (int i = 1; i <=2 ; i++) {
            int randomIndex = random.nextInt(baseStatList.size());
            baseStatList.get(randomIndex).setBaseValue(baseStatList.get(randomIndex).getBaseValue() + 1);
        }

        SoundResources.levelUp.play();
    }

    private int xpNeededForLevelUp() {
        int xpNeeded = 0;
        for (int i = 1; i <= level; i++) {
            xpNeeded += i * Constants.XP_PER_LEVEL_NEEDED;
        }
        return xpNeeded;
    }
}
