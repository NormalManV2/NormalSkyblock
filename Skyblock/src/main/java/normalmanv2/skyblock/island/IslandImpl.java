package normalmanv2.skyblock.island;

import normalmanv2.api.utils.settings.Settings;
import normalmanv2.api.utils.stats.Stats;
import normalmanv2.skyblockAPI.island.Island;
import org.bukkit.Location;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class IslandImpl implements Island {

    private final UUID owner;
    private int size;
    private final Set<UUID> members;
    private final Set<UUID> bannedMembers;
    private Location islandHome;
    private final IslandSettings settings;
    private final VisitorLog visitorLog;
    private final IslandStats stats;

    public IslandImpl(UUID owner) {
        this.owner = owner;
        this.members = new HashSet<>();
        this.bannedMembers = new HashSet<>();
        this.settings = new IslandSettings();
        this.visitorLog = new VisitorLog();
        this.stats = new IslandStats();
    }

    // Member methods

    public void addMember(UUID playerId) {
        if (members.contains(playerId)) {
            return;
        }
        members.add(playerId);
    }

    public void removeMember(UUID playerId){
        members.remove(playerId);
    }

    public void banMember(UUID playerId){
        if (bannedMembers.contains(playerId)){
            return;
        }
        bannedMembers.add(playerId);
    }

    public void unbanMember(UUID playerId){
        bannedMembers.remove(playerId);
    }

    public Set<UUID> getIslandMembers() {
        return Collections.unmodifiableSet(members);
    }

    public Set<UUID> getBannedIslandMembers() {
        return Collections.unmodifiableSet(bannedMembers);
    }

    public boolean isMember(UUID playerId){
        return members.contains(playerId);
    }

    public boolean isBanned(UUID playerId){
        return bannedMembers.contains(playerId);
    }

    // Home methods

    public void setIslandHome(Location islandHome){
        this.islandHome = islandHome;
    }

    public Location getIslandHome() {
        return islandHome;
    }

    // Other getters

    public UUID getOwner() {
        return owner;
    }

    public int getIslandSize() {
        return size;
    }

    public Settings getSettings(){
        return settings;
    }

    public VisitorLog getVisitorLog() {
        return visitorLog;
    }

    public Stats getIslandStats() {
        return stats;
    }
}
