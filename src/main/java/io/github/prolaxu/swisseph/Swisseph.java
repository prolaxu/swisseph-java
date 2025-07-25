package io.github.prolaxu.swisseph;

/**
 * JNI wrapper for the Swiss Ephemeris library, providing astronomical calculations.
 * 
 * <p>This class provides Java bindings to the Swiss Ephemeris C library, enabling high-precision
 * astrological and astronomical calculations including planetary positions, house systems,
 * eclipse and occultation computations, and other celestial phenomena.</p>
 * 
 * <p>For more information, see the <a href="https://www.astro.com/swisseph/">Swiss Ephemeris</a>
 * documentation.</p>
 * 
 * @version 2.10.03
 * @since 1.0
 */
public class Swisseph {
    // Planetary body constants
    public static final int SE_SUN = 0;
    public static final int SE_MOON = 1;
    public static final int SE_MERCURY = 2;
    public static final int SE_VENUS = 3;
    public static final int SE_MARS = 4;
    public static final int SE_JUPITER = 5;
    public static final int SE_SATURN = 6;
    public static final int SE_URANUS = 7;
    public static final int SE_NEPTUNE = 8;
    public static final int SE_PLUTO = 9;
    public static final int SE_MEAN_NODE = 10;
    public static final int SE_TRUE_NODE = 11;
    public static final int SE_MEAN_APOG = 12;
    public static final int SE_OSCU_APOG = 13;
    public static final int SE_EARTH = 14;
    public static final int SE_CHIRON = 15;
    public static final int SE_PHOLUS = 16;
    public static final int SE_CERES = 17;
    public static final int SE_PALLAS = 18;
    public static final int SE_JUNO = 19;
    public static final int SE_VESTA = 20;
    public static final int SE_INTP_APOG = 21;
    public static final int SE_INTP_PERG = 22;

    // Ephemeris flag constants
    public static final int SEFLG_JPLEPH = 1;
    public static final int SEFLG_SWIEPH = 2;
    public static final int SEFLG_MOSEPH = 4;
    public static final int SEFLG_HELCTR = 8;
    public static final int SEFLG_TRUEPOS = 16;
    public static final int SEFLG_J2000 = 32;
    public static final int SEFLG_NONUT = 64;
    public static final int SEFLG_SPEED = 256;
    public static final int SEFLG_NOGDEFL = 512;
    public static final int SEFLG_NOABERR = 1024;
    public static final int SEFLG_EQUATORIAL = 2048;
    public static final int SEFLG_XYZ = 4096;
    public static final int SEFLG_RADIANS = 8192;
    public static final int SEFLG_BARYCTR = 16384;
    public static final int SEFLG_TOPOCTR = 32768;
    public static final int SEFLG_SIDEREAL = 65536;

    // Calendar system constants
    public static final int SE_JUL_CAL = 0;
    public static final int SE_GREG_CAL = 1;

    // Sidereal mode (ayanamsa) constants
    public static final int SE_SIDM_FAGAN_BRADLEY = 0;
    public static final int SE_SIDM_LAHIRI = 1;
    public static final int SE_SIDM_DELUCE = 2;
    public static final int SE_SIDM_RAMAN = 3;
    public static final int SE_SIDM_USHASHASHI = 4;
    public static final int SE_SIDM_KRISHNAMURTI = 5;
    public static final int SE_SIDM_DJWHAL_KHUL = 6;
    public static final int SE_SIDM_YUKTESHWAR = 7;
    public static final int SE_SIDM_JN_BHASIN = 8;
    public static final int SE_SIDM_BABYL_KUGLER1 = 9;
    public static final int SE_SIDM_BABYL_KUGLER2 = 10;
    public static final int SE_SIDM_BABYL_KUGLER3 = 11;
    public static final int SE_SIDM_BABYL_HUBER = 12;
    public static final int SE_SIDM_BABYL_ETPSC = 13;
    public static final int SE_SIDM_ALDEBARAN_15TAU = 14;
    public static final int SE_SIDM_HIPPARCHOS = 15;
    public static final int SE_SIDM_SASSANIAN = 16;
    public static final int SE_SIDM_GALCENT_0SAG = 17;
    public static final int SE_SIDM_J2000 = 18;
    public static final int SE_SIDM_J1900 = 19;
    public static final int SE_SIDM_B1950 = 20;
    public static final int SE_SIDM_SURYASIDDHANTA = 21;
    public static final int SE_SIDM_SURYASIDDHANTA_MSUN = 22;
    public static final int SE_SIDM_ARYABHATA = 23;
    public static final int SE_SIDM_ARYABHATA_MSUN = 24;
    public static final int SE_SIDM_SS_REVATI = 25;
    public static final int SE_SIDM_SS_CITRA = 26;
    public static final int SE_SIDM_TRUE_CITRA = 27;
    public static final int SE_SIDM_TRUE_REVATI = 28;
    public static final int SE_SIDM_TRUE_PUSHYA = 29;
    public static final int SE_SIDM_GALCENT_RGILBRAND = 30;
    public static final int SE_SIDM_GALEQU_IAU1958 = 31;
    public static final int SE_SIDM_GALEQU_TRUE = 32;
    public static final int SE_SIDM_GALEQU_MULA = 33;
    public static final int SE_SIDM_GALALIGN_MARDYKS = 34;
    public static final int SE_SIDM_TRUE_MULA = 35;
    public static final int SE_SIDM_GALCENT_MULA_WILHELM = 36;
    public static final int SE_SIDM_ARYABHATA_522 = 37;
    public static final int SE_SIDM_BABYL_BRITTON = 38;
    public static final int SE_SIDM_TRUE_SHEORAN = 39;
    public static final int SE_SIDM_GALCENT_COCHRANE = 40;
    public static final int SE_SIDM_GALEQU_FIORENZA = 41;
    public static final int SE_SIDM_VALENS_MOON = 42;
    public static final int SE_SIDM_LAHIRI_1940 = 43;
    public static final int SE_SIDM_LAHIRI_VP285 = 44;
    public static final int SE_SIDM_KRISHNAMURTI_VP291 = 45;
    public static final int SE_SIDM_LAHIRI_ICRC = 46;
    public static final int SE_SIDM_USER = 255;

