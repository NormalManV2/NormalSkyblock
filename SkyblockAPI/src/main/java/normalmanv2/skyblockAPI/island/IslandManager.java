package normalmanv2.skyblockAPI.island;

import org.bukkit.Location;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface IslandManager {
    void loadAllIslands();
    void saveAllIslands(String fileName);
    Island createIsland(UUID owner);
    boolean deleteIsland(UUID owner);
    Island getOrThrowIslandByOwner(UUID owner);
    Island getOrThrowIslandByMember(UUID member);
    List<Island> getAllIslands();
    boolean islandExists(UUID member);
    boolean setIslandHome(Island island, Location location);
    void saveIsland(Island island, String fileName);
    void saveAndUnloadAllIslands();
    boolean logVisitor(Island island, UUID visitorId);
    void setIslandScore(Island island, double score);
    void addIslandScore(Island island, double score);
    Set<UUID> getOrThrowIslandMembersByOwner(UUID islandOwner);
    Set<UUID> getOrThrowBannedIslandMembersByOwner(UUID islandOwner);
    Set<UUID> getOrThrowIslandMembersByMember(UUID islandMember);
    Set<UUID> getOrThrowBannedIslandMembersByMember(UUID islandMember);
}
