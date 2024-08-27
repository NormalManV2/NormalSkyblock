package normalmanv2.skyblockAPI.island;

import org.bukkit.Location;

import java.util.Set;
import java.util.UUID;

public interface Island {
    UUID getOwner();
    Location getIslandHome();
    void setIslandHome(Location location);
    Set<UUID> getIslandMembers();
    Set<UUID> getBannedIslandMembers();
    boolean isMember(UUID playerId);
    boolean isBanned(UUID playerId);
    void addMember(UUID playerId);
    void removeMember(UUID playerId);
    void banMember(UUID playerId);
    void unbanMember(UUID playerId);
}
