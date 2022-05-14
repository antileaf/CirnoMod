package ThMod.powers.Cirno;

import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

import java.util.ArrayList;
import java.util.Collections;

public class KirisameMahoutenPower extends AbstractPower {
	
	public static final String POWER_ID = KirisameMahoutenPower.class.getSimpleName();
	private static final PowerStrings powerStrings =
			CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
	public static final String NAME = powerStrings.NAME;
	public static final String[] DESCRIPTIONS =
			powerStrings.DESCRIPTIONS;
	
	ArrayList<String> names;
	
	public KirisameMahoutenPower(String name) {
		this.name = NAME;
		this.ID = POWER_ID;
		this.owner = AbstractDungeon.player;
		
		this.names = new ArrayList<String>(Collections.singleton(name));
		this.amount = this.names.size();
		
		this.type = PowerType.BUFF;
		this.updateDescription();
		this.img = new Texture("img/powers/Nineball32.png");
//		this.img = new Texture("img/powers/FunkyPower.png");
	}
	
	public void stackPower(String name) {
		this.names.add(name);
		
		this.fontScale = 8F;
		this.amount = this.names.size();
		this.updateDescription();
	}
	
	@Override
	public void updateDescription() {
		StringBuilder builder = new StringBuilder(DESCRIPTIONS[0]);
		
		for (String s : this.names) {
			if (s != names.get(0))
				builder.append(DESCRIPTIONS[1]);
			builder.append(s);
		}
		
		builder.append(DESCRIPTIONS[2]);
		this.description = builder.toString();
	}
}