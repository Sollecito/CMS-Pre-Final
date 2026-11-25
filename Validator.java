
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

    
    
    
    public static boolean isValidName(String name) {
        int length = name.length();

        if (length < 2 || length > 24) {
            return false;
        }
        if (name.charAt(0) == ' ' || name.charAt(length - 1) == ' ') {
            return false;
        }

        int letters = 0;
        for (int i = 0; i < length; i++) {
            char c = name.charAt(i);
            if (isLetter(c)) {
                letters++;
            } else if (c != ' ' && c != '.' && c != '-' && c != '\'') {
                return false;                       
            }
        }
        return letters >= 2;
    }

    
    
    
    
    public static boolean isValidEmail(String email) {
        int length = email.length();

        if (length < 5 || length > 26) {
            return false;
        }

        
        int atIndex = -1;
        for (int i = 0; i < length; i++) {
            char c = email.charAt(i);
            if (c == ' ') {
                return false;
            }
            if (c == '@') {
                if (atIndex != -1) {
                    return false;                   
                }
                atIndex = i;
            }
        }
        if (atIndex < 1) {
            return false;                           
        }

        
        int dotIndex = -1;
        for (int i = length - 1; i > atIndex; i--) {
            if (email.charAt(i) == '.') {
                dotIndex = i;
                break;
            }
        }
        if (dotIndex == -1) {
            return false;                           
        }
        if (dotIndex == atIndex + 1) {
            return false;                           
        }
        if (dotIndex == length - 1) {
            return false;                           
        }
        return true;
    }

    
    private static boolean isLetter(char c) {
        return (c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') || c >= 192;
    }
}
