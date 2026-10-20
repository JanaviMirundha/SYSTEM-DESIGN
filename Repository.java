import java.util.*;

public class Repository {
    public static Map<Integer, Patient> patients = new HashMap<>();
    public static Map<Integer, Doctor> doctors = new HashMap<>();
    public static Map<Integer, Schedule> schedules = new HashMap<>();
    public static Map<Integer, Appointment> appointments = new HashMap<>();

    public static int pid = 1, did = 1, sid = 1, aid = 1;
}
