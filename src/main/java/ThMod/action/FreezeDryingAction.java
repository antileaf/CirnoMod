package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class FreezeDryingAction
		extends AbstractGameAction {
	
	private final DamageInfo info;
	private static final float DURATION = 0.1F;
	
	public FreezeDryingAction(AbstractCreature target, DamageInfo info) {
		this.info = info;
		setValues(target, info);
		this.actionType = ActionType.DAMAGE;
		this.duration = DURATION;
	}
	
	public void update() {
		if ((this.duration == 0.1F) && (this.target != null)) {
			AbstractDungeon.effectList.add(
					new FlashAtkImgEffect(
							this.target.hb.cX,
							this.target.hb.cY,
							AttackEffect.BLUNT_HEAVY
					)
			);
			
			this.target.damage(this.info);
			
			if (!this.target.isDead && this.target.currentBlock <= 0)
				this.target.damage(this.info);
			
			if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
		}
		tickDuration();
	}
}
