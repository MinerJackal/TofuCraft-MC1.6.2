package tsuteto.tofu.item;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.entity.EntityTofuSlime;
import tsuteto.tofu.params.DataType;
import tsuteto.tofu.params.EntityInfo;
import tsuteto.tofu.util.CustomPacketDispatcher;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemTofuSlimeRadar extends TcItem
{

    private Icon iconIdle;
    private Icon iconOk;
    private Icon iconFail;

    public ItemTofuSlimeRadar(int var1)
    {
        super(var1);
        this.setMaxStackSize(1);
        this.setMaxDamage(87);
    }

    @Override
    public void onUpdate(ItemStack par1ItemStack, World par2World, Entity par3Entity, int par4, boolean par5)
    {
        if (par2World.isRemote)
        {
            Entry entry = EntityInfo.instance().get(par3Entity.entityId, DataType.SlimeRadar);

            if (entry != null)
            {
                if (entry.result)
                {
                    this.itemIcon = (entry.ticks & 7) > 2 ? this.iconOk : this.iconIdle;
                }
                else
                {
                    this.itemIcon = this.iconFail;
                }
                entry.ticks--;

                if (entry.ticks < 0)
                {
                    this.itemIcon = this.iconIdle;
                    EntityInfo.instance().remove(par3Entity.entityId, DataType.SlimeRadar);
                }
                else
                {
                    EntityInfo.instance().set(par3Entity.entityId, DataType.SlimeRadar, entry);
                }
            }
        }
    }

    @Override
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));

        boolean flag = par3EntityPlayer.capabilities.isCreativeMode;

        if (flag || par1ItemStack.getItemDamage() <= par1ItemStack.getMaxDamage())
        {
            if (!par2World.isRemote)
            {
                BiomeGenBase biome = par3EntityPlayer.worldObj.getBiomeGenForCoords(MathHelper.floor_double(par3EntityPlayer.posX), MathHelper.floor_double(par3EntityPlayer.posZ));
                boolean isSpawnChunk = BiomeDictionary.isBiomeOfType(biome, TofuCraftCore.BIOME_TYPE_TOFU) || EntityTofuSlime.isSpawnChunk(par3EntityPlayer.worldObj, par3EntityPlayer.posX, par3EntityPlayer.posZ);

                CustomPacketDispatcher.create(TofuCraftCore.netChannelTofuRadar)
                        .addBoolean(isSpawnChunk)
                        .sendToPlayer(par3EntityPlayer);
            }

            if (!par3EntityPlayer.capabilities.isCreativeMode && par1ItemStack.isItemStackDamageable())
            {
                par1ItemStack.attemptDamageItem(1, par3EntityPlayer.getRNG());
            }
            par2World.playSoundAtEntity(par3EntityPlayer, "random.click", 0.5F, 1.0F);
        }
        return par1ItemStack;
    }

    public void onUse(boolean isSpawnChunk, ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        Entry entry = new Entry();
        entry.ticks = 50;
        entry.result = isSpawnChunk;
        EntityInfo.instance().set(par3EntityPlayer.entityId, DataType.SlimeRadar, entry);
    }

    @Override
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    @Override
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.none;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerIcons(IconRegister par1IconRegister)
    {
        super.registerIcons(par1IconRegister);

        this.iconIdle = par1IconRegister.registerIcon("tofucraft:tofuRadar");
        this.iconOk = par1IconRegister.registerIcon("tofucraft:tofuRadar_ok");
        this.iconFail = par1IconRegister.registerIcon("tofucraft:tofuRadar_fail");
    }

    private class Entry
    {
        public int ticks;
        public boolean result;
    }
}
