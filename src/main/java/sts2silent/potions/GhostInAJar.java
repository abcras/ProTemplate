package sts2silent.potions;


import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.powers.IntangiblePlayerPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import sts2silent.CharacterFile;
import sts2silent.ModFile;

import static sts2silent.ModFile.makeID;

public class GhostInAJar extends AbstractEasyPotion {
    public static String ID = makeID("GhostInAJar");

    public GhostInAJar() {
        super(ID, PotionRarity.RARE, PotionSize.GHOST,
                Color.WHITE, Color.WHITE,
                null,
                CharacterFile.Enums.STS2Silent, ModFile.characterColor);
        this.isThrown = false;
    }

    public void addAdditionalTips() {

        tips.add(new PowerTip(BaseMod.getKeywordTitle("intangible"), BaseMod.getKeywordDescription("intangible")));
    }

    @Override
    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1];
    }

    public void use(AbstractCreature target) {

        if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT) {
            this.addToBot(new ApplyPowerAction(AbstractDungeon.player, AbstractDungeon.player, new IntangiblePlayerPower(AbstractDungeon.player, this.potency), this.potency));
        }

    }

    public int getPotency(int ascensionLevel) {
        return 1;
    }

    public AbstractPotion makeCopy() {
        return new com.megacrit.cardcrawl.potions.CunningPotion();
    }
}

