package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.powers.AccuracyPower;
import sts2silent.actions.UpdateShiv2Action;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;

public class Accuracy extends AbstractEasyCard {
    public final static String ID = makeID("Accuracy");
    // intellij stuff power, self, uncommon, , , , , 4, 2

    public Accuracy() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new ApplyPowerAction(p, p, new AccuracyPower(p, this.magicNumber), this.magicNumber));
        this.addToBot(new UpdateShiv2Action());
    }

    public void upp() {
        upgradeMagicNumber(2);
    }
}