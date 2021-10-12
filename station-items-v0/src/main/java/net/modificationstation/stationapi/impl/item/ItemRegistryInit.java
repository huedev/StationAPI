package net.modificationstation.stationapi.impl.item;

import net.mine_diver.unsafeevents.listener.EventListener;
import net.mine_diver.unsafeevents.listener.ListenerPriority;
import net.modificationstation.stationapi.api.event.registry.ItemRegistryEvent;
import net.modificationstation.stationapi.api.mod.entrypoint.Entrypoint;
import net.modificationstation.stationapi.api.mod.entrypoint.EventBusPolicy;
import net.modificationstation.stationapi.api.registry.ItemRegistry;

import static net.minecraft.item.ItemBase.apple;
import static net.minecraft.item.ItemBase.arrow;
import static net.minecraft.item.ItemBase.bed;
import static net.minecraft.item.ItemBase.boat;
import static net.minecraft.item.ItemBase.bone;
import static net.minecraft.item.ItemBase.book;
import static net.minecraft.item.ItemBase.bow;
import static net.minecraft.item.ItemBase.bowl;
import static net.minecraft.item.ItemBase.bread;
import static net.minecraft.item.ItemBase.brick;
import static net.minecraft.item.ItemBase.bucket;
import static net.minecraft.item.ItemBase.cake;
import static net.minecraft.item.ItemBase.chainBoots;
import static net.minecraft.item.ItemBase.chainChestplate;
import static net.minecraft.item.ItemBase.chainHelmet;
import static net.minecraft.item.ItemBase.chainLeggings;
import static net.minecraft.item.ItemBase.clay;
import static net.minecraft.item.ItemBase.clock;
import static net.minecraft.item.ItemBase.coal;
import static net.minecraft.item.ItemBase.compass;
import static net.minecraft.item.ItemBase.cookedFish;
import static net.minecraft.item.ItemBase.cookedPorkchop;
import static net.minecraft.item.ItemBase.cookie;
import static net.minecraft.item.ItemBase.diamond;
import static net.minecraft.item.ItemBase.diamondAxe;
import static net.minecraft.item.ItemBase.diamondBoots;
import static net.minecraft.item.ItemBase.diamondChestplate;
import static net.minecraft.item.ItemBase.diamondHelmet;
import static net.minecraft.item.ItemBase.diamondHoe;
import static net.minecraft.item.ItemBase.diamondLeggings;
import static net.minecraft.item.ItemBase.diamondPickaxe;
import static net.minecraft.item.ItemBase.diamondShovel;
import static net.minecraft.item.ItemBase.diamondSword;
import static net.minecraft.item.ItemBase.dyePowder;
import static net.minecraft.item.ItemBase.egg;
import static net.minecraft.item.ItemBase.feather;
import static net.minecraft.item.ItemBase.fishingRod;
import static net.minecraft.item.ItemBase.flint;
import static net.minecraft.item.ItemBase.flintAndSteel;
import static net.minecraft.item.ItemBase.glowstoneDust;
import static net.minecraft.item.ItemBase.goldAxe;
import static net.minecraft.item.ItemBase.goldBoots;
import static net.minecraft.item.ItemBase.goldChestplate;
import static net.minecraft.item.ItemBase.goldHelmet;
import static net.minecraft.item.ItemBase.goldHoe;
import static net.minecraft.item.ItemBase.goldIngot;
import static net.minecraft.item.ItemBase.goldLeggings;
import static net.minecraft.item.ItemBase.goldPickaxe;
import static net.minecraft.item.ItemBase.goldShovel;
import static net.minecraft.item.ItemBase.goldSword;
import static net.minecraft.item.ItemBase.goldenApple;
import static net.minecraft.item.ItemBase.gunpowder;
import static net.minecraft.item.ItemBase.ironAxe;
import static net.minecraft.item.ItemBase.ironBoots;
import static net.minecraft.item.ItemBase.ironChestplate;
import static net.minecraft.item.ItemBase.ironDoor;
import static net.minecraft.item.ItemBase.ironHelmet;
import static net.minecraft.item.ItemBase.ironHoe;
import static net.minecraft.item.ItemBase.ironIngot;
import static net.minecraft.item.ItemBase.ironLeggings;
import static net.minecraft.item.ItemBase.ironPickaxe;
import static net.minecraft.item.ItemBase.ironShovel;
import static net.minecraft.item.ItemBase.ironSword;
import static net.minecraft.item.ItemBase.lavaBucket;
import static net.minecraft.item.ItemBase.leather;
import static net.minecraft.item.ItemBase.leatherBoots;
import static net.minecraft.item.ItemBase.leatherChestplate;
import static net.minecraft.item.ItemBase.leatherHelmet;
import static net.minecraft.item.ItemBase.leatherLeggings;
import static net.minecraft.item.ItemBase.map;
import static net.minecraft.item.ItemBase.milk;
import static net.minecraft.item.ItemBase.minecart;
import static net.minecraft.item.ItemBase.minecartChest;
import static net.minecraft.item.ItemBase.minecartFurnace;
import static net.minecraft.item.ItemBase.mushroomStew;
import static net.minecraft.item.ItemBase.painting;
import static net.minecraft.item.ItemBase.paper;
import static net.minecraft.item.ItemBase.rawFish;
import static net.minecraft.item.ItemBase.rawPorkchop;
import static net.minecraft.item.ItemBase.record13;
import static net.minecraft.item.ItemBase.recordCat;
import static net.minecraft.item.ItemBase.redstoneDust;
import static net.minecraft.item.ItemBase.redstoneRepeater;
import static net.minecraft.item.ItemBase.saddle;
import static net.minecraft.item.ItemBase.seeds;
import static net.minecraft.item.ItemBase.shears;
import static net.minecraft.item.ItemBase.sign;
import static net.minecraft.item.ItemBase.slimeball;
import static net.minecraft.item.ItemBase.snowball;
import static net.minecraft.item.ItemBase.stick;
import static net.minecraft.item.ItemBase.stoneAxe;
import static net.minecraft.item.ItemBase.stoneHoe;
import static net.minecraft.item.ItemBase.stonePickaxe;
import static net.minecraft.item.ItemBase.stoneShovel;
import static net.minecraft.item.ItemBase.stoneSword;
import static net.minecraft.item.ItemBase.string;
import static net.minecraft.item.ItemBase.sugar;
import static net.minecraft.item.ItemBase.sugarCanes;
import static net.minecraft.item.ItemBase.waterBucket;
import static net.minecraft.item.ItemBase.wheat;
import static net.minecraft.item.ItemBase.woodAxe;
import static net.minecraft.item.ItemBase.woodDoor;
import static net.minecraft.item.ItemBase.woodHoe;
import static net.minecraft.item.ItemBase.woodPickaxe;
import static net.minecraft.item.ItemBase.woodShovel;
import static net.minecraft.item.ItemBase.woodSword;
import static net.modificationstation.stationapi.api.StationAPI.LOGGER;
import static net.modificationstation.stationapi.api.registry.Identifier.of;

