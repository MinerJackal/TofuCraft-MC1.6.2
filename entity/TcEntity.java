package tsuteto.tofu.entity;

import net.minecraft.client.model.ModelSlime;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.world.WorldType;
import tsuteto.tofu.Settings;
import tsuteto.tofu.TofuCraftCore;
import tsuteto.tofu.block.tileentity.TileEntityMorijio;
import tsuteto.tofu.block.tileentity.TileEntityMorijioRenderer;
import cpw.mods.fml.client.registry.ClientRegistry;
import cpw.mods.fml.client.registry.RenderingRegistry;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class TcEntity
{
    public static int entityIdTofuSlime;
    public static int entityIdTofuCreeper;
    public static int entityIdTofunian;

    public static void register(TofuCraftCore core)
    {
        // Tofu Slime
        entityIdTofuSlime = Settings.entityIdTofuSlime == -1 ? EntityRegistry.findGlobalUniqueEntityId() : Settings.entityIdTofuSlime;
        EntityRegistry.registerGlobalEntityID(EntityTofuSlime.class, "TofuSlime", entityIdTofuSlime, 0xefeedf, 0xc2c1b8);
        EntityRegistry.registerModEntity(EntityTofuSlime.class, "TofuSlime", 1, core, 64, 2, true);
        EntityRegistry.addSpawn("TofuSlime", 8, 1, 1, EnumCreatureType.monster, WorldType.base12Biomes);

        // Tofu Creeper
        entityIdTofuCreeper = Settings.entityIdTofuCreeper == -1 ? EntityRegistry.findGlobalUniqueEntityId() : Settings.entityIdTofuCreeper;
        EntityRegistry.registerGlobalEntityID(EntityTofuCreeper.class, "TofuCreeper", entityIdTofuCreeper, 0xefeedf, 0x82817b);
        EntityRegistry.registerModEntity(EntityTofuCreeper.class, "TofuCreeper", 2, core, 64, 4, true);
        EntityRegistry.addSpawn("TofuCreeper", 1, 1, 1, EnumCreatureType.monster, WorldType.base12Biomes);

        // Tofunian
        entityIdTofunian = Settings.entityIdTofunian == -1 ? EntityRegistry.findGlobalUniqueEntityId() : Settings.entityIdTofunian;
        EntityRegistry.registerGlobalEntityID(EntityTofunian.class, "Tofunian", entityIdTofunian, 0xefeedf, 0x82817b);
        EntityRegistry.registerModEntity(EntityTofunian.class, "Tofunian", 4, core, 64, 4, true);

        // Zunda Arrow
        EntityRegistry.registerModEntity(EntityZundaArrow.class, "ZundaArrow", 0, core, 64, 2, true);
        // Fukumame
        EntityRegistry.registerModEntity(EntityFukumame.class, "Fukumame", 3, core, 64, 2, true);

        // Tofu Splash
        //EntityRegistry.registerModEntity(EntityTofuSplash.class, "TofuSplash", x, core, 32, 1, true);
    }

    @SideOnly(Side.CLIENT) // added on purpose!
    public static void registerEntityRenderer()
    {
        ClientRegistry.registerTileEntity(TileEntityMorijio.class, "TmMorijio", new TileEntityMorijioRenderer());

        RenderingRegistry.registerEntityRenderingHandler(EntityZundaArrow.class, new RenderZundaArrow());
        RenderingRegistry.registerEntityRenderingHandler(EntityFukumame.class, new RenderFukumame());
        RenderingRegistry.registerEntityRenderingHandler(EntityTofuSlime.class, new RenderTofuSlime(new ModelSlime(16), new ModelSlime(0), 0.25F));
        RenderingRegistry.registerEntityRenderingHandler(EntityTofuCreeper.class, new RenderTofuCreeper());
        RenderingRegistry.registerEntityRenderingHandler(EntityTofunian.class, new RenderTofunian());
        //RenderingRegistry.registerEntityRenderingHandler(EntityTofuSplash.class, new RenderTofuSplash());
    }
}
