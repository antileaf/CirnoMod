package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.cards.CirnoDerivation.MarisasPotion;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;

public class KirisameMahouten extends AbstractCirnoCard {
	
	public static final String ID = KirisameMahouten.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
//	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 1;
	private static final int UPGRADED_COST = 0;
	private static final int CNT = 3;
	
	public KirisameMahouten() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.SKILL,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.RARE,
			CardTarget.NONE
		);
		
		this.magicNumber = this.baseMagicNumber = CNT;
		this.exhaust = true;
		this.tags.add(CardTags.HEALING);
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		ArrayList<AbstractCard> choices = new ArrayList<>();
		for (int i = 0; i < this.magicNumber; i++)
			choices.add(new MarisasPotion(AbstractDungeon.returnRandomPotion()));
		
		this.addToBot(new ChooseOneAction(choices));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new KirisameMahouten();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeBaseCost(UPGRADED_COST);
			this.initializeDescription();
		}
	}
}