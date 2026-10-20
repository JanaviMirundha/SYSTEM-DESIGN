import java.time.*;

public class Schedule {
    public int id;
    public int doctorId;
    public LocalDate date;
    public LocalTime start;
    public LocalTime end;
    public boolean available = true;

    public Schedule(int id, int doctorId, LocalDate date, LocalTime start, LocalTime end) {
        this.id = id; this.doctorId = doctorId; this.date = date; this.start = start; this.end = end;
    }

    @Override
    public String toString() {
        return id+": Dr#"+doctorId+" " + date + " " + start + "-" + end + " available:"+available;
    }
}
