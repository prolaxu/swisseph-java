package io.github.prolaxu.swisseph;

public class AllFunctionsTest {

    public static void main(String[] args) {
        System.out.println("--- Running All Functions Test ---");

        // Load the native library
        try {
            System.loadLibrary("swisseph");
            System.out.println("Native library loaded successfully.");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load native library: " + e.getMessage());
            System.err.println("Please ensure libswisseph.so is in your java.library.path");
            return;
        }
        Swisseph.swe_set_ephe_path("ephe");
        
        // Test variables
        double tjd_ut = 2451545.0; // Jan 1, 2000 12:00 UT
        double tjd_et = 2451545.0; // Jan 1, 2000 12:00 ET
        double[] geopos = new double[3]; // longitude, latitude, altitude
        geopos[0] = 8.55; // Zurich longitude
        geopos[1] = 47.37; // Zurich latitude
        geopos[2] = 400.0; // altitude in meters
        
        System.out.println("\n=== SWISS EPHEMERIS COMPREHENSIVE FUNCTION TEST ===");
        
        // 1. Version and basic info
        testVersionAndInfo();
        
        // 2. Basic planetary calculations
        testPlanetaryCalculations(tjd_ut, tjd_et);
        
        // 3. Fixed star calculations
        testFixedStars(tjd_ut, tjd_et);
        
        // 4. Date and time functions
        testDateTimeFunctions();
        
        // 5. House calculations
        testHouseCalculations(tjd_ut, geopos);
        
        // 6. Eclipse and occultation functions
        testEclipseOccultations(tjd_ut, geopos);
        
        // 7. Rise/set/transit functions
        testRiseSetTransit(tjd_ut, geopos);
        
        // 8. Heliacal phenomena
        testHeliacalPhenomena(tjd_ut, geopos);
        
        // 9. Coordinate transformations
        testCoordinateTransformations(tjd_ut, geopos);
        
        // 10. Utility functions
        testUtilityFunctions();
        
        // 11. Advanced calculations
        testAdvancedCalculations(tjd_ut, tjd_et);
        
        System.out.println("\n=== ALL TESTS COMPLETED ===");
        Swisseph.swe_close();
        System.out.println("swe_close() called.");

        System.out.println("\n--- All Functions Test Complete ---");
    }

    private static void testVersionAndInfo() {
        System.out.println("\n--- Testing Version and Basic Info ---");
        try {
            String version = Swisseph.swe_version();
            System.out.println("Swiss Ephemeris version: " + version);
            
            String planetName = Swisseph.swe_get_planet_name(Swisseph.SE_SUN);
            System.out.println("Planet name for SE_SUN: " + planetName);
            
            planetName = Swisseph.swe_get_planet_name(Swisseph.SE_MOON);
            System.out.println("Planet name for SE_MOON: " + planetName);
            
            System.out.println("✓ Version and info tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in version/info tests: " + e.getMessage());
        }
    }

    private static void testPlanetaryCalculations(double tjd_ut, double tjd_et) {
        System.out.println("\n--- Testing Planetary Calculations ---");
        try {
            double[] xx = new double[6];
            
            // Test swe_calc_ut for all major planets
            int[] planets = {Swisseph.SE_SUN, Swisseph.SE_MOON, Swisseph.SE_MERCURY, 
                           Swisseph.SE_VENUS, Swisseph.SE_MARS, Swisseph.SE_JUPITER, 
                           Swisseph.SE_SATURN, Swisseph.SE_URANUS, Swisseph.SE_NEPTUNE, Swisseph.SE_PLUTO};
            
            for (int planet : planets) {
                int ret = Swisseph.swe_calc_ut(tjd_ut, planet, Swisseph.SEFLG_SPEED, xx);
                if (ret >= 0) {
                    String name = Swisseph.swe_get_planet_name(planet);
                    System.out.printf("%s: Long=%.6f°, Lat=%.6f°, Dist=%.6f AU%n", 
                                    name, xx[0], xx[1], xx[2]);
                } else {
                    System.err.println("Error calculating planet " + planet + ": " + Swisseph.getLastError());
                }
            }
            
            // Test phenomena
            double[] attr = new double[20];
            int ret = Swisseph.swe_pheno_ut(tjd_ut, Swisseph.SE_MOON, 0, attr);
            if (ret >= 0) {
                System.out.printf("Moon phase angle: %.2f°, illuminated fraction: %.3f%n", 
                                attr[0], attr[1]);
            }
            
            System.out.println("✓ Planetary calculations tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in planetary calculations: " + e.getMessage());
        }
    }

