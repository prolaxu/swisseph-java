# SwissEph Java

[![License: AGPL v3](https://img.shields.io/badge/License-AGPL%20v3-blue.svg)](https://www.gnu.org/licenses/agpl-3.0)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.prolaxu/swisseph-java.svg)](https://search.maven.org/artifact/io.github.prolaxu/swisseph-java)
[![Java Version](https://img.shields.io/badge/Java-8%2B-orange.svg)](https://openjdk.java.net/)

A comprehensive Java Native Interface (JNI) wrapper for the Swiss Ephemeris astronomical calculation library (version 2.10.03). This library provides high-precision astronomical calculations for planetary positions, eclipses, house systems, fixed stars, and much more.

## Features

✨ **Complete Swiss Ephemeris 2.10.03 functionality**  
🚀 **High-performance JNI implementation**  
📦 **Maven/Gradle compatible**  
🌍 **Cross-platform support** (Linux, macOS, Windows)  
🧪 **Comprehensive test suite**  
📚 **Extensive documentation and examples**  

## Quick Start

### Maven Dependency

```xml
<dependency>
    <groupId>io.github.prolaxu</groupId>
    <artifactId>swisseph-java</artifactId>
    <version>2.10.03</version>
</dependency>
```

### Gradle Dependency

```gradle
implementation 'io.github.prolaxu:swisseph-java:2.10.03'
```

### Basic Usage

```java
import io.github.prolaxu.swisseph.Swisseph;

public class Example {
    public static void main(String[] args) {
        // Get Swiss Ephemeris version
        String version = Swisseph.swe_version();
        System.out.println("Swiss Ephemeris version: " + version);
        
        // Calculate Sun position for January 1, 2000
        double[] position = new double[6];
        double julianDay = 2451545.0; // Jan 1, 2000 12:00 UT
        
        int result = Swisseph.swe_calc_ut(julianDay, Swisseph.SE_SUN, 
                                         Swisseph.SEFLG_SPEED, position);
        
        if (result >= 0) {
            System.out.printf("Sun longitude: %.6f°%n", position[0]);
            System.out.printf("Sun latitude: %.6f°%n", position[1]);
            System.out.printf("Sun distance: %.6f AU%n", position[2]);
        }
        
        // Always close when done
        Swisseph.swe_close();
    }
}
```

## Building from Source

### Prerequisites

- **Java Development Kit (JDK)**: Version 8 or higher
- **Maven**: Version 3.6 or higher
- **GCC**: For compiling native libraries
- **Git**: For cloning the repository

### Build Steps

```bash
# Clone the repository
git clone https://github.com/prolaxu/swisseph-java.git
cd swisseph-java

# Build the project
mvn clean compile

# Run tests
mvn test

# Create JAR with dependencies
mvn package
```

### Manual Native Library Build

If you need to rebuild the native library manually:

```bash
# Make the build script executable
chmod +x scripts/build-native.sh

# Build native library
./scripts/build-native.sh
```

## Available Functions

The library provides access to all major Swiss Ephemeris functions:

### 🪐 Planetary Calculations
- `swe_calc_ut()` - Calculate planetary positions (UT)
- `swe_calc()` - Calculate planetary positions (ET)
- `swe_pheno_ut()` - Planetary phenomena (phase, magnitude, etc.)
- `swe_get_planet_name()` - Get planet names

### ⭐ Fixed Stars
- `swe_fixstar_ut()` - Fixed star positions
- `swe_fixstar_mag()` - Fixed star magnitudes
- `swe_fixstar2_ut()` - Alternative fixed star calculations

### 🏠 House Systems
- `swe_houses()` - Calculate house cusps
- `swe_house_pos()` - House position of a planet
- `swe_house_name()` - House system names

### 🌑 Eclipses & Occultations
- `swe_sol_eclipse_when_glob()` - Global solar eclipses
- `swe_lun_eclipse_when()` - Lunar eclipses
- `swe_sol_eclipse_where()` - Eclipse visibility

### 🌅 Rise/Set/Transit
- `swe_rise_trans()` - Rise, set, and transit times
- `swe_rise_trans_true_hor()` - True horizon calculations

### 📅 Date & Time
- `swe_julday()` - Convert to Julian Day
- `swe_revjul()` - Convert from Julian Day
- `swe_utc_to_jd()` - UTC to Julian Day
- `swe_deltat()` - Delta T calculations

### 🧭 Coordinate Transformations
- `swe_azalt()` - Azimuth/altitude calculations
- `swe_cotrans()` - Coordinate transformations
- `swe_degnorm()` - Degree normalization

### 🔬 Advanced Calculations
- `swe_get_ayanamsa_ut()` - Ayanamsa calculations
- `swe_nod_aps_ut()` - Nodes and apsides
- `swe_get_orbital_elements()` - Orbital elements
- `swe_heliacal_ut()` - Heliacal phenomena

## Platform Support

| Platform | Native Library | Status |
|----------|----------------|--------|
| Linux x64 | `libswisseph.so` | ✅ Supported |
| macOS | `libswisseph.dylib` | ✅ Supported |
| Windows x64 | `swisseph.dll` | ✅ Supported |

## Examples

Check out the comprehensive examples in the test directory:

- **AllFunctionsTest.java**: Complete test suite demonstrating all functions
- **Planetary calculations**: Sun, Moon, planets positions
- **Eclipse predictions**: Solar and lunar eclipses
- **House calculations**: All major house systems
- **Fixed star positions**: Bright star calculations

## Documentation

- 📖 [Swiss Ephemeris Documentation](https://www.astro.com/swisseph/swephprg.htm)
- 🔗 [Original C Library](https://github.com/aloistr/swisseph)
- 📋 [API Reference](docs/API.md)
- 💡 [Usage Examples](examples/)

## Version History

- **2.10.03**: Latest Swiss Ephemeris version with important bug fixes
  - Fixed lunar eclipse calculations (766-987 CE)
  - Improved Moon magnitude calculations
  - Complete JNI wrapper implementation

## License

This project is licensed under the GNU Affero General Public License v3.0 (AGPL-3.0), same as the original Swiss Ephemeris library.

**Important**: The Swiss Ephemeris is dual-licensed:
- **AGPL v3.0**: Free for open-source projects
- **Commercial License**: Available from [Astrodienst](https://www.astro.com/swisseph/) for commercial use

## Contributing

Contributions are welcome! Please:

1. Fork the repository
2. Create a feature branch
3. Make your changes
4. Add tests if applicable
5. Submit a pull request

## Support

- 🐛 [Report Issues](https://github.com/prolaxu/swisseph-java/issues)
- 💬 [Discussions](https://groups.io/g/swisseph)

## Credits

- **Swiss Ephemeris**: Developed by Astrodienst AG, Zurich, Switzerland
- **Original Authors**: Alois Treindl and Dieter Koch
- **Java Wrapper**: Prolaxu

---

⭐ **Star this repository if you find it useful!**
