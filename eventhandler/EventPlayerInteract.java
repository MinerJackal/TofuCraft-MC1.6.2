package tsuteto.tofu.eventhandler;

import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.Action;
import tsuteto.tofu.Settings;
import tsuteto.tofu.achievement.TcAchievementMgr;
import tsuteto.tofu.block.TcBlock;

public class EventPlayerInteract
{
    @ForgeSubscribe
    public void onPlayerInteract(PlayerInteractEvent event)
    {
        if (event.action == Action.RIGHT_CLICK_BLOCK || event.action == Action.RIGHT_CLICK_AIR)
        {
            EntityPlayer player = event.entityPlayer;
            ItemStack equippedItem = player.getCurrentEquippedItem();
            if (equippedItem != null && equippedItem.itemID == Item.glassBottle.itemID)
            {
                MovingObjectPosition var4 = this.getMovingObjectPositionFromPlayer(player.worldObj, player, true);
                if (var4 != null)
                {
                    int blockId = player.worldObj.getBlockId(var4.blockX, var4.blockY, var4.blockZ);
                    if (blockId == TcBlock.soymilkStill.blockID
                            || blockId == TcBlock.soymilkHellStill.blockID
                            || blockId == TcBlock.soySauceStill.blockID)
                    {
                        event.setCanceled(true);
                    }
                }
            }
        }
    }

    @ForgeSubscribe
    public void onPlayerEntityInteract(EntityInteractEvent event)
    {
        if (event.target instanceof EntityVillager)
        {
            EntityVillager villager = (EntityVillager)event.target;
            if (villager.getProfession() == Settings.professionIdTofucook)
            {
                TcAchievementMgr.achieve(event.entityPlayer, TcAchievementMgr.Key.tofuCook);
            }
            if (villager.getProfession() == Settings.professionIdTofunian)
            {
                TcAchievementMgr.achieve(event.entityPlayer, TcAchievementMgr.Key.tofunian);
            }
        }
    }

    protected MovingObjectPosition getMovingObjectPositionFromPlayer(World par1World, EntityPlayer par2EntityPlayer, boolean par3)
    {
        float var4 = 1.0F;
        float var5 = par2EntityPlayer.prevRotationPitch + (par2EntityPlayer.rotationPitch - par2EntityPlayer.prevRotationPitch) * var4;
        float var6 = par2EntityPlayer.prevRotationYaw + (par2EntityPlayer.rotationYaw - par2EntityPlayer.prevRotationYaw) * var4;
        double var7 = par2EntityPlayer.prevPosX + (par2EntityPlayer.posX - par2EntityPlayer.prevPosX) * var4;
        double var9 = par2EntityPlayer.prevPosY + (par2EntityPlayer.posY - par2EntityPlayer.prevPosY) * var4 + 1.62D - par2EntityPlayer.yOffset;
        double var11 = par2EntityPlayer.prevPosZ + (par2EntityPlayer.posZ - par2EntityPlayer.prevPosZ) * var4;
        Vec3 var13 = par1World.getWorldVec3Pool().getVecFromPool(var7, var9, var11);
        float var14 = MathHelper.cos(-var6 * 0.017453292F - (float)Math.PI);
        float var15 = MathHelper.sin(-var6 * 0.017453292F - (float)Math.PI);
        float var16 = -MathHelper.cos(-var5 * 0.017453292F);
        float var17 = MathHelper.sin(-var5 * 0.017453292F);
        float var18 = var15 * var16;
        float var20 = var14 * var16;
        double var21 = 5.0D;
        if (par2EntityPlayer instanceof EntityPlayerMP)
        {
            var21 = ((EntityPlayerMP)par2EntityPlayer).theItemInWorldManager.getBlockReachDistance();
        }
        Vec3 var23 = var13.addVector(var18 * var21, var17 * var21, var20 * var21);
        return par1World.rayTraceBlocks_do_do(var13, var23, par3, !par3);
    }
}
