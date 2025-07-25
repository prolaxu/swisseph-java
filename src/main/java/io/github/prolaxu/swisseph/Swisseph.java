package io.github.prolaxu.swisseph;

public class Swisseph {
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

    // Additional constants from swephexp.h
    public static final int SE_JUL_CAL = 0;
    public static final int SE_GREG_CAL = 1;

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

    public static native String getLastError(); // New method to retrieve error messages

    public static native int swe_calc_ut(double tjd_ut, int ipl, int iflag, double[] xx); // Removed char[] serr

    public static native void swe_close();
    public static native void swe_set_ephe_path(String path);
    public static native void swe_set_jpl_file(String fname);
    public static native String swe_get_planet_name(int ipl); // Removed char[] spname
    public static native void swe_set_topo(double geolon, double geolat, double geoalt);
    public static native void swe_set_sid_mode(int sid_mode, double t0, double ayan_t0);
    public static native double swe_get_ayanamsa(double tjd_et);
    public static native double swe_get_ayanamsa_ut(double tjd_ut);
    public static native String swe_get_ayanamsa_name(int isidmode);
    public static native String swe_get_current_file_data(int ifno, double[] tfstart, double[] tfend, int[] denum);
    public static native int swe_fixstar(String star, double tjd, int iflag, double[] xx); // Changed char[] star to String, removed char[] serr
    public static native int swe_fixstar_ut(String star, double tjd_ut, int iflag, double[] xx); // Changed char[] star to String, removed char[] serr
    public static native int swe_fixstar_mag(String star, double[] mag); // Changed char[] star to String, removed char[] serr
    public static native int swe_fixstar2(String star, double tjd, int iflag, double[] xx); // Changed char[] star to String, removed char[] serr
    public static native int swe_fixstar2_ut(String star, double tjd_ut, int iflag, double[] xx); // Changed char[] star to String, removed char[] serr
    public static native int swe_fixstar2_mag(String star, double[] mag); // Changed char[] star to String, removed char[] serr
    public static native int swe_calc_pctr(double tjd, int ipl, int iplctr, int iflag, double[] xxret); // Removed char[] serr
    public static native double swe_solcross(double x2cross, double jd_et, int flag); // Removed char[] serr
    public static native double swe_solcross_ut(double x2cross, double jd_ut, int flag); // Removed char[] serr
    public static native double swe_mooncross(double x2cross, double jd_et, int flag); // Removed char[] serr
    public static native double swe_mooncross_ut(double x2cross, double jd_ut, int flag); // Removed char[] serr
    public static native double swe_mooncross_node(double jd_et, int flag, double[] xlon, double[] xlat); // Removed char[] serr
    public static native double swe_mooncross_node_ut(double jd_ut, int flag, double[] xlon, double[] xlat); // Removed char[] serr
    public static native int swe_helio_cross(int ipl, double x2cross, double jd_et, int iflag, int dir, double[] jd_cross); // Removed char[] serr
    public static native int swe_helio_cross_ut(int ipl, double x2cross, double jd_ut, int iflag, int dir, double[] jd_cross); // Removed char[] serr
    public static native int swe_nod_aps(double tjd_et, int ipl, int iflag, int method, double[] xnasc, double[] xndsc, double[] xperi, double[] xaphe); // Removed char[] serr
    public static native int swe_nod_aps_ut(double tjd_ut, int ipl, int iflag, int method, double[] xnasc, double[] xndsc, double[] xperi, double[] xaphe); // Removed char[] serr
    public static native int swe_get_orbital_elements(double tjd_et, int ipl, int iflag, double[] dret); // Removed char[] serr
    public static native int swe_orbit_max_min_true_distance(double tjd_et, int ipl, int iflag, double[] dmax, double[] dmin, double[] dtrue); // Removed char[] serr
    public static native int swe_pheno(double tjd, int ipl, int iflag, double[] attr); // Removed char[] serr
    public static native int swe_pheno_ut(double tjd_ut, int ipl, int iflag, double[] attr); // Removed char[] serr
    public static native void swe_azalt(double tjd_ut, int calc_flag, double[] geopos, double atpress, double attemp, double[] xin, double[] xaz);
    public static native void swe_azalt_rev(double tjd_ut, int calc_flag, double[] geopos, double[] xin, double[] xout);
    public static native int swe_rise_trans_true_hor(double tjd_ut, int ipl, String starname, int epheflag, int rsmi, double[] geopos, double atpress, double attemp, double horhgt, double[] tret); // Changed char[] starname to String, removed char[] serr
    public static native int swe_rise_trans(double tjd_ut, int ipl, String starname, int epheflag, int rsmi, double[] geopos, double atpress, double attemp, double[] tret); // Changed char[] starname to String, removed char[] serr
    public static native int swe_gauquelin_sector(double t_ut, int ipl, String starname, int iflag, int imeth, double[] geopos, double atpress, double attemp, double[] dgsect); // Changed char[] starname to String, removed char[] serr
    public static native int swe_sol_eclipse_where(double tjd, int ifl, double[] geopos, double[] attr); // Removed char[] serr
    public static native int swe_lun_occult_where(double tjd, int ipl, String starname, int ifl, double[] geopos, double[] attr); // Changed char[] starname to String, removed char[] serr
    public static native int swe_sol_eclipse_how(double tjd, int ifl, double[] geopos, double[] attr); // Removed char[] serr
    public static native int swe_sol_eclipse_when_loc(double tjd_start, int ifl, double[] geopos, double[] tret, double[] attr, int backward); // Removed char[] serr
    public static native int swe_lun_occult_when_loc(double tjd_start, int ipl, String starname, int ifl, double[] geopos, double[] tret, double[] attr, int backward); // Changed char[] starname to String, removed char[] serr
    public static native int swe_sol_eclipse_when_glob(double tjd_start, int ifl, int ifltype, double[] tret, int backward); // Removed char[] serr
    public static native int swe_lun_occult_when_glob(double tjd_start, int ipl, String starname, int ifl, int ifltype, double[] tret, int backward); // Changed char[] starname to String, removed char[] serr
    public static native int swe_lun_eclipse_how(double tjd_ut, int ifl, double[] geopos, double[] attr); // Removed char[] serr
    public static native int swe_lun_eclipse_when(double tjd_start, int ifl, int ifltype, double[] tret, int backward); // Removed char[] serr
    public static native int swe_lun_eclipse_when_loc(double tjd_start, int ifl, double[] geopos, double[] tret, double[] attr, int backward); // Removed char[] serr
    public static native int swe_heliacal_ut(double tjdstart_ut, double[] geopos, double[] datm, double[] dobs, String ObjectName, int TypeEvent, int iflag, double[] dret); // Changed char[] ObjectName to String, removed char[] serr
    public static native int swe_heliacal_pheno_ut(double tjd_ut, double[] geopos, double[] datm, double[] dobs, String ObjectName, int TypeEvent, int helflag, double[] darr); // Changed char[] ObjectName to String, removed char[] serr
    public static native int swe_vis_limit_mag(double tjdut, double[] geopos, double[] datm, double[] dobs, String ObjectName, int helflag, double[] dret); // Changed char[] ObjectName to String, removed char[] serr
    public static native int swe_heliacal_angle(double tjdut, double[] dgeo, double[] datm, double[] dobs, int helflag, double mag, double azi_obj, double azi_sun, double azi_moon, double alt_moon, double[] dret); // Removed char[] serr
    public static native int swe_topo_arcus_visionis(double tjdut, double[] dgeo, double[] datm, double[] dobs, int helflag, double mag, double azi_obj, double alt_obj, double azi_sun, double azi_moon, double alt_moon, double[] dret); // Removed char[] serr
    public static native void swe_set_astro_models(String samod, int iflag); // Changed char[] samod to String
    public static native String swe_get_astro_models(String samod, int iflag); // Changed to return String, removed sdet parameter

