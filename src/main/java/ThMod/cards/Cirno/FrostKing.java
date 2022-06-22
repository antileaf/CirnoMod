package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.FrostKingPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FrostKing extends AbstractCirnoCard {
	
	public static final String ID = FrostKing.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int BLOCK = 2;
	private static final int UPGRADE_PLUS_BLOCK = 1;
	
	public FrostKing() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.POWER,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.SELF
		);
		
//		this.block = this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = BLOCK;
	}
	
	@Override
	public void applyPowersToBlock() {
		this.block = this.baseBlock;
		this.isBlockModified = false;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
//		this.addToBot(new ApplyPowerAction(p, p, new FrostKingMotivationPower(this.magicNumber)));
		this.addToBot(new ApplyPowerAction(p, p, new FrostKingPower(this.magicNumber)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FrostKing();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
//			this.upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.upgradeMagicNumber(UPGRADE_PLUS_BLOCK);
			this.initializeDescription();
		}
	}
}