//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ThMod.action;

import ThMod.ThMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class AchiFormPlayCardsAction extends AbstractGameAction {
	ArrayList<AbstractCard> cards;
	CardGroup group;
	
	public AchiFormPlayCardsAction(ArrayList<AbstractCard> cards, CardGroup group) {
		this.cards = cards;
		this.group = group;
	}
	
	@Override
	public void update() {
		if (!this.isDone) {
			ArrayList<AbstractGameAction> actions = new ArrayList<>();
			for (AbstractCard card : cards) {
//				card.exhaust = true;
				card.exhaustOnUseOnce = true;
				AbstractMonster m = AbstractDungeon.getCurrRoom().monsters.
						getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
				ThMod.logger.info("DEBUG : selected monster " + m.name);
				
				actions.add(0, new NewQueueCardAction(card, m, false, true));
				
				group.removeCard(card);
			}
//			for (AbstractCard card : cards)
//				actions.add(0, new CirnoExhaustSpecificCardAction(card, this.group));
			
			for (AbstractGameAction act : actions)
				this.addToTop(act);
			
			this.isDone = true;
		}
	}
}
