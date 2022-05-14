package ThMod.powers.Cirno;

import ThMod.action.CirnoAnonymousAction;
import ThMod.cards.CirnoDerivation.IceConical;
import ThMod.cards.CirnoDerivation.IceCube;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Collections;

public class ColdWindPower extends AbstractPower {
	
	public static final String POWER_ID = ColdWindPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	public ArrayList<Boolean> upgraded;
	public ArrayList<AbstractCard> remaining;
	
	public ColdWindPower(boolean upgraded) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		
		this.upgraded = new ArrayList<>(Collections.singleton(upgraded));
		this.remaining = new ArrayList<>(Collections.singleton(null));
		this.amount = 1;
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FrostPillarsPower.png");
	}
	
	public void add(boolean upgraded) {
		this.upgraded.add(upgraded);
		this.remaining.add(null);
		this.amount++;
		
		this.updateDescription();
		this.fontScale = 8F;
	}
	
	@Override
	public void updateDescription() {
		StringBuilder builder = new StringBuilder(DESCRIPTIONS[0]);
		
		for (int i = 0; i < this.amount; i++) {
			builder.append(" NL ");
			
			if (remaining.get(i) != null)
				builder.append(DESCRIPTIONS[1] + remaining.get(i).name);
			else {
				AbstractCard conical = new IceConical(), cube = new IceCube();
				if (upgraded.get(i)) {
					conical.upgrade();
					cube.upgrade();
				}
				
				builder.append(DESCRIPTIONS[2] + conical.name +
						DESCRIPTIONS[3] + cube.name + DESCRIPTIONS[4]);
			}
			
			builder.append(i < this.amount - 1 ? DESCRIPTIONS[5] : DESCRIPTIONS[6]);
		}
		
		this.description = builder.toString();
	}
	
	@Override
	public void atStartOfTurnPostDraw() {
		for (int i = 0; i < this.amount; i++) {
			if (remaining.get(i) != null) {
				this.addToBot(new MakeTempCardInHandAction(remaining.get(i).makeStatEquivalentCopy()));
				remaining.set(i, null);
			}
			else {
				ArrayList<AbstractCard> choices = new ArrayList<>();
				choices.add(new IceConical(i));
				choices.add(new IceCube(i));
				
				if (upgraded.get(i))
					for (AbstractCard c : choices)
						c.upgrade();
				
				this.addToBot(new ChooseOneAction(choices));
			}
		}
		
		this.addToBot(new CirnoAnonymousAction(this::updateDescription));
//		this.updateDescription();
	}
}