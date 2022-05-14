package ThMod.cards.CirnoDerivation;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.CirnoDiscoveryAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class SanyouseisHelp extends AbstractCirnoCard {
	private static final int COST = -2;
	protected CardType cardType;
	
	public SanyouseisHelp(
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
	}
	
	@Override
	public void onChoseThisOption() {
		if (!this.upgraded) {
			AbstractCard card = AbstractDungeon.returnTrulyRandomCardInCombat(this.cardType).makeCopy();
			if (card.cost > 0)
				card.setCostForTurn(card.cost - 1);
			this.addToBot(new MakeTempCardInHandAction(card));
			
//			ThMod.logger.info("GENERATED A NEW CARD: " + card.cardID);
		}
		else {
			this.addToBot(new CirnoDiscoveryAction(this.cardType, 1));
//			ThMod.logger.info("CIRNO DISCOVERY");
		}
		
//		ThMod.frostKing();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.onChoseThisOption();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.rawDescription = CardCrawlGame.languagePack.getCardStrings(this.cardID).UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}