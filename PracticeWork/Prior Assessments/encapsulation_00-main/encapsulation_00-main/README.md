# Encapsulation_00



# Table of Contents
## 1. [Encapsulation Overview](#1-encapsulation-overview)
- Definition of encapsulation
- Importance of encapsulation in OOP
- Classess and objects
## 2. [Access Modifiers](#access-modifiers)
- Public, private, protected, and default access modifiers (Explanation of each)
- Access levels and visibility
## 3. [Setters and Getters](#setters-and-getters)
- Purpose of setters and getters in encapsulation
- Writing and using setters and getters in Java classes

## 5. [Best Practices](#best-practices)


# Encapsulation Overview

### Encapsulation Definition:
    Encapsulation is the bundling of data (attributes or fields) and methods (functions or behaviors) that operate on the data into a single unit, known as a class. It hides the internal state of an object and restricts direct access to it from outside the class.

### Importance of Encapsulation:
- Ensures data integrity and protects the object's internal implementation details. 

- Allows for an improved way to manipulate data while giving you increased control over it.
#### _Class_

This is how you would go about creating a java file(class) on IntelliJ:
![Creating java class](<resources/IntelliJ_Java_Class_Creation.png>)
- Left click on the package you'd like to create the java file (class)
- Click on 'New'
- Click on 'Java class'
- Go ahead and give it a name

NB: The name of the java file will ultimately be the name of the class

![alt text](<resources/Class_Name_Derived_From_File_Name.png>)

Let's go ahead and add some attributes/fields in our class
```java

public class Car {

    // These are attributes or fields

    private String brand;
    
    private String model;

}

```

You should've noticed now that by attributes/fields we essentially mean variables. Try get used to the jargon :smile:


Now, let's go ahead and add some methods to our class

```java

public class Car {

    // These are attributes or fields

    private String brand;

    private String model;


    // These are behaviours or methods

    public void startEngine() {
        System.out.println("Engine started for " + brand + " " + model);
    }

    public void stopEngine(){
     System.out.println("Engine stopped for " + brand + " " + model);
    }


}
```

We have added a methods to our class, by method(s)/behavoiour we mean function(s). You get it now? :smile:

Hold that thought, let's leave it here for now, we'll come back to this a bit later.

#### _Object_

Objects are basic unit of OOP. Objects are 'instances' of a class that are created to use the attributes and methods of a class.

To simplify further, you can think of objects as entities that represent real-world or conceptual things. Creating an object from a class is similar to making a specific version of something based on a blueprint,

    

    In simple english and breaking it down further, it's like you're calling a class, similar to how you'd call a function. But not really. 
    
