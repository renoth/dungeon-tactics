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
    public int level;
    Experience xp;
    int maxHealth;
    Health health;
    Defense defense;
    Damage damage;
    CriticalChance criticalChance;
    Random random;
    AttackType attackType;
    List<BaseStat> baseStatList;
    private String name;

    public Hero(String name) {
        this.name = name;
        level = 1;

        random = new Random();

        baseStatList = new ArrayList<>();
        attackType = AttackType.SLICE;

        maxHealth = Constants.INITIAL_HEALTH;

        baseStatList.add(health = new Health(Constants.INITIAL_HEALTH, StatType.HEALTH, this));
        baseStatList.add(defense = new Defense(0, StatType.DEFENSE));
        baseStatList.add(damage = new Damage(4, StatType.DAMAGE));
        baseStatList.add(xp = new Experience(0, StatType.EXPERIENCE));
        baseStatList.add(criticalChance = new CriticalChance(5, StatType.CRITICAL_CHANCE));
    }

    @Override
    public List<StyledText> getDescription() {
        ArrayList<StyledText> description = new ArrayList<>();

        description.add(new StyledText(name + " Level " + level, Resources.mplus20, Color.WHITE));
        description.add(new StyledText("XP     : " + xp.getBaseValue() + " / " + xpNeededForLevelUp(), Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Health : " + health.getValue() + " / " + (health.getMaxPossibleValue()), Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Damage : " + damage.getBaseValue() + " (+ " + damage.getBonus() + ")", Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Defense: " + defense.getBaseValue() + " (+ " + defense.getBonus() + ")", Resources.mplus12, Color.WHITE));
        description.add(new StyledText("Crit.% : " + criticalChance.getBaseValue() + " (+ " + criticalChance.getBonus() + ")", Resources.mplus12, Color.WHITE));

        return description;
    }

    public int dealDamage() {
        if (Math.random() * 100 < criticalChance.getValue()) {
            return damage.getValue() * 3;
        }
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
                    case CRITICAL_CHANCE:
                        criticalChance.modifiers.add(statModifier);
                        break;
                }
            }
        }
    }

    public Item getWeapon() {
        return GameScreen.getGameWorld().inventory.weaponSlot.getItem();
    }

    public AttackType getAttackType() {
        return attackType;
    }

    public void setAttackType(AttackType attackType) {
        this.attackType = attackType;
    }

    public List<BaseStat> getBaseStats() {
        return baseStatList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Experience getXp() {
        return xp;
    }

    public void addXP(int xp) {
        this.xp.setBaseValue(this.xp.getValue() + xp);

        if (this.xp.getBaseValue() >= xpNeededForLevelUp()) {
            levelUp();
        }
    }

    private void levelUp() {
        level++;

        health.setBaseValue(health.getMaxValue());

        for (int i = 1; i <= 3; i++) {
            BaseStat bs = baseStatList.get(random.nextInt(baseStatList.size()));
            if (bs instanceof Experience) {
                continue;
            } else if (bs instanceof Health) {
                System.out.println("Raised Health by 5 " + health.getMaxValue() + " -> " + (health.getMaxValue() + 5));
                bs.increaseBaseValue(5);
            } else {
                System.out.println("Raised " + bs.getStatType().getLabel() + " by 1 " + bs.getMaxValue() + " -> " + (bs.getMaxValue() + 1));
                bs.increaseBaseValue(1);
            }
        }

        SoundResources.levelUp.play();
    }

    private int xpNeededForLevelUp() {
        int xpNeeded = 0;
        for (int i = 1; i <= level; i++) {
            xpNeeded += (int) (Math.pow(i, 1.5d) * Constants.XP_PER_LEVEL_NEEDED);
        }
        return xpNeeded;
    }

    public void sellItem(Item selectedItem) {
        addXP(selectedItem.getXpValue());
    }

    public Damage getDamage() {
        return damage;
    }
}