    public static final int SE_CALC_RISE = 1;
    public static final int SE_CALC_SET = 2;
    public static final int SE_CALC_MTRANSIT = 4;
    public static final int SE_CALC_ITRANSIT = 8;
    public static final int SE_BIT_DISC_CENTER = 256;
    public static final int SE_BIT_DISC_BOTTOM = 8192;
    public static final int SE_BIT_GEOCTR_NO_ECL_LAT = 128;
    public static final int SE_BIT_NO_REFRACTION = 512;
    public static final int SE_BIT_CIVIL_TWILIGHT = 1024;
    public static final int SE_BIT_NAUTIC_TWILIGHT = 2048;
    public static final int SE_BIT_ASTRO_TWILIGHT = 4096;
    public static final int SE_BIT_FIXED_DISC_SIZE = 16384;
    public static final int SE_BIT_FORCE_SLOW_METHOD = 32768;
    public static final int SE_BIT_HINDU_RISING = (SE_BIT_DISC_CENTER|SE_BIT_NO_REFRACTION|SE_BIT_GEOCTR_NO_ECL_LAT);

    public static final int SE_ECL_CENTRAL = 1;
    public static final int SE_ECL_NONCENTRAL = 2;
    public static final int SE_ECL_TOTAL = 4;
    public static final int SE_ECL_ANNULAR = 8;
    public static final int SE_ECL_PARTIAL = 16;
    public static final int SE_ECL_ANNULAR_TOTAL = 32;
    public static final int SE_ECL_ALLTYPES_SOLAR = (SE_ECL_CENTRAL|SE_ECL_NONCENTRAL|SE_ECL_TOTAL|SE_ECL_ANNULAR|SE_ECL_PARTIAL|SE_ECL_ANNULAR_TOTAL);

    public static final int SE_HELIACAL_RISING = 1;
    public static final int SE_HELIACAL_SETTING = 2;
    public static final int SE_MORNING_FIRST = SE_HELIACAL_RISING;
    public static final int SE_EVENING_LAST = SE_HELIACAL_SETTING;
    public static final int SE_EVENING_FIRST = 3;
    public static final int SE_MORNING_LAST = 4;

    /**
     * Returns the last error message from the Swiss Ephemeris library.
     * 
     * @return the last error message as a String, or null if no error occurred
     */
    public static native String getLastError();

    /**
     * Calculates planetary positions for a given Julian day in Universal Time (UT).
     * 
     * @param tjd_ut Julian day in Universal Time
     * @param ipl    planet number (use SE_* constants)
     * @param iflag  calculation flags (bit flags using SEFLG_* constants)
     * @param xx     output array that will contain the calculated position
     * @return       error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_calc_ut(double tjd_ut, int ipl, int iflag, double[] xx); 

    /**
     * Closes the Swiss Ephemeris library and frees resources.
     * Should be called when the library is no longer needed.
     */
    public static native void swe_close();
    
    /**
     * Sets the path to the ephemeris files.
     * 
     * @param path the directory path containing the ephemeris files
     */
    public static native void swe_set_ephe_path(String path);
    
