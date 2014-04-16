package tsuteto.tofu.block;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import tsuteto.tofu.item.TcItem;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BlockTofuGrilled extends BlockTofuBase
{
	private Icon iconSide;

    public BlockTofuGrilled(int par1)
    {
        super(par1, Material.sponge);
        this.setCreativeTab(CreativeTabs.tabBlock);
    }

    @Override
    public Icon getIcon(int side, int metadata)
    {
        int direction = metadata;

        // Upside down!
        if (direction == 1 && side == 0 || direction == 0 && side == 1)
        {
            return this.blockIcon;
        }
        else if (direction >= 2 && direction == side)
        {
            return this.blockIcon;
        }
        else
        {
            return this.iconSide;
        }
    }

    @Override
    public void onBlockPlacedBy(World par1World, int par2, int par3, int par4, EntityLivingBase par5EntityLiving, ItemStack par6ItemStack)
    {
        int var6 = determineOrientation(par1World, par2, par3, par4, par5EntityLiving);
        par1World.setBlockMetadataWithNotify(par2, par3, par4, var6, 2);
    }

    /**
     * gets the way this piston should face for that entity that placed it.
     */
    public static int determineOrientation(World par0World, int par1, int par2, int par3, EntityLivingBase par4EntityPlayer)
    {
        if (MathHelper.abs((float)par4EntityPlayer.posX - par1) < 2.0F && MathHelper.abs((float)par4EntityPlayer.posZ - par3) < 2.0F)
        {
            double var5 = par4EntityPlayer.posY + 1.82D - par4EntityPlayer.yOffset;

            if (var5 - par2 > 2.0D)
            {
                return 0;
            }

            if (par2 - var5 > 0.0D)
            {
                return 1;
            }
        }

        int var7 = MathHelper.floor_double((par4EntityPlayer.rotationYaw * 4.0F / 360.0F) + 0.5D) & 3;
        return var7 == 0 ? 2 : (var7 == 1 ? 5 : (var7 == 2 ? 3 : (var7 == 3 ? 4 : 1)));
    }

    @Override
    public int idDropped(int par1, Random par2Random, int par3)
    {
        return TcItem.tofuGrilled.itemID;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        this.blockIcon = par1IconRegister.registerIcon("tofucraft:blockTofuGrilled");
        this.iconSide = par1IconRegister.registerIcon("tofucraft:blockTofuMomen");
    }
}
