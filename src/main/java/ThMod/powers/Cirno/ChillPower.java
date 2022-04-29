package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChillPower extends AbstractPower {
	
	public static final String POWER_ID = "ChillPower";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public ChillPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = (this.owner.hasPower("CirnoOverloadPower") ? 0 : amount);
		
		this.type = AbstractPower.PowerType.BUFF;
		updateDescription();
		this.img = new Texture("img/powers/Chill.png");
	}
	
	@Override
	public void stackPower(int stackAmount) {
		if (this.owner.hasPower("CirnoOverloadPower"))
			return;
		
		// 大号蝴蝶结：每当获得寒冷时获得 1 点干劲
		if (stackAmount > 0 && AbstractDungeon.player.hasRelic("BigBowknot")) {
			// TODO: Flash
			this.addToBot(new ApplyPowerAction(
					AbstractDungeon.player, AbstractDungeon.player, new MotivationPower(1)));
		}
		
		this.amount += stackAmount;
	}
	
	public void updateDescription() { // TODO: 还没太懂这里的逻辑，后面看一下
//		if (this.cnt > 0) {
//			this.description =
//					(
//							DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]
//									+ "," + DESCRIPTIONS[2] + (int) Math.pow(2, this.cnt) + DESCRIPTIONS[3]
//					);
//		} else {
//			this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + ".");
//		}
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + this.amount + DESCRIPTIONS[2]);
	}
	
	@Override
	public float atDamageGive(float damage, DamageInfo.DamageType type) {
		return type == DamageType.NORMAL ? damage + (float)this.amount : damage;
	}
	
	@Override
	public float modifyBlock(float blockAmount, AbstractCard card) {
		return blockAmount + this.amount;
	}
	// 只有从卡牌获得的格挡才能增幅
	@Override
	public void atStartOfTurn() {
		if (this.amount > 0) {
			this.addToTop(new ReducePowerAction(this.owner, this.owner, this.ID,
					(this.amount + 1) / 2));
		}
	}
}