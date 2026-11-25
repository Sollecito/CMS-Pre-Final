
public class Searcher {
    
    static int linearSearch(Contact[] array, int size, int target) {

        
        for (int i = 0; i < size; i++) {
            if (array[i].getId() == target) {
                return i;                
            }
        }

        return -1;                       
    }

    
    
    static int linearSearchByPhone(Contact[] array, int size, long target) {

        for (int i = 0; i < size; i++) {
            if (array[i].getPhone() == target) {
                return i;
            }
        }

        return -1;
    }


    
    static int binarySearch(Contact[] array, int size, int target) {

        int low  = 0;
        int high = size - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;      

            
            if (array[mid].getId() == target) {
                return mid;
            }

            
            if (array[mid].getId() < target) {
                low = mid + 1;
            }
            
            else {
                high = mid - 1;
            }
        }

        
        return -1;
    }

    
    
    
    
    
    static int recursiveBinarySearch(Contact[] array, int target, int low, int high) {

        if (low > high) {
            return -1;                             
        }

        int mid = low + (high - low) / 2;

        if (array[mid].getId() == target) {
            return mid;                            
        }

        if (array[mid].getId() < target) {
            
            return recursiveBinarySearch(array, target, mid + 1, high);
        }

        
        return recursiveBinarySearch(array, target, low, mid - 1);
    }
}
