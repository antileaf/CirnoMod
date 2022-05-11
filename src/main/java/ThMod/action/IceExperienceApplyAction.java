package ThMod.action;

import ThMod.powers.Cirno.IceExperiencePower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

import java.util.ArrayList;

public class IceExperienceApplyAction extends AbstractGameAction {
	
	ArrayList<AbstractCard> cards;
	
	public IceExperienceApplyAction(ArrayList<AbstractCard> cards) {
		this.cards = cards;
	}
	
	public void update() {
		if (!this.isDone) {
			AbstractPlayer p = AbstractDungeon.player;
			
			if (p.hasPower(IceExperiencePower.POWER_ID))
				((IceExperiencePower) p.getPower(IceExperiencePower.POWER_ID)).
						stackPower(cards);
			else
				this.addToTop(new ApplyPowerAction(p, p, new IceExperiencePower(cards)));
			
			this.isDone = true;
		}
	}
}
