# STL Serialization Tools
Serialisation tools for STL files for Java. 
At the moment the project support just deserialization of STL files in ASCII format into a simple java model. 

This is a Spring Boot library, and contains a starter for convenience.

## Getting started
Clone this project

Run the command in the project directory:
```bash
mvn clean install
```
    
Include the dependency into your Spring Boot application
```xml:
<dependency>
    <groupId>spiffy.rocks</groupId>
    <artifactId>stl-starter</artifactId>
    <version>0.0.1-SNAPSHOT</version>
</dependency>
```

Parse some files;

```java
ANTLRInputStream antlrInputStream = new ANTLRInputStream(getClass().getResourceAsStream("/camPlate.stl"));
ParseResult parse = asciiStlParser.parse(antlrInputStream);
Solids solids = parse.getResult();
// Use Solids for something...
// The Solids object is a full in memory representation of the loaded STL file
```

## How it works
Under the hood this project uses Antlr4 for the parsing of ASCII STL files, you can find the grammar file for STL in the stl-parse module.

