package normalmanv2.skyblock.manager.registry;

import normalmanv2.skyblock.registry.GUIRegistry;
import normalmanv2.skyblock.registry.IslandRegistry;
import normalmanv2.skyblock.registry.ItemRegistry;
import normalmanv2.skyblock.registry.NSKRegistry;

public class RegistryManager {

    private final GUIRegistry guiRegistry;
    private final IslandRegistry islandRegistry;
    private final ItemRegistry itemRegistry;
    private final NSKRegistry nskRegistry;

    public RegistryManager() {
        this.guiRegistry = new GUIRegistry();
        this.islandRegistry = new IslandRegistry();
        this.itemRegistry = new ItemRegistry();
        this.nskRegistry = new NSKRegistry();
    }

    public GUIRegistry getGuiRegistry() {
        return guiRegistry;
    }

    public IslandRegistry getIslandRegistry() {
        return islandRegistry;
    }

    public ItemRegistry getItemRegistry() {
        return itemRegistry;
    }

    public NSKRegistry getNSKRegistry() {
        return nskRegistry;
    }
}