    public static native int swe_date_conversion(int y, int m, int d, double utime, char c, double[] tjd);
    public static native double swe_julday(int year, int month, int day, double hour, int gregflag);
    public static native void swe_revjul(double jd, int gregflag, int[] jyear, int[] jmon, int[] jday, double[] jut);
    public static native int swe_utc_to_jd(int iyear, int imonth, int iday, int ihour, int imin, double dsec, int gregflag, double[] dret); // Removed char[] serr
    public static native void swe_jdet_to_utc(double tjd_et, int gregflag, int[] iyear, int[] imonth, int[] iday, int[] ihour, int[] imin, double[] dsec);
    public static native void swe_jdut1_to_utc(double tjd_ut, int gregflag, int[] iyear, int[] imonth, int[] iday, int[] ihour, int[] imin, double[] dsec);
    public static native void swe_utc_time_zone(int iyear, int imonth, int iday, int ihour, int imin, double dsec, double d_timezone, int[] iyear_out, int[] imonth_out, int[] iday_out, int[] ihour_out, int[] imin_out, double[] dsec_out);

    public static native double swe_deltat(double tjd);
    public static native double swe_deltat_ex(double tjd); // Removed char[] serr
    public static native int swe_time_equ(double tjd, double[] te); // Removed char[] serr
    public static native int swe_lmt_to_lat(double tjd_lmt, double geolon, double[] tjd_lat); // Removed char[] serr
    public static native int swe_lat_to_lmt(double tjd_lat, double geolon, double[] tjd_lmt); // Removed char[] serr
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
    public static native String swe_cs2timestr(int t, int sep, boolean suppressZero); // Removed char[] a
    public static native String swe_cs2lonlatstr(int t, char pchar, char mchar); // Removed char[] s
    public static native String swe_cs2degstr(int t); // Removed char[] a

    public static native int swe_houses(double tjd_ut, double geolat, double geolon, int hsys, double[] cusps, double[] ascmc);
    public static native int swe_houses_ex(double tjd_ut, int iflag, double geolat, double geolon, int hsys, double[] cusps, double[] ascmc);
    public static native int swe_houses_ex2(double tjd_ut, int iflag, double geolat, double geolon, int hsys, double[] cusps, double[] ascmc, double[] cusp_speed, double[] ascmc_speed); // Removed char[] serr
    public static native int swe_houses_armc(double armc, double geolat, double eps, int hsys, double[] cusps, double[] ascmc);
    public static native int swe_houses_armc_ex2(double armc, double geolat, double eps, int hsys, double[] cusps, double[] ascmc, double[] cusp_speed, double[] ascmc_speed); // Removed char[] serr
    public static native double swe_house_pos(double armc, double geolat, double eps, int hsys, double[] xpin); // Removed char[] serr
    public static native String swe_house_name(int hsys);

    public static native String swe_version();

    static {
        System.loadLibrary("swisseph");
    }
}
