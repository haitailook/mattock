package cn.tlgjx.mattock.tag;

import cn.tlgjx.mattock.Mattock;
import net.minecraft.block.Block;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModBlockTags {

    public static final TagKey<Block> MATTOCK_MINEABLE = of("mattock_mineable");

    private static TagKey<Block> of(String id) {
        return TagKey.of(RegistryKeys.BLOCK, new Identifier(Mattock.MOD_ID, id));
    }
}