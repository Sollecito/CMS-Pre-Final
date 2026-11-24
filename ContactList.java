






















public class ContactList implements ContactADT {

    private int       capacity    = 4;                  
    private int       size        = 0;                  
    private Contact[] contactlist = new Contact[4];     

    public int getSize()     { return size; }
    public int getCapacity() { return capacity; }

    
    
    

    
    public void insertAtEnd(Contact c) {

        
        if (size == capacity) {
            resize();
        }

        contactlist[size] = c;   
        size++;                  
    }

    
    public void resize() {

        capacity = capacity * 2;

        Contact[] newContactlist = new Contact[capacity];

        for (int i = 0; i < size; i++) {
            newContactlist[i] = contactlist[i];
        }

        contactlist = newContactlist;
    }

    
    public Contact get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return contactlist[index];
    }

    
    
    public boolean isSortedById() {
        for (int i = 0; i < size - 1; i++) {
            if (contactlist[i].getId() > contactlist[i + 1].getId()) {
                return false;
            }
        }
        return true;
    }

    
    public int linearSearch(int id) {
        return Searcher.linearSearch(contactlist, size, id);
    }

    public int linearSearchByPhone(long phone) {
        return Searcher.linearSearchByPhone(contactlist, size, phone);
    }

    public int binarySearch(int id) {
        return Searcher.binarySearch(contactlist, size, id);
    }

    public int recursiveBinarySearch(int id) {
        return Searcher.recursiveBinarySearch(contactlist, id, 0, size - 1);
    }

    
    
    

    
    
    
    
    
    
    
    
    
    
    
    public boolean insertAtPosition(int index, Contact c) {
        if (index < 0 || index > size) {
            return false;
        }

        if (size == capacity) {
            resize();
        }

        for (int i = size; i > index; i--) {
            contactlist[i] = contactlist[i - 1];
        }

        contactlist[index] = c;
        size++;
        return true;
    }

    
    
    
    
    
    
    
    public boolean delete(int index) {
        if (index < 0 || index >= size) {
            return false;
        }

        for (int i = index; i < size - 1; i++) {
            contactlist[i] = contactlist[i + 1];
        }

        contactlist[size - 1] = null;
        size--;
        return true;
    }
}
