package ThMod.action;

import ThMod.ThMod;
import ThMod.cards.Cirno.IceWave;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class IceWaveAction extends AbstractGameAction {
	private IceWave card;
	
	public IceWaveAction(IceWave card, int amount) {
		this.card = card;
		this.amount = amount;
	}
	
	public void update() {
		if (!this.isDone) {
			ThMod.iceWaveAdd(this.amount);
			
			this.card.updateOutOfHand();
			
			ArrayList<ArrayList<AbstractCard>> piles = new ArrayList<>();
			piles.add(AbstractDungeon.player.drawPile.group);
			piles.add(AbstractDungeon.player.discardPile.group);
			piles.add(AbstractDungeon.player.exhaustPile.group);
			
			for (ArrayList<AbstractCard> group : piles)
				for (AbstractCard c : group)
					if (c instanceof IceWave)
						((IceWave) c).updateOutOfHand();
			
			this.isDone = true;
		}
	}
}
