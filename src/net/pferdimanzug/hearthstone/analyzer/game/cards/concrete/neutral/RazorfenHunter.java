package net.pferdimanzug.hearthstone.analyzer.game.cards.concrete.neutral;

import net.pferdimanzug.hearthstone.analyzer.game.GameTag;
import net.pferdimanzug.hearthstone.analyzer.game.actions.Battlecry;
import net.pferdimanzug.hearthstone.analyzer.game.actions.TargetSelection;
import net.pferdimanzug.hearthstone.analyzer.game.cards.MinionCard;
import net.pferdimanzug.hearthstone.analyzer.game.cards.Rarity;
import net.pferdimanzug.hearthstone.analyzer.game.entities.heroes.HeroClass;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Minion;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Race;
import net.pferdimanzug.hearthstone.analyzer.game.spells.SummonSpell;

public class RazorfenHunter extends MinionCard {

	private class Boar extends MinionCard {

		public Boar() {
			super("Boar", Rarity.FREE, HeroClass.ANY, 1);
		}

		@Override
		public Minion summon() {
			return createMinion(1, 1, Race.BEAST);
		}

	}

	public RazorfenHunter() {
		super("Razorfen Hunter", Rarity.FREE, HeroClass.ANY, 3);
	}

	@Override
	public Minion summon() {
		Minion razorfenHunter = createMinion(2, 3);
		Battlecry battlecry = Battlecry.createBattlecry(new SummonSpell(new Boar()), TargetSelection.NONE);
		razorfenHunter.setTag(GameTag.BATTLECRY, battlecry);
		return razorfenHunter;
	}

}