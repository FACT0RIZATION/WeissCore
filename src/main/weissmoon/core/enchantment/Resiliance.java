package weissmoon.core.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnumEnchantmentType;

public class Resiliance extends Enchantment {

    public Resiliance(int id) {
        super(id, 2, EnumEnchantmentType.breakable);
    }
}
