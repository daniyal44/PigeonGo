# Pigeon GO 🐦💨

### Real-time Mobile Responsive Development & Tunneling Bridge

Pigeon GO is an Android application engineered as a real-time mobile development bridge. It enables developer teams to test locally-running applications or live deployment tunnels directly on target hardware with integrated dev-tools, real-time logging, and local session caching.

---

## 🚀 Vision & Key Features

* **Instant Dynamic Tunneling**: Test local servers (`localhost:3000`, `192.168.1.5`) or public tunnels (`*.ngrok-free.app`, `*.loca.lt`) instantly without redeploying Android APKs.
* **Integrated Console Mirror**: Persistent console overlay showcasing live hot-module-reload (HMR), build updates, and compilation logs dynamically.
* **Smart Local Persistence**: Powered by **Jetpack Room**, session logs and active connection tunnels are archived locally for quick reboot and testing.
* **Optimized Android Architecture**: Built entirely in **Kotlin**, **Jetpack Compose**, and standard Material Design 3. Fully compliant with modern Android quality practices, standard edge-to-edge layouts (`enableEdgeToEdge`), and local JVM testing via **Robolectric**.

---

## 🛠️ Tech Stack & Dependencies

- **UI Framework**: Jetpack Compose (Material Design 3)
- **Local Persistence**: Room Database (with KSP)
- **Navigation**: Jetpack Navigation (Compose)
- **Networking**: Built-in Android WebKit Web Engine
- **Test Harness**: Robolectric & JUnit 4 (configured for Local JVM testing)
- **Build Tooling**: Gradle (Kotlin DSL, AGP 8.5)

---

## 📂 Project Structure

```
├── app
│   ├── src
│   │   ├── main
│   │   │   ├── java/com/example          # Core Application Layer
│   │   │   │   ├── data                  # Room Entities, DAOs, and Database Configuration
│   │   │   │   ├── ui                    # Jetpack Compose Screens, Theme, ViewModels
│   │   │   │   └── MainActivity.kt       # Application Entry point
│   │   │   └── res                       # Icons, Themes, XML configurations
│   │   └── test                          # Local JVM Test suite running on Robolectric
│   └── build.gradle.kts                  # App Build Configuration
├── build.gradle.kts                      # Root Gradle Config
└── settings.gradle.kts                   # Project Repository Definitions
```

---

## ⚙️ Setup & Testing Instruction

### Prerequisites
- JDK 17+
- Android SDK (compiled for target SDK 34)

### Compile the App
```bash
gradle assembleDebug
```

### Run Local Unit & Integration Tests
The project features complete coverage for local system state utilizing **Robolectric**:
```bash
gradle :app:testDebugUnitTest
```
