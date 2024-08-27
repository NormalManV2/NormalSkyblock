package normalmanv2.skyblock;

import normalmanv2.common.invite.InviteService;
import normalmanv2.skyblock.command.IslandCommand;
import normalmanv2.skyblock.gui.RoleSettingsGUI;
import normalmanv2.skyblock.manager.island.IslandFileManager;
import normalmanv2.skyblock.manager.island.IslandManagerImpl;
import normalmanv2.skyblock.manager.player.PlayerDataManager;
import normalmanv2.skyblock.manager.player.PlayerFileManager;
import normalmanv2.skyblock.manager.registry.RegistryManager;
import normalmanv2.skyblock.manager.role.RoleManager;
import org.bukkit.command.CommandExecutor;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

public final class Skyblock extends JavaPlugin {

    private PlayerFileManager playerFileManager;
    private IslandFileManager islandFileManager;
    private IslandManagerImpl islandManagerImpl;
    private InviteService inviteService;
    private PlayerDataManager playerDataManager;
    private RoleManager roleManager;
    private RegistryManager registryManager;


    private final Path playerDir = Paths.get("PlayerData");
    private final Path islandDir = Paths.get("IslandData");

    @Override
    public void onLoad() {
        Logger logger = this.getLogger();

        this.playerFileManager = new PlayerFileManager(logger, playerDir);
        this.islandFileManager = new IslandFileManager(logger, islandDir);
    }

    @Override
    public void onEnable() {
        initRegistries();
        initSkyblockComponents();
        registerCommands();
    }

    private void initRegistries(){
        this.registryManager = new RegistryManager();
        InitNamespacedKeys initNamespacedKeys = new InitNamespacedKeys(this);
        this.roleManager = new RoleManager();
        RoleSettingsGUI roleSettingsGUI = new RoleSettingsGUI(this, registryManager);
    }

    private void initSkyblockComponents(){
        this.islandManagerImpl = new IslandManagerImpl(this);
        this.inviteService = new InviteService();
        this.playerDataManager = new PlayerDataManager(playerFileManager);

    }

    private void registerCommands(){
        new IslandCommand(this);
    }

    @Override
    public void onDisable() {

    }

    public PlayerFileManager getPlayerFileManager() {
        return playerFileManager;
    }

    public IslandFileManager getIslandFileManager() {
        return islandFileManager;
    }

    public IslandManagerImpl getIslandManager() {
        return islandManagerImpl;
    }

    public PlayerDataManager getPlayerTracker() {
        return playerDataManager;
    }

    public InviteService getInviteService() {
        return inviteService;
    }

    public RoleManager getRoleManager() {
        return roleManager;
    }

    public RegistryManager getRegistryManager() {
        return registryManager;
    }
}