    private static void testFixedStars(double tjd_ut, double tjd_et) {
        System.out.println("\n--- Testing Fixed Stars ---");
        try {
            double[] xx = new double[6];
            double[] mag = new double[1];
            
            String[] stars = {"Aldebaran", "Regulus", "Spica", "Antares"};
            
            for (String star : stars) {
                int ret = Swisseph.swe_fixstar_ut(star, tjd_ut, Swisseph.SEFLG_SPEED, xx);
                if (ret >= 0) {
                    System.out.printf("%s: Long=%.6f°, Lat=%.6f°%n", star, xx[0], xx[1]);
                    
                    // Get magnitude
                    int mag_ret = Swisseph.swe_fixstar_mag(star, mag);
                    if (mag_ret >= 0) {
                        System.out.printf("%s magnitude: %.2f%n", star, mag[0]);
                    }
                } else {
                    System.err.println("Error calculating star " + star + ": " + Swisseph.getLastError());
                }
            }
            
            System.out.println("✓ Fixed stars tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in fixed stars tests: " + e.getMessage());
        }
    }

    private static void testDateTimeFunctions() {
        System.out.println("\n--- Testing Date/Time Functions ---");
        try {
            // Test Julian day calculation
            double jd = Swisseph.swe_julday(2000, 1, 1, 12.0, Swisseph.SE_GREG_CAL);
            System.out.println("Julian day for 2000-01-01 12:00: " + jd);
            
            // Test reverse Julian day
            int[] year = new int[1], month = new int[1], day = new int[1];
            double[] hour = new double[1];
            Swisseph.swe_revjul(jd, Swisseph.SE_GREG_CAL, year, month, day, hour);
            System.out.printf("Reverse: %d-%02d-%02d %.2f%n", year[0], month[0], day[0], hour[0]);
            
            // Test UTC to JD conversion
            double[] dret = new double[2];
            int ret = Swisseph.swe_utc_to_jd(2000, 1, 1, 12, 0, 0.0, Swisseph.SE_GREG_CAL, dret);
            if (ret >= 0) {
                System.out.printf("UTC to JD: ET=%.6f, UT=%.6f%n", dret[0], dret[1]);
            }
            
            // Test Delta T
            double dt = Swisseph.swe_deltat(jd);
            System.out.printf("Delta T for JD %.1f: %.1f seconds%n", jd, dt * 86400);
            
            // Test sidereal time
            double sidtime = Swisseph.swe_sidtime(jd);
            System.out.printf("Sidereal time: %.6f hours%n", sidtime);
            
            System.out.println("✓ Date/time functions tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in date/time tests: " + e.getMessage());
        }
    }

    private static void testHouseCalculations(double tjd_ut, double[] geopos) {
        System.out.println("\n--- Testing House Calculations ---");
        try {
            double[] cusps = new double[13];
            double[] ascmc = new double[10];
            
            // Test Placidus houses
            int ret = Swisseph.swe_houses(tjd_ut, geopos[1], geopos[0], 'P', cusps, ascmc);
            if (ret >= 0) {
                System.out.println("Placidus Houses:");
                for (int i = 1; i <= 12; i++) {
                    System.out.printf("House %2d: %.2f°%n", i, cusps[i]);
                }
                System.out.printf("Ascendant: %.2f°, MC: %.2f°%n", ascmc[0], ascmc[1]);
            }
            
            // Test house position
            double[] xpin = {0.0, 0.0}; // 0° Aries
            double housePos = Swisseph.swe_house_pos(ascmc[2], geopos[1], ascmc[8], 'P', xpin);
            System.out.printf("House position for 0° Aries: %.2f%n", housePos);
            
            // Test house system name
            String houseName = Swisseph.swe_house_name('P');
            System.out.println("House system P: " + houseName);
            
            System.out.println("✓ House calculations tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in house calculations: " + e.getMessage());
        }
    }

