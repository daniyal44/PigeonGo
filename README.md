# 🐦 Pigeon GO - Local Machine Execution & Run Guide
### Comprehensive Step-by-Step Guide for Windows, macOS, and Linux

This guide provides clear, step-by-step instructions to set up, build, install, and run **Pigeon GO** (or any modern Jetpack Compose Android application) on your local development machine using **Windows**, **macOS**, or **Linux**.

---

## 🛠️ Step 1: System Prerequisites (All Operating Systems)

Before running development commands, you must configure your local machine with the correct dependencies.

### 1. Java Development Kit (JDK 17)
Modern Android Gradle builds require **JDK 17**.
* **macOS / Linux**: Use [SDKMAN!](https://sdkman.io/) or Homebrew.
  ```bash
  # Via SDKMAN!
  sdk install java 17.0.10-tem
  ```
* **Windows**: Download individual binaries from [Adoptium Temurin](https://adoptium.net/temurin/releases/?version=17) or run via Winget:
  ```powershell
  winget install Eclipse.Temurin.17.JDK
  ```

### 2. Android Command Line Tools or Android Studio
* To manage SDK packages easily, it is highly recommended to install [Android Studio](https://developer.android.com/studio).
* Android Studio will automatically install the **Android SDK**, **Android SDK Platform-Tools** (containing `adb`), and the **Android Emulator**.

### 3. Environment Variables Setup
Your terminal commands need to know where the JDK and Android SDK are located.

#### ❖ Windows
Open PowerShell as Administrator and run:
```powershell
[System.Environment]::SetEnvironmentVariable("JAVA_HOME", "C:\Program Files\Eclipse Foundation\jdk-17.0.10.7-hotspot", "User")
[System.Environment]::SetEnvironmentVariable("ANDROID_HOME", "$env:LOCALAPPDATA\Android\Sdk", "User")

# Add Platform-Tools (containing adb) to the PATH variable
$oldPath = [System.Environment]::GetEnvironmentVariable("Path", "User")
[System.Environment]::SetEnvironmentVariable("Path", "$oldPath;$env:LOCALAPPDATA\Android\Sdk\platform-tools", "User")
```
*Restart your PowerShell terminal after applying these.*

#### ❖ macOS (zsh)
Add the following to your `~/.zshrc` or `~/.bash_profile`:
```bash
export JAVA_HOME=$(/usr/libexec/java_home -v 17)
export ANDROID_HOME=$HOME/Library/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools:/usr/local/bin
```
Apply the changes:
```bash
source ~/.zshrc
```

#### ❖ Linux (bash)
Add the following to your `~/.bashrc`:
```bash
export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64 # Adapt to your local JDK path
export ANDROID_HOME=$HOME/Android/Sdk
export PATH=$PATH:$ANDROID_HOME/platform-tools
```
Apply the changes:
```bash
source ~/.bashrc
```

---

## 📱 Step 2: Preparing Your Testing Device

You can run the application on either a **Physical Device** or a **Virtual Emulator**.

### Option A: Using a Physical Android Device
1. On your phone, go to **Settings > About Phone** and tap **Build Number** 7 times to enable Development Settings.
2. Go to **Settings > System > Developer Options** and enable **USB Debugging**.
3. Plug your phone into your local machine using a high-quality USB cable.
4. Verify your device is connected by running:
   ```bash
   adb devices
   ```
   *(Ensure you authorize the prompt on your phone's screen).*

### Option B: Using an Android Virtual Device (AVD)
1. Open **Android Studio**.
2. Click on the **More Actions** menu (the 3 dots) on the welcome page, or navigate to **Tools > Device Manager**.
3. Click **Create Device**, select a hardware profile (e.g., Pixel 7), and click **Next**.
4. Download and select a recent system image (e.g., API Level 34 / UpsideDownCake) and click **Finish**.
5. Click the play button (▶) next to your virtual device to launch the emulator.

---

## 🚀 Step 3: Terminal Run Commands (Step-by-Step)

Navigate to your local project repository root folder on your terminal and run the commands appropriate for your Operating System:

### ❖ macOS / Linux
```bash
# 1. Clean the project cached directories
./gradlew clean

# 2. Compile and assemble the Debug APK binary package
./gradlew assembleDebug

# 3. Compile and launch local units & unit test runners
./gradlew testDebugUnitTest

# 4. Install the debug APK in your active device/emulator
./gradlew installDebug

# 5. Start the application instantly on your physical device or emulator using monkey
adb shell monkey -p "com.aistudio.pigeongo.pgo" -c android.intent.category.LAUNCHER 1
```

### ❖ Windows (PowerShell)
```powershell
# 1. Verify environment configuration
java -version; adb devices

# 2. Clean cached build structures
.\gradlew.bat clean

# 3. Assemble development binary package
.\gradlew.bat assembleDebug

# 4. Run local test suites on JVM
.\gradlew.bat testDebugUnitTest

# 5. Install the APK to the default target connected device
.\gradlew.bat installDebug

# 6. Execute direct device launch command
adb shell monkey -p "com.aistudio.pigeongo.pgo" -c android.intent.category.LAUNCHER 1
```

---

## 🎨 Step 4: Running via Android Studio (GUI Alternative)

If you prefer using a visual IDE:
1. Launch **Android Studio**.
2. Click **Open** and select the root directory of your project (where `settings.gradle.kts` lives).
3. Wait for the initial Gradle Sync operation to complete successfully (this downloads all necessary libraries and packages).
4. Select your connected target phone or AVD emulator from the device drop-down menu in the top toolbar.
5. Click the green play icon (▶) or press `Shift + F10` (`Control + R` on macOS) to build, deploy, and launch the application.

---

## 🔍 Troubleshooting Guide

| Issue | Cause | Solution |
| :--- | :--- | :--- |
| **`No connected devices found`** | ADB cannot communicate with your handset or emulator. | Verify your phone's developer options are active and run `adb kill-server && adb start-server` on your console. |
| **`Unsupported class file major version`** | Gradle and Java versions are mismatched. | Ensure your terminal's `java -version` returns exactly JDK 17. Adjust `JAVA_HOME` configuration. |
| **`SDK location not found`** | The Android SDK location is not declared. | Define a local properties environment config or create a `local.properties` file in the root containing: `sdk.dir=/USER_DIR/Android/Sdk` |
| **`Permission Denied (macOS/Linux)`** | The main `./gradlew` execution file is missing permissions. | Execute `chmod +x gradlew` directly from your repository root to grant execute permissions. |
