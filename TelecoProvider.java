
public class TelecoProvider {

    
    public static final TelecoProvider SMART = new TelecoProvider("Smart", new int[] {
            813, 907, 908, 909, 910, 811, 912, 913, 914, 918, 919, 920, 921,
            928, 929, 930, 938, 939, 940, 946, 947, 948, 949, 950, 951, 961,
            963, 968, 969, 970, 981, 989, 992, 998, 999
    });

    public static final TelecoProvider GLOBE = new TelecoProvider("Globe", new int[] {
            817, 905, 906, 915, 916, 917, 926, 927, 935, 936, 945, 955,
            956, 965, 966, 967, 975, 976, 977, 995, 997
    });

    public static final TelecoProvider DITO = new TelecoProvider("DITO", new int[] {
            895, 896, 897, 898, 991, 992, 993, 994
    });

    
    public static final TelecoProvider[] ALL = { SMART, GLOBE, DITO };

    private String name;
    private int[]  prefixes;

    private TelecoProvider(String name, int[] prefixes) {
        this.name     = name;
        this.prefixes = prefixes;
    }

    public String getName() { return name; }

    
    public boolean hasPrefix(int prefix) {
        for (int i = 0; i < prefixes.length; i++) {
            if (prefixes[i] == prefix) {
                return true;
            }
        }
        return false;
    }

    
    public static TelecoProvider detect(int prefix) {
        for (int i = 0; i < ALL.length; i++) {
            if (ALL[i].hasPrefix(prefix)) {
                return ALL[i];
            }
        }
        return null;
    }
}
