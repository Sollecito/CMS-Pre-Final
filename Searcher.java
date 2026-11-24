
















public class Searcher {

    
    
    
    static int linearSearch(Contact[] array, int size, int id) {

        for (int i = 0; i < size; i++) {
            if (array[i].getId() == id) {
                return i;                
            }
        }
        return -1;                       
    }

    
    
    static int linearSearchByPhone(Contact[] array, int size, long phone) {

        for (int i = 0; i < size; i++) {
            if (array[i].getPhone() == phone) {
                return i;
            }
        }
        return -1;
    }

    
    
    
    
    
    
    
    
    static int binarySearch(Contact[] array, int size, int id) {

        int low  = 0;
        int high = size - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;     

            if (array[mid].getId() == id) {
                return mid;                        
            } else if (array[mid].getId() < id) {
                low = mid + 1;                     
            } else {
                high = mid - 1;                    
            }
        }
        return -1;                                 
    }

    
    
    
    
    
    static int recursiveBinarySearch(Contact[] array, int id, int low, int high) {

        if (low > high) {
            return -1;                             
        }

        int mid = low + (high - low) / 2;

        if (array[mid].getId() == id) {
            return mid;                            
        }

        if (array[mid].getId() < id) {
            return recursiveBinarySearch(array, id, mid + 1, high);   
        }

        return recursiveBinarySearch(array, id, low, mid - 1);        
    }
}