    /**
     * Sets the JPL ephemeris file to be used for calculations.
     * 
     * @param fname the filename of the JPL ephemeris file
     */
    public static native void swe_set_jpl_file(String fname);
    /**
     * Returns the name of a planet or point based on its number.
     * 
     * @param ipl planet number (use SE_* constants)
     * @return the name of the planet as a String
     */
    public static native String swe_get_planet_name(int ipl); 
    /**
     * Sets the observer's geographic location for topocentric calculations.
     * 
     * @param geolon geographic longitude in degrees (east positive, west negative)
     * @param geolat geographic latitude in degrees (north positive, south negative)
     * @param geoalt altitude above sea level in meters
     */
    public static native void swe_set_topo(double geolon, double geolat, double geoalt);
    
    /**
     * Sets the sidereal mode (ayanamsa) for sidereal calculations.
     * 
     * @param sid_mode sidereal mode (use SE_SIDM_* constants)
     * @param t0 reference date in Julian days
     * @param ayan_t0 initial ayanamsa value at t0
     */
    public static native void swe_set_sid_mode(int sid_mode, double t0, double ayan_t0);
    /**
     * Calculates the ayanamsa (sidereal zodiac offset) for a given ephemeris time.
     * 
     * @param tjd_et Julian day in Ephemeris/DeltaT time
     * @return ayanamsa value in degrees
     */
    public static native double swe_get_ayanamsa(double tjd_et);
    
    /**
     * Calculates the ayanamsa (sidereal zodiac offset) for a given Universal Time.
     * 
     * @param tjd_ut Julian day in Universal Time
     * @return ayanamsa value in degrees
     */
    public static native double swe_get_ayanamsa_ut(double tjd_ut);
    
