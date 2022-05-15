package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.action.CirnoExhaustSpecificCardAction;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class IceTornado extends AbstractCirnoCard {
	
	public static final String ID = IceTornado.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	private static final int ATTACK_DMG = 7;
	private static final int UPGRADE_PLUS_ATTACK_DMG = 3;
	
	public IceTornado() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.RARE,
			CardTarget.ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.exhaust = true;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		int cnt = 0;
		for (AbstractCard card : p.hand.group)
			if (card != this && card.type == CardType.ATTACK) {
				cnt++;
				this.addToBot(new CirnoExhaustSpecificCardAction(card, p.hand));
			}
		
		for (int i = 0; i < cnt; i++)
			this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage,
					this.damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new IceTornado();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			this.upgradeName();
			
			this.upgradeDamage(UPGRADE_PLUS_ATTACK_DMG);
			this.initializeDescription();
		}
	}
}