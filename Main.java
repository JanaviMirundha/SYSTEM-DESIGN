import java.time.*;
import java.util.*;

public class Main {
    static NotificationService notifier = new NotificationService();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        seedSampleData();
        notifier.subscribe("patient:1", (topic,msg)-> System.out.println("[To Patient 1] " + msg));

        while (true) {
            showMenu();
            String opt = scanner.nextLine().trim();
            if (opt.equals("0")) break;
            handleMenu(opt);
        }
        System.out.println("Exiting.");
    }

    static void seedSampleData() {
        Patient p = new Patient(Repository.pid++, "Arun", 30, "9999999999", "arun@example.com");
        Repository.patients.put(p.id, p);
        Doctor d = new Doctor(Repository.did++, "Dr. Kumar", "General");
        Repository.doctors.put(d.id, d);
        Schedule s = new Schedule(Repository.sid++, d.id, LocalDate.now().plusDays(1), LocalTime.of(9,0), LocalTime.of(9,30));
        Repository.schedules.put(s.id, s);
    }

    static void showMenu() {
        System.out.println("\nHospital CLI");
        System.out.println("1. List patients");
        System.out.println("2. List doctors");
        System.out.println("3. List schedules");
        System.out.println("4. Book appointment");
        System.out.println("5. Cancel appointment");
        System.out.println("6. List appointments");
        System.out.println("0. Exit");
        System.out.print("Choose: ");
    }

    static void handleMenu(String opt) {
        try {
            switch (opt) {
                case "1": listPatients(); break;
                case "2": listDoctors(); break;
                case "3": listSchedules(); break;
                case "4": bookAppointment(); break;
                case "5": cancelAppointment(); break;
                case "6": listAppointments(); break;
                default: System.out.println("Invalid");
            }
        } catch(Exception e) { System.out.println("Error: " + e.getMessage()); }
    }

    static void listPatients() { Repository.patients.values().forEach(System.out::println); }
    static void listDoctors() { Repository.doctors.values().forEach(System.out::println); }
    static void listSchedules() { Repository.schedules.values().forEach(System.out::println); }
    static void listAppointments() { Repository.appointments.values().forEach(System.out::println); }

    static void bookAppointment() {
        System.out.print("Enter patient id: "); int pid = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Enter schedule id: "); int sid = Integer.parseInt(scanner.nextLine().trim());
        Schedule s = Repository.schedules.get(sid);
        if (s==null) { System.out.println("Invalid schedule"); return; }
        if (!s.available) { System.out.println("Slot not available"); return; }
        s.available = false; // mark reserved
        Appointment a = new Appointment(Repository.aid++, pid, s.doctorId, s.id);
        Repository.appointments.put(a.id, a);
        System.out.println("Booked: " + a);
        notifier.publish("patient:"+pid, "Your appointment " + a.id + " is booked for " + s.date + " " + s.start);
    }

    static void cancelAppointment() {
        System.out.print("Enter appointment id: "); int aid = Integer.parseInt(scanner.nextLine().trim());
        Appointment a = Repository.appointments.get(aid);
        if (a==null) { System.out.println("Invalid"); return; }
        if ("CANCELLED".equals(a.status)) { System.out.println("Already cancelled"); return; }
        a.status = "CANCELLED";
        Schedule s = Repository.schedules.get(a.scheduleId);
        if (s!=null) s.available = true;
        System.out.println("Cancelled: " + a);
        notifier.publish("patient:"+a.patientId, "Your appointment " + a.id + " was cancelled.");
    }
}