    /**
     * Returns the name of the ayanamsa method.
     * 
     * @param isidmode sidereal mode (use SE_SIDM_* constants)
     * @return the name of the ayanamsa method as a String
     */
    public static native String swe_get_ayanamsa_name(int isidmode);
    /**
     * Retrieves information about the currently loaded ephemeris file.
     * 
     * @param ifno     ephemeris file number (0 = first file)
     * @param tfstart  output array that will contain the start Julian day of the file
     * @param tfend    output array that will contain the end Julian day of the file
     * @param denum    output array that will contain the ephemeris number (DE number)
     * @return         the ephemeris file path, or null if no file is loaded
     */
    public static native String swe_get_current_file_data(int ifno, double[] tfstart, double[] tfend, int[] denum);
    /**
     * Calculates the position of a fixed star for a given ephemeris time.
     * 
     * @param star  the name of the fixed star (e.g., "Aldebaran")
     * @param tjd   Julian day in Ephemeris/DeltaT time
     * @param iflag calculation flags (bit flags using SEFLG_* constants)
     * @param xx    output array that will contain the calculated position
     * @return      error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_fixstar(String star, double tjd, int iflag, double[] xx);
    
    /**
     * Calculates the position of a fixed star for a given Universal Time.
     * 
     * @param star   the name of the fixed star (e.g., "Aldebaran")
     * @param tjd_ut Julian day in Universal Time
     * @param iflag  calculation flags (bit flags using SEFLG_* constants)
     * @param xx     output array that will contain the calculated position
     * @return       error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_fixstar_ut(String star, double tjd_ut, int iflag, double[] xx); 
    /**
     * Retrieves the magnitude of a fixed star.
     * 
     * @param star the name of the fixed star (e.g., "Aldebaran")
     * @param mag  output array that will contain the star's magnitude
     * @return     error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_fixstar_mag(String star, double[] mag); 
    /**
     * Calculates the position of a fixed star for a given ephemeris time (extended version).
     * 
     * @param star  the name of the fixed star (e.g., "Aldebaran")
     * @param tjd   Julian day in Ephemeris/DeltaT time
     * @param iflag calculation flags (bit flags using SEFLG_* constants)
     * @param xx    output array that will contain the calculated position
     * @return      error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_fixstar2(String star, double tjd, int iflag, double[] xx);
    
    /**
     * Calculates the position of a fixed star for a given Universal Time (extended version).
     * 
     * @param star   the name of the fixed star (e.g., "Aldebaran")
     * @param tjd_ut Julian day in Universal Time
     * @param iflag  calculation flags (bit flags using SEFLG_* constants)
     * @param xx     output array that will contain the calculated position
     * @return       error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_fixstar2_ut(String star, double tjd_ut, int iflag, double[] xx);
    
    /**
     * Retrieves the magnitude of a fixed star (extended version).
     * 
     * @param star the name of the fixed star (e.g., "Aldebaran")
     * @param mag  output array that will contain the star's magnitude
     * @return     error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_fixstar2_mag(String star, double[] mag); 
    public static native int swe_calc_pctr(double tjd, int ipl, int iplctr, int iflag, double[] xxret); 
    public static native double swe_solcross(double x2cross, double jd_et, int flag); 
    public static native double swe_solcross_ut(double x2cross, double jd_ut, int flag); 
    public static native double swe_mooncross(double x2cross, double jd_et, int flag); 
    public static native double swe_mooncross_ut(double x2cross, double jd_ut, int flag); 
    public static native double swe_mooncross_node(double jd_et, int flag, double[] xlon, double[] xlat); 
    public static native double swe_mooncross_node_ut(double jd_ut, int flag, double[] xlon, double[] xlat); 
    public static native int swe_helio_cross(int ipl, double x2cross, double jd_et, int iflag, int dir, double[] jd_cross); 
    public static native int swe_helio_cross_ut(int ipl, double x2cross, double jd_ut, int iflag, int dir, double[] jd_cross); 
    public static native int swe_nod_aps(double tjd_et, int ipl, int iflag, int method, double[] xnasc, double[] xndsc, double[] xperi, double[] xaphe); 
    public static native int swe_nod_aps_ut(double tjd_ut, int ipl, int iflag, int method, double[] xnasc, double[] xndsc, double[] xperi, double[] xaphe); 
    public static native int swe_get_orbital_elements(double tjd_et, int ipl, int iflag, double[] dret); 
    public static native int swe_orbit_max_min_true_distance(double tjd_et, int ipl, int iflag, double[] dmax, double[] dmin, double[] dtrue); 
    public static native int swe_pheno(double tjd, int ipl, int iflag, double[] attr); 
    public static native int swe_pheno_ut(double tjd_ut, int ipl, int iflag, double[] attr); 
    /**
     * Calculates the altitude and azimuth of a celestial body for a given time and location.
     * 
     * @param tjd_ut  Julian day in Universal Time
     * @param calc_flag calculation flags (bit flags using SEFLG_* constants)
     * @param geopos  geographic position [longitude, latitude]
     * @param atpress atmospheric pressure in mbar
     * @param attemp  atmospheric temperature in degrees Celsius
     * @param xin     input array containing the celestial body's position
     * @param xaz     output array that will contain the calculated altitude and azimuth
     */
    public static native void swe_azalt(double tjd_ut, int calc_flag, double[] geopos, double atpress, double attemp, double[] xin, double[] xaz);
    /**
     * Calculates the altitude and azimuth of a celestial body for a given time and location (reverse calculation).
     * 
     * @param tjd_ut  Julian day in Universal Time
     * @param calc_flag calculation flags (bit flags using SEFLG_* constants)
     * @param geopos  geographic position [longitude, latitude]
     * @param xin     input array containing the celestial body's position
     * @param xout    output array that will contain the calculated altitude and azimuth
     */
    public static native void swe_azalt_rev(double tjd_ut, int calc_flag, double[] geopos, double[] xin, double[] xout);
    /**
     * Calculates the time of rising or setting of a celestial body for a given date and location.
     * 
     * @param tjd_ut  Julian day in Universal Time
     * @param ipl     planet number (use SE_* constants)
     * @param starname name of the star (for fixed stars)
     * @param epheflag ephemeris flag (use SEFLG_* constants)
     * @param rsmi    rise/set flag (SE_CALC_RISE or SE_CALC_SET)
     * @param geopos  geographic position [longitude, latitude]
     * @param atpress atmospheric pressure in mbar
     * @param attemp  atmospheric temperature in degrees Celsius
     * @param horhgt  horizon height above sea level in meters
     * @param tret    output array that will contain the calculated time
     * @return        error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_rise_trans_true_hor(double tjd_ut, int ipl, String starname, int epheflag, int rsmi, double[] geopos, double atpress, double attemp, double horhgt, double[] tret); 
    /**
     * Calculates the time of rising or setting of a celestial body for a given date and location.
     * 
     * @param tjd_ut  Julian day in Universal Time
     * @param ipl     planet number (use SE_* constants)
     * @param starname name of the star (for fixed stars)
     * @param epheflag ephemeris flag (use SEFLG_* constants)
     * @param rsmi    rise/set flag (SE_CALC_RISE or SE_CALC_SET)
     * @param geopos  geographic position [longitude, latitude]
     * @param atpress atmospheric pressure in mbar
     * @param attemp  atmospheric temperature in degrees Celsius
     * @param tret    output array that will contain the calculated time
     * @return        error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_rise_trans(double tjd_ut, int ipl, String starname, int epheflag, int rsmi, double[] geopos, double atpress, double attemp, double[] tret); 
    /**
     * Calculates the Gauquelin sector of a celestial body for a given date and location.
     * 
     * @param t_ut    Julian day in Universal Time
     * @param ipl     planet number (use SE_* constants)
     * @param starname name of the star (for fixed stars)
     * @param iflag   calculation flags (bit flags using SEFLG_* constants)
     * @param imeth   method flag (use SE_GAUQUELIN_METHOD_* constants)
     * @param geopos  geographic position [longitude, latitude]
     * @param atpress atmospheric pressure in mbar
     * @param attemp  atmospheric temperature in degrees Celsius
     * @param dgsect  output array that will contain the calculated Gauquelin sector
     * @return        error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_gauquelin_sector(double t_ut, int ipl, String starname, int iflag, int imeth, double[] geopos, double atpress, double attemp, double[] dgsect); 
    /**
     * Calculates the geographic position where a solar eclipse is central or maximal.
     * 
     * @param tjd     Julian day in Ephemeris/DeltaT time
     * @param ifl     calculation flags (bit flags using SEFLG_* constants)
     * @param geopos  output array that will contain the geographic position [longitude, latitude]
     * @param attr    output array that will contain additional attributes
     * @return        error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_sol_eclipse_where(double tjd, int ifl, double[] geopos, double[] attr);
    
    /**
     * Calculates the geographic position where a lunar occultation is central or maximal.
     * 
     * @param tjd      Julian day in Ephemeris/DeltaT time
     * @param ipl      planet number of occulting body (use SE_* constants)
     * @param starname name of the occulted star
     * @param ifl      calculation flags (bit flags using SEFLG_* constants)
     * @param geopos   output array that will contain the geographic position [longitude, latitude]
     * @param attr     output array that will contain additional attributes
     * @return         error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_lun_occult_where(double tjd, int ipl, String starname, int ifl, double[] geopos, double[] attr); 
    /**
     * Calculates attributes of a solar eclipse for a given time and location.
     * 
     * @param tjd     Julian day in Ephemeris/DeltaT time
     * @param ifl     calculation flags (bit flags using SEFLG_* constants)
     * @param geopos  geographic position [longitude, latitude]
     * @param attr    output array that will contain the eclipse attributes
     * @return        error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_sol_eclipse_how(double tjd, int ifl, double[] geopos, double[] attr);
    
    /**
     * Finds the next solar eclipse for a specific location.
     * 
     * @param tjd_start  start time for search (Julian day in Ephemeris/DeltaT time)
     * @param ifl        calculation flags (bit flags using SEFLG_* constants)
     * @param geopos     geographic position [longitude, latitude]
     * @param tret       output array that will contain the eclipse times
     * @param attr       output array that will contain the eclipse attributes
     * @param backward   search direction (0 = forward, 1 = backward)
     * @return           error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_sol_eclipse_when_loc(double tjd_start, int ifl, double[] geopos, double[] tret, double[] attr, int backward);
    
    /**
     * Finds the next lunar occultation for a specific location.
     * 
     * @param tjd_start  start time for search (Julian day in Ephemeris/DeltaT time)
     * @param ipl        planet number of occulting body (use SE_* constants)
     * @param starname   name of the occulted star
     * @param ifl        calculation flags (bit flags using SEFLG_* constants)
     * @param geopos     geographic position [longitude, latitude]
     * @param tret       output array that will contain the occultation times
     * @param attr       output array that will contain the occultation attributes
     * @param backward   search direction (0 = forward, 1 = backward)
     * @return           error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_lun_occult_when_loc(double tjd_start, int ipl, String starname, int ifl, double[] geopos, double[] tret, double[] attr, int backward);
    
    /**
     * Finds the next global solar eclipse.
     * 
     * @param tjd_start  start time for search (Julian day in Ephemeris/DeltaT time)
     * @param ifl        calculation flags (bit flags using SEFLG_* constants)
     * @param ifltype    eclipse type filter (0 = any, SE_ECL_TOTAL, SE_ECL_ANNULAR, etc.)
     * @param tret       output array that will contain the eclipse times
     * @param backward   search direction (0 = forward, 1 = backward)
     * @return           error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_sol_eclipse_when_glob(double tjd_start, int ifl, int ifltype, double[] tret, int backward);
    
    /**
     * Finds the next global lunar occultation.
     * 
     * @param tjd_start  start time for search (Julian day in Ephemeris/DeltaT time)
     * @param ipl        planet number of occulting body (use SE_* constants)
     * @param starname   name of the occulted star
     * @param ifl        calculation flags (bit flags using SEFLG_* constants)
     * @param ifltype    occultation type filter (0 = any, SE_ECL_TOTAL, etc.)
     * @param tret       output array that will contain the occultation times
     * @param backward   search direction (0 = forward, 1 = backward)
     * @return           error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_lun_occult_when_glob(double tjd_start, int ipl, String starname, int ifl, int ifltype, double[] tret, int backward);
    
    /**
     * Calculates attributes of a lunar eclipse for a given time and location.
     * 
     * @param tjd_ut  Julian day in Universal Time
     * @param ifl     calculation flags (bit flags using SEFLG_* constants)
     * @param geopos  geographic position [longitude, latitude]
     * @param attr    output array that will contain the eclipse attributes
     * @return        error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_lun_eclipse_how(double tjd_ut, int ifl, double[] geopos, double[] attr);
    
    /**
     * Finds the next lunar eclipse.
     * 
     * @param tjd_start  start time for search (Julian day in Ephemeris/DeltaT time)
     * @param ifl        calculation flags (bit flags using SEFLG_* constants)
     * @param ifltype    eclipse type filter (0 = any, SE_ECL_TOTAL, SE_ECL_PENUMBRAL, etc.)
     * @param tret       output array that will contain the eclipse times
     * @param backward   search direction (0 = forward, 1 = backward)
     * @return           error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_lun_eclipse_when(double tjd_start, int ifl, int ifltype, double[] tret, int backward);
    
    /**
     * Finds the next lunar eclipse for a specific location.
     * 
     * @param tjd_start  start time for search (Julian day in Ephemeris/DeltaT time)
     * @param ifl        calculation flags (bit flags using SEFLG_* constants)
     * @param geopos     geographic position [longitude, latitude]
     * @param tret       output array that will contain the eclipse times
     * @param attr       output array that will contain the eclipse attributes
     * @param backward   search direction (0 = forward, 1 = backward)
     * @return           error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_lun_eclipse_when_loc(double tjd_start, int ifl, double[] geopos, double[] tret, double[] attr, int backward); 
    /**
     * Calculates heliacal risings and settings of planets and stars.
     * 
     * @param tjdstart_ut start time for search (Julian day in Universal Time)
     * @param geopos      geographic position [longitude, latitude, height]
     * @param datm        atmospheric conditions [pressure, temperature, relative humidity]
     * @param dobs        observer data [age, height, eye type]
     * @param ObjectName  name of the object (planet or fixed star)
     * @param TypeEvent   type of event (SE_HELIACAL_RISING, SE_HELIACAL_SETTING, etc.)
     * @param iflag       calculation flags (bit flags using SEFLG_* constants)
     * @param dret        output array that will contain the event data
     * @return            error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_heliacal_ut(double tjdstart_ut, double[] geopos, double[] datm, double[] dobs, String ObjectName, int TypeEvent, int iflag, double[] dret);
    
    /**
     * Calculates heliacal phenomena data for a given time.
     * 
     * @param tjd_ut     Julian day in Universal Time
     * @param geopos     geographic position [longitude, latitude, height]
     * @param datm       atmospheric conditions [pressure, temperature, relative humidity]
     * @param dobs       observer data [age, height, eye type]
     * @param ObjectName name of the object (planet or fixed star)
     * @param TypeEvent  type of event (SE_HELIACAL_RISING, SE_HELIACAL_SETTING, etc.)
     * @param helflag    calculation flags for heliacal events
     * @param darr       output array that will contain the event data
     * @return           error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_heliacal_pheno_ut(double tjd_ut, double[] geopos, double[] datm, double[] dobs, String ObjectName, int TypeEvent, int helflag, double[] darr);
    
    /**
     * Calculates the limiting visual magnitude for a given time and location.
     * 
     * @param tjdut      Julian day in Universal Time
     * @param geopos     geographic position [longitude, latitude, height]
     * @param datm       atmospheric conditions [pressure, temperature, relative humidity]
     * @param dobs       observer data [age, height, eye type]
     * @param ObjectName name of the object (planet or fixed star)
     * @param helflag    calculation flags for heliacal events
     * @param dret       output array that will contain the magnitude data
     * @return           error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_vis_limit_mag(double tjdut, double[] geopos, double[] datm, double[] dobs, String ObjectName, int helflag, double[] dret);
    
    /**
     * Calculates the arcus visionis (arc of visibility) for heliacal events.
     * 
     * @param tjdut    Julian day in Universal Time
     * @param dgeo     geographic position [longitude, latitude, height]
     * @param datm     atmospheric conditions [pressure, temperature, relative humidity]
     * @param dobs     observer data [age, height, eye type]
     * @param helflag  calculation flags for heliacal events
     * @param mag      magnitude of the object
     * @param azi_obj  azimuth of the object in degrees
     * @param azi_sun  azimuth of the sun in degrees
     * @param azi_moon azimuth of the moon in degrees
     * @param alt_moon altitude of the moon in degrees
     * @param dret     output array that will contain the angle data
     * @return         error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_heliacal_angle(double tjdut, double[] dgeo, double[] datm, double[] dobs, int helflag, double mag, double azi_obj, double azi_sun, double azi_moon, double alt_moon, double[] dret);
    
    /**
     * Calculates the arcus visionis (arc of visibility) with topographic corrections.
     * 
     * @param tjdut    Julian day in Universal Time
     * @param dgeo     geographic position [longitude, latitude, height]
     * @param datm     atmospheric conditions [pressure, temperature, relative humidity]
     * @param dobs     observer data [age, height, eye type]
     * @param helflag  calculation flags for heliacal events
     * @param mag      magnitude of the object
     * @param azi_obj  azimuth of the object in degrees
     * @param alt_obj  altitude of the object in degrees
     * @param azi_sun  azimuth of the sun in degrees
     * @param azi_moon azimuth of the moon in degrees
     * @param alt_moon altitude of the moon in degrees
     * @param dret     output array that will contain the angle data
     * @return         error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_topo_arcus_visionis(double tjdut, double[] dgeo, double[] datm, double[] dobs, int helflag, double mag, double azi_obj, double alt_obj, double azi_sun, double azi_moon, double alt_moon, double[] dret);
    
    /**
     * Sets the astronomical models to be used for calculations.
     * 
     * @param samod  string containing the model specifications
     * @param iflag  flags specifying which models to set
     */
    public static native void swe_set_astro_models(String samod, int iflag);
    
