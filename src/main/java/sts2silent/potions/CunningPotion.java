package sts2silent.potions;
//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import basemod.BaseMod;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Shiv;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PowerTip;
import com.megacrit.cardcrawl.potions.AbstractPotion;
import com.megacrit.cardcrawl.rooms.AbstractRoom.RoomPhase;
import sts2silent.CharacterFile;
import sts2silent.ModFile;

import static sts2silent.ModFile.makeID;

public class CunningPotion extends AbstractEasyPotion {
    public static String ID = makeID("CunningPotion");

    public CunningPotion() {
        super(ID, PotionRarity.UNCOMMON, PotionSize.SPIKY,
                Color.GRAY, Color.DARK_GRAY,
                null,
                CharacterFile.Enums.STS2Silent, ModFile.characterColor);
        this.isThrown = false;
        this.targetRequired = false;
    }

    public void addAdditionalTips() {

        tips.add(new PowerTip(BaseMod.getKeywordTitle("shiv"), BaseMod.getKeywordDescription("shiv")));
    }

    @Override
    public String getDescription() {
        return strings.DESCRIPTIONS[0] + potency + strings.DESCRIPTIONS[1];
    }

    public void use(AbstractCreature target) {
        AbstractCard shiv = new Shiv();
        shiv.upgrade();
        if (AbstractDungeon.getCurrRoom().phase == RoomPhase.COMBAT) {
            this.addToBot(new MakeTempCardInHandAction(shiv.makeStatEquivalentCopy(), this.potency));
        }

    }

    public int getPotency(int ascensionLevel) {
        return 3;
    }

    public AbstractPotion makeCopy() {
        return new com.megacrit.cardcrawl.potions.CunningPotion();
    }
}
