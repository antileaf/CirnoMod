package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.cards.CirnoChoiceCards.BlueTextbook;
import ThMod.cards.CirnoChoiceCards.RedTextbook;
import ThMod.cards.CirnoChoiceCards.YellowTextbook;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class NineMathTextbooks extends AbstractCirnoCard {
	
	public static final String ID = "NineMathTextbooks";
	public static final String IMG_PATH = "img/cards/NineMathTextbooks.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int CNT = 4;
	private static final int UPGRADE_PLUS_CNT = 2;
	
	public NineMathTextbooks() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.SELF
		);
		
		this.magicNumber = this.baseMagicNumber = CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		ArrayList<AbstractCard> choices = new ArrayList<>();
		choices.add(new RedTextbook());
		choices.add(new BlueTextbook());
		choices.add(new YellowTextbook());
		
		if (this.upgraded) {
			for (AbstractCard c : choices)
				c.upgrade();
		}
		
		this.addToBot(new ChooseOneAction(choices));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new NineMathTextbooks();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeMagicNumber(UPGRADE_PLUS_CNT);
			initializeDescription();
		}
	}
}