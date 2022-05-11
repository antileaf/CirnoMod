package ThMod.patches;

import ThMod.ThMod;
import ThMod.action.CirnoDiscoveryAction;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;

public class FrostKingPatch {
	
//	static void work(AbstractGameAction act) throws NoSuchFieldException {
//		if (act instanceof MakeTempCardInHandAction ||
//			act instanceof MakeTempCardInDrawPileAction ||
//			act instanceof MakeTempCardInDiscardAction ||
//			act instanceof MakeTempCardAtBottomOfDeckAction ||
//			act instanceof MakeTempCardInDiscardAndDeckAction) {
//			if (act.isDone) {
//				if (act.getClass().getDeclaredField("sameUUID").equals(false))
//			}
//		}
//	}
	
	@SpirePatch(clz = MakeTempCardInHandAction.class, method = "update")
	public static class FrostKingHandPatch {
		@SpirePostfixPatch
		public static void Postfix(MakeTempCardInHandAction act) {
			if (act.isDone)
				ThMod.frostKing();
		}
	}
	
	@SpirePatch(clz = MakeTempCardInDrawPileAction.class, method = "update")
	public static class FrostKingDrawPilePatch {
		@SpirePostfixPatch
		public static void Postfix(MakeTempCardInDrawPileAction act) {
			if (act.isDone)
				ThMod.frostKing();
		}
	}
	
	@SpirePatch(clz = MakeTempCardInDiscardAction.class, method = "update")
	public static class FrostKingDiscardPatch {
		@SpirePostfixPatch
		public static void Postfix(MakeTempCardInDiscardAction act) {
			if (act.isDone)
				ThMod.frostKing();
		}
	}
	
	@SpirePatch(clz = MakeTempCardAtBottomOfDeckAction.class, method = "update")
	public static class FrostKingBottomOfDeckPatch {
		@SpirePostfixPatch
		public static void Postfix(MakeTempCardAtBottomOfDeckAction act) {
			if (act.isDone)
				ThMod.frostKing();
		}
	}
	
	@SpirePatch(clz = MakeTempCardInDiscardAndDeckAction.class, method = "update")
	public static class FrostKingDiscardAndDeckPatch {
		@SpirePostfixPatch
		public static void Postfix(MakeTempCardInDiscardAndDeckAction act) {
			if (act.isDone)
				ThMod.frostKing();
		}
	}
	
	@SpirePatch(clz = DiscoveryAction.class, method = "update")
	public static class FrostKingDiscoveryPatch {
		@SpirePostfixPatch
		public static void Postfix(DiscoveryAction act) {
			if (act.isDone)
				ThMod.frostKing();
		}
	}
	
	@SpirePatch(clz = CirnoDiscoveryAction.class, method = "update")
	public static class FrostKingCirnoDiscoveryPatch {
		@SpirePostfixPatch
		public static void Postfix(CirnoDiscoveryAction act) {
			if (act.isDone)
				ThMod.frostKing();
		}
	}
	
	/*
	@SpirePatch(clz = AbstractCreature.class, method = "hasPower")
	public static class FrostKingUglyPatch {
		@SpirePostfixPatch
		public static void Postfix(AbstractCreature test, String targetID) {
			if (targetID.equals("MasterRealityPower"))
				ThMod.frostKing(); // 离谱
		}
	}
	*/
}
