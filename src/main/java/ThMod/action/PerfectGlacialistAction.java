package ThMod.action;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

@Deprecated
public class PerfectGlacialistAction
		extends AbstractGameAction {
	
	private final DamageInfo info;
	private static final float DURATION = 0.1F;
	private final int damage;
	
	public PerfectGlacialistAction(AbstractCreature target, DamageInfo info, int damage) {
		this.info = info;
		setValues(target, info);
		this.actionType = ActionType.DAMAGE;
		this.duration = DURATION;
		this.damage = damage;
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
			
			AbstractMonster mon = (AbstractMonster) this.target;
			
			int tmp = mon.currentHealth;
			
			this.target.damage(this.info);
			
			int res;
			
			if ((this.target.isDying) || (this.target.currentHealth <= 0))
				res = tmp;
			else
				res = tmp - mon.currentHealth;
			
			AbstractPlayer p = AbstractDungeon.player;
			
//			p.addBlock(this.damage - res);
			this.addToTop(new GainBlockAction(p, this.damage - res));
			
			if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
				AbstractDungeon.actionManager.clearPostCombatActions();
			}
		}
		tickDuration();
	}
}
