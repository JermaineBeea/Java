# Simple Java Project with Build Scripts

This is the simplest possible Java project example with Maven and Make build scripts.

## Project Structure

```
simple-java-project/
├── pom.xml                    # Maven configuration
├── Makefile                   # Build script
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               └── App.java          # Main application
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── CalculatorTest.java   # Test file
└── target/                    # Generated build files (created by Maven)
```

## What This Project Does

- **App.java**: A simple program that prints "Hello World!" and demonstrates basic calculator functions
- **CalculatorTest.java**: Tests for the calculator to make sure it works correctly
- **pom.xml**: Tells Maven how to build the project
- **Makefile**: Simple commands to build, test, and run the project

## How to Use

### Prerequisites
- Java 11 or later installed
- Maven installed
- Make installed (usually comes with Linux/Mac, Windows users can install it)

### Basic Commands

1. **Build everything**: `make` or `make all`
2. **Just compile**: `make compile`
3. **Run tests**: `make test`
4. **Run the program**: `make run`
5. **Create JAR file**: `make package`
6. **Clean up**: `make clean`

### Step by Step Example

1. **First time setup**:
   ```bash
   make setup
   ```

2. **Build and test**:
   ```bash
   make all
   ```

3. **Run the program**:
   ```bash
   make run
   ```
   Output should be:
   ```
   Hello World!
   5 + 3 = 8
   4 * 7 = 28
   ```

4. **Run as JAR file**:
   ```bash
   make run-jar
   ```

### Development vs Release

- **Development build**: `make dev-build` (keeps -SNAPSHOT in version)
- **Release build**: `make release-build` (removes -SNAPSHOT for final version)

### Understanding the Build Process

1. **Compile**: Turns your `.java` files into `.class` files
2. **Test**: Runs your tests to make sure everything works
3. **Package**: Creates a `.jar` file you can distribute and run anywhere

### Maven Commands (if you want to use Maven directly)

- `mvn compile` - compile code
- `mvn test` - run tests  
- `mvn package` - create JAR file
- `mvn exec:java` - run the main class
- `mvn clean` - clean up build files

## Key Learning Points

1. **Maven** handles Java-specific tasks (compile, test, package)
2. **Make** orchestrates the overall build process and can run multiple commands
3. The **pom.xml** file configures how Maven builds your project
4. Tests are important - they run automatically during the build
5. The final **JAR file** can be run on any computer with Java installed

This setup gives you a solid foundation for any Java project!