package normalmanv2.api.utils.file;

import java.nio.file.Path;

public interface FileManager<T> {

    void saveData(T data, String fileName);
    T loadFileData(String fileName, Class<T> clazz);
    boolean deleteFileData(String fileName);
    boolean dataFileExists(String fileName);
    Path getFilePath(String fileName);

}
