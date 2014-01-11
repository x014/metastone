package net.pferdimanzug.hearthstone.analyzer.game.cards.concrete.shaman;

import net.pferdimanzug.hearthstone.analyzer.game.GameTag;
import net.pferdimanzug.hearthstone.analyzer.game.actions.TargetSelection;
import net.pferdimanzug.hearthstone.analyzer.game.cards.Rarity;
import net.pferdimanzug.hearthstone.analyzer.game.cards.SpellCard;
import net.pferdimanzug.hearthstone.analyzer.game.entities.heroes.HeroClass;
import net.pferdimanzug.hearthstone.analyzer.game.spells.ApplyTagSpell;
import net.pferdimanzug.hearthstone.analyzer.game.spells.MetaSpell;
import net.pferdimanzug.hearthstone.analyzer.game.spells.SingleTargetDamageSpell;

public class FrostShock extends SpellCard {

	public FrostShock() {
		super("Frost Shock", Rarity.FREE, HeroClass.SHAMAN, 1);
		setTargetRequirement(TargetSelection.ENEMY_CHARACTERS);
		setSpell(new MetaSpell(new SingleTargetDamageSpell(1), new ApplyTagSpell(GameTag.FROZEN)));
	}

}