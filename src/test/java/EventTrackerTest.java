import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EventTrackerTest {

    @Test
    public void instantiate() {
        EventTracker et = new EventTracker(new HashMap<>());

        Assert.assertNotNull(et);
    }

    @Test
    public void push() {
        HashMap<String, Integer> testList = new HashMap<>();
        EventTracker eventTracker = new EventTracker(testList);

        eventTracker.push("test");

        Assert.assertEquals(Integer.valueOf(1), testList.get("test"));
    }

    @Test
    public void tracker() {
        HashMap<String, Integer> testList = new HashMap<>();

        EventTracker eventTracker = new EventTracker(testList);

        eventTracker.push("test");

        Assert.assertEquals(testList, eventTracker.getTracker());

    }

    @Test
    public void has() {
        HashMap<String, Integer> testList = new HashMap<>();
        testList.put("test", 1);
        EventTracker eventTracker = new EventTracker(testList);

        boolean trackerHasTest = eventTracker.has("test");

        Assert.assertTrue(trackerHasTest);
    }

    @Test
    public void has2() {
        HashMap<String, Integer> testList = new HashMap<>();
        testList.put("test", 1);
        testList.put("folcrum", 1);
        EventTracker eventTracker = new EventTracker(testList);

        boolean trackerHasTest = eventTracker.has("folcrum");

        Assert.assertTrue(trackerHasTest);
    }

    @Test
    public void handle() {
        HashMap<String, Integer> testList = new HashMap<>();
        testList.put("test", 1);
        EventTracker eventTracker = new EventTracker(testList);

        eventTracker.handle("test", () -> {});

        boolean trackerHasTest = eventTracker.has("test");

        Assert.assertFalse(trackerHasTest);
    }

    @Test
    public void handle2() {
        HashMap<String, Integer> testList = new HashMap<>();
        testList.put("test", 2);
        EventTracker eventTracker = new EventTracker(testList);

        eventTracker.handle("test", () -> {});

        Assert.assertTrue(eventTracker.has("test"));
    }
}