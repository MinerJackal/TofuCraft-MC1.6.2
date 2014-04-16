package tsuteto.tofu.potion;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.ai.attributes.BaseAttributeMap;
import net.minecraft.potion.Potion;
import tsuteto.tofu.Settings;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.glowtofu.GlowingHandler;
import tsuteto.tofu.util.CustomPacketDispatcher;

public class PotionGlowing extends Potion
{

    public PotionGlowing(int par1, boolean par2, int par3)
    {
        super(par1, par2, par3);
    }
    
    @Override
    public void performEffect(EntityLivingBase par1EntityLivingBase, int par2)
    {
        int intv = par1EntityLivingBase.worldObj.isRemote ?
                Settings.clientGlowTofuLightInterval
                : Settings.serverGlowTofuLightInterval;

        if (par1EntityLivingBase.ticksExisted % intv == 0)
        {
            GlowingHandler.lightTarget(par1EntityLivingBase.worldObj, par1EntityLivingBase);
        }
    }
    
    @Override
    public void removeAttributesModifiersFromEntity(EntityLivingBase par1EntityLivingBase, BaseAttributeMap par2BaseAttributeMap, int par3)
    {
        super.removeAttributesModifiersFromEntity(par1EntityLivingBase, par2BaseAttributeMap, par3);
        
        GlowingHandler.removeLight(par1EntityLivingBase.worldObj, par1EntityLivingBase);
        
        CustomPacketDispatcher.create(TofuCraftCore.netChannelGlowingFinish)
                .addInt(par1EntityLivingBase.entityId)
                .sendToAllInDimension(par1EntityLivingBase.dimension);
    }
    
    @Override
    public boolean isReady(int par1, int par2)
    {
        return true;
    }
}
