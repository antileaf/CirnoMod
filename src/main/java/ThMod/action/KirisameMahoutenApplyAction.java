package ThMod.action;

import ThMod.powers.Cirno.KirisameMahoutenPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class KirisameMahoutenApplyAction extends AbstractGameAction {
	String name;
	
	public KirisameMahoutenApplyAction(String name) {
		this.name = name;
	}
	
	public void update() {
		if (!this.isDone) {
			AbstractPlayer p = AbstractDungeon.player;
			
			if (p.hasPower(KirisameMahoutenPower.POWER_ID))
				((KirisameMahoutenPower) p.getPower(KirisameMahoutenPower.POWER_ID)).
						stackPower(name);
			else
				this.addToTop(new ApplyPowerAction(p, p, new KirisameMahoutenPower(name)));
			
			this.isDone = true;
		}
	}
}
