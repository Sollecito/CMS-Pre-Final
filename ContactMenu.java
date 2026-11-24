













public class ContactMenu {

    private ContactADT list;

    public ContactMenu(ContactADT list) {
        this.list = list;
    }

    
    public void showOptions() {
        System.out.println();
        System.out.println("╔══════════════════════════════════════╗");
        System.out.println("║           CONTACT MANAGER            ║");
        System.out.println("╠══════════════════════════════════════╣");
        System.out.println("║  1. Add contact (at the end)         ║");
        System.out.println("║  2. Add contact (at a position)      ║");
        System.out.println("║  3. Show all contacts                ║");
        System.out.println("║  4. Delete contact (by position)     ║");
        System.out.println("║  5. Search contact                   ║");
        System.out.println("║  6. Show size and capacity           ║");
        System.out.println("║  7. Show contacts by provider        ║");
        System.out.println("║  8. Load sample contacts             ║");
        System.out.println("║  0. Exit                             ║");
        System.out.println("╚══════════════════════════════════════╝");
    }

    
    public void showContacts() {
        System.out.println("════════════════ CONTACTS ════════════════");
        if (list.getSize() == 0) {
            System.out.println("  No contacts yet.");
        }
        for (int i = 0; i < list.getSize(); i++) {
            showContact(i + 1, list.get(i));
        }
        System.out.println("═══════════════════════════════════════════");
    }

    
    
    
    public void showContactsByProvider(TelecoProvider provider) {
        System.out.println("═══════ " + provider.getName() + " CONTACTS ═══════");
        int found = 0;
        for (int i = 0; i < list.getSize(); i++) {
            Contact c = list.get(i);
            if (c.getProvider() == provider) {
                showContact(i + 1, c);
                found++;
            }
        }
        if (found == 0) {
            System.out.println("  No contacts for this provider.");
        }
        System.out.println("═══════════════════════════════════════════");
    }

    
    public void showContact(int position, Contact c) {
        System.out.println("  " + position + ". ID " + c.getId() + " | " + c.getName()
                + " | " + c.getPhoneDisplay() + " | " + c.getEmail()
                + " | " + c.getProvider().getName());
    }
}
