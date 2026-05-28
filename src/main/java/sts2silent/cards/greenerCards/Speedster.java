package sts2silent.cards.greenerCards;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;
import sts2silent.powers.SpeedsterPower;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Speedster extends AbstractEasyCard {
    public final static String ID = makeID("Speedster");
    // intellij stuff power, self, uncommon, , , , , 2, 1

    public Speedster() {
        super(ID, 2, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        magicNumber = baseMagicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new SpeedsterPower(p, magicNumber));
    }

    public void upp() {
        this.isInnate = true;
    }
}