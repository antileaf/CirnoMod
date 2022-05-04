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

public class DaiyouseisHelp extends AbstractCirnoCard {
	
	public static final String ID = "DaiyouseisHelp";
	public static final String IMG_PATH = "img/cards/DaiyouseisHelp.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 2;
	private static final int RETURN_CNT = 1;
	private static final int UPGRADE_PLUS_RETURN_CNT = 1;
	
	public DaiyouseisHelp() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.NONE
		);
		
		this.block = this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = RETURN_CNT;
		this.retain = true;
	}
	
	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		return false;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
	}
	
	@Override
	public void triggerOnEndOfPlayerTurn() {
		this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
	}
	
	@Override
	public void triggerOnExhaust() {
		// TODO
//		this.addToBot(new CirnoDiscardPileToHandAction(this.magicNumber));
	}
	
	public AbstractCard makeCopy() {
		return new DaiyouseisHelp();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeBlock(UPGRADE_PLUS_BLOCK);
			upgradeMagicNumber(UPGRADE_PLUS_RETURN_CNT);
			initializeDescription();
		}
	}
}