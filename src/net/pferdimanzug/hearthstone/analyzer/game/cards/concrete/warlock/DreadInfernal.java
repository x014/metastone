package net.pferdimanzug.hearthstone.analyzer.game.cards.concrete.warlock;

import net.pferdimanzug.hearthstone.analyzer.game.GameTag;
import net.pferdimanzug.hearthstone.analyzer.game.actions.Battlecry;
import net.pferdimanzug.hearthstone.analyzer.game.actions.TargetSelection;
import net.pferdimanzug.hearthstone.analyzer.game.cards.MinionCard;
import net.pferdimanzug.hearthstone.analyzer.game.cards.Rarity;
import net.pferdimanzug.hearthstone.analyzer.game.entities.heroes.HeroClass;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Minion;
import net.pferdimanzug.hearthstone.analyzer.game.entities.minions.Race;
import net.pferdimanzug.hearthstone.analyzer.game.spells.AreaDamageSpell;

public class DreadInfernal extends MinionCard {

	public DreadInfernal() {
		super("Dread Infernal", Rarity.FREE, HeroClass.WARLOCK, 6);
	}

	@Override
	public Minion summon() {
		Minion dreadInfernal = createMinion(6, 6, Race.DEMON);
		Battlecry infernoBattlecry = Battlecry.createBattlecry(new AreaDamageSpell(1, TargetSelection.ANY));
		dreadInfernal.setTag(GameTag.BATTLECRY, infernoBattlecry);
		return dreadInfernal;
	}
	

}