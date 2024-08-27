package normalmanv2.skyblock.manager.role;

import normalmanv2.skyblock.role.IslandPermission;
import normalmanv2.skyblock.role.Role;

import java.util.Collections;
import java.util.EnumMap;
import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

public class RoleManager {
    private final Map<Role, Set<IslandPermission>> rolePermissions;

    public RoleManager() {
        this.rolePermissions = new EnumMap<>(Role.class);
        initializeDefaultPermissions();
    }

    private void initializeDefaultPermissions() {
        rolePermissions.put(Role.VISITOR, EnumSet.noneOf(IslandPermission.class));

        rolePermissions.put(Role.MEMBER, EnumSet.of(
                IslandPermission.PLACE,
                IslandPermission.BREAK,
                IslandPermission.INTERACT));

        rolePermissions.put(Role.MODERATOR, EnumSet.of(
                IslandPermission.PLACE,
                IslandPermission.BREAK,
                IslandPermission.INTERACT,
                IslandPermission.INVITE_MEMBER,
                IslandPermission.BAN_MEMBER,
                IslandPermission.UNBAN_MEMBER,
                IslandPermission.KICK_MEMBER));

        rolePermissions.put(Role.CO_LEADER, EnumSet.allOf(IslandPermission.class));

        rolePermissions.put(Role.LEADER, EnumSet.allOf(IslandPermission.class));
    }

    public Set<IslandPermission> getPermissions(Role role) {
        return Collections.unmodifiableSet(rolePermissions.getOrDefault(role, EnumSet.noneOf(IslandPermission.class)));
    }

    public boolean hasPermission(Role role, IslandPermission islandPermission) {
        return getPermissions(role).contains(islandPermission);
    }

    public void addPermission(Role role, IslandPermission islandPermission) {
        rolePermissions.computeIfAbsent(role, k -> EnumSet.noneOf(IslandPermission.class)).add(islandPermission);
    }

    public void removePermission(Role role, IslandPermission islandPermission) {
        rolePermissions.computeIfPresent(role, (k, v) -> {
            v.remove(islandPermission);
            return v;
        });
    }

    public Map<Role, Set<IslandPermission>> getAllPermissions() {
        Map<Role, Set<IslandPermission>> allPermissions = new EnumMap<>(Role.class);
        rolePermissions.forEach((role, perms) -> allPermissions.put(role, EnumSet.copyOf(perms)));
        return allPermissions;
    }
}
