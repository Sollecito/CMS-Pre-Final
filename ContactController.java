import java.util.Scanner;   




















public class ContactController {

    private ContactList list;     
    private ContactMenu menu;     
    private Scanner     input;    
    private int         nextId;   

    public ContactController() {
        input  = new Scanner(System.in);
        list   = new ContactList();
        menu   = new ContactMenu(list);   
        nextId = 1;
    }

    public void start() {
        System.out.println("   ╔══════════════════════════════╗");
        System.out.println("   ║       CONTACT MANAGER        ║");
        System.out.println("   ╚══════════════════════════════╝");
        System.out.println("   Press ENTER to start...");
        input.nextLine();   
        mainLoop();
    }

    
    public void mainLoop() {
        int choice;

        do {
            menu.showOptions();
            System.out.print("Choose: ");
            choice = readInt();

            switch (choice) {
                case 1:
                    addAtEnd();
                    break;
                case 2:
                    addAtPosition();
                    break;
                case 3:
                    menu.showContacts();
                    break;
                case 4:
                    deleteContact();
                    break;
                case 5:
                    searchContact();
                    break;
                case 6:
                    System.out.println("Size: " + list.getSize()
                            + ", Capacity: " + list.getCapacity());
                    break;
                case 7:
                    menu.showContactsByProvider(askForProvider());
                    break;
                case 8:
                    loadSample();
                    break;
                case 0:
                    System.out.println("Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice, try again.");
                    break;
            }
        } while (choice != 0);
    }

    
    private void addAtEnd() {
        Contact c = askForContact();
        list.insertAtEnd(c);
        nextId++;
        System.out.println("Contact added with ID " + c.getId() + ".");
    }

    
    private void addAtPosition() {
        menu.showContacts();
        System.out.print("What Index?: ");
        int position = readInt();

        
        if (!Validator.isValidChoice(position, 1, list.getSize() + 1)) {
            System.out.println("Invalid position! Pick 1 to " + (list.getSize() + 1) + ".");
            return;
        }

        Contact c = askForContact();
        
        if (list.insertAtPosition(position - 1, c)) {
            nextId++;
            System.out.println("Contact inserted.");
        } else {
            System.out.println("Could not insert (insertAtPosition() not written yet?).");
        }
    }

    
    private void deleteContact() {
        menu.showContacts();
        if (list.getSize() == 0) {
            return;
        }
        System.out.print("Delete position: ");
        int position = readInt();

        if (!Validator.isValidChoice(position, 1, list.getSize())) {
            System.out.println("Invalid position! Pick 1 to " + list.getSize() + ".");
            return;
        }

        if (list.delete(position - 1)) {
            System.out.println("Deleted.");
        } else {
            System.out.println("Could not delete (delete() not written yet?).");
        }
    }

    
    
    private void searchContact() {
        System.out.println("Search using:");
        System.out.println("  1. Linear search (by ID)");
        System.out.println("  2. Binary search (by ID)");
        System.out.println("  3. Recursive binary search (by ID)");
        System.out.println("  4. Linear search (by phone number)");
        System.out.print("Choose: ");
        int type = readInt();

        int result = -1;

        switch (type) {
            case 1:
                result = list.linearSearch(askForId());
                break;
            case 2:
                
                if (!list.isSortedById()) {
                    System.out.println("The list is NOT sorted by ID, so binary search cannot be used.");
                    return;
                }
                result = list.binarySearch(askForId());
                break;
            case 3:
                if (!list.isSortedById()) {
                    System.out.println("The list is NOT sorted by ID, so binary search cannot be used.");
                    return;
                }
                result = list.recursiveBinarySearch(askForId());
                break;
            case 4:
                System.out.print("Phone number to find (e.g. 09171234567): ");
                result = list.linearSearchByPhone(readLong());
                break;
            default:
                System.out.println("Invalid search type.");
                return;
        }

        if (result != -1) {
            System.out.println("Found:");
            menu.showContact(result + 1, list.get(result));
        } else {
            System.out.println("Not found.");
        }
    }

    
    private void loadSample() {
        int loaded = SampleData.load(list, nextId);
        nextId = nextId + loaded;
        System.out.println("Loaded " + loaded + " sample contacts.");
    }

    

    private int askForId() {
        System.out.print("ID to find: ");
        return readInt();
    }

    
    
    private TelecoProvider askForProvider() {
        int choice = 0;

        while (!Validator.isValidChoice(choice, 1, TelecoProvider.ALL.length)) {
            System.out.println("Provider:");
            for (int i = 0; i < TelecoProvider.ALL.length; i++) {
                System.out.println("  " + (i + 1) + ". " + TelecoProvider.ALL[i].getName());
            }
            System.out.print("Choose (1-" + TelecoProvider.ALL.length + "): ");
            choice = readInt();
        }
        return TelecoProvider.ALL[choice - 1];
    }

    
    
    private Contact askForContact() {
        System.out.print("Name: ");
        String name = input.nextLine();

        
        System.out.print("Phone (e.g. 09171234567): ");
        long phone = readLong();
        while (!Validator.isValidPhone(phone)) {
            System.out.print("Invalid number. Use 11 digits starting with 09 or 08: ");
            phone = readLong();
        }

        
        
        TelecoProvider provider = TelecoProvider.detect(Validator.getPrefix(phone));
        if (provider == null) {
            System.out.println("Provider not recognised from the number.");
            provider = askForProvider();
        } else {
            System.out.println("Detected provider: " + provider.getName());
        }

        System.out.print("Email: ");
        String email = input.nextLine();

        return new Contact(nextId, name, phone, email, provider);
    }

    
    
    
    private int readInt() {
        while (true) {
            try {
                int number = input.nextInt();
                input.nextLine();
                return number;
            } catch (Exception e) {
                input.nextLine();                       
                System.out.print("Numbers only, try again: ");
            }
        }
    }

    
    private long readLong() {
        while (true) {
            try {
                long number = input.nextLong();
                input.nextLine();
                return number;
            } catch (Exception e) {
                input.nextLine();
                System.out.print("Numbers only, try again: ");
            }
        }
    }
}
