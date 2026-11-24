












public class SampleData {

    
    
    public static int load(ContactList list, int firstId) {

        int id = firstId;

        list.insertAtEnd(new Contact(id++, "Anna Reyes",    9171234567L, "anna@mail.com",  TelecoProvider.GLOBE));
        list.insertAtEnd(new Contact(id++, "Bea Garcia",    9181234567L, "bea@mail.com",   TelecoProvider.SMART));
        list.insertAtEnd(new Contact(id++, "Carlo Mendoza", 8951234567L, "carlo@mail.com", TelecoProvider.DITO));
        list.insertAtEnd(new Contact(id++, "Diana Lopez",   9201234567L, "diana@mail.com", TelecoProvider.SMART));
        list.insertAtEnd(new Contact(id++, "Ethan Cruz",    9271234567L, "ethan@mail.com", TelecoProvider.GLOBE));
        list.insertAtEnd(new Contact(id++, "Faith Ramos",   9911234567L, "faith@mail.com", TelecoProvider.DITO));

        return id - firstId;
    }
}
