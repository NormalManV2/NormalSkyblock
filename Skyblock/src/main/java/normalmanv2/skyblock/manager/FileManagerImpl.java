package normalmanv2.skyblock.manager;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import normalmanv2.api.utils.file.FileManager;
import normalmanv2.api.utils.file.LocationAdapter;
import org.bukkit.Location;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.logging.Logger;

public class FileManagerImpl <T> implements FileManager<T> {

    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .registerTypeAdapter(Location.class, new LocationAdapter())
            .create();

    protected final Path directory;
    protected final Logger logger;

    public FileManagerImpl(Path directory, Logger logger) {
        this.directory = directory;
        this.logger = logger;

        try {
            if (!Files.exists(directory)){
                Files.createDirectories(directory);
            }
        } catch (IOException exception) {
            logger.severe("Failed to create directory: " + directory);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public void saveData(T data, String fileName) {
        Path filePath = directory.resolve(fileName);
        try {
            Files.writeString(filePath, GSON.toJson(data), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException exception) {
            this.logger.severe("Failed to save data: " + fileName);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public T loadFileData(String fileName, Class<T> clazz) {
        Path filePath = directory.resolve(fileName);
        if (!Files.exists(filePath)) {
            this.logger.severe("File does not exist or is not found: " + fileName);
            return null;
        }
        try {
            String context = Files.readString(filePath);
            return GSON.fromJson(context, clazz);
        } catch (IOException exception) {
            this.logger.severe("Failed to load data: " + fileName);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean deleteFileData(String fileName) {
        Path filePath = this.directory.resolve(fileName);
        try {
            return Files.deleteIfExists(filePath);
        } catch (IOException exception) {
            this.logger.severe("Failed to delete data: " + fileName);
            throw new RuntimeException(exception);
        }
    }

    @Override
    public boolean dataFileExists(String fileName) {
        return Files.exists(this.directory.resolve(fileName + ".json"));
    }

    @Override
    public Path getFilePath(String fileName) {
        return this.directory.resolve(fileName + ".json");
    }
}
