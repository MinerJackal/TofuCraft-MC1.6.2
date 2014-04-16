package tsuteto.tofu.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import tsuteto.tofu.util.Utils;

public class ItemTcFood extends ItemFood
{
    private int customDuration = -1;

    public ItemTcFood(int par1, int par2, float par3, boolean par4)
    {
        super(par1, par2, par3, par4);
    }

    public ItemTcFood setEatingDuration(int duration)
    {
        this.customDuration = duration;
        return this;
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        if (customDuration == -1)
        {
            return super.getMaxItemUseDuration(par1ItemStack);
        }
        else
        {
            return customDuration;
        }
    }

    @Override
    public CreativeTabs[] getCreativeTabs()
    {
        return Utils.getCreativeTabs(this);
    }

    @Override
    public ItemTcFood setUnlocalizedName(String name)
    {
        super.setUnlocalizedName(name);
        this.setTextureName(name);
        return this;
    }
}
