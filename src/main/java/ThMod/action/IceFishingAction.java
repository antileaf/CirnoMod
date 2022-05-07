package ThMod.action;

import basemod.BaseMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class IceFishingAction extends AbstractGameAction {
	
	public IceFishingAction() {
		this.actionType = ActionType.CARD_MANIPULATION;
	}
	
	public void update() {
//		while (AbstractDungeon.player.hand.group.size() < 10) {
		int cnt = BaseMod.MAX_HAND_SIZE - AbstractDungeon.player.hand.size();
		
		for (int i = 0; i < cnt; i++) {
			AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat();
			
			this.addToTop(new MakeTempCardInHandAction(card));
//			ThMod.frostKing();
		}
		
		this.isDone = true;
	}
}
