#include <jni.h>
#include "sweph.h"
#include "swephexp.h"
#include "sweodef.h" // For AS_MAXCH
#include <string.h>  // For strcpy, strlen, strncpy

static char global_serr_buf[AS_MAXCH + 1]; // Global buffer for error messages

// JNI function to retrieve the last error message
JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_getLastError
  (JNIEnv *env, jclass cls) {
    if (strlen(global_serr_buf) > 0) {
        jstring error_str = (*env)->NewStringUTF(env, global_serr_buf);
        global_serr_buf[0] = '\0'; // Clear the buffer after reading
        return error_str;
    }
    return NULL;
}

JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1version
  (JNIEnv *env, jclass cls) {
    return (*env)->NewStringUTF(env, SE_VERSION);
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1calc_1ut
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint ipl, jint iflag, jdoubleArray xx) {
    jdouble *xx_arr = (*env)->GetDoubleArrayElements(env, xx, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_calc_ut(tjd_ut, ipl, iflag, xx_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, xx, xx_arr, 0);

    return ret;
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1close
  (JNIEnv *env, jclass cls) {
    swe_close();
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1set_1ephe_1path
  (JNIEnv *env, jclass cls, jstring path) {
    const char *cpath = (*env)->GetStringUTFChars(env, path, 0);
    swe_set_ephe_path(cpath);
    (*env)->ReleaseStringUTFChars(env, path, cpath);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1set_1jpl_1file
  (JNIEnv *env, jclass cls, jstring fname) {
    const char *cfname = (*env)->GetStringUTFChars(env, fname, 0);
    swe_set_jpl_file(cfname);
    (*env)->ReleaseStringUTFChars(env, fname, cfname);
}

JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1get_1planet_1name
  (JNIEnv *env, jclass cls, jint ipl) {
    char planet_name_buf[AS_MAXCH + 1];
    planet_name_buf[0] = '\0';
    swe_get_planet_name(ipl, planet_name_buf);
    return (*env)->NewStringUTF(env, planet_name_buf);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1set_1topo
  (JNIEnv *env, jclass cls, jdouble geolon, jdouble geolat, jdouble geoalt) {
    swe_set_topo(geolon, geolat, geoalt);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1set_1sid_1mode
  (JNIEnv *env, jclass cls, jint sid_mode, jdouble t0, jdouble ayan_t0) {
    swe_set_sid_mode(sid_mode, t0, ayan_t0);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1get_1ayanamsa
  (JNIEnv *env, jclass cls, jdouble tjd_et) {
    return swe_get_ayanamsa(tjd_et);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1get_1ayanamsa_1ut
  (JNIEnv *env, jclass cls, jdouble tjd_ut) {
    return swe_get_ayanamsa_ut(tjd_ut);
}

JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1get_1ayanamsa_1name
  (JNIEnv *env, jclass cls, jint isidmode) {
    const char *ret = swe_get_ayanamsa_name(isidmode);
    return (*env)->NewStringUTF(env, ret);
}

JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1get_1current_1file_1data
  (JNIEnv *env, jclass cls, jint ifno, jdoubleArray tfstart, jdoubleArray tfend, jintArray denum) {
    jdouble *tfstart_arr = (*env)->GetDoubleArrayElements(env, tfstart, 0);
    jdouble *tfend_arr = (*env)->GetDoubleArrayElements(env, tfend, 0);
    jint *denum_arr = (*env)->GetIntArrayElements(env, denum, 0);
    const char *ret = swe_get_current_file_data(ifno, tfstart_arr, tfend_arr, denum_arr);
    (*env)->ReleaseDoubleArrayElements(env, tfstart, tfstart_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, tfend, tfend_arr, 0);
    (*env)->ReleaseIntArrayElements(env, denum, denum_arr, 0);
    return (*env)->NewStringUTF(env, ret);
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1fixstar
  (JNIEnv *env, jclass cls, jstring star, jdouble tjd, jint iflag, jdoubleArray xx) {
    const char *star_c = (star == NULL) ? "" : (*env)->GetStringUTFChars(env, star, 0);
    jdouble *xx_arr = (*env)->GetDoubleArrayElements(env, xx, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_fixstar((char *)star_c, tjd, iflag, xx_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (star != NULL) (*env)->ReleaseStringUTFChars(env, star, star_c);
    (*env)->ReleaseDoubleArrayElements(env, xx, xx_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1fixstar_1ut
  (JNIEnv *env, jclass cls, jstring star, jdouble tjd_ut, jint iflag, jdoubleArray xx) {
    const char *star_c = (star == NULL) ? "" : (*env)->GetStringUTFChars(env, star, 0);
    jdouble *xx_arr = (*env)->GetDoubleArrayElements(env, xx, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_fixstar_ut((char *)star_c, tjd_ut, iflag, xx_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (star != NULL) (*env)->ReleaseStringUTFChars(env, star, star_c);
    (*env)->ReleaseDoubleArrayElements(env, xx, xx_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1fixstar_1mag
  (JNIEnv *env, jclass cls, jstring star, jdoubleArray mag) {
    const char *star_c = (star == NULL) ? "" : (*env)->GetStringUTFChars(env, star, 0);
    jdouble *mag_arr = (*env)->GetDoubleArrayElements(env, mag, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_fixstar_mag((char *)star_c, mag_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (star != NULL) (*env)->ReleaseStringUTFChars(env, star, star_c);
    (*env)->ReleaseDoubleArrayElements(env, mag, mag_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1fixstar2
  (JNIEnv *env, jclass cls, jstring star, jdouble tjd, jint iflag, jdoubleArray xx) {
    const char *star_c = (star == NULL) ? "" : (*env)->GetStringUTFChars(env, star, 0);
    jdouble *xx_arr = (*env)->GetDoubleArrayElements(env, xx, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_fixstar2((char *)star_c, tjd, iflag, xx_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (star != NULL) (*env)->ReleaseStringUTFChars(env, star, star_c);
    (*env)->ReleaseDoubleArrayElements(env, xx, xx_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1fixstar2_1ut
  (JNIEnv *env, jclass cls, jstring star, jdouble tjd_ut, jint iflag, jdoubleArray xx) {
    const char *star_c = (star == NULL) ? "" : (*env)->GetStringUTFChars(env, star, 0);
    jdouble *xx_arr = (*env)->GetDoubleArrayElements(env, xx, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_fixstar2_ut((char *)star_c, tjd_ut, iflag, xx_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (star != NULL) (*env)->ReleaseStringUTFChars(env, star, star_c);
    (*env)->ReleaseDoubleArrayElements(env, xx, xx_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1fixstar2_1mag
  (JNIEnv *env, jclass cls, jstring star, jdoubleArray mag) {
    const char *star_c = (star == NULL) ? "" : (*env)->GetStringUTFChars(env, star, 0);
    jdouble *mag_arr = (*env)->GetDoubleArrayElements(env, mag, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_fixstar2_mag((char *)star_c, mag_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (star != NULL) (*env)->ReleaseStringUTFChars(env, star, star_c);
    (*env)->ReleaseDoubleArrayElements(env, mag, mag_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1calc_1pctr
  (JNIEnv *env, jclass cls, jdouble tjd, jint ipl, jint iplctr, jint iflag, jdoubleArray xxret) {
    jdouble *xxret_arr = (*env)->GetDoubleArrayElements(env, xxret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_calc_pctr(tjd, ipl, iplctr, iflag, xxret_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, xxret, xxret_arr, 0);

    return ret;
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1solcross
  (JNIEnv *env, jclass cls, jdouble x2cross, jdouble jd_et, jint flag) {
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';
    double ret = swe_solcross(x2cross, jd_et, flag, serr_buf);
    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }
    return ret;
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1solcross_1ut
  (JNIEnv *env, jclass cls, jdouble x2cross, jdouble jd_ut, jint flag) {
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';
    double ret = swe_solcross_ut(x2cross, jd_ut, flag, serr_buf);
    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }
    return ret;
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1mooncross
  (JNIEnv *env, jclass cls, jdouble x2cross, jdouble jd_et, jint flag) {
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';
    double ret = swe_mooncross(x2cross, jd_et, flag, serr_buf);
    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }
    return ret;
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1mooncross_1ut
  (JNIEnv *env, jclass cls, jdouble x2cross, jdouble jd_ut, jint flag) {
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';
    double ret = swe_mooncross_ut(x2cross, jd_ut, flag, serr_buf);
    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }
    return ret;
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1mooncross_1node
  (JNIEnv *env, jclass cls, jdouble jd_et, jint flag, jdoubleArray xlon, jdoubleArray xlat) {
    jdouble *xlon_arr = (*env)->GetDoubleArrayElements(env, xlon, 0);
    jdouble *xlat_arr = (*env)->GetDoubleArrayElements(env, xlat, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    double ret = swe_mooncross_node(jd_et, flag, xlon_arr, xlat_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, xlon, xlon_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xlat, xlat_arr, 0);

    return ret;
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1mooncross_1node_1ut
  (JNIEnv *env, jclass cls, jdouble jd_ut, jint flag, jdoubleArray xlon, jdoubleArray xlat) {
    jdouble *xlon_arr = (*env)->GetDoubleArrayElements(env, xlon, 0);
    jdouble *xlat_arr = (*env)->GetDoubleArrayElements(env, xlat, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    double ret = swe_mooncross_node_ut(jd_ut, flag, xlon_arr, xlat_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, xlon, xlon_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xlat, xlat_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1helio_1cross
  (JNIEnv *env, jclass cls, jint ipl, jdouble x2cross, jdouble jd_et, jint iflag, jint dir, jdoubleArray jd_cross) {
    jdouble *jd_cross_arr = (*env)->GetDoubleArrayElements(env, jd_cross, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_helio_cross(ipl, x2cross, jd_et, iflag, dir, jd_cross_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, jd_cross, jd_cross_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1helio_1cross_1ut
  (JNIEnv *env, jclass cls, jint ipl, jdouble x2cross, jdouble jd_ut, jint iflag, jint dir, jdoubleArray jd_cross) {
    jdouble *jd_cross_arr = (*env)->GetDoubleArrayElements(env, jd_cross, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_helio_cross_ut(ipl, x2cross, jd_ut, iflag, dir, jd_cross_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, jd_cross, jd_cross_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1nod_1aps
  (JNIEnv *env, jclass cls, jdouble tjd_et, jint ipl, jint iflag, jint method, jdoubleArray xnasc, jdoubleArray xndsc, jdoubleArray xperi, jdoubleArray xaphe) {
    jdouble *xnasc_arr = (*env)->GetDoubleArrayElements(env, xnasc, 0);
    jdouble *xndsc_arr = (*env)->GetDoubleArrayElements(env, xndsc, 0);
    jdouble *xperi_arr = (*env)->GetDoubleArrayElements(env, xperi, 0);
    jdouble *xaphe_arr = (*env)->GetDoubleArrayElements(env, xaphe, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_nod_aps(tjd_et, ipl, iflag, method, xnasc_arr, xndsc_arr, xperi_arr, xaphe_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, xnasc, xnasc_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xndsc, xndsc_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xperi, xperi_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xaphe, xaphe_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1nod_1aps_1ut
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint ipl, jint iflag, jint method, jdoubleArray xnasc, jdoubleArray xndsc, jdoubleArray xperi, jdoubleArray xaphe) {
    jdouble *xnasc_arr = (*env)->GetDoubleArrayElements(env, xnasc, 0);
    jdouble *xndsc_arr = (*env)->GetDoubleArrayElements(env, xndsc, 0);
    jdouble *xperi_arr = (*env)->GetDoubleArrayElements(env, xperi, 0);
    jdouble *xaphe_arr = (*env)->GetDoubleArrayElements(env, xaphe, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_nod_aps_ut(tjd_ut, ipl, iflag, method, xnasc_arr, xndsc_arr, xperi_arr, xaphe_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, xnasc, xnasc_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xndsc, xndsc_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xperi, xperi_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xaphe, xaphe_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1get_1orbital_1elements
  (JNIEnv *env, jclass cls, jdouble tjd_et, jint ipl, jint iflag, jdoubleArray dret) {
    jdouble *dret_arr = (*env)->GetDoubleArrayElements(env, dret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_get_orbital_elements(tjd_et, ipl, iflag, dret_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, dret, dret_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1orbit_1max_1min_1true_1distance
  (JNIEnv *env, jclass cls, jdouble tjd_et, jint ipl, jint iflag, jdoubleArray dmax, jdoubleArray dmin, jdoubleArray dtrue) {
    jdouble *dmax_arr = (*env)->GetDoubleArrayElements(env, dmax, 0);
    jdouble *dmin_arr = (*env)->GetDoubleArrayElements(env, dmin, 0);
    jdouble *dtrue_arr = (*env)->GetDoubleArrayElements(env, dtrue, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_orbit_max_min_true_distance(tjd_et, ipl, iflag, dmax_arr, dmin_arr, dtrue_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, dmax, dmax_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dmin, dmin_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dtrue, dtrue_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1pheno
  (JNIEnv *env, jclass cls, jdouble tjd, jint ipl, jint iflag, jdoubleArray attr) {
    jdouble *attr_arr = (*env)->GetDoubleArrayElements(env, attr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_pheno(tjd, ipl, iflag, attr_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, attr, attr_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1pheno_1ut
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint ipl, jint iflag, jdoubleArray attr) {
    jdouble *attr_arr = (*env)->GetDoubleArrayElements(env, attr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_pheno_ut(tjd_ut, ipl, iflag, attr_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, attr, attr_arr, 0);

    return ret;
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1azalt
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint calc_flag, jdoubleArray geopos, jdouble atpress, jdouble attemp, jdoubleArray xin, jdoubleArray xaz) {
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *xin_arr = (*env)->GetDoubleArrayElements(env, xin, 0);
    jdouble *xaz_arr = (*env)->GetDoubleArrayElements(env, xaz, 0);
    swe_azalt(tjd_ut, calc_flag, geopos_arr, atpress, attemp, xin_arr, xaz_arr);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xin, xin_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xaz, xaz_arr, 0);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1azalt_1rev
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint calc_flag, jdoubleArray geopos, jdoubleArray xin, jdoubleArray xout) {
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *xin_arr = (*env)->GetDoubleArrayElements(env, xin, 0);
    jdouble *xout_arr = (*env)->GetDoubleArrayElements(env, xout, 0);
    swe_azalt_rev(tjd_ut, calc_flag, geopos_arr, xin_arr, xout_arr);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xin, xin_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xout, xout_arr, 0);
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1rise_1trans_1true_1hor
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint ipl, jstring starname, jint epheflag, jint rsmi, jdoubleArray geopos, jdouble atpress, jdouble attemp, jdouble horhgt, jdoubleArray tret) {
    const char *starname_c = (starname == NULL) ? "" : (*env)->GetStringUTFChars(env, starname, 0);
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *tret_arr = (*env)->GetDoubleArrayElements(env, tret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_rise_trans_true_hor(tjd_ut, ipl, (char *)starname_c, epheflag, rsmi, geopos_arr, atpress, attemp, horhgt, tret_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (starname != NULL) (*env)->ReleaseStringUTFChars(env, starname, starname_c);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, tret, tret_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1rise_1trans
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint ipl, jstring starname, jint epheflag, jint rsmi, jdoubleArray geopos, jdouble atpress, jdouble attemp, jdoubleArray tret) {
    const char *starname_c = (starname == NULL) ? "" : (*env)->GetStringUTFChars(env, starname, 0);
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *tret_arr = (*env)->GetDoubleArrayElements(env, tret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_rise_trans(tjd_ut, ipl, (char *)starname_c, epheflag, rsmi, geopos_arr, atpress, attemp, tret_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (starname != NULL) (*env)->ReleaseStringUTFChars(env, starname, starname_c);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, tret, tret_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1gauquelin_1sector
  (JNIEnv *env, jclass cls, jdouble t_ut, jint ipl, jstring starname, jint iflag, jint imeth, jdoubleArray geopos, jdouble atpress, jdouble attemp, jdoubleArray dgsect) {
    const char *starname_c = (starname == NULL) ? "" : (*env)->GetStringUTFChars(env, starname, 0);
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *dgsect_arr = (*env)->GetDoubleArrayElements(env, dgsect, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_gauquelin_sector(t_ut, ipl, (char *)starname_c, iflag, imeth, geopos_arr, atpress, attemp, dgsect_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (starname != NULL) (*env)->ReleaseStringUTFChars(env, starname, starname_c);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dgsect, dgsect_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1sol_1eclipse_1where
  (JNIEnv *env, jclass cls, jdouble tjd, jint ifl, jdoubleArray geopos, jdoubleArray attr) {
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *attr_arr = (*env)->GetDoubleArrayElements(env, attr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_sol_eclipse_where(tjd, ifl, geopos_arr, attr_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, attr, attr_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1lun_1occult_1where
  (JNIEnv *env, jclass cls, jdouble tjd, jint ipl, jstring starname, jint ifl, jdoubleArray geopos, jdoubleArray attr) {
    const char *starname_c = (starname == NULL) ? "" : (*env)->GetStringUTFChars(env, starname, 0);
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *attr_arr = (*env)->GetDoubleArrayElements(env, attr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_lun_occult_where(tjd, ipl, (char *)starname_c, ifl, geopos_arr, attr_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (starname != NULL) (*env)->ReleaseStringUTFChars(env, starname, starname_c);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, attr, attr_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1sol_1eclipse_1how
  (JNIEnv *env, jclass cls, jdouble tjd, jint ifl, jdoubleArray geopos, jdoubleArray attr) {
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *attr_arr = (*env)->GetDoubleArrayElements(env, attr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_sol_eclipse_how(tjd, ifl, geopos_arr, attr_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, attr, attr_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1sol_1eclipse_1when_1loc
  (JNIEnv *env, jclass cls, jdouble tjd_start, jint ifl, jdoubleArray geopos, jdoubleArray tret, jdoubleArray attr, jint backward) {
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *tret_arr = (*env)->GetDoubleArrayElements(env, tret, 0);
    jdouble *attr_arr = (*env)->GetDoubleArrayElements(env, attr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_sol_eclipse_when_loc(tjd_start, ifl, geopos_arr, tret_arr, attr_arr, backward, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, tret, tret_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, attr, attr_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1lun_1occult_1when_1loc
  (JNIEnv *env, jclass cls, jdouble tjd_start, jint ipl, jstring starname, jint ifl, jdoubleArray geopos, jdoubleArray tret, jdoubleArray attr, jint backward) {
    const char *starname_c = (starname == NULL) ? "" : (*env)->GetStringUTFChars(env, starname, 0);
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *tret_arr = (*env)->GetDoubleArrayElements(env, tret, 0);
    jdouble *attr_arr = (*env)->GetDoubleArrayElements(env, attr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_lun_occult_when_loc(tjd_start, ipl, (char *)starname_c, ifl, geopos_arr, tret_arr, attr_arr, backward, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (starname != NULL) (*env)->ReleaseStringUTFChars(env, starname, starname_c);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, tret, tret_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, attr, attr_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1sol_1eclipse_1when_1glob
  (JNIEnv *env, jclass cls, jdouble tjd_start, jint ifl, jint ifltype, jdoubleArray tret, jint backward) {
    jdouble *tret_arr = (*env)->GetDoubleArrayElements(env, tret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_sol_eclipse_when_glob(tjd_start, ifl, ifltype, tret_arr, backward, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, tret, tret_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1lun_1occult_1when_1glob
  (JNIEnv *env, jclass cls, jdouble tjd_start, jint ipl, jstring starname, jint ifl, jint ifltype, jdoubleArray tret, jint backward) {
    const char *starname_c = (starname == NULL) ? "" : (*env)->GetStringUTFChars(env, starname, 0);
    jdouble *tret_arr = (*env)->GetDoubleArrayElements(env, tret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_lun_occult_when_glob(tjd_start, ipl, (char *)starname_c, ifl, ifltype, tret_arr, backward, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (starname != NULL) (*env)->ReleaseStringUTFChars(env, starname, starname_c);
    (*env)->ReleaseDoubleArrayElements(env, tret, tret_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1lun_1eclipse_1how
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint ifl, jdoubleArray geopos, jdoubleArray attr) {
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *attr_arr = (*env)->GetDoubleArrayElements(env, attr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_lun_eclipse_how(tjd_ut, ifl, geopos_arr, attr_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, attr, attr_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1lun_1eclipse_1when
  (JNIEnv *env, jclass cls, jdouble tjd_start, jint ifl, jint ifltype, jdoubleArray tret, jint backward) {
    jdouble *tret_arr = (*env)->GetDoubleArrayElements(env, tret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_lun_eclipse_when(tjd_start, ifl, ifltype, tret_arr, backward, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, tret, tret_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1lun_1eclipse_1when_1loc
  (JNIEnv *env, jclass cls, jdouble tjd_start, jint ifl, jdoubleArray geopos, jdoubleArray tret, jdoubleArray attr, jint backward) {
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *tret_arr = (*env)->GetDoubleArrayElements(env, tret, 0);
    jdouble *attr_arr = (*env)->GetDoubleArrayElements(env, attr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_lun_eclipse_when_loc(tjd_start, ifl, geopos_arr, tret_arr, attr_arr, backward, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, tret, tret_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, attr, attr_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1heliacal_1ut
  (JNIEnv *env, jclass cls, jdouble tjdstart_ut, jdoubleArray geopos, jdoubleArray datm, jdoubleArray dobs, jstring ObjectName, jint TypeEvent, jint iflag, jdoubleArray dret) {
    const char *ObjectName_c = (ObjectName == NULL) ? "" : (*env)->GetStringUTFChars(env, ObjectName, 0);
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *datm_arr = (*env)->GetDoubleArrayElements(env, datm, 0);
    jdouble *dobs_arr = (*env)->GetDoubleArrayElements(env, dobs, 0);
    jdouble *dret_arr = (*env)->GetDoubleArrayElements(env, dret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_heliacal_ut(tjdstart_ut, geopos_arr, datm_arr, dobs_arr, (char *)ObjectName_c, TypeEvent, iflag, dret_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (ObjectName != NULL) (*env)->ReleaseStringUTFChars(env, ObjectName, ObjectName_c);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, datm, datm_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dobs, dobs_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dret, dret_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1heliacal_1pheno_1ut
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jdoubleArray geopos, jdoubleArray datm, jdoubleArray dobs, jstring ObjectName, jint TypeEvent, jint helflag, jdoubleArray darr) {
    const char *ObjectName_c = (ObjectName == NULL) ? "" : (*env)->GetStringUTFChars(env, ObjectName, 0);
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *datm_arr = (*env)->GetDoubleArrayElements(env, datm, 0);
    jdouble *dobs_arr = (*env)->GetDoubleArrayElements(env, dobs, 0);
    jdouble *darr_arr = (*env)->GetDoubleArrayElements(env, darr, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_heliacal_pheno_ut(tjd_ut, geopos_arr, datm_arr, dobs_arr, (char *)ObjectName_c, TypeEvent, helflag, darr_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (ObjectName != NULL) (*env)->ReleaseStringUTFChars(env, ObjectName, ObjectName_c);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, datm, datm_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dobs, dobs_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, darr, darr_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1vis_1limit_1mag
  (JNIEnv *env, jclass cls, jdouble tjdut, jdoubleArray geopos, jdoubleArray datm, jdoubleArray dobs, jstring ObjectName, jint helflag, jdoubleArray dret) {
    const char *ObjectName_c = (ObjectName == NULL) ? "" : (*env)->GetStringUTFChars(env, ObjectName, 0);
    jdouble *geopos_arr = (*env)->GetDoubleArrayElements(env, geopos, 0);
    jdouble *datm_arr = (*env)->GetDoubleArrayElements(env, datm, 0);
    jdouble *dobs_arr = (*env)->GetDoubleArrayElements(env, dobs, 0);
    jdouble *dret_arr = (*env)->GetDoubleArrayElements(env, dret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_vis_limit_mag(tjdut, geopos_arr, datm_arr, dobs_arr, (char *)ObjectName_c, helflag, dret_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    if (ObjectName != NULL) (*env)->ReleaseStringUTFChars(env, ObjectName, ObjectName_c);
    (*env)->ReleaseDoubleArrayElements(env, geopos, geopos_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, datm, datm_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dobs, dobs_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dret, dret_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1heliacal_1angle
  (JNIEnv *env, jclass cls, jdouble tjdut, jdoubleArray dgeo, jdoubleArray datm, jdoubleArray dobs, jint helflag, jdouble mag, jdouble azi_obj, jdouble azi_sun, jdouble azi_moon, jdouble alt_moon, jdoubleArray dret) {
    jdouble *dgeo_arr = (*env)->GetDoubleArrayElements(env, dgeo, 0);
    jdouble *datm_arr = (*env)->GetDoubleArrayElements(env, datm, 0);
    jdouble *dobs_arr = (*env)->GetDoubleArrayElements(env, dobs, 0);
    jdouble *dret_arr = (*env)->GetDoubleArrayElements(env, dret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_heliacal_angle(tjdut, dgeo_arr, datm_arr, dobs_arr, helflag, mag, azi_obj, azi_sun, azi_moon, alt_moon, dret_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, dgeo, dgeo_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, datm, datm_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dobs, dobs_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dret, dret_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1topo_1arcus_1visionis
  (JNIEnv *env, jclass cls, jdouble tjdut, jdoubleArray dgeo, jdoubleArray datm, jdoubleArray dobs, jint helflag, jdouble mag, jdouble azi_obj, jdouble alt_obj, jdouble azi_sun, jdouble azi_moon, jdouble alt_moon, jdoubleArray dret) {
    jdouble *dgeo_arr = (*env)->GetDoubleArrayElements(env, dgeo, 0);
    jdouble *datm_arr = (*env)->GetDoubleArrayElements(env, datm, 0);
    jdouble *dobs_arr = (*env)->GetDoubleArrayElements(env, dobs, 0);
    jdouble *dret_arr = (*env)->GetDoubleArrayElements(env, dret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_topo_arcus_visionis(tjdut, dgeo_arr, datm_arr, dobs_arr, helflag, mag, azi_obj, alt_obj, azi_sun, azi_moon, alt_moon, dret_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, dgeo, dgeo_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, datm, datm_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dobs, dobs_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dret, dret_arr, 0);

    return ret;
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1set_1astro_1models
  (JNIEnv *env, jclass cls, jstring samod) {
    const char *samod_c = (samod == NULL) ? "" : (*env)->GetStringUTFChars(env, samod, 0);
    swe_set_astro_models((char *)samod_c, 0); // iflag is not used in the C function, assuming 0
    if (samod != NULL) (*env)->ReleaseStringUTFChars(env, samod, samod_c);
}

JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1get_1astro_1models
  (JNIEnv *env, jclass cls, jstring samod, jint iflag) {
    const char *samod_c = (samod == NULL) ? "" : (*env)->GetStringUTFChars(env, samod, 0);
    char sdet_buf[AS_MAXCH + 1];
    sdet_buf[0] = '\0';

    swe_get_astro_models((char *)samod_c, sdet_buf, iflag);

    if (samod != NULL) (*env)->ReleaseStringUTFChars(env, samod, samod_c);

    return (*env)->NewStringUTF(env, sdet_buf);
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1date_1conversion
  (JNIEnv *env, jclass cls, jint y, jint m, jint d, jdouble utime, jchar c, jdoubleArray tjd) {
    jdouble *tjd_arr = (*env)->GetDoubleArrayElements(env, tjd, 0);
    int ret = swe_date_conversion(y, m, d, utime, c, tjd_arr);
    (*env)->ReleaseDoubleArrayElements(env, tjd, tjd_arr, 0);
    return ret;
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1julday
  (JNIEnv *env, jclass cls, jint year, jint month, jint day, jdouble hour, jint gregflag) {
    return swe_julday(year, month, day, hour, gregflag);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1revjul
  (JNIEnv *env, jclass cls, jdouble jd, jint gregflag, jintArray jyear, jintArray jmon, jintArray jday, jdoubleArray jut) {
    jint *jyear_arr = (*env)->GetIntArrayElements(env, jyear, 0);
    jint *jmon_arr = (*env)->GetIntArrayElements(env, jmon, 0);
    jint *jday_arr = (*env)->GetIntArrayElements(env, jday, 0);
    jdouble *jut_arr = (*env)->GetDoubleArrayElements(env, jut, 0);
    swe_revjul(jd, gregflag, jyear_arr, jmon_arr, jday_arr, jut_arr);
    (*env)->ReleaseIntArrayElements(env, jyear, jyear_arr, 0);
    (*env)->ReleaseIntArrayElements(env, jmon, jmon_arr, 0);
    (*env)->ReleaseIntArrayElements(env, jday, jday_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, jut, jut_arr, 0);
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1utc_1to_1jd
  (JNIEnv *env, jclass cls, jint iyear, jint imonth, jint iday, jint ihour, jint imin, jdouble dsec, jint gregflag, jdoubleArray dret) {
    jdouble *dret_arr = (*env)->GetDoubleArrayElements(env, dret, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_utc_to_jd(iyear, imonth, iday, ihour, imin, dsec, gregflag, dret_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, dret, dret_arr, 0);

    return ret;
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1jdet_1to_1utc
  (JNIEnv *env, jclass cls, jdouble tjd_et, jint gregflag, jintArray iyear, jintArray imonth, jintArray iday, jintArray ihour, jintArray imin, jdoubleArray dsec) {
    jint *iyear_arr = (*env)->GetIntArrayElements(env, iyear, 0);
    jint *imonth_arr = (*env)->GetIntArrayElements(env, imonth, 0);
    jint *iday_arr = (*env)->GetIntArrayElements(env, iday, 0);
    jint *ihour_arr = (*env)->GetIntArrayElements(env, ihour, 0);
    jint *imin_arr = (*env)->GetIntArrayElements(env, imin, 0);
    jdouble *dsec_arr = (*env)->GetDoubleArrayElements(env, dsec, 0);
    swe_jdet_to_utc(tjd_et, gregflag, iyear_arr, imonth_arr, iday_arr, ihour_arr, imin_arr, dsec_arr);
    (*env)->ReleaseIntArrayElements(env, iyear, iyear_arr, 0);
    (*env)->ReleaseIntArrayElements(env, imonth, imonth_arr, 0);
    (*env)->ReleaseIntArrayElements(env, iday, iday_arr, 0);
    (*env)->ReleaseIntArrayElements(env, ihour, ihour_arr, 0);
    (*env)->ReleaseIntArrayElements(env, imin, imin_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dsec, dsec_arr, 0);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1jdut1_1to_1utc
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint gregflag, jintArray iyear, jintArray imonth, jintArray iday, jintArray ihour, jintArray imin, jdoubleArray dsec) {
    jint *iyear_arr = (*env)->GetIntArrayElements(env, iyear, 0);
    jint *imonth_arr = (*env)->GetIntArrayElements(env, imonth, 0);
    jint *iday_arr = (*env)->GetIntArrayElements(env, iday, 0);
    jint *ihour_arr = (*env)->GetIntArrayElements(env, ihour, 0);
    jint *imin_arr = (*env)->GetIntArrayElements(env, imin, 0);
    jdouble *dsec_arr = (*env)->GetDoubleArrayElements(env, dsec, 0);
    swe_jdut1_to_utc(tjd_ut, gregflag, iyear_arr, imonth_arr, iday_arr, ihour_arr, imin_arr, dsec_arr);
    (*env)->ReleaseIntArrayElements(env, iyear, iyear_arr, 0);
    (*env)->ReleaseIntArrayElements(env, imonth, imonth_arr, 0);
    (*env)->ReleaseIntArrayElements(env, iday, iday_arr, 0);
    (*env)->ReleaseIntArrayElements(env, ihour, ihour_arr, 0);
    (*env)->ReleaseIntArrayElements(env, imin, imin_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dsec, dsec_arr, 0);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1utc_1time_1zone
  (JNIEnv *env, jclass cls, jint iyear, jint imonth, jint iday, jint ihour, jint imin, jdouble dsec, jdouble d_timezone, jintArray iyear_out, jintArray imonth_out, jintArray iday_out, jintArray ihour_out, jintArray imin_out, jdoubleArray dsec_out) {
    jint *iyear_out_arr = (*env)->GetIntArrayElements(env, iyear_out, 0);
    jint *imonth_out_arr = (*env)->GetIntArrayElements(env, imonth_out, 0);
    jint *iday_out_arr = (*env)->GetIntArrayElements(env, iday_out, 0);
    jint *ihour_out_arr = (*env)->GetIntArrayElements(env, ihour_out, 0);
    jint *imin_out_arr = (*env)->GetIntArrayElements(env, imin_out, 0);
    jdouble *dsec_out_arr = (*env)->GetDoubleArrayElements(env, dsec_out, 0);
    swe_utc_time_zone(iyear, imonth, iday, ihour, imin, dsec, d_timezone, iyear_out_arr, imonth_out_arr, iday_out_arr, ihour_out_arr, imin_out_arr, dsec_out_arr);
    (*env)->ReleaseIntArrayElements(env, iyear_out, iyear_out_arr, 0);
    (*env)->ReleaseIntArrayElements(env, imonth_out, imonth_out_arr, 0);
    (*env)->ReleaseIntArrayElements(env, iday_out, iday_out_arr, 0);
    (*env)->ReleaseIntArrayElements(env, ihour_out, ihour_out_arr, 0);
    (*env)->ReleaseIntArrayElements(env, imin_out, imin_out_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dsec_out, dsec_out_arr, 0);
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1houses
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jdouble geolat, jdouble geolon, jint hsys, jdoubleArray cusps, jdoubleArray ascmc) {
    jdouble *cusps_arr = (*env)->GetDoubleArrayElements(env, cusps, 0);
    jdouble *ascmc_arr = (*env)->GetDoubleArrayElements(env, ascmc, 0);
    int ret = swe_houses(tjd_ut, geolat, geolon, hsys, cusps_arr, ascmc_arr);
    (*env)->ReleaseDoubleArrayElements(env, cusps, cusps_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, ascmc, ascmc_arr, 0);
    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1houses_1ex
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint iflag, jdouble geolat, jdouble geolon, jint hsys, jdoubleArray cusps, jdoubleArray ascmc) {
    jdouble *cusps_arr = (*env)->GetDoubleArrayElements(env, cusps, 0);
    jdouble *ascmc_arr = (*env)->GetDoubleArrayElements(env, ascmc, 0);
    int ret = swe_houses_ex(tjd_ut, iflag, geolat, geolon, hsys, cusps_arr, ascmc_arr);
    (*env)->ReleaseDoubleArrayElements(env, cusps, cusps_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, ascmc, ascmc_arr, 0);
    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1houses_1ex2
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jint iflag, jdouble geolat, jdouble geolon, jint hsys, jdoubleArray cusps, jdoubleArray ascmc, jdoubleArray cusp_speed, jdoubleArray ascmc_speed) {
    jdouble *cusps_arr = (*env)->GetDoubleArrayElements(env, cusps, 0);
    jdouble *ascmc_arr = (*env)->GetDoubleArrayElements(env, ascmc, 0);
    jdouble *cusp_speed_arr = (*env)->GetDoubleArrayElements(env, cusp_speed, 0);
    jdouble *ascmc_speed_arr = (*env)->GetDoubleArrayElements(env, ascmc_speed, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_houses_ex2(tjd_ut, iflag, geolat, geolon, hsys, cusps_arr, ascmc_arr, cusp_speed_arr, ascmc_speed_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, cusps, cusps_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, ascmc, ascmc_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, cusp_speed, cusp_speed_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, ascmc_speed, ascmc_speed_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1houses_1armc
  (JNIEnv *env, jclass cls, jdouble armc, jdouble geolat, jdouble eps, jint hsys, jdoubleArray cusps, jdoubleArray ascmc) {
    jdouble *cusps_arr = (*env)->GetDoubleArrayElements(env, cusps, 0);
    jdouble *ascmc_arr = (*env)->GetDoubleArrayElements(env, ascmc, 0);
    int ret = swe_houses_armc(armc, geolat, eps, hsys, cusps_arr, ascmc_arr);
    (*env)->ReleaseDoubleArrayElements(env, cusps, cusps_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, ascmc, ascmc_arr, 0);
    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1houses_1armc_1ex2
  (JNIEnv *env, jclass cls, jdouble armc, jdouble geolat, jdouble eps, jint hsys, jdoubleArray cusps, jdoubleArray ascmc, jdoubleArray cusp_speed, jdoubleArray ascmc_speed) {
    jdouble *cusps_arr = (*env)->GetDoubleArrayElements(env, cusps, 0);
    jdouble *ascmc_arr = (*env)->GetDoubleArrayElements(env, ascmc, 0);
    jdouble *cusp_speed_arr = (*env)->GetDoubleArrayElements(env, cusp_speed, 0);
    jdouble *ascmc_speed_arr = (*env)->GetDoubleArrayElements(env, ascmc_speed, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_houses_armc_ex2(armc, geolat, eps, hsys, cusps_arr, ascmc_arr, cusp_speed_arr, ascmc_speed_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, cusps, cusps_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, ascmc, ascmc_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, cusp_speed, cusp_speed_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, ascmc_speed, ascmc_speed_arr, 0);

    return ret;
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1house_1pos
  (JNIEnv *env, jclass cls, jdouble armc, jdouble geolat, jdouble eps, jint hsys, jdoubleArray xpin) {
    jdouble *xpin_arr = (*env)->GetDoubleArrayElements(env, xpin, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    double ret = swe_house_pos(armc, geolat, eps, hsys, xpin_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, xpin, xpin_arr, 0);

    return ret;
}

JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1house_1name
  (JNIEnv *env, jclass cls, jint hsys) {
    const char *ret = swe_house_name(hsys);
    return (*env)->NewStringUTF(env, ret);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1deltat
  (JNIEnv *env, jclass cls, jdouble tjd) {
    return swe_deltat(tjd);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1deltat_1ex
  (JNIEnv *env, jclass cls, jdouble tjd) {
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';
    double ret = swe_deltat_ex(tjd, 0, serr_buf); // iflag is not used in the C function, assuming 0
    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }
    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1time_1equ
  (JNIEnv *env, jclass cls, jdouble tjd, jdoubleArray te) {
    jdouble *te_arr = (*env)->GetDoubleArrayElements(env, te, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_time_equ(tjd, te_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, te, te_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1lmt_1to_1lat
  (JNIEnv *env, jclass cls, jdouble tjd_lmt, jdouble geolon, jdoubleArray tjd_lat) {
    jdouble *tjd_lat_arr = (*env)->GetDoubleArrayElements(env, tjd_lat, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_lmt_to_lat(tjd_lmt, geolon, tjd_lat_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, tjd_lat, tjd_lat_arr, 0);

    return ret;
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1lat_1to_1lmt
  (JNIEnv *env, jclass cls, jdouble tjd_lat, jdouble geolon, jdoubleArray tjd_lmt) {
    jdouble *tjd_lmt_arr = (*env)->GetDoubleArrayElements(env, tjd_lmt, 0);
    char serr_buf[AS_MAXCH + 1];
    serr_buf[0] = '\0';

    int ret = swe_lat_to_lmt(tjd_lat, geolon, tjd_lmt_arr, serr_buf);

    if (ret < 0) {
        strncpy(global_serr_buf, serr_buf, AS_MAXCH);
        global_serr_buf[AS_MAXCH] = '\0';
    }

    (*env)->ReleaseDoubleArrayElements(env, tjd_lmt, tjd_lmt_arr, 0);

    return ret;
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1sidtime0
  (JNIEnv *env, jclass cls, jdouble tjd_ut, jdouble eps, jdouble nut) {
    return swe_sidtime0(tjd_ut, eps, nut);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1sidtime
  (JNIEnv *env, jclass cls, jdouble tjd_ut) {
    return swe_sidtime(tjd_ut);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1set_1interpolate_1nut
  (JNIEnv *env, jclass cls, jboolean do_interpolate) {
    swe_set_interpolate_nut(do_interpolate);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1cotrans
  (JNIEnv *env, jclass cls, jdoubleArray xpo, jdoubleArray xpn, jdouble eps) {
    jdouble *xpo_arr = (*env)->GetDoubleArrayElements(env, xpo, 0);
    jdouble *xpn_arr = (*env)->GetDoubleArrayElements(env, xpn, 0);
    swe_cotrans(xpo_arr, xpn_arr, eps);
    (*env)->ReleaseDoubleArrayElements(env, xpo, xpo_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xpn, xpn_arr, 0);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1cotrans_1sp
  (JNIEnv *env, jclass cls, jdoubleArray xpo, jdoubleArray xpn, jdouble eps) {
    jdouble *xpo_arr = (*env)->GetDoubleArrayElements(env, xpo, 0);
    jdouble *xpn_arr = (*env)->GetDoubleArrayElements(env, xpn, 0);
    swe_cotrans_sp(xpo_arr, xpn_arr, eps);
    (*env)->ReleaseDoubleArrayElements(env, xpo, xpo_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, xpn, xpn_arr, 0);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1get_1tid_1acc
  (JNIEnv *env, jclass cls) {
    return swe_get_tid_acc();
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1set_1tid_1acc
  (JNIEnv *env, jclass cls, jdouble t_acc) {
    swe_set_tid_acc(t_acc);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1set_1delta_1t_1userdef
  (JNIEnv *env, jclass cls, jdouble dt) {
    swe_set_delta_t_userdef(dt);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1degnorm
  (JNIEnv *env, jclass cls, jdouble x) {
    return swe_degnorm(x);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1radnorm
  (JNIEnv *env, jclass cls, jdouble x) {
    return swe_radnorm(x);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1rad_1midp
  (JNIEnv *env, jclass cls, jdouble x1, jdouble x0) {
    return swe_rad_midp(x1, x0);
}

JNIEXPORT jdouble JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1deg_1midp
  (JNIEnv *env, jclass cls, jdouble x1, jdouble x0) {
    return swe_deg_midp(x1, x0);
}

JNIEXPORT void JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1split_1deg
  (JNIEnv *env, jclass cls, jdouble ddeg, jint roundflag, jintArray ideg, jintArray imin, jintArray isec, jdoubleArray dsecfr, jintArray isgn) {
    jint *ideg_arr = (*env)->GetIntArrayElements(env, ideg, 0);
    jint *imin_arr = (*env)->GetIntArrayElements(env, imin, 0);
    jint *isec_arr = (*env)->GetIntArrayElements(env, isec, 0);
    jdouble *dsecfr_arr = (*env)->GetDoubleArrayElements(env, dsecfr, 0);
    jint *isgn_arr = (*env)->GetIntArrayElements(env, isgn, 0);
    swe_split_deg(ddeg, roundflag, ideg_arr, imin_arr, isec_arr, dsecfr_arr, isgn_arr);
    (*env)->ReleaseIntArrayElements(env, ideg, ideg_arr, 0);
    (*env)->ReleaseIntArrayElements(env, imin, imin_arr, 0);
    (*env)->ReleaseIntArrayElements(env, isec, isec_arr, 0);
    (*env)->ReleaseDoubleArrayElements(env, dsecfr, dsecfr_arr, 0);
    (*env)->ReleaseIntArrayElements(env, isgn, isgn_arr, 0);
}

JNIEXPORT jint JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1day_1of_1week
  (JNIEnv *env, jclass cls, jdouble jd) {
    return swe_day_of_week(jd);
}

JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1cs2timestr
  (JNIEnv *env, jclass cls, jint t, jint sep, jboolean suppressZero) {
    char buf[AS_MAXCH + 1];
    buf[0] = '\0';
    char *ret = swe_cs2timestr(t, sep, suppressZero, buf);
    return (*env)->NewStringUTF(env, ret);
}

JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1cs2lonlatstr
  (JNIEnv *env, jclass cls, jint t, jchar pchar, jchar mchar) {
    char buf[AS_MAXCH + 1];
    buf[0] = '\0';
    char *ret = swe_cs2lonlatstr(t, pchar, mchar, buf);
    return (*env)->NewStringUTF(env, ret);
}

JNIEXPORT jstring JNICALL Java_io_github_prolaxu_swisseph_Swisseph_swe_1cs2degstr
  (JNIEnv *env, jclass cls, jint t) {
    char buf[AS_MAXCH + 1];
    buf[0] = '\0';
    char *ret = swe_cs2degstr(t, buf);
    return (*env)->NewStringUTF(env, ret);
}