    /**
     * Gets the current astronomical models being used for calculations.
     * 
     * @param samod  buffer to store the model specifications
     * @param iflag  flags specifying which models to get
     * @return       string containing the model specifications
     */
    public static native String swe_get_astro_models(String samod, int iflag); 

    /**
     * Converts a calendar date to Julian day with timezone handling.
     * 
     * @param y      year
     * @param m      month (1-12)
     * @param d      day of month (1-31)
     * @param utime  time of day in decimal hours (0-24)
     * @param c      timezone specifier ('g' or 'l' for UTC, 'z' for local time)
     * @param tjd    output array that will contain the Julian day
     * @return       error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_date_conversion(int y, int m, int d, double utime, char c, double[] tjd);
    
    /**
     * Converts calendar date and time to Julian day.
     * 
     * @param year     year (e.g., 2023)
     * @param month    month (1-12)
     * @param day      day of month (1-31)
     * @param hour     hour of day (0-23) with decimal fractions
     * @param gregflag calendar system (SE_JUL_CAL or SE_GREG_CAL)
     * @return         Julian day as a double
     */
    public static native double swe_julday(int year, int month, int day, double hour, int gregflag);
    
    /**
     * Converts a Julian day to calendar date and time.
     * 
     * @param jd      Julian day to convert
     * @param gregflag calendar system (SE_JUL_CAL or SE_GREG_CAL)
     * @param jyear   output array for year
     * @param jmon    output array for month (1-12)
     * @param jday    output array for day of month (1-31)
     * @param jut     output array for time of day in decimal hours (0-24)
     */
    public static native void swe_revjul(double jd, int gregflag, int[] jyear, int[] jmon, int[] jday, double[] jut);
    
