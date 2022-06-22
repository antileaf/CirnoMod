package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.evacipated.cardcrawl.mod.stslib.powers.interfaces.OnPlayerDeathPower;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.potions.FairyPotion;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.relics.LizardTail;

public class FridgePower extends AbstractPower implements OnPlayerDeathPower {
	
	public static final String POWER_ID = FridgePower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public boolean used;
	
	public FridgePower() {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = -1;
		this.used = false;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FridgePower.png");
	}
	
	@Override
	public void updateDescription() {
		if (!this.used)
			this.description = DESCRIPTIONS[0];
		else
			this.description = DESCRIPTIONS[1];
	}
	
	
	// interface form stslib
	public boolean onPlayerDeath(AbstractPlayer p, DamageInfo info) {
		if ((p.hasRelic(LizardTail.ID) && p.getRelic(LizardTail.ID).counter != -2) ||
				p.hasPotion(FairyPotion.POTION_ID))
			return true;
		
		if (!this.used) {
			p.currentHealth = 0;
			
			int val = Integer.max(1, (int) (0.3 * p.maxHealth));
			p.heal(val);
			
			this.used = true;
			this.updateDescription();
			return false;
		}
		else
			return true;
	}
}