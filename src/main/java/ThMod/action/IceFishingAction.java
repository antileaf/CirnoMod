package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class IceFishingAction extends AbstractGameAction {
	int max_cnt;
	public IceFishingAction(int max_cnt) {
		this.actionType = ActionType.CARD_MANIPULATION;
		this.max_cnt = max_cnt;
	}
	
	public void update() {
//		while (AbstractDungeon.player.hand.group.size() < 10) {
		int cnt = this.max_cnt - AbstractDungeon.player.hand.size();
		
		for (int i = 0; i < cnt; i++) {
			AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
			
			this.addToTop(new MakeTempCardInHandAction(card));
//			ThMod.frostKing();
		}
		
		this.isDone = true;
	}
}
