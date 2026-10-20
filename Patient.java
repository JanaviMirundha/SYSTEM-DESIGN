public class Patient {
    public int id;
    public String name;
    public int age;
    public String phone;
    public String email;

    public Patient(int id, String name, int age, String phone, String email) {
        this.id = id; this.name = name; this.age = age; this.phone = phone; this.email = email;
    }

    @Override
    public String toString() { return id+": " + name + " ("+age+") " + phone + " " + email; }
}
