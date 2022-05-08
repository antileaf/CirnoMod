//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

public class ButterflyFairysHelpSingleAction extends AbstractGameAction {
	DamageInfo info;
	public boolean upgraded;
	
	public ButterflyFairysHelpSingleAction(DamageInfo info, boolean upgraded) {
		this.actionType = ActionType.DAMAGE;
		this.info = info;
		this.upgraded = upgraded;
	}
	
	public void update() {
		if (!this.isDone) {
			AbstractMonster o = AbstractDungeon.getMonsters().getRandomMonster(
					null, true, AbstractDungeon.cardRandomRng);
			
			if (o != null) {
				if (this.upgraded)
					this.addToTop(new ApplyPowerAction(o, info.owner,
							new VulnerablePower(o, 1, false)));
				
				this.addToTop(new ApplyPowerAction(o, info.owner,
						new WeakPower(o, 1, false)));
				this.addToTop(new DamageAction(o, info));
			}
			
			this.isDone = true;
		}
	}
}
