# All Types of Lambda Expressions in Java

Here's a comprehensive overview of different types of lambda expressions in Java:

## 1. Basic Lambda Patterns

### No Parameters, No Return Value
```java
Runnable task = () -> System.out.println("Simple task");
```

### Single Parameter, No Return Value
```java
// Parentheses optional for single parameter without type
Consumer<String> printer = s -> System.out.println(s);
```

### Multiple Parameters, No Return Value
```java
BiConsumer<String, Integer> describe = (name, age) -> System.out.println(name + " is " + age + " years old");
```

### No Parameters, With Return Value
```java
Supplier<Double> randomValue = () -> Math.random();
```

### Single Parameter, With Return Value
```java
Function<Integer, Integer> square = x -> x * x;
```

### Multiple Parameters, With Return Value
```java
BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
```

## 2. Parameter Variations

### With Explicit Type Declarations
```java
Function<Integer, String> converter = (Integer num) -> String.valueOf(num);
```

### Without Type Declarations (Type Inference)
```java
Function<Integer, String> converter = num -> String.valueOf(num);
```

## 3. Body Variations

### Single Expression (No Curly Braces, Implicit Return)
```java
Predicate<String> isEmpty = s -> s.isEmpty();
```

### Multiple Statements (Requires Curly Braces and Explicit Return)
```java
Function<Integer, Integer> factorial = n -> {
    int result = 1;
    for (int i = 1; i <= n; i++) {
        result *= i;
    }
    return result;
};
```

## 4. Method and Constructor References

### Static Method Reference
```java
Function<String, Integer> parser = Integer::parseInt;
```

### Instance Method Reference of a Particular Object
```java
String prefix = "Mr. ";
Function<String, String> addPrefix = prefix::concat;
```

### Instance Method Reference of an Arbitrary Object of a Particular Type
```java
Function<String, Integer> getLength = String::length;
```

### Constructor Reference
```java
Supplier<ArrayList<String>> listCreator = ArrayList::new;
```

## 5. Specialized Functional Interfaces

### Primitive Specializations
```java
IntPredicate isEven = num -> num % 2 == 0;
LongConsumer printLong = value -> System.out.println(value);
DoubleSupplier pi = () -> 3.14159;
```

### Binary Operator (Same Type for Operands and Result)
```java
BinaryOperator<String> concatenate = (s1, s2) -> s1 + s2;
```

### Unary Operator (Same Type for Input and Result)
```java
UnaryOperator<String> toUpperCase = s -> s.toUpperCase();
```

## 6. Using Lambda with Your Own Functional Interfaces

```java
// Custom functional interface
@FunctionalInterface
interface TriFunction<A, B, C, R> {
    R apply(A a, B b, C c);
}

// Using the custom interface
TriFunction<Integer, Integer, Integer, Integer> sum3 = (a, b, c) -> a + b + c;
```

Each of these patterns has specific use cases where they're most appropriate, making lambdas a powerful and flexible feature in Java for writing more concise and readable code.