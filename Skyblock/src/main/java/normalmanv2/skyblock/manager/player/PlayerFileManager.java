package normalmanv2.skyblock.manager.player;

import normalmanv2.skyblock.manager.FileManagerImpl;
import normalmanv2.skyblock.player.PlayerData;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

public class PlayerFileManager extends FileManagerImpl<PlayerData> {

    public PlayerFileManager(Logger logger, Path playerDir) {
        super(playerDir, logger);
    }

    public PlayerData loadPlayerData(String fileName){
        Path filePath = getFilePath(fileName);
        if (!Files.exists(filePath)){
            return null;
        }
        return loadFileData(fileName, PlayerData.class);
    }

    public List<PlayerData> loadAllPlayerData(){
        List<PlayerData> playerDataList = new ArrayList<>();
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(this.directory, "*.json")) {
            for (Path path : stream) {
                UUID playerId = UUID.fromString(path.getFileName().toString());
                PlayerData playerData = loadPlayerData(playerId.toString());
                if (playerData != null){
                    playerDataList.add(playerData);
                }
            }
        } catch (IOException exception) {
            logger.severe("Failed to load player data");
        }
        return playerDataList;
    }
}
