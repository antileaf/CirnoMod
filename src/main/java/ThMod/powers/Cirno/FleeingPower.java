package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.rooms.AbstractRoom;

public class FleeingPower extends AbstractPower {
	
	public static final String POWER_ID = "FleeingPower";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public FleeingPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		
		this.type = PowerType.BUFF;
		updateDescription();
		this.img = new Texture("img/powers/Chill.png");
	}
	
	@Override
	public void stackPower(int stackAmount) {
		// 不能堆叠
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
	public void atEndOfTurn(boolean isPlayer) {
		if (!isPlayer)
			return;
		
		if (this.amount <= 1) {
			if (AbstractDungeon.getCurrRoom().phase == AbstractRoom.RoomPhase.COMBAT) {
				AbstractDungeon.getCurrRoom().smoked = true;
				AbstractDungeon.player.hideHealthBar();
				AbstractDungeon.player.isEscaping = true;
				AbstractDungeon.player.flipHorizontal = !AbstractDungeon.player.flipHorizontal;
				AbstractDungeon.overlayMenu.endTurnButton.disable();
				AbstractDungeon.player.escapeTimer = 2.5F;
			}
			
			return;
		}
		
		this.addToBot(new ReducePowerAction(this.owner, this.owner, "FleeingPower", 1));
	}
}