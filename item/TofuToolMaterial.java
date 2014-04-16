package tsuteto.tofu.item;

import net.minecraft.block.Block;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraftforge.common.EnumHelper;

public class TofuToolMaterial
{
    public static EnumToolMaterial KINU;
    public static EnumToolMaterial MOMEN;
    public static EnumToolMaterial SOLID;
    public static EnumToolMaterial METAL;
    public static EnumToolMaterial DIAMOND;
    
    static
    {
        KINU    = EnumHelper.addToolMaterial("TOFU_KINU",    0,    1, 1.0F, -3, 50);
        MOMEN   = EnumHelper.addToolMaterial("TOFU_MOMEN",   0,    5, 1.2F, -3,  2);
        SOLID   = EnumHelper.addToolMaterial("TOFU_SOLID",   1,  183, 3.0F, -1,  6);
        METAL   = EnumHelper.addToolMaterial("TOFU_METAL",   2,  415, 6.0F,  2,  8);
        DIAMOND = EnumHelper.addToolMaterial("TOFU_DIAMOND", 3, 1212, 8.0F,  4, 18);
    }
}
