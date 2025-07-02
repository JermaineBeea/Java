# Simple Makefile for Java Project

# Variables
MAVEN = mvn
JAR_FILE = target/simple-app-*-jar-with-dependencies.jar

# Default target
.PHONY: all
all: compile test package

# Compile the Java code
.PHONY: compile
compile:
	@echo "Compiling Java code..."
	$(MAVEN) compile

# Run all tests
.PHONY: test
test:
	@echo "Running tests..."
	$(MAVEN) test

# Package into executable JAR
.PHONY: package
package:
	@echo "Creating JAR file..."
	$(MAVEN) package

# Clean build files
.PHONY: clean
clean:
	@echo "Cleaning build files..."
	$(MAVEN) clean

# Run the application using Maven
.PHONY: run
run: compile
	@echo "Running the application..."
	$(MAVEN) exec:java

# Run the JAR file directly
.PHONY: run-jar
run-jar: package
	@echo "Running JAR file..."
	@java -jar $(JAR_FILE)

# Development build (no version changes)
.PHONY: dev-build
dev-build: clean compile test package
	@echo "Development build completed!"

# Release build (removes SNAPSHOT from version)
.PHONY: release-build
release-build: clean
	@echo "Preparing release build..."
	@sed -i 's/-SNAPSHOT//' pom.xml
	@$(MAKE) compile test package
	@echo "Release build completed!"
	@echo "Don't forget to commit the version change!"

# Show current version
.PHONY: version
version:
	@echo "Current version:"
	@grep -E "<version>.*</version>" pom.xml | head -1 | sed 's/.*<version>\(.*\)<\/version>.*/\1/'

# Setup project (install dependencies)
.PHONY: setup
setup:
	@echo "Setting up project..."
	$(MAVEN) dependency:resolve

# Help
.PHONY: help
help:
	@echo "Available commands:"
	@echo "  all         - Compile, test, and package (default)"
	@echo "  compile     - Compile Java source code"
	@echo "  test        - Run all tests"
	@echo "  package     - Create executable JAR file"
	@echo "  clean       - Remove build files"
	@echo "  run         - Run application with Maven"
	@echo "  run-jar     - Run the JAR file directly"
	@echo "  dev-build   - Complete development build"
	@echo "  release-build - Release build (removes SNAPSHOT)"
	@echo "  version     - Show current version"
	@echo "  setup       - Install dependencies"
	@echo "  help        - Show this help"