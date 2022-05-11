//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ThMod.action;

import com.evacipated.cardcrawl.mod.stslib.actions.common.MoveCardsAction;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class AchiFormAddToHandAction extends AbstractGameAction {
	CardGroup group;
	
	public AchiFormAddToHandAction(CardGroup group) {
		this.group = group;
	}
	
	@Override
	public void update() {
		if (!this.isDone) {
			ArrayList<AbstractGameAction> actions = new ArrayList<>();
			
			actions.add(0, new MoveCardsAction(AbstractDungeon.player.drawPile,
					this.group, this.group.size()));
			
			actions.add(0, new DrawCardAction(this.group.size()));
			
			for (AbstractGameAction act : actions)
				this.addToTop(act);
			
			this.isDone = true;
		}
	}
}
