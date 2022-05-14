//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ThMod.action;

import ThMod.ThMod;
import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;
import java.util.function.Consumer;

public class CirnoSelectSomeAndMoveAction extends AbstractGameAction {
	private final SelectCardsAction act;
	private final CardGroup selected, src, dest;
	private final int amount;
	
	public CirnoSelectSomeAndMoveAction(ArrayList<AbstractCard> cardList, CardGroup src, CardGroup dest,
	                                    int amount, String text, Consumer<AbstractCard> work) {
		this.src = src;
		this.dest = dest;
		this.amount = amount;
		this.selected = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
		
		this.act = new SelectCardsAction(cardList, amount, text, (cards) -> {
			for (AbstractCard card : cards) {
				this.src.removeCard(card);
				
//				ThMod.logger.info("FUCK! there is a chosen card: " + card.name);
				
				this.selected.addToBottom(card);
				work.accept(card);
//				card.stopGlowing();
			}
			
			AbstractDungeon.player.hand.refreshHandLayout();
		});
	}
	
	public CirnoSelectSomeAndMoveAction(ArrayList<AbstractCard> cardList, CardGroup src, CardGroup dest,
	                                    int amount, String text) {
		this(cardList, src, dest, amount, text, (c) -> {});
	}
	
	public void update() {
		if (!this.isDone) {
			if (this.amount > 0) {
				ArrayList<AbstractGameAction> actions = new ArrayList<>();
				actions.add(0, this.act);
				actions.add(0, new MoveCardsAction(this.dest, this.selected, this.amount));

				if (this.dest == AbstractDungeon.player.hand)
					ThMod.logger.info("rnm，老子手牌呢？  amount = " + this.amount);
					
				for (AbstractGameAction o : actions)
					this.addToTop(o);
			}
			
			this.isDone = true;
		}
	}
}
