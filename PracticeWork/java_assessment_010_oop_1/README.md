---
id: OOP-010
theme: OOP 
keywords: 
   - encapsulation
   - abstraction
   - money
---
# Encapsulation 1

## Starter Code

Examine the code in `Account.java` and its three sub-classes. You will notice that where ever a money amount is required or returned -- in the methods

- `getAvailableBalance`
- `creditAmount`
- `debitAmount`

the code uses `float` values to represent money.

This is a **Very Bad Thing**!

In real-world banking (or any other application involving money) we would *never* use a `float` variable to hold or calculate money values. `float` values are an *approximate* representation of a Real number (in the math sense of "Real"). They are not only inexact, but suffer from a number of mysterious failure modes where simple computations will yield the "wrong" answer. Because `float` values are approximations, you might do some calculation with them, and then test whether they're `equal` to a value, and... they're *not* equal. 

Don't use `float` unless you're doing scientific programming. And then you'll want to learn much more about how `float` works and its limitations and gotchas.

And remember that `double` values are just `float` values with more room for precision, so even *more* fertile ground for numbers to not be exactly the same.

*So what should we do instead?*

## Step 1: Create a `Money` Class

For this assessment:

Create a class `Money` that stores a money quantity as a `long`: the money amount **in cents**.

The class must provide the following operations on `Money` objects:

- `Money add(Money amount)` Adds an arbitrary `Money` amount to the receiver `Money` object, and returns the result as a `Money` instance.
- `Money subtract(Money amount)` Same as `add`, but subtracting.
- `String formatAsRands()` Format the `Money` object that receives this message as a String of the form "R`rand-value`.`2-digit-cents-value`". (e.g. R12.03, R-99.00)

In this step, your code must pass the unit-tests provided in the `MoneyTest` class. You are encouraged to add _more_ tests you may feel are necessary. Any changes to the provided tests will be overwritten.

## Discussion: Mutable Money vs. Immutable Money?

Think about whether to make the `add` and `subtract` methods

* _change the value of the receiving `Money` object_ or to
* _return an entirely new `Money` object representing the result value_

Discuss with your colleagues the consequences of these choices. In the first case we'd be making `Money` instances **mutable** -- their value can change, while in the second case we'd be deciding to make `Money` instances **immutable**.

Which do you think is better? Why?

**Pro Tip:**

In order to make all the unit tests in `MoneyTest` pass, you may find it necessary to implement `equals` and `hashCode` methods in your `Money` implementation, overriding the methods inherited from `Object`.

## Step 2: Convert the application to use `Money` objects instead of `float` values

Once you have written the `Money` class and your implementation passes the tests in `MoneyTest`, change the `Account` class and its sub-classes to store `Money` objects instead of `float`. i.e. Make the unit-tests for `Account`, `SavingsAccount`, `CreditAccount` and `BusinessAccount` pass.
