package ThMod.cards.CirnoDerivation;

import ThMod.ThMod;
import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import ThMod.powers.Cirno.ColdWindPower;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IceCube extends AbstractCirnoCard {
	
	public static final String ID = IceCube.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int BLOCK = 5;
	private static final int UPGRADE_PLUS_BLOCK = 3;
	
	int index;
	public IceCube(int index) {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_DERIVATION_COLOR,
			CardRarity.SPECIAL,
			CardTarget.SELF
		);
		
		this.block = this.baseBlock = BLOCK;
		this.selfRetain = true;
		this.exhaust = true;
		
		this.index = index;
	}
	
	public IceCube() {
		this(-1);
	}
	
	@Override
	public void onChoseThisOption() {
		this.addToTop(new MakeTempCardInHandAction(this.makeStatEquivalentCopy()));
		
		if (this.index >= 0) {
			AbstractPlayer p = AbstractDungeon.player;
			if (p.hasPower(ColdWindPower.POWER_ID)) {
				ColdWindPower o = (ColdWindPower) p.getPower(ColdWindPower.POWER_ID);
				
				if (this.index < o.amount) {
					AbstractCard other = new IceConical();
					if (this.upgraded)
						other.upgrade();
					
					o.remaining.set(this.index, other);
				}
				else
					ThMod.logger.info("衍生卡咋给你选择出来的，你是不是有什么大病");
			}
		}
		else
			ThMod.logger.info("你咋做到的选择这卡还没下标？");
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new GainBlockAction(p, this.block));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IceCube();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeBlock(UPGRADE_PLUS_BLOCK);
			this.initializeDescription();
		}
	}
}