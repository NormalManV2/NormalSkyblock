package normalmanv2.skyblock.manager.island;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import normalmanv2.api.utils.file.LocationAdapter;
import normalmanv2.skyblock.island.IslandImpl;
import normalmanv2.skyblock.manager.FileManagerImpl;
import org.bukkit.Location;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class IslandFileManager extends FileManagerImpl<IslandImpl> {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Location.class, new LocationAdapter())
            .create();


    public IslandFileManager(Logger logger, Path islandDir) {
        super(islandDir, logger);
    }

    public IslandImpl loadIslandData(String fileName) {
        Path filePath = getFilePath(fileName);
        if (!Files.exists(filePath)) {
            return null;
        }
        return loadFileData(fileName, IslandImpl.class);
    }

    public List<IslandImpl> loadAllIslands() {
        List<IslandImpl> islands = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.directory, "*.json")) {
            for (Path entry : stream) {
                UUID owner = UUID.fromString(entry.getFileName().toString().replace(".json", ""));
                IslandImpl island = loadIslandData(owner.toString());
                if (island != null) {
                    islands.add(island);
                }
            }
        } catch (IOException exception) {
            logger.severe("Failed to load islands");
        }
        return islands;
    }
}
