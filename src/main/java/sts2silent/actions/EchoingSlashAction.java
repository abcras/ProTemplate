package sts2silent.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import sts2silent.cards.greenerCards.rare_ancient.EchoingSlash;

public class EchoingSlashAction extends AbstractGameAction {
    private AbstractCard funCard;


    public EchoingSlashAction(AbstractCard card) {
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.DAMAGE;
        this.source = AbstractDungeon.player;
        this.funCard = card;
    }

    public void update() {
        if (this.duration == Settings.ACTION_DUR_FAST) {
            isDone = true;


            if (!AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead())
                for (AbstractMonster mon : AbstractDungeon.getCurrRoom().monsters.monsters) {
                    if (!((EchoingSlash)funCard).killedMonsters.contains(mon) && (mon.isDying || mon.currentHealth <= 0)) {
                        ((EchoingSlash)funCard).killedMonsters.add(mon);
                        AbstractDungeon.actionManager.addToBottom(new EchoingSlashAction(funCard));
                        this.addToBot(new SFXAction("ATTACK_HEAVY"));
                        this.addToBot(new VFXAction(source, new CleaveEffect(), 0.1F));
                        this.addToBot(new DamageAllEnemiesAction(source, funCard.multiDamage, funCard.damageTypeForTurn, AttackEffect.NONE));
                        break;
                    }
                }
        }
        this.isDone = true;
    }

}
