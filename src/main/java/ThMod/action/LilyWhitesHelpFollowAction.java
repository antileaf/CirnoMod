//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class LilyWhitesHelpFollowAction extends AbstractGameAction {
	int amount;
	
	public LilyWhitesHelpFollowAction(int amount) {
		this.amount = amount;
	}
	
	public void update() {
		if (!this.isDone) {
			AbstractPlayer p = AbstractDungeon.player;
			ArrayList<AbstractCard> draw = new ArrayList<>(), discard = new ArrayList<>();
			
			for (AbstractCard card : p.drawPile.group)
				if (card.type == AbstractCard.CardType.STATUS ||
						card.type == AbstractCard.CardType.CURSE)
					draw.add(card);
			
			for (AbstractCard card : p.discardPile.group)
				if (card.type == AbstractCard.CardType.STATUS ||
						card.type == AbstractCard.CardType.CURSE)
					discard.add(card);
			
			for (int i = 0; i < this.amount && !(draw.isEmpty() && discard.isEmpty()); i++) {
				int k = AbstractDungeon.miscRng.random(0, draw.size() + discard.size() - 1);
				
				if (k < draw.size()) {
					this.addToBot(new CirnoExhaustSpecificCardAction(draw.get(k), p.drawPile));
					draw.remove(k);
				}
				else {
					k -= draw.size();
					this.addToBot(new CirnoExhaustSpecificCardAction(discard.get(k), p.discardPile));
					discard.remove(k);
				}
			}
			
			this.isDone = true;
		}
	}
}