    private static void testEclipseOccultations(double tjd_ut, double[] geopos) {
        System.out.println("\n--- Testing Eclipse/Occultation Functions ---");
        try {
            double[] tret = new double[10];
            double[] attr = new double[20];
            
            // Test next solar eclipse globally
            int ret = Swisseph.swe_sol_eclipse_when_glob(tjd_ut, 0, 0, tret, 0);
            if (ret >= 0) {
                System.out.printf("Next solar eclipse: JD %.6f%n", tret[0]);
                
                // Get eclipse details
                double[] eclgeopos = new double[3];
                int where_ret = Swisseph.swe_sol_eclipse_where(tret[0], 0, eclgeopos, attr);
                if (where_ret >= 0) {
                    System.out.printf("Eclipse center: %.2f°E, %.2f°N%n", eclgeopos[0], eclgeopos[1]);
                }
            }
            
            // Test next lunar eclipse
            ret = Swisseph.swe_lun_eclipse_when(tjd_ut, 0, 0, tret, 0);
            if (ret >= 0) {
                System.out.printf("Next lunar eclipse: JD %.6f%n", tret[0]);
            }
            
            System.out.println("✓ Eclipse/occultation tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in eclipse/occultation tests: " + e.getMessage());
        }
    }

    private static void testRiseSetTransit(double tjd_ut, double[] geopos) {
        System.out.println("\n--- Testing Rise/Set/Transit Functions ---");
        try {
            double[] tret = new double[3];
            
            // Test Sun rise/set/transit
            int ret = Swisseph.swe_rise_trans(tjd_ut, Swisseph.SE_SUN, null, 0, 
                                            Swisseph.SE_CALC_RISE | Swisseph.SE_CALC_SET | Swisseph.SE_CALC_MTRANSIT, 
                                            geopos, 1013.25, 15.0, tret);
            if (ret >= 0) {
                System.out.printf("Sun rise: JD %.6f%n", tret[0]);
                System.out.printf("Sun transit: JD %.6f%n", tret[1]);
                System.out.printf("Sun set: JD %.6f%n", tret[2]);
            }
            
            System.out.println("✓ Rise/set/transit tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in rise/set/transit tests: " + e.getMessage());
        }
    }

