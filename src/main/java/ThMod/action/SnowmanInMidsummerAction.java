package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ExhaustSpecificCardAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class SnowmanInMidsummerAction extends AbstractGameAction {
	
	boolean upgraded;
	
	public SnowmanInMidsummerAction(boolean upgraded) {
		this.upgraded = upgraded;
		this.actionType = ActionType.CARD_MANIPULATION;
	}
	
	public void update() {
		ArrayList<AbstractCard> oldcards = new ArrayList<>(), newcards = new ArrayList<>();
		
		for (AbstractCard card : AbstractDungeon.player.hand.group)
			if (card.type != AbstractCard.CardType.ATTACK) {
				oldcards.add(card);
				newcards.add(AbstractDungeon.returnTrulyRandomCardInCombat(
						AbstractCard.CardType.ATTACK).makeCopy());
			}
		
		for (AbstractCard card : oldcards)
			this.addToTop(new ExhaustSpecificCardAction(card, AbstractDungeon.player.hand));
		
		for (AbstractCard c : newcards) {
			AbstractCard card = c.makeCopy();
			
			if (upgraded)
				card.upgrade();
//			card.updateCost(-1);
			card.modifyCostForCombat(-1);
			
			this.addToBot(new MakeTempCardInHandAction(card));
		}
		
		this.isDone = true;
	}
}
