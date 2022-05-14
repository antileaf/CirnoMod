package ThMod.cards.Cirno;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.ChillPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;

import java.util.ArrayList;

public class FreezeAtmosphere extends AbstractCirnoCard {
	
	public static final String ID = FreezeAtmosphere.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int CNT = 1;
	private static final int UPGRADE_PLUS_CNT = 1;
	private static final int MOTIVATION_COST = -1;
	
	public FreezeAtmosphere() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.ALL_ENEMY
		);
		
		this.magicNumber = this.baseMagicNumber = CNT;
		this.motivationCost = MOTIVATION_COST;
		this.exhaust = true;
	}
	
	@Override
	public void triggerOnGlowCheck() {
		super.triggerOnGlowCheck();
		
		if (ThMod.calcMotivated(this) > 0) {
			boolean debuff = false;
			for (AbstractPower p : AbstractDungeon.player.powers)
				debuff |= (p.type == AbstractPower.PowerType.DEBUFF);
			
			if (debuff)
				this.glowColor = GOLD_BORDER_GLOW_COLOR.cpy();
		}
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
//		this.setMotivated(ThMod.calcMotivated(this));
		
		this.addToBot(new ApplyPowerAction(p, p, new ChillPower(this.magicNumber)));
		for (AbstractMonster o : AbstractDungeon.getCurrRoom().monsters.monsters) {
			if (o.intent == AbstractMonster.Intent.ATTACK ||
					o.intent == AbstractMonster.Intent.ATTACK_BUFF ||
					o.intent == AbstractMonster.Intent.ATTACK_DEBUFF ||
					o.intent == AbstractMonster.Intent.ATTACK_DEFEND)
				this.addToBot(new ApplyPowerAction(o, p,
						new WeakPower(o, this.magicNumber, false)));
			else
				this.addToBot(new ApplyPowerAction(o, p,
						new VulnerablePower(o, this.magicNumber, false)));
		}
		
		if (this.isMotivated) {
			ArrayList<String> debuffs = new ArrayList<>();
			for (AbstractPower power : p.powers)
				if (power.type == AbstractPower.PowerType.DEBUFF)
					debuffs.add(power.ID);
			
			this.motivatedCnt = Integer.min(this.motivatedCnt, debuffs.size());
			
			for (int i = 0; i < this.motivatedCnt; i++) {
				int k = AbstractDungeon.miscRng.random(0, debuffs.size() - 1);
				String s = debuffs.get(k);
				this.addToTop(new RemoveSpecificPowerAction(p, p, s));
				
				debuffs.remove(k);
			}
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FreezeAtmosphere();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADE_PLUS_CNT);
			this.initializeDescription();
		}
	}
}