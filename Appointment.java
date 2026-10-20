import java.time.*;

public class Appointment {
    public int id;
    public int patientId;
    public int doctorId;
    public int scheduleId;
    public LocalDateTime bookedAt;
    public String status; // BOOKED, CANCELLED, COMPLETED

    public Appointment(int id, int patientId, int doctorId, int scheduleId) {
        this.id = id; this.patientId = patientId; this.doctorId = doctorId; this.scheduleId = scheduleId;
        this.bookedAt = LocalDateTime.now(); this.status = "BOOKED";
    }

    @Override
    public String toString() {
        return id+": patient#"+patientId+" dr#"+doctorId+" sched#"+scheduleId+" status:"+status+" @"+bookedAt;
    }
}
