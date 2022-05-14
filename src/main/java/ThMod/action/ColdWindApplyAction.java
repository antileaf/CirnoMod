package ThMod.action;

import ThMod.powers.Cirno.ColdWindPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class ColdWindApplyAction extends AbstractGameAction {
	boolean upgraded;
	
	public ColdWindApplyAction(boolean upgraded) {
		this.upgraded = upgraded;
	}
	
	public void update() {
		if (!this.isDone) {
			AbstractPlayer p = AbstractDungeon.player;
			
			if (p.hasPower(ColdWindPower.POWER_ID))
				((ColdWindPower) p.getPower(ColdWindPower.POWER_ID)).
						add(upgraded);
			else
				this.addToTop(new ApplyPowerAction(p, p, new ColdWindPower(upgraded)));
			
			this.isDone = true;
		}
	}
}
