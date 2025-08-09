# Bitly Pro

## Overview
Bitly Pro is an Android application designed to test internet speed in real-time. The app provides a user-friendly interface to initiate speed tests and view historical results through charts. It utilizes modern Android development practices, including Kotlin, Material 3 components, and Room for database management.

## Features
- Real-time internet speed testing using OkHttp or raw sockets.
- Historical data visualization with MPAndroidChart or custom Canvas.
- Local database management with Room for storing speed test results.
- Clean and modern UI built with Material 3 components.

## Project Structure
The project is organized into several packages:
- **data**: Contains the database, network, and repository classes.
- **ui**: Contains the fragments and ViewModels for the user interface.
- **utils**: Contains utility functions for network operations.

## Setup Instructions
1. **Clone the repository**:
   ```bash
   git clone https://github.com/yourusername/BitlyPro.git
   cd BitlyPro
   ```

2. **Open the project in Android Studio**.

3. **Sync the Gradle files** to download the necessary dependencies.

4. **Run the application** on an emulator or physical device.

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