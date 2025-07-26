package io.github.prolaxu.swisseph;

public class SimpleExample {
    // Planet names for display
    private static final String[] PLANET_NAMES = {
        "Sun", "Moon", "Mercury", "Venus", "Mars",
        "Jupiter", "Saturn", "Uranus", "Neptune", "Pluto",
        "Mean Node", "True Node", "Mean Apogee", "Osc. Apogee", "Earth"
    };
    
    // House system to use (P = Placidus, K = Koch, etc.)
    private static final char HSYS = 'P';
    
    // Kathmandu coordinates (27.7172° N, 85.3240° E)
    private static final double KATHMANDU_LAT = 27.7172;
    private static final double KATHMANDU_LON = 85.3240;
    
    public static void main(String[] args) {
        try {
            System.loadLibrary("swisseph");
            System.out.println("Native library loaded successfully.\n");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load native library: " + e.getMessage());
            System.err.println("Please ensure `libswisseph.so` is in your `java.library.path`");
            return;
        }
        System.out.println("Swiss Ephemeris version: " + Swisseph.swe_version());

        // Set ephemeris path and sidereal mode
        Swisseph.swe_set_ephe_path("ephe");
        Swisseph.swe_set_sid_mode(Swisseph.SE_SIDM_KRISHNAMURTI_VP291, 0.0, 0.0);

        // Birth details: 2025-01-01 05:00:54 AM in Kathmandu (UTC+5:45)
        int year = 2025, month = 1, day = 1, hour = 5, minute = 0;
        double second = 54.0;  // Include seconds for precision
        double tzOffset = 5.75; // UTC+05:45 for Nepal (5.75 hours ahead of UTC)

        // Convert local time to UTC
        // Convert local time (NPT) to UTC
        double localDecimal = hour + (minute / 60.0) + (second / 3600.0);
        double utcDecimal = localDecimal - tzOffset;
        int utcDay = day;
        int utcMonth = month;
        int utcYear = year;

        if (utcDecimal < 0) {
            utcDecimal += 24;
            utcDay -= 1;
            // Handle month/year decrement if needed
            if (utcDay < 1) {
                utcMonth -= 1;
                if (utcMonth < 1) {
                    utcMonth = 12;
                    utcYear -= 1;
                }
                // Get days in previous month
                utcDay = getDaysInMonth(utcYear, utcMonth);
            }
        }
        // Calculate Julian Day
        double jd = Swisseph.swe_julday(utcYear, utcMonth, utcDay, utcDecimal, Swisseph.SE_GREG_CAL);
        
        // Calculate houses (cusp and house positions)
        double[] cusps = new double[13];
        double[] ascmc = new double[10];
        int result = Swisseph.swe_houses(jd, KATHMANDU_LAT, KATHMANDU_LON, HSYS, cusps, ascmc);
        
        if (result < 0) {
            System.err.println("Error calculating houses: " + Swisseph.getLastError());
            return;
        }

        // Print birth chart header
        System.out.println("========================================");
        System.out.println("        BIRTH CHART CALCULATION         ");
        System.out.println("========================================");
        System.out.printf("Date   : %04d-%02d-%02d%n", year, month, day);
        System.out.printf("Time   : %02d:%02d NPT (UTC+5:45)%n", hour, minute);
        System.out.printf("Place  : Kathmandu, Nepal (%.4f°N, %.4f°E)%n", KATHMANDU_LAT, KATHMANDU_LON);
        System.out.println("Ayanamsa:  - " + Swisseph.swe_get_ayanamsa_ut(jd));
        System.out.println("========================================\n");

        // Calculate and print planetary positions
        System.out.println("PLANETARY POSITIONS");
        System.out.println("------------------");
        
        int[] planets = {
            Swisseph.SE_SUN, Swisseph.SE_MOON, Swisseph.SE_MERCURY, Swisseph.SE_VENUS,
            Swisseph.SE_MARS, Swisseph.SE_JUPITER, Swisseph.SE_SATURN, Swisseph.SE_URANUS,
            Swisseph.SE_NEPTUNE, Swisseph.SE_PLUTO, Swisseph.SE_MEAN_NODE
        };
        
        double[] xx = new double[6];
        int hflags = Swisseph.SEFLG_SIDEREAL | Swisseph.SEFLG_SPEED;
        
        for (int i = 0; i < planets.length; i++) {
            int ret = Swisseph.swe_calc_ut(jd, planets[i], hflags, xx);
            if (ret >= 0) {
                String sign = getZodiacSign(xx[0]);
                System.out.printf("%-10s: %9.6f° (%s) %s%n", 
                    PLANET_NAMES[i < PLANET_NAMES.length ? i : i - PLANET_NAMES.length],
                    xx[0], sign, xx[3] >= 0 ? "D" : "R");
            }
        }
        
        // Print houses (cusp positions)
        System.out.println("\nHOUSES (Placidus)");
        System.out.println("----------------");
        for (int i = 1; i <= 12; i++) {
            String sign = getZodiacSign(cusps[i]);
            System.out.printf("House %2d: %9.6f° (%s)%n", i, cusps[i], sign);
        }
        
        // Print Ascendant and MC
        System.out.println("\nANGLES");
        System.out.println("------");
        System.out.printf("Ascendant (AC): %9.6f° (%s)%n", 
            ascmc[0], getZodiacSign(ascmc[0]));
        System.out.printf("MC           : %9.6f° (%s)%n", 
            ascmc[1], getZodiacSign(ascmc[1]));
            
        // Print Ayanamsa
        double ayanamsa = Swisseph.swe_get_ayanamsa_ut(jd);
        System.out.printf("\nAyanamsa: %.6f° \n", ayanamsa);
        
        // Clean up
        Swisseph.swe_close();
    }
    
    // Helper method to get days in a month, including leap years
    private static int getDaysInMonth(int year, int month) {
        switch (month) {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
                return 31;
            case 4: case 6: case 9: case 11:
                return 30;
            case 2:
                // Leap year check
                if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                    return 29;
                } else {
                    return 28;
                }
            default:
                throw new IllegalArgumentException("Invalid month: " + month);
        }
    }

    // Helper method to get zodiac sign from longitude
    private static String getZodiacSign(double longitude) {
        String[] signs = {"Ari", "Tau", "Gem", "Can", "Leo", "Vir",
                         "Lib", "Sco", "Sag", "Cap", "Aqu", "Pis"};
        int sign_idx = ((int)longitude / 30) % 12;
        double pos_in_sign = longitude % 30;
        int degrees = (int)pos_in_sign;
        double minutes = (pos_in_sign - degrees) * 60;
        return String.format("%d°%02d' %s", 
            degrees, (int)minutes, signs[sign_idx]);
    }
}
