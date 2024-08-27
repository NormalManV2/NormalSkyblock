package normalmanv2.skyblock.command;

import normalmanv2.common.command.Command;
import normalmanv2.skyblock.Skyblock;
import normalmanv2.skyblock.registry.GUIRegistry;
import normalmanv2.skyblock.role.Role;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class IslandCommand extends Command {

    private final Skyblock plugin;

    public IslandCommand(Skyblock plugin) {
        super("island",
                new String[]{"is", "islands", "nsb"},
                "NormalSkyblock: Island command, Usage: </island, /is, /islands, /nsb",
                "nsb.command.island.use");
        this.plugin = plugin;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if (!(sender instanceof Player player)) {
            return;
        }
        GUIRegistry guiRegistry = plugin.getRegistryManager().getGuiRegistry();
        player.openInventory(guiRegistry.getOrThrow("role_settings_" + Role.VISITOR.name()));

    }

    @Override
    public List<String> onTabComplete(CommandSender sender, String[] args) {
        return null;
    }
}
