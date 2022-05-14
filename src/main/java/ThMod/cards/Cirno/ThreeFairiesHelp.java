package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.cards.CirnoDerivation.LunaChildsHelp;
import ThMod.cards.CirnoDerivation.StarSapphiresHelp;
import ThMod.cards.CirnoDerivation.SunnyMilksHelp;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.watcher.ChooseOneAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import java.util.ArrayList;
import java.util.Collections;

public class ThreeFairiesHelp extends AbstractCirnoCard {
	
	public static final String ID = ThreeFairiesHelp.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;
	private static final int COST = 0;
	private static final int MOTIVATION_COST = 1;
	
	public ThreeFairiesHelp() {
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
		
		this.exhaust = true;
		this.motivationCost = MOTIVATION_COST;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
//		this.setMotivated(ThMod.calcMotivated(this));
		
		ArrayList<AbstractCard> choices = new ArrayList<>();
		choices.add(new SunnyMilksHelp());
		choices.add(new LunaChildsHelp());
		choices.add(new StarSapphiresHelp());
		
		if (this.isMotivated) {
			for (AbstractCard card : choices)
				card.upgrade();
		}
		
		if (this.upgraded) {
			this.addToBot(new ChooseOneAction(choices));
		}
		else {
			AbstractCard card = choices.get(AbstractDungeon.cardRng.random(0, choices.size() - 1));
			this.addToBot(new ChooseOneAction(new ArrayList<>(
					Collections.singletonList(card))));
//			this.addToBot(new MakeTempCardInHandAction(card));
		}
		
//		if (this.isMotivated)
//			this.addToTop(new ReducePowerAction(p, p, "MotivationPower", this.motivationCost));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new ThreeFairiesHelp();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.rawDescription = UPGRADE_DESCRIPTION;
			this.initializeDescription();
		}
	}
}