package ThMod.cards.CirnoDerivation;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.KirisameMahoutenApplyAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.AbstractPotion;

public class MarisasPotion extends AbstractCirnoCard {
	public static final String ID = MarisasPotion.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = -2;
	public AbstractPotion potion;
	
	public MarisasPotion(AbstractPotion potion) {
		super(
				ID,
				potion == null ? NAME : potion.name,
				"img/cards/Cirno/Th123Cirno.png",
				COST,
				potion == null ? DESCRIPTION : potion.description,
				CardType.SKILL,
				AbstractCardEnum.CIRNO_DERIVATION_COLOR,
				CardRarity.SPECIAL,
				CardTarget.NONE
		);
		
		this.potion = potion;
		this.exhaust = true; // 正常情况下不应该得到这张卡
		
//		ThMod.logger.info("DEBUG : on init, this.potion = " + this.potion);
	}
	
	public MarisasPotion() {
		this(null);
	}
	
	@Override
	public void onChoseThisOption() {
//		ThMod.logger.info("DEBUG : this.potion = " + this.potion);
		
		AbstractDungeon.getCurrRoom().addPotionToRewards(this.potion.makeCopy());
		this.addToBot(new KirisameMahoutenApplyAction(this.potion.name));
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.onChoseThisOption();
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new MarisasPotion(this.potion);
	}
	
	public void upgrade() {
		if (!this.upgraded) { // 正常不应该升级
			this.upgradeName();
			
			this.initializeDescription();
		}
	}
}