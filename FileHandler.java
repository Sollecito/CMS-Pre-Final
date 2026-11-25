import java.io.File;          
import java.io.PrintWriter;   
import java.util.Scanner;     




public class FileHandler {

    private String fileName;

    public FileHandler(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() { return fileName; }

    
    
    public boolean save(ContactList list, int nextId) {
        try {
            PrintWriter out = new PrintWriter(fileName);

            out.println(nextId);
            out.println(list.getSize());

            for (int i = 0; i < list.getSize(); i++) {
                Contact c = list.get(i);
                out.println(c.getId());
                out.println(c.getName());
                out.println(c.getPhone());
                out.println(c.getEmail());
                out.println(providerNumber(c.getProvider()));
            }

            out.close();
            return true;

        } catch (Exception e) {
            return false;
        }
    }

    
    
    
    public int load(ContactList list) {
        try {
            Scanner in = new Scanner(new File(fileName));

            int nextId = in.nextInt();
            in.nextLine();                       
            int count = in.nextInt();
            in.nextLine();

            
            
            ContactList temp = new ContactList();

            for (int i = 0; i < count; i++) {
                int    id    = in.nextInt();
                in.nextLine();
                String name  = in.nextLine();
                long   phone = in.nextLong();
                in.nextLine();
                String email = in.nextLine();
                int    p     = in.nextInt();
                in.nextLine();

                temp.insertAtEnd(new Contact(id, name, phone, email, TelecoProvider.ALL[p - 1]));
            }
            in.close();

            
            list.clear();
            for (int i = 0; i < temp.getSize(); i++) {
                list.insertAtEnd(temp.get(i));
            }
            return nextId;

        } catch (Exception e) {
            return -1;
        }
    }

    
    
    private int providerNumber(TelecoProvider provider) {
        for (int i = 0; i < TelecoProvider.ALL.length; i++) {
            if (TelecoProvider.ALL[i] == provider) {
                return i + 1;
            }
        }
        return 1;
    }
}
