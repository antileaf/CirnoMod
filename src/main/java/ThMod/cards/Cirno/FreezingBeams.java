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
import com.megacrit.cardcrawl.powers.VulnerablePower;

import java.util.ArrayList;

public class FreezingBeams extends AbstractCirnoCard {
	
	public static final String ID = FreezingBeams.class.getSimpleName();
	public static final String IMG_PATH = "img/cards/" + ID + ".png";
	private static final CardStrings cardStrings =
			CardCrawlGame.languagePack.getCardStrings(ID);
	public static final String NAME = cardStrings.NAME;
	public static final String DESCRIPTION = cardStrings.DESCRIPTION;
	private static final int COST = 0;
	
	private static final int ATTACK_DMG = 6;
	private static final int CNT = 3;
	
	private static final int UPGRADE_PLUS_DMG = 3;
	
	public FreezingBeams() {
		super(
			ID,
			NAME,
			IMG_PATH,
			COST,
			DESCRIPTION,
			CardType.ATTACK,
			AbstractCardEnum.CIRNO_COLOR,
			CardRarity.COMMON,
			CardTarget.ALL_ENEMY
		);
		
		this.damage = this.baseDamage = ATTACK_DMG;
		this.magicNumber = this.baseMagicNumber = CNT;
	}
	
	public void use(AbstractPlayer p, AbstractMonster m) {
		ArrayList<AbstractMonster> vul = new ArrayList<>(), other = new ArrayList<>(),
				res = new ArrayList<>();
		
		for (AbstractMonster o : AbstractDungeon.getMonsters().monsters)
			(o.hasPower(VulnerablePower.POWER_ID) ? vul : other).add(o);
		
		if (vul.size() >= this.magicNumber) {
			for (int i = 0; i < this.magicNumber; i++) {
				int k = AbstractDungeon.miscRng.random(0, vul.size() - 1);
				
				res.add(vul.get(k));
				vul.remove(k);
			}
		}
		else {
			res.addAll(vul);
			
			for (int i = 0; !other.isEmpty() && i < this.magicNumber; i++) {
				int k = AbstractDungeon.miscRng.random(0, other.size() - 1);
				
				res.add(other.get(k));
				other.remove(k);
			}
		}
		
		for (AbstractMonster o : res)
			this.addToBot(new DamageAction(o, new DamageInfo(p, this.damage, this.damageTypeForTurn),
					AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
	}
	
	@Override
	public AbstractCard makeCopy() {
		return new FreezingBeams();
	}
	
	public void upgrade() {
		if (!this.upgraded) {
			upgradeName();
			
			upgradeDamage(UPGRADE_PLUS_DMG);
			initializeDescription();
		}
	}
}