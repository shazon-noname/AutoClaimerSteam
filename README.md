# Steam Bot - Build Instructions

## How to build the executable:

### Option 1: Using the build script (Recommended)
1. Double-click `build.bat`
2. Wait for the build to complete
3. The EXE file will be created in the `dist` folder

### Option 2: Manual build
1. Open command prompt in this directory
2. Run: `mvn clean package`
3. The JAR file will be created in `target/untitled-1.0-SNAPSHOT.jar`
4. Run with: `java -jar target/untitled-1.0-SNAPSHOT.jar`

### Option 3: Create EXE manually (if jpackage is available)
1. First build the JAR: `mvn clean package`
2. Then create EXE: 
   ```
   jpackage --name SteamBot --input target/classes --main-jar untitled-1.0-SNAPSHOT.jar --main-class org.example.Main --type exe --dest dist
   ```

## Requirements:
- Java 25 or higher installed
- Maven installed and configured
- Chrome browser installed (for Selenium)
- ChromeDriver in PATH or with the application

## For your friend:
Your friend needs to have Java and Chrome browser installed to run the application.
If they don't have Java, you can use tools like Launch4j or jpackage to create a standalone EXE that includes Java runtime.
