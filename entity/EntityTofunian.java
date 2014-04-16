package tsuteto.tofu.entity;

import net.minecraft.entity.EntityAgeable;
import net.minecraft.entity.EntityLivingData;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.world.World;
import tsuteto.tofu.Settings;
import tsuteto.tofu.item.TcItem;

public class EntityTofunian extends EntityVillager
{
    private int friendship = 0;

    public EntityTofunian(World par1World)
    {
        this(par1World, 0);
    }

    public EntityTofunian(World par1World, int par2)
    {
        super(par1World, Settings.professionIdTofunian);
        this.setSize(0.45F, 1.4F);
    }

    @Override
    public float getEyeHeight()
    {
        return 1.0f;
    }

    @Override
    public EntityLivingData onSpawnWithEgg(EntityLivingData par1EntityLivingData)
    {
        par1EntityLivingData = super.onSpawnWithEgg(par1EntityLivingData);
        this.setProfession(Settings.professionIdTofunian);
        return par1EntityLivingData;
    }

    @Override
    public EntityVillager func_90012_b(EntityAgeable par1EntityAgeable)
    {
        EntityTofunian tofunian = new EntityTofunian(this.worldObj);
        tofunian.onSpawnWithEgg((EntityLivingData)null);
        return tofunian;
    }

    @Override
    public void useRecipe(MerchantRecipe par1MerchantRecipe)
    {
        super.useRecipe(par1MerchantRecipe);

        if (par1MerchantRecipe.getItemToBuy().itemID == TcItem.tofuMomen.itemID)
        {
            this.friendship += par1MerchantRecipe.getItemToBuy().stackSize;
        }
    }

    public int getFriendship()
    {
        return this.friendship;
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    @Override
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.writeEntityToNBT(par1NBTTagCompound);
        par1NBTTagCompound.setInteger("Riches", this.friendship);
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    @Override
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        super.readEntityFromNBT(par1NBTTagCompound);
        this.friendship = par1NBTTagCompound.getInteger("Riches");
    }

    @Override
    public void playSound(String par1Str, float par2, float par3)
    {
        this.worldObj.playSoundAtEntity(this, par1Str.replace("mob.villager", "mob.tofunian"), par2, par3);
    }
}
