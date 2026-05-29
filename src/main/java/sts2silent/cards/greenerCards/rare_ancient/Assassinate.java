package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.powers.VulnerablePower;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class Assassinate extends AbstractEasyCard {
    public final static String ID = makeID("Assassinate");
    // intellij stuff attack, enemy, rare, 10, 3, , , 1, 1

    public Assassinate() {
        super(ID, 0, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 10;
        baseMagicNumber = magicNumber = 1;
        exhaust = true;
        isInnate = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
        applyToEnemy(m, new VulnerablePower(m, magicNumber, false));
    }

    public void upp() {
        upgradeDamage(3);
        upgradeMagicNumber(1);

    }
}