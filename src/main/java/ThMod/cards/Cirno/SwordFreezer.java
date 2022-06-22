package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.watcher.VigorPower;

import java.util.Iterator;

@Deprecated
public class SwordFreezer extends AbstractCirnoCard {
	
	public static final String ID = SwordFreezer.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int MULTIPLIER = 2;
	private static final int UPGRADE_PLUS_MULTIPLIER = 1;
	private static final int MOTIVATION_COST = 1;
	private static final int BLOCK = 1;
	private static final int UPGRADE_PLUS_BLOCK = 1;
	
	public SwordFreezer() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.COMMON,
			CardTarget.SELF
		);
		
		this.magicNumber = this.baseMagicNumber = MULTIPLIER;
		this.block = this.baseBlock = BLOCK;
		this.motivationCost = MOTIVATION_COST;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
//		this.setMotivated(ThMod.calcMotivated(this));
		
		int attack = 0, skill = 0;
		for (AbstractCard card : p.hand.group) {
			if (card.type == CardType.ATTACK)
				attack++;
			if (card.type == CardType.SKILL)
				skill++;
		}
		
		if (this.magicNumber * attack > 0)
			this.addToBot(new ApplyPowerAction(p, p,
					new VigorPower(p, this.magicNumber * attack)));
		
		if (this.isMotivated) {
			int per = BLOCK;
			if (this.upgraded)
				per += UPGRADE_PLUS_BLOCK;
			this.baseBlock = per * skill;
			this.calculateBlock();
			this.addToBot(new GainBlockAction(p, this.block));
			
			this.resumeBlock();
		}
	}
	
	@Override
	protected void applyPowersToBlock() {
	
	}
	
	void calculateBlock() {
		this.isBlockModified = false;
		float tmp = (float)this.baseBlock;
		
		Iterator var2;
		AbstractPower p;
		for(var2 = AbstractDungeon.player.powers.iterator(); var2.hasNext(); tmp = p.modifyBlock(tmp, this)) {
			p = (AbstractPower)var2.next();
		}
		
		for(var2 = AbstractDungeon.player.powers.iterator(); var2.hasNext(); tmp = p.modifyBlockLast(tmp)) {
			p = (AbstractPower)var2.next();
		}
		
		if (this.baseBlock != MathUtils.floor(tmp)) {
			this.isBlockModified = true;
		}
		
		if (tmp < 0.0F) {
			tmp = 0.0F;
		}
		
		this.block = MathUtils.floor(tmp);
	}
	
	void resumeBlock() {
		this.block = this.baseBlock = BLOCK;
		if (this.upgraded) {
			this.block += UPGRADE_PLUS_BLOCK;
			this.baseBlock += UPGRADE_PLUS_BLOCK;
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new SwordFreezer();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADE_PLUS_MULTIPLIER);
			this.upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.initializeDescription();
		}
	}
}