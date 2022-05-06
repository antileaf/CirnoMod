package ThMod.action;

import ThMod.ThMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class IceFishingAction extends AbstractGameAction {
	
	public IceFishingAction() {
		this.actionType = ActionType.CARD_MANIPULATION;
	}
	
	public void update() {
		while (AbstractDungeon.player.hand.group.size() < 10) {
			AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat();
			
			this.addToBot(new MakeTempCardInHandAction(card));
			ThMod.frostKing();
		}
		
		this.isDone = true;
	}
}