    /**
     * Converts UTC date and time to Julian day in UT and ET/TT.
     * 
     * @param iyear    year
     * @param imonth   month (1-12)
     * @param iday     day of month (1-31)
     * @param ihour    hour (0-23)
     * @param imin     minute (0-59)
     * @param dsec     seconds with decimal fractions
     * @param gregflag calendar system (SE_JUL_CAL or SE_GREG_CAL)
     * @param dret     output array [0] = Julian day in UT, [1] = Julian day in ET/TT
     * @return         error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_utc_to_jd(int iyear, int imonth, int iday, int ihour, int imin, double dsec, int gregflag, double[] dret);
    
    /**
     * Converts Julian day in ET/TT to UTC date and time.
     * 
     * @param tjd_et  Julian day in Ephemeris Time (ET/TT)
     * @param gregflag calendar system (SE_JUL_CAL or SE_GREG_CAL)
     * @param iyear   output array for year
     * @param imonth  output array for month (1-12)
     * @param iday    output array for day of month (1-31)
     * @param ihour   output array for hour (0-23)
     * @param imin    output array for minute (0-59)
     * @param dsec    output array for seconds with decimal fractions
     */
    public static native void swe_jdet_to_utc(double tjd_et, int gregflag, int[] iyear, int[] imonth, int[] iday, int[] ihour, int[] imin, double[] dsec);
    
