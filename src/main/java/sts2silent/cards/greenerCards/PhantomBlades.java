package sts2silent.cards.greenerCards;

import sts2silent.actions.UpdateShiv2Action;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;
import sts2silent.powers.PhantomBladesPower;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class PhantomBlades extends AbstractEasyCard {
    public final static String ID = makeID("PhantomBlades");
    // intellij stuff power, self, uncommon, , , , , 9, 3

    public PhantomBlades() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 9;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new PhantomBladesPower(p, magicNumber));
        this.addToBot(new UpdateShiv2Action());
    }

    public void upp() {
        upgradeMagicNumber(3);

    }
}