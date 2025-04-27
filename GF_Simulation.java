import greenfoot.core.Simulation;
import greenfoot.core.WorldHandler;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class GF_Simulation {
    public static Simulation forceInitializeSimulation(WorldHandler worldHandler_) {
        Simulation simulation = Simulation.getInstance();
        try {
            Field instanceField = Simulation.class.getDeclaredField("instance");
            instanceField.setAccessible(true);

            if (instanceField.get(null) == null) {
                Constructor<Simulation> constructor = Simulation.class.getDeclaredConstructor();
                constructor.setAccessible(true);

                simulation = constructor.newInstance();
                instanceField.set(null, simulation);

                // Pastikan worldHandler diinisialisasi
                WorldHandler worldHandler = worldHandler_;
                Field worldHandlerField = Simulation.class.getDeclaredField("worldHandler");
                worldHandlerField.setAccessible(true);
                worldHandlerField.set(simulation, worldHandler);

                System.out.println("Simulation injected successfully, worldHandler initialized.");
            } else {
                System.out.println("Simulation already initialized.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Gagal inject Simulation", e);
        }

        return simulation;
    }
}
