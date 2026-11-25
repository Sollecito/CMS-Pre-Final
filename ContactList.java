

public class ContactList {

    private int       capacity = 4;                  
    private int       size     = 0;                  
    private Contact[] array    = new Contact[4];     

    public int getSize()     { return size; }
    public int getCapacity() { return capacity; }

    
    public void insertAtEnd(Contact c) {

        
        if (size == capacity) {
            resize();
        }
        array[size] = c;

        
        size++;
    }

    
    
    public void insertAtPosition(int index, Contact c) {

        
        if (index < 0 || index > size) {
            return;
        }

        
        if (size == capacity) {
            resize();
        }

        
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }

        
        array[index] = c;

        
        size++;
    }

    
    public void delete(int index) {

        
        if (index < 0 || index >= size) {
            return;
        }

        
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }

        
        size--;
    }

    
    public void resize() {

        capacity = capacity * 2;

        
        Contact[] newArray = new Contact[capacity];

        
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }

        
        array = newArray;
    }

    
    public Contact get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return array[index];
    }

    
    public void clear() {
        capacity = 4;
        size     = 0;
        array    = new Contact[4];
    }

    
    
    public boolean isSortedById() {
        for (int i = 0; i < size - 1; i++) {
            if (array[i].getId() > array[i + 1].getId()) {
                return false;
            }
        }
        return true;
    }

    
    public int linearSearch(int id) {
        return Searcher.linearSearch(array, size, id);
    }

    public int linearSearchByPhone(long phone) {
        return Searcher.linearSearchByPhone(array, size, phone);
    }

    public int binarySearch(int id) {
        return Searcher.binarySearch(array, size, id);
    }

    public int recursiveBinarySearch(int id) {
        return Searcher.recursiveBinarySearch(array, id, 0, size - 1);
    }
}
