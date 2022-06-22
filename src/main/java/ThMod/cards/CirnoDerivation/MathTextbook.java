package ThMod.cards.CirnoDerivation;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.MathTextbookFollowAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MathTextbook extends AbstractCirnoCard {
	private static final int COST = -2;
	private static final int CNT = 3;
	private static final int UPGRADED_CNT = 4;
	protected CardType cardType;
	
	public MathTextbook(
			String id,
			String name,
			String img,
			String rawDescription) {
		super(
			id,
			name,
			"img/cards/Cirno/Th123Cirno.png",
			COST,
			rawDescription,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_DERIVATION_COLOR,
			CardRarity.SPECIAL,
			CardTarget.NONE
		);
		
		this.magicNumber = this.baseMagicNumber = CNT;
	}
	
	@Override
	public void onChoseThisOption() {
//		for (int i = 0; i < this.magicNumber; i++)
//			this.addToBot(new DrawCardAction(1, new MathTextbookFollowAction(this.cardType)));
		
		this.addToBot(new DrawCardAction(this.magicNumber, new MathTextbookFollowAction(this.cardType)));
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.onChoseThisOption();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeMagicNumber(UPGRADED_CNT - CNT);
			this.initializeDescription();
		}
	}
}