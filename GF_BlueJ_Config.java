import bluej.Config;
import java.lang.reflect.Field;
import java.util.Properties;

public class GF_BlueJ_Config {
    public static void forceInitializeConfig() {
        try {
            Field propsField = Config.class.getDeclaredField("commandProps");
            propsField.setAccessible(true);

            Properties commandProps = new Properties();
            // Isi properti penting biar BlueJ/Greenfoot nggak ngambek
            commandProps.setProperty("bluej.vm.version", System.getProperty("java.version"));
            commandProps.setProperty("bluej.runtime.version", "standalone");
            commandProps.setProperty("bluej.version", "3.0.0");
            commandProps.setProperty("greenfoot.version", "3.5.4"); // atau sesuai versimu
            commandProps.setProperty("bluej.url.open", "false");
            
            propsField.set(null, commandProps); // null karena field static

            System.out.println("Config props injected successfully.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal inject props ke Config", e);
        }
    }
}
