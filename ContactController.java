import java.util.Scanner;

public class ContactController {

    
    private ContactList list;     
    private ContactMenu menu;     
    private FileHandler files;    
    private Scanner     input;    
    private int         nextId;   

    
    
    
    public ContactController() {
        input  = new Scanner(System.in);
        list   = new ContactList();
        menu   = new ContactMenu(list);   
        files  = new FileHandler("contacts.txt");
        nextId = 1;
    }

    
    
    public void start() {
        System.out.println("   ╔══════════════════════════════╗");
        System.out.println("   ║         CONTACT BOOK         ║");
        System.out.println("   ╚══════════════════════════════╝");
        System.out.println("        .-------------------.     ");
        System.out.println("        |   CONTACT  BOOK   |     ");
        System.out.println("        |  ~~~~~~~~~~~~~~~  |     ");
        System.out.println("        |  Smart            |     ");
        System.out.println("        |  Globe            |     ");
        System.out.println("        |  DITO             |     ");
        System.out.println("        '-------------------'     ");
        System.out.println("==============================================");
        System.out.println("   Welcome to the Contact Book!");
        System.out.println("   Press ENTER to start...");
        System.out.println("==============================================");

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
                    System.out.println("Size    : " + list.getSize() + " contact(s)");
                    System.out.println("Capacity: " + list.getCapacity() + " slot(s)");
                    break;
                case 7:
                    menu.showContactsByProvider(askForProvider());
                    break;
                case 8:
                    loadSample();
                    break;
                case 9:
                    saveContacts();
                    break;
                case 10:
                    loadContacts();
                    break;
                case 0:
                    System.out.println("Thank you sir pogi");
                    break;
                default:
                    System.out.println("Invalid choice! Please pick 0 to 10.");
                    break;
            }
        } while (choice != 0);
    }
 
    
    
    private void addAtEnd() {
        Contact c = askForContact();
        list.insertAtEnd(c);
        nextId++;
        System.out.println("Contact added! (ID " + c.getId() + ")");
    }

    
    
    private void addAtPosition() {
        menu.showContacts();

        int last = list.getSize() + 1;          
        System.out.print("Insert at position (1-" + last + "): ");
        int position = readInt();

        
        if (!Validator.isValidChoice(position, 1, last)) {
            System.out.println("Invalid choice! Please pick 1 to " + last + ".");
            return;
        }

        Contact c = askForContact();

        
        list.insertAtPosition(position - 1, c);
        nextId++;
        System.out.println("Contact inserted at position " + position + "! (ID " + c.getId() + ")");
    }

    
    
    private void deleteContact() {
        if (list.getSize() == 0) {
            System.out.println("No contacts to delete.");
            return;
        }

        menu.showContacts();
        System.out.print("Delete position (1-" + list.getSize() + "): ");
        int position = readInt();

        if (!Validator.isValidChoice(position, 1, list.getSize())) {
            System.out.println("Invalid choice! Please pick 1 to " + list.getSize() + ".");
            return;
        }

        
        Contact c = list.get(position - 1);
        System.out.println("Delete " + c.getName() + "?");
        System.out.println("  1. Yes, delete");
        System.out.println("  2. No, keep");
        System.out.print("Choose: ");
        int sure = readInt();

        if (sure == 1) {
            list.delete(position - 1);
            System.out.println("Deleted.");
        } else if (sure == 2) {
            System.out.println("Cancelled. Nothing was deleted.");
        } else {
            System.out.println("Invalid choice! Nothing was deleted.");
        }
    }

    
    
    private void searchContact() {
        if (list.getSize() == 0) {
            System.out.println("No contacts yet. Add some first!");
            return;
        }

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
                    printNotSorted();
                    return;
                }
                result = list.binarySearch(askForId());
                break;
            case 3:
                if (!list.isSortedById()) {
                    printNotSorted();
                    return;
                }
                result = list.recursiveBinarySearch(askForId());
                break;
            case 4:
                result = list.linearSearchByPhone(askForPhoneToFind());
                break;
            default:
                System.out.println("Invalid choice! Please pick 1 to 4.");
                return;
        }

        if (result != -1) {
            System.out.println("Found at position " + (result + 1) + "!");
            menu.showContact(result + 1, list.get(result));
        } else {
            System.out.println("Not found.");
        }
    }

    
    private void loadSample() {
        int loaded = SampleData.load(list, nextId);
        nextId = nextId + loaded;

        if (loaded == 0) {
            System.out.println("The sample contacts are already in the book.");
        } else {
            System.out.println("Loaded " + loaded + " sample contacts.");
        }
    }

    private void saveContacts() {
        if (files.save(list, nextId)) {
            System.out.println("Saved " + list.getSize() + " contact(s) to " + files.getFileName() + ".");
        } else {
            System.out.println("Could not save the file.");
        }
    }

    private void loadContacts() {
        int savedNextId = files.load(list);

        if (savedNextId == -1) {
            System.out.println("Could not load (file missing or damaged). Nothing was changed.");
        } else {
            nextId = savedNextId;
            System.out.println("Loaded " + list.getSize() + " contact(s) from " + files.getFileName() + ".");
        }
    }

    

    
    private void printNotSorted() {
        System.out.println("Binary search needs the IDs in order, but they are NOT in order");
        System.out.println("right now (this happens after \"Add at a position\").");
        System.out.println("Please use Linear search instead.");
    }

    
    private int askForId() {
        System.out.print("ID to find: ");
        int id = readInt();

        while (id < 1) {
            System.out.println("!An ID is 1 or higher!");
            System.out.print("ID to find: ");
            id = readInt();
        }
        return id;
    }

    
    private long askForPhoneToFind() {
        System.out.print("Phone number to find (e.g. 09171234567): ");
        long phone = readLong();

        while (!Validator.isValidPhone(phone)) {
            System.out.println("!Invalid number! Use 11 digits starting with 09 or 08!");
            System.out.print("Phone number to find: ");
            phone = readLong();
        }
        return phone;
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

            if (!Validator.isValidChoice(choice, 1, TelecoProvider.ALL.length)) {
                System.out.println("Invalid choice! Please pick 1 to " + TelecoProvider.ALL.length + ".");
            }
        }
        return TelecoProvider.ALL[choice - 1];
    }

    
    
    
    private Contact askForContact() {

        
        System.out.print("Name: ");
        String name = input.nextLine();
        while (!Validator.isValidName(name)) {
            System.out.println("!Name must be 2-24 characters: letters, spaces, . - ' only!");
            System.out.print("Name: ");
            name = input.nextLine();
        }

        
        
        System.out.print("Phone (11 digits, e.g. 09171234567): ");
        long phone = readLong();
        while (!Validator.isValidPhone(phone) || list.linearSearchByPhone(phone) != -1) {
            if (!Validator.isValidPhone(phone)) {
                System.out.println("!Invalid number! Use 11 digits starting with 09 or 08!");
            } else {
                System.out.println("!This phone number is already in the book!");
            }
            System.out.print("Phone: ");
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
        while (!Validator.isValidEmail(email)) {
            System.out.println("!Email must look like name@mail.com (5-26 characters, no spaces)!");
            System.out.print("Email: ");
            email = input.nextLine();
        }

        return new Contact(nextId, name, phone, email, provider);
    }

    
    
    
    private int readInt() {
        while (true) {
            try {
                int number = input.nextInt();
                input.nextLine();
                return number;
            } catch (Exception e) {
                System.out.println("!Please Input Valid Numbers only!");
                input.nextLine();                       
                System.out.print("Try again: ");
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
                System.out.println("!Please Input Valid Numbers only!");
                input.nextLine();
                System.out.print("Try again: ");
            }
        }
    }
}
