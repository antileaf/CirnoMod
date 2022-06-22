package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;

public class FunkyPower extends AbstractPower {
	
	public static final String POWER_ID = FunkyPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	boolean attack, skill, power;
	
	public FunkyPower(int amount) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		this.amount = amount;
		this.attack = this.skill = this.power = false;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FunkyPower.png");
	}
	
	public void updateDescription() {
		this.description = DESCRIPTIONS[0] + this.amount + DESCRIPTIONS[1] + " NL ";
		
		if (this.attack && this.skill && this.power)
			this.description += DESCRIPTIONS[8];
		else {
			this.description += DESCRIPTIONS[2];
			ArrayList<String> list = new ArrayList<>();
			
			if (!this.attack)
				list.add(DESCRIPTIONS[3]);
			if (!this.skill)
				list.add(DESCRIPTIONS[4]);
			if (!this.power)
				list.add(DESCRIPTIONS[5]);
			
			for (int i = 0; i < list.size(); i++) {
				if (i > 0)
					this.description += DESCRIPTIONS[6];
				
				this.description += list.get(i);
			}
			
			this.description += DESCRIPTIONS[7];
		}
	}
	
	@Override
	public void onAfterCardPlayed(AbstractCard card) {
		boolean flag = false;
		
		if (card.type == AbstractCard.CardType.ATTACK) {
			if (!this.attack)
				flag = this.attack = true;
		}
		else if (card.type == AbstractCard.CardType.SKILL) {
			if (!this.skill)
				flag = this.skill = true;
		}
		else if (card.type == AbstractCard.CardType.POWER) {
			if (!this.power)
				flag = this.power = true;
		}
		
		if (flag) {
			this.addToBot(new ApplyPowerAction(this.owner, this.owner, new ChillPower(this.amount)));
			this.updateDescription();
		}
	}
	
	@Override
	public void atStartOfTurn() {
		this.attack = this.skill = this.power = false;
		this.updateDescription();
	}
}