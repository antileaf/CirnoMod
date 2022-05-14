package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInDrawPileAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class TengusCamera extends AbstractCirnoCard {
	
	public static final String ID = TengusCamera.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;
	
	public TengusCamera() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.RARE,
			CardTarget.SELF
		);
		
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		ArrayList<AbstractCard> cards = new ArrayList<>();
		
		for (AbstractCard card : p.hand.group)
			if (card != this)
				cards.add(card.makeStatEquivalentCopy());
		
		for (AbstractCard card : cards) {
			this.addToBot(new MakeTempCardInDrawPileAction(card,
					1, true, true));
			
//			ThMod.frostKing();
		}
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new TengusCamera();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeBaseCost(UPGRADED_COST);
			this.initializeDescription();
		}
	}
}