package ThMod.action;

import ThMod.ThMod;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class FairyPunchUpdateHandAction extends AbstractGameAction {
	
	public FairyPunchUpdateHandAction() {
		this.actionType = ActionType.SPECIAL;
	}
	
	public void update() {
		if (!this.isDone) {
			for (AbstractCard card : AbstractDungeon.player.hand.group)
				ThMod.fairyPunch(card);
			
			this.isDone = true;
		}
	}
}
