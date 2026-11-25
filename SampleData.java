
public class SampleData {

    
    
    public static int load(ContactList list, int firstId) {

        int id = firstId;

        if (addIfNew(list, id, "Anna Reyes",    9171234567L, "anna@mail.com",  TelecoProvider.GLOBE)) { id++; }
        if (addIfNew(list, id, "Bea Garcia",    9181234567L, "bea@mail.com",   TelecoProvider.SMART)) { id++; }
        if (addIfNew(list, id, "Carlo Mendoza", 8951234567L, "carlo@mail.com", TelecoProvider.DITO))  { id++; }
        if (addIfNew(list, id, "Diana Lopez",   9201234567L, "diana@mail.com", TelecoProvider.SMART)) { id++; }
        if (addIfNew(list, id, "Ethan Cruz",    9271234567L, "ethan@mail.com", TelecoProvider.GLOBE)) { id++; }
        if (addIfNew(list, id, "Faith Ramos",   9911234567L, "faith@mail.com", TelecoProvider.DITO))  { id++; }

        return id - firstId;
    }

    
    private static boolean addIfNew(ContactList list, int id, String name, long phone,
                                    String email, TelecoProvider provider) {
        if (list.linearSearchByPhone(phone) != -1) {
            return false;
        }
        list.insertAtEnd(new Contact(id, name, phone, email, provider));
        return true;
    }
}
