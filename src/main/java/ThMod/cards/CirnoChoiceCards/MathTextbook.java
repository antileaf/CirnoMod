package ThMod.cards.CirnoChoiceCards;

import ThMod.action.MathTextbookFollowAction;
import ThMod.patches.AbstractCardEnum;
import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MathTextbook extends CustomCard {
	private static final int COST = -2;
	private static final int CNT = 4;
	private static final int UPGRADED_CNT = 6;
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
			AbstractCardEnum.CIRNO_CHOICE_COLOR,
			CardRarity.SPECIAL,
			CardTarget.NONE
		);
		
		this.magicNumber = this.baseMagicNumber = CNT;
	}
	
	@Override
	public void onChoseThisOption() {
		this.addToBot(new DrawCardAction(this.magicNumber, new MathTextbookFollowAction(this.cardType)));
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.onChoseThisOption();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeMagicNumber(UPGRADED_CNT - CNT);
			initializeDescription();
		}
	}
}