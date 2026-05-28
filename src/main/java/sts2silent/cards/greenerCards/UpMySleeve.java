package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.common.ReduceCostAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class UpMySleeve extends AbstractEasyCard {
    public final static String ID = makeID("UpMySleeve");
    // intellij stuff skill, self, uncommon, , , , , 3, 1

    public UpMySleeve() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(new Shiv2(), magicNumber);
        atb(new ReduceCostAction(this));
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}