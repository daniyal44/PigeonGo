# Pigeon GO - Quick Start & Execution Guide 🐦💨

For the comprehensive, step-by-step local machine setup and run commands for Windows, macOS, and Linux, see the main [README.md](./README.md) file.

### Quick Start Shell Commands

#### ❖ macOS / Linux
```bash
./gradlew clean assembleDebug installDebug
adb shell monkey -p "com.aistudio.pigeongo.pgo" -c android.intent.category.LAUNCHER 1
```

#### ❖ Windows
```powershell
.\gradlew.bat clean assembleDebug installDebug
adb shell monkey -p "com.aistudio.pigeongo.pgo" -c android.intent.category.LAUNCHER 1
```

*For troubleshooting and detailed system environment variable setup, please read [README.md](./README.md).*
