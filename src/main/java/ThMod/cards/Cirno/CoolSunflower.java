package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.evacipated.cardcrawl.mod.stslib.actions.common.SelectCardsInHandAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CoolSunflower extends AbstractCirnoCard {
	
	public static final String ID = CoolSunflower.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int CNT = 3;
	private static final int UPGRADE_PLUS_CNT = 2;
	
	public CoolSunflower() {
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
		
		this.magicNumber = this.baseMagicNumber = CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.addToBot(new SelectCardsInHandAction(this.magicNumber,
				cardStrings.EXTENDED_DESCRIPTION[0] + this.magicNumber +
						cardStrings.EXTENDED_DESCRIPTION[1] + " (" + this.name + ")",
				true,
				true,
				(c) -> (c != this),
				(cards) -> {
					AbstractDungeon.actionManager.addToTop(new DrawCardAction(cards.size()));
				}));
	}
	
	@Override
	public void triggerOnExhaust() {
		this.use(AbstractDungeon.player, null);
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new CoolSunflower();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADE_PLUS_CNT);
			this.initializeDescription();
		}
	}
}