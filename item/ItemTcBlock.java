package tsuteto.tofu.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemBlock;
import tsuteto.tofu.util.Utils;

public class ItemTcBlock extends ItemBlock {

	public ItemTcBlock(int par1) {
		super(par1);
	}

	@Override
	public CreativeTabs[] getCreativeTabs() {
		return Utils.getCreativeTabs(this);
	}
}
