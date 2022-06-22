package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.cards.DamageInfo.DamageType;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class ChillPower extends AbstractPower {
	
	public static final String POWER_ID = ChillPower.class.getSimpleName();
	public static final String IMG_PATH = "img/powers/" + POWER_ID + ".png";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public ChillPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
//		this.amount = (this.owner.hasPower(CirnoOverloadPower.POWER_ID) ? 0 : amount);
		this.amount = amount;
		
		this.type = AbstractPower.PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture(IMG_PATH);
	}
	
	@Override
	public void stackPower(int stackAmount) {
//		if (this.owner.hasPower(CirnoOverloadPower.POWER_ID))
//			return;
		
		// 大号蝴蝶结：每当获得寒冷时获得 1 点干劲
//		if (stackAmount > 0 && AbstractDungeon.player.hasRelic("BigBowknot")) {
//			this.addToBot(new ApplyPowerAction(
//					AbstractDungeon.player, AbstractDungeon.player, new MotivationPower(1)));
//		}
		
		this.amount += stackAmount;
	}
	
	@Override
	public void updateDescription() {
		this.description = (DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1]);
	}
	
	@Override
	public float atDamageGive(float damage, DamageInfo.DamageType type) {
		if (type == DamageType.NORMAL) {
			int augment = this.amount;
			if (this.owner.hasPower(AbsoluteZeroPower.POWER_ID))
				augment *= 1 + this.owner.getPower(AbsoluteZeroPower.POWER_ID).amount;
			
			damage += augment;
		}
		
		return damage;
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