/**
 * This mod element is always locked. Enter your code in the methods below.
 * If you don't need some of these methods, you can remove them as they
 * are overrides of the base class CocModElements.ModElement.
 *
 * You can register new events in this class too.
 *
 * As this class is loaded into mod element list, it NEEDS to extend
 * ModElement class. If you remove this extend statement or remove the
 * constructor, the compilation will fail.
 *
 * If you want to make a plain independent class, create it using
 * Project Browser - New... and make sure to make the class
 * outside net.mcreator.coc as this package is managed by MCreator.
 *
 * If you change workspace package, modid or prefix, you will need
 * to manually adapt this file to these changes or remake it.
*/
package net.mcreator.coc;

import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;
import java.util.UUID;
import net.minecraft.entity.ai.attributes.AttributeModifier;

@CocModElements.ModElement.Tag
public class CustomAttributes extends CocModElements.ModElement 
{
	/**
	 * Do not remove this constructor
	 */
	public CustomAttributes(CocModElements instance) {
		super(instance, 870);
	}

	public static final UUID STUNNED_SLOW_ID = UUID.fromString("ee07efd8-1cab-11eb-adc1-0242ac120002");
	public static final AttributeModifier STUNNED_SLOW = (new AttributeModifier(STUNNED_SLOW_ID, "Stunned slow effect", (double)-1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
	public static final UUID STUNNED_BLIND_ID = UUID.fromString("60c18351-399a-420e-8531-ce7a8a17e52d");
	public static final AttributeModifier STUNNED_BLIND = (new AttributeModifier(STUNNED_BLIND_ID, "Stunned prevent targeting", (double)-1.0F, AttributeModifier.Operation.MULTIPLY_TOTAL)).setSaved(false);
}
