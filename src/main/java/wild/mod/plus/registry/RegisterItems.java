package wild.mod.plus.registry;

import frozenblock.wild.mod.WildMod;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public abstract class RegisterItems {

    public static final Item GOAT_HORN = new ToolItem(new ToolMaterial() {
        @Override
        public int getDurability() {
            return 150;
        }

        @Override
        public float getMiningSpeedMultiplier() {
            return 0;
        }

        @Override
        public float getAttackDamage() {
            return 0;
        }

        @Override
        public int getMiningLevel() {
            return 0;
        }

        @Override
        public int getEnchantability() {
            return 0;
        }

        @Override
        public Ingredient getRepairIngredient() {
            return null;
        }
    }, new FabricItemSettings().group(ItemGroup.TOOLS).maxCount(1));

    public static void RegisterItems() {
        Registry.register(Registry.ITEM, new Identifier(WildMod.MOD_ID, "goat_horn"), GOAT_HORN);
    }
}
