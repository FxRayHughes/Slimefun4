package io.github.thebusybiscuit.slimefun4.implementation.items.weapons;

import me.mrCookieSlime.Slimefun.Objects.Category;
import me.mrCookieSlime.Slimefun.Objects.handlers.BowShootHandler;
import me.mrCookieSlime.Slimefun.api.SlimefunItemStack;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class ExplosiveBow extends SlimefunBow {

    public ExplosiveBow(Category category, SlimefunItemStack item, ItemStack[] recipe) {
        super(category, item, recipe);
    }

    @Override
    public BowShootHandler onShoot() {
        return (e, n) -> {
            Vector vector = n.getVelocity();
            vector.setY(0.6);
            n.setVelocity(vector);
            n.getWorld().createExplosion(n.getLocation(), 0F);
            n.getWorld().playSound(n.getLocation(), Sound.ENTITY_GENERIC_EXPLODE, 1F, 1F);
        };
    }

}
