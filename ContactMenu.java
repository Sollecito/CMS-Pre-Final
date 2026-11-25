
public class ContactMenu {

    
    
    private ContactList list;

    
    public ContactMenu(ContactList list) {
        this.list = list;
    }

    
    public void showOptions() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║             CONTACT BOOK             ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Add contact (at the end)         ║");
        System.out.println("║  2. Add contact (at a position)      ║");
        System.out.println("║  3. Show all contacts                ║");
        System.out.println("║  4. Delete a contact                 ║");
        System.out.println("║  5. Search a contact                 ║");
        System.out.println("║  6. Show size and capacity           ║");
        System.out.println("║  7. Show contacts by provider        ║");
        System.out.println("║  8. Load sample contacts             ║");
        System.out.println("║  9. Save contacts to file            ║");
        System.out.println("║  10. Load contacts from file         ║");
        System.out.println("║  0. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    
    
    public void showContacts() {
        printTop("ALL CONTACTS");

        if (list.getSize() == 0) {
            System.out.printf("║  %-83s  ║%n", "No contacts yet.");
        }

        for (int i = 0; i < list.getSize(); i++) {
            printRow(i + 1, list.get(i));
        }

        printBottom();
    }

    
    
    
    
    public void showContactsByProvider(TelecoProvider provider) {
        printTop(provider.getName() + " CONTACTS");

        int found = 0;
        for (int i = 0; i < list.getSize(); i++) {
            Contact c = list.get(i);
            if (c.getProvider() == provider) {
                printRow(i + 1, c);
                found++;
            }
        }

        if (found == 0) {
            System.out.printf("║  %-83s  ║%n", "No contacts for this provider.");
        }

        printBottom();
    }

    
    public void showContact(int position, Contact c) {
        printTop("SEARCH RESULT");
        printRow(position, c);
        printBottom();
    }

    

    
    private void printTop(String title) {
        System.out.println("╔═══════════════════════════════════════════════════════════════════════════════════════╗");
        System.out.printf("║  %-83s  ║%n", title);
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════════════════╣");
        System.out.printf("║  %-4s %-5s %-24s %-12s %-26s %-8s ║%n",
                "No.", "ID", "Name", "Phone", "Email", "Provider");
        System.out.println("╠═══════════════════════════════════════════════════════════════════════════════════════╣");
    }

    
    private void printRow(int position, Contact c) {
        System.out.printf("║  %-4d %-5d %-24s %-12s %-26s %-8s ║%n",
                position,
                c.getId(),
                c.getName(),
                c.getPhoneDisplay(),
                c.getEmail(),
                c.getProvider().getName());
    }

    
    private void printBottom() {
        System.out.println("╚═══════════════════════════════════════════════════════════════════════════════════════╝");
    }
}
