package com.waldy.simplysit;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public final class SimplySit extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getLogger().info("SimplySit plugin enabled");
        getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
        getLogger().info("SimplySit plugin disabled");
    }

    @EventHandler
    public void onPlayerUseCommand(PlayerCommandPreprocessEvent command){
        if(Objects.equals(command.toString(), "sit")){
            command.getPlayer().sendMessage("sit");
        }
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(command.getName().equalsIgnoreCase("sit")){
            if(sender instanceof Player){
                Player p = (Player) sender;

                World w = p.getWorld();
                Location playerLocation = p.getLocation();
                playerLocation.setY(playerLocation.getY()- 1.9d);
                ArmorStand invArmorStand = (ArmorStand) w.spawnEntity(playerLocation, EntityType.ARMOR_STAND);
                invArmorStand.setSilent(true);
                invArmorStand.setVisible(false);
                invArmorStand.setCanMove(false);
                invArmorStand.setInvulnerable(true);
                invArmorStand.setBodyYaw(p.getYaw());
                invArmorStand.addPassenger(p);
            }
        }

        return true;
    }
}
