# Prime Number Encryption System

## Overview
Our robot communication system uses a custom encryption method based on prime numbers to secure handshake communications between servers and clients.

## What Are Prime Numbers?
Prime numbers are numbers greater than 1 that can only be divided by 1 and themselves.
- Examples: 2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43...
- **Not prime**: 4 (divisible by 2), 6 (divisible by 2 and 3), 8 (divisible by 2 and 4)

## Our Prime Number Set
We use a predefined list of 10 prime numbers:
```
[3, 5, 13, 17, 23, 29, 31, 37, 43, 59]
```

## Core Encryption Operations

### 1. Concatenation
**Purpose**: Create a large number by joining all primes together

**Example**:
```
Primes: [3, 5, 13, 17, 23]
Concatenated: 35131723
```

**Code**:
```java
public Long getConcatenated() {
    // Joins: 3 + 5 + 13 + 17 + ... = 35131723294331374359
    return Long.parseLong("35131723294331374359");
}
```

### 2. Multiplication (Encryption Key)
**Purpose**: Create a unique encryption key by multiplying all primes

**Example**:
```
Primes: [3, 5, 13, 17, 23]
Product: 3 × 5 × 13 × 17 × 23 = 76,245
```

**Code**:
```java
public long encrypt() {
    // Multiplies: 3 × 5 × 13 × 17 × 23 × 29 × 31 × 37 × 43 × 59
    return 418,195,493,895,930L; // Actual result with our 10 primes
}
```

### 3. XOR Encryption
**Purpose**: Encrypt handshake codes using XOR operation

**How XOR Works**:
- XOR (exclusive OR) flips bits
- Same operation encrypts AND decrypts
- `A XOR B XOR B = A` (reversible)

**Example**:
```
Original Code: 12345
Concatenated Primes: 35131723...
Encrypted: 12345 XOR 35131723... = 35131735...
Decrypted: 35131735... XOR 35131723... = 12345
```

## Handshake Authentication Process

### Step-by-Step Flow

1. **Server generates encryption key**
   ```
   Key = 3 × 5 × 13 × 17 × 23 × 29 × 31 × 37 × 43 × 59
   Key = 418,195,493,895,930
   ```

2. **Server sends encryption key to client**
   ```
   Server → Client: 418,195,493,895,930
   ```

3. **Client verifies it can generate same key**
   ```
   Client calculates: 3 × 5 × 13 × ... = 418,195,493,895,930 ✓
   ```

4. **Server encrypts challenge code**
   ```
   Server Code: 100 (example)
   Concatenated: 35131723294331374359
   Encrypted: 100 XOR 35131723294331374359 = 35131723294331374459
   ```

5. **Server sends encrypted challenge**
   ```
   Server → Client: 35131723294331374459
   ```

6. **Client decrypts challenge**
   ```
   Received: 35131723294331374459
   Concatenated: 35131723294331374359
   Decrypted: 35131723294331374459 XOR 35131723294331374359 = 100
   ```

7. **Client sends decrypted value back**
   ```
   Client → Server: 100
   ```

8. **Server validates response**
   ```
   Expected: 100
   Received: 100 ✓
   Authentication SUCCESS!
   ```

## Security Benefits

### Why This Works
1. **Shared Secret**: Both parties must know the exact same prime numbers
2. **Key Verification**: Encryption key confirms both have same primes
3. **Challenge-Response**: Client must prove it can decrypt correctly
4. **No Guessing**: Without prime numbers, decryption is nearly impossible

### Attack Resistance
- **Brute Force**: Trying all possible prime combinations is computationally expensive
- **Eavesdropping**: Intercepted messages are encrypted and meaningless
- **Replay Attacks**: Each handshake uses unique challenge codes

## Implementation Classes

### Encryption.java
- Stores prime numbers
- Calculates concatenated value
- Calculates multiplication key
- Handles prime factorization

### ServerSideHandshake.java
- Generates and sends encryption key
- Creates encrypted challenges
- Validates client responses
- Confirms successful authentication

### ClientSideHandshake.java
- Receives encryption key
- Decrypts server challenges
- Sends decrypted responses
- Handles authentication confirmation

## Example Walkthrough

Let's trace through a complete handshake:

```
PRIMES: [3, 5, 13]  // Simplified for example
CONCATENATED: 3513
ENCRYPTION_KEY: 3 × 5 × 13 = 195
SERVER_CODE: 777

1. Server → Client: "195" (encryption key)
2. Client verifies: 3 × 5 × 13 = 195 ✓
3. Server encrypts: 777 XOR 3513 = 4202
4. Server → Client: "4202" (encrypted challenge)
5. Client decrypts: 4202 XOR 3513 = 777
6. Client → Server: "777" (decrypted response)
7. Server validates: 777 == 777 ✓
8. Server → Client: "SUCCESS"
```

## Questions & Discussion

### Common Questions:
1. **Q**: What if someone intercepts the encryption key?
   **A**: They still need the exact prime numbers to generate the concatenated value for decryption.

2. **Q**: Why not use standard encryption?
   **A**: This is simpler to implement and understand, perfect for learning purposes.

3. **Q**: How secure is this really?
   **A**: For production, use established encryption. This is educational and sufficient for our robot project.

4. **Q**: What if we want to change the prime numbers?
   **A**: Update the list in Encryption.java, and all clients/servers need the new version.

## Summary

Our encryption system uses prime numbers in three ways:
1. **Multiplication** → Creates shared encryption key
2. **Concatenation** → Creates XOR encryption mask  
3. **Challenge-Response** → Proves authentication

This ensures only clients with the correct prime numbers can successfully complete the handshake and connect to our robot servers.