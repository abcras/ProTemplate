package sts2silent.cards.greenerCards;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Dash extends AbstractEasyCard {
    public final static String ID = makeID("Dash");
    // intellij stuff attack, enemy, uncommon, 10, 3, 10, 3, , 

    public Dash() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 10;
        baseBlock = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

    }

    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);

    }
}