    /**
     * Converts Julian day in UT to UTC date and time.
     * 
     * @param tjd_ut  Julian day in Universal Time (UT)
     * @param gregflag calendar system (SE_JUL_CAL or SE_GREG_CAL)
     * @param iyear   output array for year
     * @param imonth  output array for month (1-12)
     * @param iday    output array for day of month (1-31)
     * @param ihour   output array for hour (0-23)
     * @param imin    output array for minute (0-59)
     * @param dsec    output array for seconds with decimal fractions
     */
    public static native void swe_jdut1_to_utc(double tjd_ut, int gregflag, int[] iyear, int[] imonth, int[] iday, int[] ihour, int[] imin, double[] dsec);
    
    /**
     * Converts between timezones for a given UTC date and time.
     * 
     * @param iyear      input year
     * @param imonth     input month (1-12)
     * @param iday       input day of month (1-31)
     * @param ihour      input hour (0-23)
     * @param imin       input minute (0-59)
     * @param dsec       input seconds with decimal fractions
     * @param d_timezone timezone offset in hours (positive east of UTC)
     * @param iyear_out  output array for year
     * @param imonth_out output array for month (1-12)
     * @param iday_out   output array for day of month (1-31)
     * @param ihour_out  output array for hour (0-23)
     * @param imin_out   output array for minute (0-59)
     * @param dsec_out   output array for seconds with decimal fractions
     */
    public static native void swe_utc_time_zone(int iyear, int imonth, int iday, int ihour, int imin, double dsec, double d_timezone, int[] iyear_out, int[] imonth_out, int[] iday_out, int[] ihour_out, int[] imin_out, double[] dsec_out);

