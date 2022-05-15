package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.IceCream;

public class IceCreamMachinePower extends AbstractPower {
	
	public static final String POWER_ID = IceCreamMachinePower.class.getSimpleName();
	public static final String IMG_PATH = "img/powers/" + POWER_ID + ".png";
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public IceCreamMachinePower() {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = -1;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
//		this.img = new Texture("img/powers/Nineball32.png");
		this.img = new Texture(IMG_PATH);
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0];
	}
	
	@Override
	public void onVictory() {
		AbstractDungeon.player.loseRelic(IceCream.ID);
	}
}