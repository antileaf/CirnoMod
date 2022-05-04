package ThMod.cards.Cirno;

import ThMod.abstracts.AbstractCirnoCard;
import ThMod.patches.AbstractCardEnum;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class GiganticIceMagic extends AbstractCirnoCard {
	
	public static final String ID = "GiganticIceMagic";
	public static final String IMG_PATH = "img/cards/GiganticIceMagic.png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 2;
	
	private static final int ATTACK_DMG = 24;
	private static final int UPGRADE_PLUS_DMG = 6;
	
	public GiganticIceMagic() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.UNCOMMON,
			CardTarget.ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = -1;
	}
	
	@Override
	public void applyPowers() {
		super.applyPowers();
		
		int cnt = AbstractDungeon.actionManager.cardsPlayedThisTurn.size();
		this.magicNumber = cnt;
		this.damage = this.baseDamage - 12 * cnt;
		
		initializeDescription();
	}
	
	@Override
	public void onMoveToDiscard() {
		this.magicNumber = -1;
		initializeDescription();
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		this.applyPowers();
//		this.addToBot(new VFXAction()); TODO
		this.addToBot(new DamageAction(m, new DamageInfo(p, this.damage,
				this.damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
	}
	
	@Override
	public void initializeDescription() {
		this.rawDescription = DESCRIPTION;
		if (this.magicNumber >= 0)
			this.rawDescription += cardStrings.EXTENDED_DESCRIPTION;
		
		super.initializeDescription();
	}
	
	public AbstractCard makeCopy() {
		return new GiganticIceMagic();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeDamage(UPGRADE_PLUS_DMG);
			initializeDescription();
		}
	}
}