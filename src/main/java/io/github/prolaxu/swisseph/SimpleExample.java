/*
 * SimpleExample.java
 *
 * Example usage of Swiss Ephemeris Java wrapper
 * Copyright (C) 2023 Prolaxu. All rights reserved.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 * For more information about this project, visit:
 * https://github.com/prolaxu/swisseph-java
 */

package io.github.prolaxu.swisseph;

/**
 * A simple example demonstrating basic usage of the Swiss Ephemeris Java wrapper.
 * 
 * <p>This example shows how to:
 * <ul>
 *   <li>Load the native library</li>
 *   <li>Set the ephemeris path</li>
 *   <li>Calculate planetary positions</li>
 *   <li>Handle errors</li>
 * </ul>
 * 
 * @version 2.10.03
 * @see <a href="https://github.com/prolaxu/swisseph-java">GitHub Repository</a>
 */
public class SimpleExample {
    /**
     * Main method demonstrating basic Swiss Ephemeris functionality.
     * 
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            System.loadLibrary("swisseph");
            System.out.println("Native library loaded successfully.");
        } catch (UnsatisfiedLinkError e) {
            System.err.println("Failed to load native library: " + e.getMessage());
            System.err.println("Please ensure `libswisseph.so` is in your `java.library.path`");
            return;
        }

        // Ephemeris path
        Swisseph.swe_set_ephe_path("/home/laxu/Documents/tests/swisseph-java-latest/ephe");
        Swisseph.swe_set_sid_mode(Swisseph.SE_SIDM_LAHIRI, 0.0, 0.0);

        // Date & Time in IST (UTC+5:30)
        int year = 2025, month = 1, day = 1, hour = 12, minute = 0;
        double second = 0.0;
        double tzOffset = 5.5; // UTC+05:30

        // Convert IST to UTC for Julian Day calculation
        double utcHour = hour - tzOffset; // 12:00 IST = 6.5 UTC
        double jd = Swisseph.swe_julday(year, month, day, utcHour, Swisseph.SE_GREG_CAL);

        // Optionally, use swe_utc_to_jd if needed for timezone-aware conversion
        // double[] dret = new double[2];
        // int ret = Swisseph.swe_utc_to_jd(year, month, day, hour, minute, second, Swisseph.SE_GREG_CAL, dret);
        // double jd = dret[1]; // UT Julian Day

        // Planet: Sun
        int ipl = Swisseph.SE_SUN;
        int iflag = Swisseph.SEFLG_SPEED;
        double[] xx = new double[6];

        int ret = Swisseph.swe_calc_ut(jd, ipl, iflag, xx);

        if (ret < 0) {
            System.err.println("Error calculating Sun's position: " + Swisseph.getLastError());
        } else {
            System.out.printf("Sun's Longitude on Jan 1, 2025, 12:00 IST: %.6f°\n", xx[0]);
        }

        double ayanamsa = Swisseph.swe_get_ayanamsa_ut(jd);
        System.out.printf("Ayanamsa UT on Jan 1, 2025: %.6f°\n", ayanamsa);
        Swisseph.swe_close();
    }
}
