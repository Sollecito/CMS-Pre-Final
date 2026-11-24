












public class Contact {

    private int            id;
    private String         name;
    private long           phone;
    private String         email;
    private TelecoProvider provider;

    public Contact(int id, String name, long phone, String email, TelecoProvider provider) {
        this.id       = id;
        this.name     = name;
        this.phone    = phone;
        this.email    = email;
        this.provider = provider;
    }

    public int            getId()       { return id; }
    public String         getName()     { return name; }
    public long           getPhone()    { return phone; }
    public String         getEmail()    { return email; }
    public TelecoProvider getProvider() { return provider; }

    
    public String getPhoneDisplay() {
        return "0" + phone;
    }
}
