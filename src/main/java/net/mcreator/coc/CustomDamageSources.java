package net.mcreator.coc;

import net.minecraft.util.DamageSource;

@CocModElements.ModElement.Tag
public class CustomDamageSources extends CocModElements.ModElement {
	/**
	 * Do not remove this constructor
	 */
	public CustomDamageSources(CocModElements instance) {
		super(instance, 614);
	}
	public static final DamageSource SPORES = (new DamageSource("spores")).setDamageBypassesArmor();
	public static final DamageSource GEYSER = (new DamageSource("geyser"));
	public static final DamageSource SHIELDING = (new DamageSource("shielding"));
	public static final DamageSource RADIANCE = (new DamageSource("radiance")).setDamageBypassesArmor();
	public static final DamageSource DOUBLE_EDGED = (new DamageSource("double_edged")).setDamageBypassesArmor();

}
