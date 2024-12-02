# ECM2414 – Software Development Continuous Assessment

## Overview
- **Module Leader**: Prof. Solomon Oyelere
- **Academic Year**: 2024/25
- **Set**: 21st October 2024
- **Submission deadline**: 10th December 2024
- **Contribution**: 40% of the total module mark

## Building and Running the Project

To create a `Cards.jar` with the `.class` and `.jar` files:

```shell
javac -cp .\lib\junit-4.13.1.jar -d out .\src\main\*.java .\src\test\*.java
jar cvf Cards.jar -C out . -C src .
```

To run the project:

```shell
java -cp Cards.jar main.CardGame
```

To run the tests:

```shell
java -cp "Cards.jar;.\lib\junit-4.13.1.jar;.\lib\hamcrest-core-1.3.jar" org.junit.runner.JUnitCore test.AllTests
```