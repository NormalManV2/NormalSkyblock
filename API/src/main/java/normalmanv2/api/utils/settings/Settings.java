package normalmanv2.api.utils.settings;

import java.util.Map;

public interface Settings {

    void setSetting(String key, Boolean value);
    boolean getSetting(String key);
    void removeSetting(String key);
    Map<String, Boolean> getSettings();

}
