public class Doctor {
    public int id;
    public String name;
    public String specialization;

    public Doctor(int id, String name, String specialization) {
        this.id = id; this.name = name; this.specialization = specialization;
    }

    @Override
    public String toString() { return id+": "+name+" - "+specialization; }
}
