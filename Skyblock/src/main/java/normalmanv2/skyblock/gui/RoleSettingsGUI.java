package normalmanv2.skyblock.gui;

import normalmanv2.common.item.ItemBuilder;
import normalmanv2.skyblock.Skyblock;
import normalmanv2.skyblock.manager.registry.RegistryManager;
import normalmanv2.skyblock.manager.role.RoleManager;
import normalmanv2.skyblock.role.IslandPermission;
import normalmanv2.skyblock.role.Role;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class RoleSettingsGUI {

    private final Skyblock plugin;
    private final RegistryManager registryManager;
    private final Map<Role, Inventory> roleInventories;

    public RoleSettingsGUI(Skyblock plugin, RegistryManager registryManager) {
        this.plugin = plugin;
        this.registryManager = registryManager;
        this.roleInventories = new HashMap<>();
        initRoleInventories();
        registerInventories();
    }

    private void initRoleInventories() {
        for (Role role : Role.values()) {
            Inventory inventory = createRoleInventory(role);
            roleInventories.put(role, inventory);
        }
    }

    private Inventory createRoleInventory(Role role) {
        Inventory inventory = Bukkit.createInventory(null, 54, ChatColor.translateAlternateColorCodes('&', "&cRole Settings: " + role.name()));
        updateRoleInventory(role, inventory);
        return inventory;
    }

    private void updateRoleInventory(Role role, Inventory inventory) {
        inventory.clear();
        for (IslandPermission permission : IslandPermission.values()) {
            boolean enabled = plugin.getRoleManager().hasPermission(role, permission);
            NamespacedKey skyblockItemKey = plugin.getRegistryManager().getNSKRegistry().getOrThrow("skyblockItem");

            ItemBuilder builder = new ItemBuilder(Material.PAPER)
                    .setDisplayName(permission.name())
                    .setLore(Collections.singletonList(enabled ? "&aEnabled" : "&cDisabled").toArray(new String[0]))
                    .addLoreToPDC(skyblockItemKey)
                    .addPDCData(skyblockItemKey, PersistentDataType.STRING, permission.name());

            ItemStack itemStack = builder.build();
            inventory.addItem(itemStack);
        }
    }

    private void registerInventories() {
        roleInventories.forEach((role, inventory) -> {
            this.registryManager.getGuiRegistry().register("role_settings_" + role.name(), inventory);
        });
    }

    public Inventory getInventoryForRole(Role role) {
        return roleInventories.get(role);
    }

    public void togglePermission(Role role, IslandPermission permission) {
        RoleManager roleManager = plugin.getRoleManager();
        if (roleManager.hasPermission(role, permission)) {
            roleManager.removePermission(role, permission);
        } else {
            roleManager.addPermission(role, permission);
        }
        Inventory inventory = getInventoryForRole(role);
        updateRoleInventory(role, inventory);
    }

}
