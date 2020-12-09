import java.util.HashMap;
import java.util.Map;

public class EventTracker implements Tracker {

    private static EventTracker INSTANCE = new EventTracker();

    private Map<String, Integer> tracker;

    private EventTracker() {
        this.tracker = new HashMap<>();
    }

    /**
     *
     * The open bracket after getInstance() is similar to lock.lock(). The method's closing bracket is similar to
     * lock.unlock(). The method's code block is where we access the shared resource.
     *
     * Used article here to model the block of code in the method
     * https://medium.com/swlh/java-singleton-pattern-and-synchronization-32665cbf6ad7
     *
     */
    public static synchronized EventTracker getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new EventTracker();
        }
        return INSTANCE;
    }

    @Override
    // https://www.techiedelight.com/increment-keys-value-map-java/
    synchronized public void push(String message) {
        Integer count = this.tracker.get(message);

        if (count == null) {
           this.tracker.put(message, 1);
        } else {
            this.tracker.put(message, count + 1);
        }
//        this.tracker.merge(message, 1, Integer::sum);
    }

    @Override
    synchronized public Boolean has(String message) {
        if (this.tracker.get(message) != null && this.tracker.get(message) > 0) {
            return true;
        }
        return false;
    }

    @Override
    synchronized public void handle(String message, EventHandler e) {
        try {
            e.handle(); // we don't know what e is yet
            this.tracker.put(message, this.tracker.get(message) - 1);
        } catch (NullPointerException n) {
            System.out.println("Message is not currently tracked");
        }
    }

    // Do not use this. This constructor is for tests only
    // Using it breaks the singleton class
    EventTracker(Map<String, Integer> tracker) {
        this.tracker = tracker;
    }


    public HashMap<String, Integer> getTracker() {
        return (HashMap<String, Integer>) this.tracker;
    }
}
