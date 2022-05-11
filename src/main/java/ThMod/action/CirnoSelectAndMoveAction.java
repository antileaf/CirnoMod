//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ThMod.action;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;

import java.util.function.Consumer;

public class CirnoSelectAndMoveAction extends CirnoSelectSomeAndMoveAction {
	
	public CirnoSelectAndMoveAction(CardGroup src, CardGroup dest, int amount, String text,
	                                Consumer<AbstractCard> work) {
		super(src.group, src, dest, amount, text, work);
		
		this.actionType = ActionType.CARD_MANIPULATION;
	}
	
	public CirnoSelectAndMoveAction(CardGroup src, CardGroup dest, int amount, String text) {
		super(src.group, src, dest, amount, text);
	}
}
