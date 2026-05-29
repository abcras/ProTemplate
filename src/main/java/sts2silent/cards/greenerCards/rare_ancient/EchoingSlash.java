package sts2silent.cards.greenerCards.rare_ancient;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import sts2silent.actions.EchoingSlashAction;
import sts2silent.cards.AbstractEasyCard;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

import static sts2silent.ModFile.makeID;

public class EchoingSlash extends AbstractEasyCard {
    public final static String ID = makeID(EchoingSlash.class.getSimpleName());
    // intellij stuff ATTACK, ALL, RARE, 10, 3, , , ,

    public ArrayList<AbstractMonster> killedMonsters = new ArrayList<>();

    public EchoingSlash() {
        super(ID, 1, CardType.ATTACK, CardRarity.RARE, CardTarget.ALL_ENEMY);
        baseDamage = 10;
        isMultiDamage = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        EchoSlash(p);
    }

    public void EchoSlash(AbstractPlayer p) {


        this.addToBot(new SFXAction("ATTACK_HEAVY"));
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.1F));
        this.addToBot(new DamageAllEnemiesAction(p, this.multiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));

        this.addToBot(new EchoingSlashAction(this));
        /*allDmg(AbstractGameAction.AttackEffect.NONE);
        this.addToBot(new VFXAction(p, new CleaveEffect(), 0.0F));

        if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
            for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
                if (!killedMonsters.contains(mon) && (mon.isDying || mon.currentHealth <= 0)) {
                    killedMonsters.add(mon);
                    EchoSlash(p);
                    break;
                }
            }*/
    }

    public void upp() {
        upgradeDamage(3);

    }
}