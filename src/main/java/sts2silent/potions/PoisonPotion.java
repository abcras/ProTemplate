package sts2silent.potions;

import basemod.BaseMod;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import sts2silent.CharacterFile;
import sts2silent.ModFile;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.helpers.PowerTip;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class PoisonPotion extends AbstractEasyPotion {
    public static String ID = makeID("PoisonPotion");

    public PoisonPotion() {
        super(ID, PotionRarity.COMMON, PotionSize.M,
                new Color(0.2f, 0.8f, 0.0f, 1f),
                new Color(0.2f, 0.8f, 0.0f, 1f),
                new Color(0.2f, 0.4f, 0.0f, 1f),
                CharacterFile.Enums.STS2Silent, ModFile.characterColor);
        this.isThrown = true;
        this.targetRequired = true;
    }


    public int getPotency(int ascensionLevel) {
        return 6;
    }

    public void use(AbstractCreature creature) {

        applyToEnemy((AbstractMonster) creature, new PoisonPower(creature, AbstractDungeon.player, potency));
        //applyToSelf(new StrengthPower(adp(), potency));
    }

    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1];
    }

    public void addAdditionalTips() {

        tips.add(new PowerTip(BaseMod.getKeywordTitle("poison"), BaseMod.getKeywordDescription("poison")));
    }
}
