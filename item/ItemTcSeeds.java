package tsuteto.tofu.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemSeeds;
import tsuteto.tofu.util.Utils;

public class ItemTcSeeds extends ItemSeeds {

	public ItemTcSeeds(int par1, int par2, int par3) {
		super(par1, par2, par3);
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return Utils.getCreativeTabs(this);
	}

    @Override
    public ItemTcSeeds setUnlocalizedName(String name)
    {
        super.setUnlocalizedName(name);
        this.setTextureName(name);
        return this;
    }
}
