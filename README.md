# ECM2414 – Software Development Continuous Assessment

## Overview
- **Module Leader**: Prof. Solomon Oyelere
- **Academic Year**: 2024/25
- **Set**: 21st October 2024
- **Submission deadline**: 10th December 2024
- **Contribution**: 40% of the total module mark

## Building and Running the Project and Tests
### To Build
Compile the Java files:
```shell
javac -cp ".\lib\junit-4.13.1.jar;.\lib\hamcrest-core-1.3.jar" -d out ".\src\main\*.java" ".\src\test\*.java"
```
Unpack libraries:
```shell
cd out
jar xf "..\lib\junit-4.13.1.jar"
jar xf "..\lib\hamcrest-core-1.3.jar"
cd ..
```
Create the JAR file:
```shell
jar cvf Cards.jar -C out .
```

### To Run the Project
```shell
java -cp Cards.jar main.CardGame
```

### To Run the Tests
```shell
java -cp Cards.jar org.junit.runner.JUnitCore test.AllTests
```
