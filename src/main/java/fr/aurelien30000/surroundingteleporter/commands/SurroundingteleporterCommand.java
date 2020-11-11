package fr.aurelien30000.surroundingteleporter.commands;

import com.google.common.collect.ImmutableList;
import fr.aurelien30000.surroundingteleporter.SurroundingTeleporter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;

import java.util.List;

public class SurroundingteleporterCommand implements CommandExecutor, TabCompleter {

    private final SurroundingTeleporter instance = SurroundingTeleporter.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String lavel, String[] args) {
        switch (args.length) {
            case 1:
                switch (args[0]) {
                    case "reload":
                        instance.reloadConfig();
                        instance.setRadius(instance.getConfig().getInt("radius"));
                        sender.sendMessage("§b§l[SuTp] §aConfig successfully reloaded!");
                        break;
                }
                break;
            default:
                sender.sendMessage("§b§l[SuTp] §eAvailable commands:");
                sender.sendMessage("§6/§2§lsutp reload §f- §7§oReloads the config.");
                break;
        }

        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String lavel, String[] args) {
        switch (args.length) {
            default:
                return ImmutableList.of("reload");
        }
    }

}
