package normalmanv2.skyblock.role;

import java.util.Collections;
import java.util.EnumSet;
import java.util.Set;

public enum Role {

    VISITOR(EnumSet.noneOf(IslandPermission.class)),

    MEMBER(EnumSet.of(
            IslandPermission.PLACE,
            IslandPermission.BREAK,
            IslandPermission.INTERACT,
            IslandPermission.TELEPORT_HOME)),

    MODERATOR(EnumSet.of(
            IslandPermission.PLACE,
            IslandPermission.BREAK,
            IslandPermission.INTERACT,
            IslandPermission.TELEPORT_HOME,
            IslandPermission.INVITE_MEMBER,
            IslandPermission.KICK_MEMBER,
            IslandPermission.BAN_MEMBER,
            IslandPermission.UNBAN_MEMBER)),

    CO_LEADER(EnumSet.allOf(IslandPermission.class)),
    LEADER(EnumSet.allOf(IslandPermission.class)),;

    private final Set<IslandPermission> islandPermissions;

    Role(Set<IslandPermission> islandPermissions) {
        this.islandPermissions = islandPermissions;
    }

    public Set<IslandPermission> getPermissions() {
        return Collections.unmodifiableSet(islandPermissions);
    }

    public boolean hasPermission(IslandPermission islandPermission) {
        return islandPermissions.contains(islandPermission);
    }
}
