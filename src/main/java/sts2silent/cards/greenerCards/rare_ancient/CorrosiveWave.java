package sts2silent.cards.greenerCards.rare_ancient;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.powers.CorrosiveWavePower;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class CorrosiveWave extends AbstractEasyCard {
    public final static String ID = makeID("CorrosiveWave");
    // intellij stuff skill, self, rare, , , , , 2, 1

    public CorrosiveWave() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new CorrosiveWavePower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}