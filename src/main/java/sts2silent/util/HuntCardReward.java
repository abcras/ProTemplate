package sts2silent.util;

import basemod.abstracts.CustomReward;
import basemod.abstracts.CustomSavable;
import com.evacipated.cardcrawl.mod.stslib.patches.CustomCardReward;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.rewards.RewardItem;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

public class HuntCardReward implements CustomSavable<Integer> {
    public static int numberOfCardRewardsToRegenerate = 0;

    @Override
    public Integer onSave() {
        return numberOfCardRewardsToRegenerate;
    }

    @Override
    public void onLoad(Integer integer) {

        numberOfCardRewardsToRegenerate = integer;



        /*if (AbstractDungeon.getCurrRoom() instanceof MonsterRoom || (AbstractDungeon.getCurrRoom() instanceof MonsterRoomElite) || (AbstractDungeon.getCurrRoom() instanceof MonsterRoomBoss)) {
            RewardItem cardReward = new RewardItem();
            for (int i = 0; i < integer; i++) {
                if (!cardReward.cards.isEmpty()) {
                    AbstractDungeon.getCurrRoom().rewards.add(cardReward);
                }
            }
        }*/
    }
}