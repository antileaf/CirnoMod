package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.IcicleConeCrushFollowAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IcicleConeCrush extends AbstractCirnoCard {
	
	public static final String ID = IcicleConeCrush.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int DRAW_CNT = 3;
	private static final int UPGRADE_PLUS_DRAW_CNT = 1;
	private static final int ATTACK_DMG = 5;
	private static final int BLOCK = 6;
	private static final int CHILL_GAIN = 2;
	private static final int MOTIVATION_GAIN = 1;
	
	public IcicleConeCrush() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY
		);
		
		this.magicNumber = this.baseMagicNumber = DRAW_CNT;
		this.damage = this.baseDamage = ATTACK_DMG;
		this.block = this.baseBlock = BLOCK;
		this.chillGain = CHILL_GAIN;
		this.motivationGain = MOTIVATION_GAIN;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new DrawCardAction(this.magicNumber, new IcicleConeCrushFollowAction(
				m, this.damage, this.block, this.chillGain, this.motivationGain)));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IcicleConeCrush();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADE_PLUS_DRAW_CNT);
			this.initializeDescription();
		}
	}
}