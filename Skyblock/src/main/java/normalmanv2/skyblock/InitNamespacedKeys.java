package normalmanv2.skyblock;

import normalmanv2.skyblock.registry.NSKRegistry;
import org.bukkit.NamespacedKey;

public class InitNamespacedKeys {

    private final Skyblock plugin;

    public InitNamespacedKeys(Skyblock plugin) {
        this.plugin = plugin;
        NSKRegistry nskRegistry = plugin.getRegistryManager().getNSKRegistry();
        NamespacedKey skyblockItemKey = new NamespacedKey(plugin, "skyblockItem");
        nskRegistry.register("skyblockItem", skyblockItemKey);
    }
}