/**
 * @author mine_diver
 */
@Entrypoint(eventBus = @EventBusPolicy(registerInstance = false))
public class ItemRegistryInit {

    @EventListener(priority = ListenerPriority.HIGH)
    private static void registerItems(ItemRegistryEvent event) {
        ItemRegistry r = event.registry;

        r.register(of("iron_shovel"), ironShovel);
        r.register(of("iron_pickaxe"), ironPickaxe);
        r.register(of("iron_axe"), ironAxe);
        r.register(of("flint_and_steel"), flintAndSteel);
        r.register(of("apple"), apple);
        r.register(of("bow"), bow);
        r.register(of("arrow"), arrow);
        r.register(of("coal"), coal);
        r.register(of("diamond"), diamond);
        r.register(of("iron_ingot"), ironIngot);
        r.register(of("gold_ingot"), goldIngot);
        r.register(of("iron_sword"), ironSword);
        r.register(of("wooden_sword"), woodSword);
        r.register(of("wooden_shovel"), woodShovel);
        r.register(of("wooden_pickaxe"), woodPickaxe);
        r.register(of("wooden_axe"), woodAxe);
        r.register(of("stone_sword"), stoneSword);
        r.register(of("stone_shovel"), stoneShovel);
        r.register(of("stone_pickaxe"), stonePickaxe);
        r.register(of("stone_axe"), stoneAxe);
        r.register(of("diamond_sword"), diamondSword);
        r.register(of("diamond_shovel"), diamondShovel);
        r.register(of("diamond_pickaxe"), diamondPickaxe);
        r.register(of("diamond_axe"), diamondAxe);
        r.register(of("stick"), stick);
        r.register(of("bowl"), bowl);
        r.register(of("mushroom_stew"), mushroomStew);
        r.register(of("golden_sword"), goldSword);
        r.register(of("golden_shovel"), goldShovel);
        r.register(of("golden_pickaxe"), goldPickaxe);
        r.register(of("golden_axe"), goldAxe);
        r.register(of("string"), string);
        r.register(of("feather"), feather);
        r.register(of("gunpowder"), gunpowder);
        r.register(of("wooden_hoe"), woodHoe);
        r.register(of("stone_hoe"), stoneHoe);
        r.register(of("iron_hoe"), ironHoe);
        r.register(of("diamond_hoe"), diamondHoe);
        r.register(of("golden_hoe"), goldHoe);
        r.register(of("wheat_seeds"), seeds);
        r.register(of("wheat"), wheat);
        r.register(of("bread"), bread);
        r.register(of("leather_helmet"), leatherHelmet);
        r.register(of("leather_chestplate"), leatherChestplate);
        r.register(of("leather_leggings"), leatherLeggings);
        r.register(of("leather_boots"), leatherBoots);
        r.register(of("chainmail_helmet"), chainHelmet);
        r.register(of("chainmail_chestplate"), chainChestplate);
        r.register(of("chainmail_leggings"), chainLeggings);
        r.register(of("chainmail_boots"), chainBoots);
        r.register(of("iron_helmet"), ironHelmet);
        r.register(of("iron_chestplate"), ironChestplate);
        r.register(of("iron_leggings"), ironLeggings);
        r.register(of("iron_boots"), ironBoots);
        r.register(of("diamond_helmet"), diamondHelmet);
        r.register(of("diamond_chestplate"), diamondChestplate);
        r.register(of("diamond_leggings"), diamondLeggings);
        r.register(of("diamond_boots"), diamondBoots);
        r.register(of("golden_helmet"), goldHelmet);
        r.register(of("golden_chestplate"), goldChestplate);
        r.register(of("golden_leggings"), goldLeggings);
        r.register(of("golden_boots"), goldBoots);
        r.register(of("flint"), flint);
        r.register(of("porkchop"), rawPorkchop);
        r.register(of("cooked_porkchop"), cookedPorkchop);
        r.register(of("painting"), painting);
        r.register(of("golden_apple"), goldenApple);
        r.register(of("sign"), sign);
        r.register(of("oak_door"), woodDoor);
        r.register(of("bucket"), bucket);
        r.register(of("water_bucket"), waterBucket);
        r.register(of("lava_bucket"), lavaBucket);
        r.register(of("minecart"), minecart);
        r.register(of("saddle"), saddle);
        r.register(of("iron_door"), ironDoor);
        r.register(of("redstone"), redstoneDust);
        r.register(of("snowball"), snowball);
        r.register(of("oak_boat"), boat);
        r.register(of("leather"), leather);
        r.register(of("milk_bucket"), milk);
        r.register(of("brick"), brick);
        r.register(of("clay_ball"), clay);
        r.register(of("sugar_cane"), sugarCanes);
        r.register(of("paper"), paper);
        r.register(of("book"), book);
        r.register(of("slime_ball"), slimeball);
        r.register(of("chest_minecart"), minecartChest);
        r.register(of("furnace_minecart"), minecartFurnace);
        r.register(of("egg"), egg);
        r.register(of("compass"), compass);
        r.register(of("fishing_rod"), fishingRod);
        r.register(of("clock"), clock);
        r.register(of("glowstone_dust"), glowstoneDust);
        r.register(of("fish"), rawFish);
        r.register(of("cooked_fish"), cookedFish);
        r.register(of("dye"), dyePowder);
        r.register(of("bone"), bone);
        r.register(of("sugar"), sugar);
        r.register(of("cake"), cake);
        r.register(of("bed"), bed);
        r.register(of("repeater"), redstoneRepeater);
        r.register(of("cookie"), cookie);
        r.register(of("map"), map);
        r.register(of("shears"), shears);
        r.register(of("music_disc_13"), record13);
        r.register(of("music_disc_cat"), recordCat);

        LOGGER.info("Added vanilla items to the registry.");
    }
}