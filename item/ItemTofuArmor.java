package tsuteto.tofu.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.util.Utils;

public class ItemTofuArmor extends ItemArmor
{
    private String armorTextureFile;

    public ItemTofuArmor(int par1, EnumArmorMaterial material, int par3, int par4)
    {
        super(par1, material, par3, par4);

    }

    public ItemTofuArmor setArmorTexture(String file)
    {
        armorTextureFile = file;
        return this;
    }

    @Override
    public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer)
    {
        return armorTextureFile;
    }

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return Utils.getCreativeTabs(this);
	}
}
