package sts2silent.cards.greenerCards.rare_ancient;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.powers.SerpentFormPower;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class SerpentForm extends AbstractEasyCard {
    public final static String ID = makeID("SerpentForm");
    // intellij stuff power, self, rare, , , , , 4, 2

    public SerpentForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SerpentFormPower(p, magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(2);

    }
}