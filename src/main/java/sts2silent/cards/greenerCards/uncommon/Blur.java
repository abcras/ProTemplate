package sts2silent.cards.greenerCards.uncommon;

import com.megacrit.cardcrawl.powers.BlurPower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Blur extends AbstractEasyCard {
    public final static String ID = makeID("Blur");
    // intellij stuff skill, self, uncommon, , , 5, 3, , 

    public Blur() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        applyToSelf(new BlurPower(p, 1));
    }

    public void upp() {
        upgradeBlock(3);

    }
}