import greenfoot.core.WorldHandler;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class GF_WorldHandler {
    public static WorldHandler forceInitializeWorldHandler() {
        WorldHandler handler = WorldHandler.getInstance();
        try {
            Field instanceField = WorldHandler.class.getDeclaredField("instance");
            instanceField.setAccessible(true);

            if (instanceField.get(null) == null) {
                Constructor<WorldHandler> constructor = WorldHandler.class.getDeclaredConstructor();
                constructor.setAccessible(true);

                handler = constructor.newInstance();
                instanceField.set(null, handler);

                System.out.println("WorldHandler injected successfully.");
            } else {
                System.out.println("WorldHandler already initialized.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal inject WorldHandler", e);
        }
        return handler;
    }
}
