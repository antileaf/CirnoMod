package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.GameActionManager;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class MathTextbookFollowAction extends AbstractGameAction {
	AbstractCard.CardType cardType;
	
	public MathTextbookFollowAction(AbstractCard.CardType type) {
		this.actionType = ActionType.CARD_MANIPULATION;
		
		this.cardType = type;
	}
	
	public void update() {
		if (!this.isDone) {
			for (AbstractCard card : DrawCardAction.drawnCards) {
				if (card.type != cardType) {
					AbstractDungeon.player.hand.moveToDiscardPile(card);
					card.triggerOnManualDiscard();
					GameActionManager.incrementDiscard(false);
					
//					this.addToTop(new WaitAction(0.2f));
				}
			}
			
			this.isDone = true;
		}
	}
}
