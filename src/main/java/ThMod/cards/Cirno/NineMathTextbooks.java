package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.cards.CirnoDerivation.BlueTextbook;
import ThMod.cards.CirnoDerivation.RedTextbook;
import ThMod.cards.CirnoDerivation.YellowTextbook;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class NineMathTextbooks extends AbstractCirnoCard {
	
	public static final String ID = NineMathTextbooks.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	private static final int CNT = 3;
	private static final int UPGRADE_PLUS_CNT = 1;
	
	public NineMathTextbooks() {
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
		
		this.magicNumber = this.baseMagicNumber = CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		ArrayList<AbstractCard> choices = new ArrayList<>();
		choices.add(new RedTextbook());
		choices.add(new YellowTextbook());
		choices.add(new BlueTextbook());
		
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
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADE_PLUS_CNT);
			this.initializeDescription();
		}
	}
}