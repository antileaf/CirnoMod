package ThMod.patches;

import ThMod.ThMod;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.evacipated.cardcrawl.modthespire.patcher.PatchingException;
import com.megacrit.cardcrawl.actions.common.*;
import com.megacrit.cardcrawl.actions.unique.DiscoveryAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.unlock.UnlockTracker;
import javassist.CannotCompileException;
import javassist.CtBehavior;

import java.lang.reflect.Field;
import java.util.ArrayList;

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
	
	public static Object getPrivate(Object obj, String name)
			throws NoSuchFieldException, IllegalAccessException {
		Field f = obj.getClass().getDeclaredField(name);
		f.setAccessible(true);
		return f.get(obj);
	}
	
	@SpirePatch(clz = MakeTempCardInHandAction.class, method = "update")
	public static class FrostKingHandPatch {
		@SpirePostfixPatch
		public static void Postfix(MakeTempCardInHandAction act)
				throws NoSuchFieldException, IllegalAccessException {
			if (act.isDone)
				ThMod.frostKing((AbstractCard) getPrivate(act, "c"));
		}
	}
	
	@SpirePatch(clz = MakeTempCardInDrawPileAction.class, method = "update")
	public static class FrostKingDrawPilePatch {
		@SpirePostfixPatch
		public static void Postfix(MakeTempCardInDrawPileAction act)
				throws NoSuchFieldException, IllegalAccessException {
			if (act.isDone)
				ThMod.frostKing((AbstractCard) getPrivate(act, "cardToMake"));
		}
	}
	
	@SpirePatch(clz = MakeTempCardInDiscardAction.class, method = "update")
	public static class FrostKingDiscardPatch {
		@SpirePostfixPatch
		public static void Postfix(MakeTempCardInDiscardAction act)
				throws NoSuchFieldException, IllegalAccessException {
			if (act.isDone)
				ThMod.frostKing((AbstractCard) getPrivate(act, "c"));
		}
	}
	
	@SpirePatch(clz = MakeTempCardAtBottomOfDeckAction.class, method = "update")
	public static class FrostKingBottomOfDeckPatch {
			@SpireInsertPatch(locator = BottomLocator.class, localvars = "c")
			public static void Insert(MakeTempCardAtBottomOfDeckAction act, AbstractCard c) {
				ThMod.frostKing(c);
			}
			
			private static class BottomLocator extends SpireInsertLocator {
			public int[] Locate(CtBehavior ctMethodToPatch)
					throws CannotCompileException, PatchingException {
				Matcher finalMatcher = new Matcher.MethodCallMatcher(
						UnlockTracker.class, "markCardAsSeen");
				int[] loc = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
				return new int[]{loc[0]};
			}
		}
	}
	
	@SpirePatch(clz = MakeTempCardInDiscardAndDeckAction.class, method = "update")
	public static class FrostKingDiscardAndDeckPatch {
		@SpirePostfixPatch
		public static void Postfix(MakeTempCardInDiscardAndDeckAction act)
				throws NoSuchFieldException, IllegalAccessException {
			if (act.isDone)
				ThMod.frostKing((AbstractCard) getPrivate(act, "cardToMake"));
		}
	}
	
	@SpirePatch(clz = DiscoveryAction.class, method = "update")
	public static class FrostKingDiscoveryPatch {
//		@SpirePostfixPatch
//		public static void Postfix(DiscoveryAction act)
//				throws NoSuchFieldException, IllegalAccessException {
//				if (act.isDone)
//					ThMod.frostKing((AbstractCard) getPrivate(act, "c"));
//		}
		
		@SpireInsertPatch(locator = DiscoveryLocator.class)
		public static void Insert(DiscoveryAction act) {
			ThMod.frostKing(AbstractDungeon.cardRewardScreen.discoveryCard);
		}
		
		private static class DiscoveryLocator extends SpireInsertLocator {
			public int[] Locate(CtBehavior ctMethodToPatch)
					throws CannotCompileException, PatchingException {
				Matcher finalMatcher = new Matcher.MethodCallMatcher(
						AbstractPlayer.class, "hasPower");
				int[] loc = LineFinder.findInOrder(ctMethodToPatch, new ArrayList<>(), finalMatcher);
				return new int[]{loc[0]};
			}
		}
	}
//
//	@SpirePatch(clz = CirnoDiscoveryAction.class, method = "update")
//	public static class FrostKingCirnoDiscoveryPatch {
//		@SpirePostfixPatch
//		public static void Postfix(CirnoDiscoveryAction act) {
//			if (act.isDone)
//				ThMod.frostKing(act.amount);
//		}
//	}
	
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
