package ThMod.patches;

import ThMod.powers.Cirno.FreezeTouchMePower;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class FreezeTouchMePatch {
	
	@SpirePatch(clz = AbstractCreature.class, method = "loseBlock", paramtypez = {int.class})
	public static class FreezeTouchMePostLoseBlockPatch {
		@SpirePostfixPatch
		public static void Postfix(AbstractCreature c, int amount) {
			if (c instanceof AbstractPlayer) {
				AbstractPlayer p = (AbstractPlayer) c;
				
				if (p.hasPower(FreezeTouchMePower.POWER_ID))
					((FreezeTouchMePower) p.getPower(FreezeTouchMePower.POWER_ID)).
							triggerOnce(amount / 2);
			}
		}
	}
}