    public static native double swe_deltat(double tjd);
    public static native double swe_deltat_ex(double tjd); 
    public static native int swe_time_equ(double tjd, double[] te); 
    public static native int swe_lmt_to_lat(double tjd_lmt, double geolon, double[] tjd_lat); 
    public static native int swe_lat_to_lmt(double tjd_lat, double geolon, double[] tjd_lmt); 
    public static native double swe_sidtime0(double tjd_ut, double eps, double nut);
    public static native double swe_sidtime(double tjd_ut);
    public static native void swe_set_interpolate_nut(boolean do_interpolate);
    public static native void swe_cotrans(double[] xpo, double[] xpn, double eps);
    public static native void swe_cotrans_sp(double[] xpo, double[] xpn, double eps);
    public static native double swe_get_tid_acc();
    public static native void swe_set_tid_acc(double t_acc);
    public static native void swe_set_delta_t_userdef(double dt);
    public static native double swe_degnorm(double x);
    public static native double swe_radnorm(double x);
    public static native double swe_rad_midp(double x1, double x0);
    public static native double swe_deg_midp(double x1, double x0);
    public static native void swe_split_deg(double ddeg, int roundflag, int[] ideg, int[] imin, int[] isec, double[] dsecfr, int[] isgn);
    public static native int swe_day_of_week(double jd);
    public static native String swe_cs2timestr(int t, int sep, boolean suppressZero); 
    public static native String swe_cs2lonlatstr(int t, char pchar, char mchar); 
    public static native String swe_cs2degstr(int t); 

    /**
     * Calculates house cusps and other house system data.
     * 
     * @param tjd_ut  Julian day in Universal Time
     * @param geolat  geographic latitude in degrees (-90 to +90)
     * @param geolon  geographic longitude in degrees (-180 to +180, east is positive)
     * @param hsys    house system code (e.g., 'P' for Placidus, 'K' for Koch)
     * @param cusps   output array for house cusps (must have length >= 13)
     * @param ascmc   output array for special points like MC, ASC, etc. (must have length >= 10)
     * @return        error status (0 = OK, negative values indicate errors)
     */
    public static native int swe_houses(double tjd_ut, double geolat, double geolon, int hsys, double[] cusps, double[] ascmc);
    public static native int swe_houses_ex(double tjd_ut, int iflag, double geolat, double geolon, int hsys, double[] cusps, double[] ascmc);
    public static native int swe_houses_ex2(double tjd_ut, int iflag, double geolat, double geolon, int hsys, double[] cusps, double[] ascmc, double[] cusp_speed, double[] ascmc_speed); 
    public static native int swe_houses_armc(double armc, double geolat, double eps, int hsys, double[] cusps, double[] ascmc);
    public static native int swe_houses_armc_ex2(double armc, double geolat, double eps, int hsys, double[] cusps, double[] ascmc, double[] cusp_speed, double[] ascmc_speed); 
    public static native double swe_house_pos(double armc, double geolat, double eps, int hsys, double[] xpin); 
    /**
     * Returns the name of the house system corresponding to the given house system code.
     *
     * @param hsys the house system code (e.g., 'P' for Placidus, 'K' for Koch)
     * @return the name of the house system as a String
     */
    public static native String swe_house_name(int hsys);

    /**
     * Returns the version string of the Swiss Ephemeris library.
     *
     * @return the version string in the format "2.10.03"
     */
    public static native String swe_version();

    static {
        System.loadLibrary("swisseph");
    }
}
