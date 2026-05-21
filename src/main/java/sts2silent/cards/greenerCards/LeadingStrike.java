package sts2silent.cards.greenerCards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.AbstractEasyCard;

import static sts2silent.CharacterFile.Enums.Greener;
import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.atb;
import static sts2silent.util.Wiz.makeInHand;

public class LeadingStrike extends AbstractEasyCard {
    public final static String ID = makeID(LeadingStrike.class.getSimpleName());
    // intellij stuff skill, self, uncommon, , , , , ,

    //constructed on Beta 0.104.0

    public LeadingStrike() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY); // This card is a 1 cost Uncommon Skill that targets the Self.
        cardsToPreview = new Shiv2(); // Preview a Shiv when hovering over this card.
        //Unnecessary
        color = Greener;
        this.baseDamage = 3;
        //this. = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        makeInHand(new Shiv2());
        makeInHand(new Shiv2());
    }

    @Override
    public void upp() {
        upgradeDamage(3);
    }

}