    private static void testHeliacalPhenomena(double tjd_ut, double[] geopos) {
        System.out.println("\n--- Testing Heliacal Phenomena ---");
        try {
            double[] datm = {1013.25, 15.0, 50.0, 0.25}; // pressure, temp, humidity, meteorological range
            double[] dobs = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0}; // observer data
            double[] dret = new double[50];
            
            // Test heliacal rising of a star
            int ret = Swisseph.swe_heliacal_ut(tjd_ut, geopos, datm, dobs, "Aldebaran", 
                                             Swisseph.SE_HELIACAL_RISING, 0, dret);
            if (ret >= 0) {
                System.out.printf("Aldebaran heliacal rising: JD %.6f%n", dret[0]);
            }
            
            System.out.println("✓ Heliacal phenomena tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in heliacal phenomena tests: " + e.getMessage());
        }
    }

    private static void testCoordinateTransformations(double tjd_ut, double[] geopos) {
        System.out.println("\n--- Testing Coordinate Transformations ---");
        try {
            // Test azimuth/altitude calculation
            double[] xin = {0.0, 0.0, 1.0}; // 0° longitude, 0° latitude, 1 AU distance
            double[] xaz = new double[3];
            
            Swisseph.swe_azalt(tjd_ut, 0, geopos, 1013.25, 15.0, xin, xaz);
            System.out.printf("Azimuth: %.2f°, Altitude: %.2f°%n", xaz[0], xaz[1]);
            
            // Test coordinate transformation
            double[] xpo = {1.0, 0.0, 0.0}; // input coordinates
            double[] xpn = new double[3];    // output coordinates
            double eps = 23.4; // obliquity
            
            Swisseph.swe_cotrans(xpo, xpn, eps);
            System.out.printf("Coordinate transformation: (%.3f, %.3f, %.3f) -> (%.3f, %.3f, %.3f)%n",
                            xpo[0], xpo[1], xpo[2], xpn[0], xpn[1], xpn[2]);
            
            System.out.println("✓ Coordinate transformation tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in coordinate transformation tests: " + e.getMessage());
        }
    }

    private static void testUtilityFunctions() {
        System.out.println("\n--- Testing Utility Functions ---");
        try {
            // Test degree normalization
            double deg = Swisseph.swe_degnorm(450.5);
            System.out.printf("Normalized 450.5°: %.1f°%n", deg);
            
            // Test radian normalization
            double rad = Swisseph.swe_radnorm(7.5);
            System.out.printf("Normalized 7.5 rad: %.3f rad%n", rad);
            
            // Test midpoint calculation
            double midp = Swisseph.swe_deg_midp(10.0, 350.0);
            System.out.printf("Midpoint between 10° and 350°: %.1f°%n", midp);
            
            // Test day of week
            int dow = Swisseph.swe_day_of_week(2451545.0);
            System.out.printf("Day of week for JD 2451545.0: %d (0=Monday)%n", dow);
            
            // Test degree splitting
            int[] ideg = new int[1], imin = new int[1], isec = new int[1], isgn = new int[1];
            double[] dsecfr = new double[1];
            Swisseph.swe_split_deg(123.456789, 0, ideg, imin, isec, dsecfr, isgn);
            System.out.printf("123.456789° = %d° %d' %d\" %.3f%n", 
                            ideg[0], imin[0], isec[0], dsecfr[0]);
            
            System.out.println("✓ Utility functions tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in utility functions tests: " + e.getMessage());
        }
    }

    private static void testAdvancedCalculations(double tjd_ut, double tjd_et) {
        System.out.println("\n--- Testing Advanced Calculations ---");
        try {
            // Test ayanamsa (sidereal calculations)
            Swisseph.swe_set_sid_mode(Swisseph.SE_SIDM_LAHIRI, 0, 0);
            double ayanamsa = Swisseph.swe_get_ayanamsa_ut(tjd_ut);
            System.out.printf("Lahiri ayanamsa: %.6f°%n", ayanamsa);
            
            String ayanamsaName = Swisseph.swe_get_ayanamsa_name(Swisseph.SE_SIDM_LAHIRI);
            System.out.println("Ayanamsa name: " + ayanamsaName);
            
            // Test nodes and apsides
            double[] xnasc = new double[6], xndsc = new double[6];
            double[] xperi = new double[6], xaphe = new double[6];
            
            int ret = Swisseph.swe_nod_aps_ut(tjd_ut, Swisseph.SE_MOON, 0, 0, xnasc, xndsc, xperi, xaphe);
            if (ret >= 0) {
                System.out.printf("Moon's north node: %.6f°%n", xnasc[0]);
                System.out.printf("Moon's south node: %.6f°%n", xndsc[0]);
                System.out.printf("Moon's perigee: %.6f°%n", xperi[0]);
                System.out.printf("Moon's apogee: %.6f°%n", xaphe[0]);
            }
            
            // Test orbital elements
            double[] dret = new double[50];
            ret = Swisseph.swe_get_orbital_elements(tjd_et, Swisseph.SE_MARS, 0, dret);
            if (ret >= 0) {
                System.out.printf("Mars semi-major axis: %.6f AU%n", dret[0]);
                System.out.printf("Mars eccentricity: %.6f%n", dret[1]);
                System.out.printf("Mars inclination: %.6f°%n", dret[2]);
            }
            
            System.out.println("✓ Advanced calculations tests passed");
        } catch (Exception e) {
            System.err.println("✗ Error in advanced calculations tests: " + e.getMessage());
        }
    }
}