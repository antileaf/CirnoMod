package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class ZettaiRyoiki extends AbstractCirnoCard {
	
	public static final String ID = ZettaiRyoiki.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
//	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 6;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int MULTIPLIER = 3;
	private static final int UPGRADE_PLUS_MULTIPLIER = 1;
	
	int cnt;
	
	public ZettaiRyoiki() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.BASIC,
			CardTarget.SELF
		);
		
		this.block = this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = MULTIPLIER;
		this.cnt = 0;
		
		if (AbstractDungeon.actionManager != null)
			for (AbstractCard card : AbstractDungeon.actionManager.cardsPlayedThisCombat)
				if (card.type == CardType.POWER)
					this.cnt++;
		
		if (AbstractDungeon.player != null)
			this.applyPowers();
	}
	
	@Override
	public void triggerOnCardPlayed(AbstractCard c) {
		if (c.type == CardType.POWER)
			this.cnt++;
		
		this.applyPowers();
	}
	
	@Override
	public void applyPowers() {
		int tmp = this.baseBlock;
		this.baseBlock += this.magicNumber * this.cnt;
		
		super.applyPowers();
		
		this.baseBlock = tmp;
		this.isBlockModified = (this.baseBlock != this.block);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new GainBlockAction(p, this.block));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ZettaiRyoiki();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.upgradeMagicNumber(UPGRADE_PLUS_MULTIPLIER);
			this.initializeDescription();
		}
	}
}