You can learn more on this from this [documentation](https://docs.oracle.com/javase/tutorial/java/javaOO/objectcreation.html).

##### Constructors and instances

We now know how to create a java class, we know how to add some attributes and some behaviours into it, but now how do we use it?

The answer is; instantiation. This refers to the process of creating an instance or object from a class.

Below are a couple of ways on how you can go about institating(creating an object) a class.

```java
// #1

new ClassName();

// #2      

This is the name of the class
    |
    |    This is a variable you want to assign to the new object
    |             |
    |             |    Assigning variable on the left to object on the right
    |             |          |
    |             |          |  This is a constructor. 
    |             |          |          |
    |             |          |          |
    v             v          v          v
ClassName assignedObjectName = new ClassName();
                              \_______________/
                                     ^
                                     |
                                     |
                         This is new instance of the ClassName
```

What's a constructor?

A constructor in programming is a special type of method or function that is automatically called when an object is created/invoked/instantiated. Constructors are used to ***initialise*** the newly created object.

Constructor declarations look like method declarations—except that they use the name of the class and have no return type.

You can go through the documentation in detail [here](https://docs.oracle.com/javase/tutorial/java/javaOO/constructors.html).

Let's explore this. Let's refer back to our java Car class above

```java
public class Car {

    // These are attributes or fields

    private String brand;

    ......... *SNIP* 

    ......... *SNIP*

```

Creating an object/ instantiating the class above looks something like this.

```java
Car car = new Car(); // Car() is the constructor
```

The Car class (any other class) has a default constructor even though it may not have been declared. This default constructor is a no-argument constructor (constructor without parameters).

Now... There's a problem here, our class attributes are still null, this doesn't help much.

Let's go ahead and create our own custom constructor to **'initialise'** these values. 

```java

public class Car{

    // These are attributes or fields

    private String brand;

    private String model;

    // This is a constructor

    public Car(){

        this.brand = "Audi";

        this.model = "A3"

    }

}
```
More on the 'this' keyword [here](https://docs.oracle.com/javase/tutorial/java/javaOO/thiskey.html).

Now... How do we get this to be dynamic? A class can have more than one constructor. We'll leave the first constructor to be our default constructor, we'll add a second constructor to allow our program to be dynamic.

```java
public class Car{

    // These are attributes or fields

    private String brand;

    private String model;

    // This is our default custom constructor

    public Car(){

        this.brand = "Audi";

        this.model = "A3"

    }

    public Car(String brand, String model){
        
        this.brand = brand;

        this.model = model;

    }


}
```

Now, you can create Car objects using either the default constructor or the parameterised constructor based on your needs.

```java
Car car2 = new Car("BMW", "325iS"); // Uses parameterised constructor
```


NOTE: Each object, while originating from the same class, is distinct in memory allocation. This means that even if multiple objects share the same class structure and behavior, they occupy separate memory locations. As a result, modifications to one object do not affect the state or behavior of other objects, ensuring data encapsulation and object integrity.



# [Access Modifiers](https://docs.oracle.com/javase/tutorial/java/javaOO/accesscontrol.html)

Access modifiers are keywords that control the visibility and accessibility of classes, variables, methods, and constructors in Java. There are four main access modifiers:

### public keyword

The public modifier makes the class, method, or variable accessible from any other class in the same package or different packages. It provides the widest scope of accessibility.

- Class Visibility: Accessible within the class itself.
- Package Visibility: Accessible from any class within the same package.
- Subclass Visibility: Accessible from subclasses in any package.
- World Visibility: Accessible from any class in any package.

```java
public class SomeClass{

    public void myMethod(){

        // Some code
    }
}
```

### Protected (protected) 

The protected modifier allows access within the same package and subclasses (even if they are in different packages). This means that subclasses can access protected members, but other classes outside the package cannot.

- Class Visibility: Accessible within the class itself.
- Package Visibility: Accessible from any class within the same package.
- Subclass Visibility: Accessible from subclasses in any package.
- World Visibility: Not accessible from unrelated classes outside the package.

```java
class BaseClass {

    protected int myField;

}

public class DerivedClass extends BaseClass { //More on inheritence and polymorphism later

    public void myMethod() {

        myField = 10; // Accessing protected field from superclass

    }

}
```

### Default (Package-Private, no modifier)

If no access modifier is specified, it is known as the default access level (also called package-private). Members with default access are accessible only within the same package.

- Class Visibility: Accessible within the class itself.
- Package Visibility: Accessible from any class within the same package.
- Subclass Visibility: Not accessible from subclasses outside the package.
- World Visibility: Not accessible from unrelated classes outside the package.

```java
class SomeClass {

    void someMethod() {

        // Some Code

    }

}
```

## Private (private) 
The private modifier restricts access to the specific class where the member is defined. Private members are not accessible from outside the class, including subclasses.

- Class Visibility: Accessible within the class itself.
- Package Visibility: Not accessible from any other class within the same package.
- Subclass Visibility: Not accessible from subclasses.
- World Visibility: Not accessible from any other class in any package.

```java
public class SomeClass {

    private int someField;

    private void someMethod() {

        // Some Code

    }

}
```

Using access modifiers helps in implementing encapsulation, which is a fundamental concept in object-oriented programming. It allows you to control the visibility of classes and members, protecting sensitive data and exposing only what is necessary for other classes to interact with.

Below is a tabulated summary of how access level modifiers work:

![tabulated level access modifiers](<resources/Tabulated_Level_Access_Modifiers.png>)



# Setters And Getters

What's all the fuss? What's the point of having these access modifiers, specifically the private modifier if we can't even access the data? 

Well... This is where setters and getters come in. These are called ***access/mutator methods***.

Getters and setters are methods used to access and modify the private fields (attributes) of a class in a controlled manner, following the principle of encapsulation in object-oriented programming.

Let's break it down.

### Getters

- **Purpose:** Getters are used to retrieve the values of private fields (attributes) from a class.
- **Naming Convention:** Getters typically follow the naming convention ```java getVariableName()```.
- **Usage:** They provide read-only access to the private fields, allowing other classes to retrieve but not modify the values directly.


```java
public class SomeClass {

    private int someDefinedNumber;

    public int getSomeNumber() { //Mind the naming convention

        return this.someDefinedNumber;

    }

}

```
### Setters

- **Purpose:** Setters are used to modify or update the values of private fields (attributes) in a class.
- **Naming Convention:** Setters typically follow the naming convention setVariableName().
- **Usage:** They provide write-only access to the private fields, allowing controlled modification of the values.

```java
public class SomeClass {

    private String myString;

    public void setMyString(String newStringValue) { //Mind the 

        myString = newStringValue;

    }
}
```

getters and setters typically have a public access modifier to allow other classes to access and modify the private fields of a class. This is necessary because private fields are not directly accessible from outside the class, and providing public accessor methods is a common practice to enable controlled access to these fields.

# Best Practices

 Here are some best practices for encapsulation in Java:

 ### Use Private Access Modifiers:

 Declare class fields (attributes) as private to restrict direct access from outside the class. This prevents external code from modifying the state of an object directly.

 ```java
private int someInteger;

private String someString;
 ```

### Provide Accessors and Mutators:

Use public getter (accessor) and setter (mutator) methods to access and modify private fields, respectively. This allows controlled and validated access to class attributes.

```java
private int someInteger;

private String someString;

//This is a getter
public String getSomeString(){

    return this.someString;

}

// This is a setter
public String setSomeString(String newString){
    
    this.someString = newString;

}
```

### Use Constructors for Initialization:

Use constructors to initialise object state. Constructors can set initial values for private fields based on parameters passed during instantiation, if any.

```java
public Class SomeClass{

    private int someInt;

    private String someString;

    public SomeClass(int someInt, String someString){
        this.someInt = someInt;

        this.someString = someString;
    }
}
```

There's more best practices when approaching encapsulation but we're going to keep it simple and basic for now. 



