import java.util.HashMap;

public class C_CoolDown extends Component {
    private HashMap<String, Float> coolDowns = new HashMap<>();
    private HashMap<String, Float> currentCoolDowns = new HashMap<>();
    
    public C_CoolDown() {
        // Do nothing
    }
    public C_CoolDown(String name, float coolDown) {
        addCoolDown(name, coolDown);
    }

    public void start() {
        super.start();
    }

    public void update() {
        super.update();
        for (String name : currentCoolDowns.keySet()) {
            currentCoolDowns.put(name, currentCoolDowns.get(name) <= 0f ? 0f : currentCoolDowns.get(name) - (float)owner.getDeltaTime());
        }
    }

    public void addCoolDown(String name, float coolDown) {
        coolDowns.put(name, coolDown);
        currentCoolDowns.put(name, 0f);
    }
    public void removeCoolDown(String name) {
        coolDowns.remove(name);
        currentCoolDowns.remove(name);
    }
    public void clearCoolDowns() {
        coolDowns.clear();
        currentCoolDowns.clear();
    }
    public boolean isCoolDownActive(String name) {
        return currentCoolDowns.get(name) <= 0f;
    }
    public void resetCoolDown(String name) {
        currentCoolDowns.put(name, coolDowns.get(name));
    }
    public float getCoolDownTime(String name) {
        return coolDowns.get(name);
    }
    public float getCurrentCoolDownTime(String name) {
        return currentCoolDowns.get(name);
    }
}
