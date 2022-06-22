package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.CirnoSelectAndMoveAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class DaiyouseisHelp extends AbstractCirnoCard {
	
	public static final String ID = DaiyouseisHelp.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -2;
	private static final int BLOCK = 3;
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
			CardRarity.RARE,
			CardTarget.NONE
		);
		
		this.block = this.baseBlock = BLOCK;
		this.magicNumber = this.baseMagicNumber = RETURN_CNT;
		this.selfRetain = true;
	}
	
	@Override
	public boolean canUse(AbstractPlayer p, AbstractMonster m) {
		this.cantUseMessage = cardStrings.EXTENDED_DESCRIPTION[0];
		return false;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
	}
	
//	@Override
//	public void triggerOnEndOfPlayerTurn() {
//		this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
//	}
	
	@Override
	public void triggerOnExhaust() {
//		this.addToBot(new FetchAction(
//				AbstractDungeon.player.exhaustPile, this.magicNumber));
		
		AbstractPlayer p = AbstractDungeon.player;
		this.addToBot(new CirnoSelectAndMoveAction(p.exhaustPile, p.hand, this.magicNumber,
				cardStrings.EXTENDED_DESCRIPTION[1] + this.magicNumber +
						cardStrings.EXTENDED_DESCRIPTION[2] + " (" + this.name + ")"));
		
//		for (int i = 0; i < this.magicNumber; i++)
//			this.addToBot(new OrbitalAction());
	}
	
	@Override
	public void onRetained() {
		applyPowers();
		
		boolean bad = false;
		for (AbstractCard c : AbstractDungeon.player.hand.group)
			if (c != this && c instanceof DaiyouseisHelp) {
				bad = true;
				break;
			}
		
		if (!bad)
			this.addToBot(new GainBlockAction(AbstractDungeon.player, this.block));
//		AbstractPlayer p = AbstractDungeon.player;
//		this.addToBot(new ApplyPowerAction(p, p, new MotivationPower(this.magicNumber)));
	}
	
	@Override
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