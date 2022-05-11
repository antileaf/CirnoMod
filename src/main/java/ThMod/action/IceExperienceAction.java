package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class IceExperienceAction extends AbstractGameAction {
	
	private boolean upgraded;
	
	public IceExperienceAction(boolean upgraded) {
		this.upgraded = upgraded;
	}
	
	public void update() {
		if (!this.isDone) {
			AbstractPlayer p = AbstractDungeon.player;
			
			int cnt = p.hand.size();
			ArrayList<AbstractCard> cards = new ArrayList();
			
			for (int i = 0; i < cnt && !p.hand.isEmpty(); i++) {
				AbstractCard card = p.hand.group.get(0);
				p.hand.removeCard(card);
				cards.add(card);
			}
			
			this.addToBot(new IceExperienceApplyAction(cards));
			
			for (int i = 0; i < cnt; i++) {
				AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat().makeCopy();
				if (this.upgraded)
					card.upgrade();
				
				this.addToBot(new MakeTempCardInHandAction(card));
			}
			
			this.isDone = true;
		}
	}
}
