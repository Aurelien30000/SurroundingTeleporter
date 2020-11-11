package fr.aurelien30000.surroundingteleporter;

import fr.aurelien30000.surroundingteleporter.commands.SurroundingteleporterCommand;
import fr.aurelien30000.surroundingteleporter.listeners.PlayerListener;
import org.bukkit.command.PluginCommand;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

public class SurroundingTeleporter extends JavaPlugin implements Listener {

    private static SurroundingTeleporter INSTANCE;
    private int radius;

    private long loadtime;

    @Override
    public void onEnable() {
        startPluginLoading();

        registerConfig();

        registerListeners();

        registerCommands();

        finishPluginLoading();
    }

    @Override
    public void onDisable() {
        startPluginDisabling();

        finishPluginDisabling();
    }

    private void startPluginLoading() {
        loadtime = System.currentTimeMillis();

        getLogger().info("=-=-=-= [" + getName() + "] by " + getDescription().getAuthors().toString() + " =-=-=-=-=");
        getLogger().info("");

        INSTANCE = this;
    }

    private void registerConfig() {
        getLogger().info("Loading Config...");

        saveDefaultConfig();
        radius = getConfig().getInt("radius");

        getLogger().info("✔ Config loaded !");
        getLogger().info("");
    }

    private void registerListeners() {
        getLogger().info("Loading Listeners...");

        getServer().getPluginManager().registerEvents(new PlayerListener(), this);

        getLogger().info("✔ Listeners loaded !");
        getLogger().info("");
    }

    private void registerCommands() {
        getLogger().info("Loading Commands...");

        final PluginCommand pluginCommand = getCommand("surroundingteleporter");
        final SurroundingteleporterCommand surroundingteleporterCommand = new SurroundingteleporterCommand();
        pluginCommand.setExecutor(surroundingteleporterCommand);
        pluginCommand.setTabCompleter(surroundingteleporterCommand);

        getLogger().info("✔ Commands loaded !");
        getLogger().info("");
    }

    private void finishPluginLoading() {
        getLogger().info("✔ Plugin entirely loaded in " + (System.currentTimeMillis() - loadtime) + " ms.");
        getLogger().info("");
        getLogger().info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    private void startPluginDisabling() {
        getLogger().info("=-=-=-= [" + getName() + "] by " + getDescription().getAuthors().toString() + " =-=-=-=-=");
        getLogger().info("");
    }

    private void finishPluginDisabling() {
        getLogger().info("✔ Plugin entirely disabled.");
        getLogger().info("");
        getLogger().info("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }

    public static SurroundingTeleporter getInstance() {
        return INSTANCE;
    }

    public int getRadius() {
        return radius;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

}
