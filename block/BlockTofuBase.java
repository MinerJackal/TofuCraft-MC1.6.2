package tsuteto.tofu.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public abstract class BlockTofuBase extends TcBlock
{
    public BlockTofuBase(int par1, Material material)
    {
        super(par1, material);
    }

    private int dropItemId;
    
    /** Whether the tofu can be scooped with Tofu Scoop */
    private boolean scoopable = true;

    /**
     * Returns the quantity of items to drop on block destruction.
     */
    @Override
    public int quantityDropped(Random par1Random)
    {
        return 4;
    }

    /**
     * Returns the ID of the items to drop on destruction.
     */
    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return dropItemId;
    }

    public BlockTofuBase setDropItem(Item dropItem)
    {
        this.dropItemId = dropItem.itemID;
        return this;
    }

    public BlockTofuBase setScoopable(boolean b)
    {
        this.scoopable = b;
        return this;
    }

    public boolean isScoopable()
    {
        return this.scoopable;
    }

    public ItemStack createScoopedBlockStack()
    {
        return new ItemStack(this);
    }
}
