package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.powers.AfterImagePower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Afterimage extends AbstractEasyCard {
    public final static String ID = makeID("Afterimage");
    // intellij stuff power, self, rare, , , , , 1, 

    public Afterimage() {
        super(ID, 1, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new AfterImagePower(p, 1));
    }

    public void upp() {
        isInnate = true;
    }
}