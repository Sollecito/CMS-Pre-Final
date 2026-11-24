
public class Validator {

    
    public static boolean isValidChoice(int value, int min, int max) {
        return value >= min && value <= max;
    }

    
    
    
    public static boolean isValidPhone(long phone) {
        return phone >= 8000000000L && phone <= 9999999999L;
    }

    
    
    public static int getPrefix(long phone) {
        return (int) (phone / 10000000L);
    }
}
