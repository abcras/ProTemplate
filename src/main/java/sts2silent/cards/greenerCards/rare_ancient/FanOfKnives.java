package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import sts2silent.actions.UpdateShiv2Action;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import sts2silent.cards.greenerCards.Shiv2;
import sts2silent.powers.FanOfKnivesPower;

import static sts2silent.ModFile.makeID;
import static sts2silent.util.Wiz.*;

public class FanOfKnives extends AbstractEasyCard {
    public final static String ID = makeID("FanOfKnives");
    // intellij stuff power, self, rare, , , , , 4, 1

    public FanOfKnives() {
        super(ID, 2, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 4;
        cardsToPreview = new Shiv2();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {

        if (!AbstractDungeon.player.hasPower(makeID("FanOfKnivesPower"))) {
            applyToSelf(new FanOfKnivesPower(p));
            this.addToBot(new UpdateShiv2Action());
        }
        makeInHand(new Shiv2(), magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(1);

    }
}