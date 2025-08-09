# Bitly Pro - Android Speed Test App

## Overview
Bitly Pro is a modern Android application designed to test internet speed in real-time. Built with Kotlin and Material 3, it provides download/upload speed measurements, ping testing, and historical data tracking.

## 🚀 Features
- **Real-time Speed Testing**: Accurate download, upload, and ping measurements
- **Historical Data**: Save and view previous test results with Room database  
- **Modern UI**: Material 3 design with dark/light theme support
- **Share Results**: Export test results as text
- **Network Detection**: Automatic connection type detection (WiFi/Mobile)

## 🔧 Technical Stack
- **Language**: Kotlin
- **UI**: Material 3 Components with XML layouts
- **Architecture**: MVVM with ViewModels and StateFlow
- **Database**: Room for local storage
- **Networking**: OkHttp for accurate speed measurements
- **Build System**: Gradle 8.10.2 with Android Gradle Plugin 8.6.1

## 📋 Requirements
- **Minimum SDK**: API 24 (Android 7.0)
- **Target SDK**: API 35 (Android 15)
- **Java**: JDK 17+ (compatible with JDK 24)
- **Gradle**: 8.10.2+

## 🛠️ Setup Instructions

1. **Clone the repository**:
   ```bash
   git clone <repository-url>
   cd BitlyPro
   ```

2. **Build the project**:
   ```bash
   ./gradlew assembleDebug
   ```

3. **Run tests**:
   ```bash
   ./gradlew test
   ```

## 🔧 Troubleshooting - GitHub Actions Fix ✅

**Issue Resolved**: The GitHub Actions workflow was failing due to missing Gradle wrapper files and version compatibility issues.

**Fixes Applied**:
1. ✅ Created missing `gradlew` and `gradlew.bat` scripts
2. ✅ Updated Gradle to version 8.10.2 for Java 24+ compatibility
3. ✅ Updated Android Gradle Plugin to 8.6.1  
4. ✅ Fixed GitHub Actions workflow paths and working directories
5. ✅ Moved `.github` folder to repository root
6. ✅ Downloaded correct gradle-wrapper.jar

**Result**: The project now builds successfully in CI/CD! 🎉

## Dependencies
- Kotlin
- Material 3
- OkHttp
- Room
- MPAndroidChart

## GitHub Actions
The project includes a GitHub Actions workflow for building APKs automatically on push events. Check the `.github/workflows/build.yml` file for configuration details.

## License
This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.