import java.util.*;

interface Subscriber { void onNotify(String topic, String message); }

public class NotificationService {
    private Map<String, List<Subscriber>> subs = new HashMap<>();

    public void publish(String topic, String message) {
        System.out.println("[Notification publish] topic="+topic+" msg="+message);
        List<Subscriber> list = subs.getOrDefault(topic, Collections.emptyList());
        for (Subscriber s : list) s.onNotify(topic, message);
    }

    public void subscribe(String topic, Subscriber s) {
        subs.computeIfAbsent(topic, k->new ArrayList<>()).add(s);
    }
}
