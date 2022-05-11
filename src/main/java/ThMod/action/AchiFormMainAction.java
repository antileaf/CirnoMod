//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ThMod.action;

import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class AchiFormMainAction extends AbstractGameAction {
	int amount;
	String text;
	public AchiFormMainAction(int amount, String text) {
		this.actionType = ActionType.DRAW;
		this.amount = amount;
		this.text = text;
	}
	
	@Override
	public void update() {
		if (!this.isDone) {
			AbstractPlayer p = AbstractDungeon.player;
			CardGroup group = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
			int cnt = Integer.min(this.amount, p.drawPile.size());
			for (int i = 0; i < cnt; i++)
				group.group.add(p.drawPile.getNCardFromTop(i));

//		AbstractDungeon.gridSelectScreen.open(group, cnt + 1, true,
//				DESCRIPTIONS[2] + " (" + this.name + ")");
			
			for (AbstractCard c : group.group) {
				c.untip();
				c.unhover();
				p.drawPile.removeCard(c);
			}
			
			ArrayList<AbstractCard> selected = new ArrayList<>();
			
			ArrayList<AbstractGameAction> actions = new ArrayList<>();
			
			actions.add(0, new SelectCardsAction(group.group, group.size(),
					this.text,
					true,
					(c) -> true,
					(selectedCards) -> {
						selected.addAll(selectedCards);
//					unselected.removeAll(selectedCards);
					}));
			
			actions.add(0, new AchiFormPlayCardsAction(selected, group));
			
			actions.add(0, new AchiFormAddToHandAction(group));
			
			for (AbstractGameAction act : actions)
				this.addToTop(act);
			
			this.isDone = true;
		}
	}
}
