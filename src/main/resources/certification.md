# Java 1Z0-808

## Chapter 1

### Java Building Blocks

Class order.

    1. Package declaration
    2. Imports stataments
    3. Class declaration
    4. Fields declarations
    5. Methods declarations

Have 2 elements primaries

1. Methods (Funciones, procedimientos)
2. Fields  (Variables, atributos)

* Constructors
    * Matches with the name of class
    * No return type

* Order of executions

    1. run the fields and initializer blocks (run in order which appear)
    2. run constructor

#### Command line execution

 * Compile one class

```bash
    #javac compile java file
    javac Zoo.java

    #java execute java file after compiled
    java Zoo
```

* Compile classes and packages

```bash
    #Compile all classes
    javac ackagea/ClassA.java packageb/ClassB.java

    #java execute java main class in specify package
    java packageb.ClassB
```

##### How it's works ?

After the javac compile the file, this was generate a new file with ```bytecode``` 
content called with the same name and with the extension .class

## Chapter 2 Java Operators

#### Operators availables


|      Operator      |            Symbol                 | 
|--------------------|-----------------------------------|
|Post-unary operator, apply before use|expression **++**, expression **--**|

##### Promotion rules

   1. If two values are different data type, java promote to the most larger of the two data types.
   2. Java promote the integral values to floating if one of boths is floating.
   3. Small data types (byte, short, char) are promoted to int data type.

#### Unary Operators

|      Operator      |       Description             | 
|--------------------|-------------------------------|
| +| Positive Number|


#### Compound Operations

1. When you use compound, this promote and cast the result.

#### Equals Operators

1. If use only "&" evalute both conditions at same time, but if used "&&", evaluate condition by parts. 
This apply too to `or` "| - ||" operator

## Best Practices

* Any method what change a state on an object, it is recommended must be a void return.
* Variables can not start with numbers

## Pay attention in examen.

1. Execution orders
2. Blocks orders
3. Scopes and variables
4. Types of variables are asked (Local, Instance)
5. Estructure of constructors (confused with void method with same name Class)
6. Operators applied to invalid data types (!2 ; --true)
7. See the break instruction on switch stataments.
