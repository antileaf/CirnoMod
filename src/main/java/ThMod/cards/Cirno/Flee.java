package ThMod.cards.Cirno;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.FleeingPower;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.SmokeBomb;
import com.megacrit.cardcrawl.vfx.ThoughtBubble;

public class Flee extends AbstractCirnoCard {
	
	public static final String ID = Flee.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int ROUND_CNT = 4;
	private static final int MOTIVATION_COST = -1;
	
	public Flee() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.POWER,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.COMMON,
			CardTarget.SELF
		);
		
		this.magicNumber = this.baseMagicNumber = ROUND_CNT;
		this.motivationCost = MOTIVATION_COST;
//		this.exhaust = true;
		this.isEthereal = true;
		
		this.dontUsePatch = true;
	}
	
	@Override
	public void triggerOnGlowCheck() {
		super.triggerOnGlowCheck();
		
		if (!(new SmokeBomb().canUse()))
			this.glowColor = Color.DARK_GRAY.cpy();
		else {
			int cnt = this.magicNumber;
			
			AbstractPlayer p = AbstractDungeon.player;
			if (p.hasPower("MotivationPower"))
				cnt -= p.getPower("MotivationPower").amount;
			
			if (cnt <= 1)
				this.glowColor = GOLD_BORDER_GLOW_COLOR;
		}
	}
	
	@Override
	public void applyPowers() {
		this.rawDescription = (this.upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION);
		int cnt = this.magicNumber;
		
		AbstractPlayer p = AbstractDungeon.player;
		if (p.hasPower("MotivationPower"))
			cnt -= p.getPower("MotivationPower").amount;
		
		this.rawDescription += " NL ";
		
		if (cnt > 1)
			this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] +
					cnt + cardStrings.EXTENDED_DESCRIPTION[1];
		else
			this.rawDescription += cardStrings.EXTENDED_DESCRIPTION[0] +
					cardStrings.EXTENDED_DESCRIPTION[2];
		
		this.initializeDescription();
	}
	
	@Override
	public void onMoveToDiscard() {
		this.rawDescription = (this.upgraded ? UPGRADE_DESCRIPTION : DESCRIPTION);
		this.initializeDescription();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		if (!(new SmokeBomb().canUse())) {
			AbstractDungeon.effectList.add(new ThoughtBubble(
					AbstractDungeon.player.dialogX, AbstractDungeon.player.dialogY, 3.0F,
					cardStrings.EXTENDED_DESCRIPTION[3], true));
			
			return;
		}
		
		this.setMotivated(ThMod.calcMotivated(this));
		
		int cnt = this.magicNumber - this.motivatedCnt;
		
//		if (this.isMotivated)
//			this.addToTop(new ReducePowerAction(p, p, "MotivationPower", this.motivatedCnt));
		
		if (cnt <= 1) // 按照卡面描述，如果回合数小于等于 1 则立刻逃跑，不必等到回合结束
			(new SmokeBomb()).use(m);
		else
			this.addToBot(new ApplyPowerAction(p, p, new FleeingPower(cnt)));
	}
	
	public AbstractCard makeCopy() {
		return new Flee();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.isInnate = true;
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}