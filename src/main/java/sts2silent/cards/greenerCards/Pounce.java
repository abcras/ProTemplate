package sts2silent.cards.greenerCards;

import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;
import sts2silent.powers.FreeSkillPower;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Pounce extends AbstractEasyCard {
    public final static String ID = makeID("Pounce");
    // intellij stuff attack, enemy, uncommon, 14, 6, , , , 

    public Pounce() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 14;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        applyToSelf(new FreeSkillPower(p, 1));
    }

    public void upp() {
        upgradeDamage(6);

    }
}