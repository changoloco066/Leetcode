# 191. Number of 1 Bits

## 📋 Problem Statement

Write a function that takes the binary representation of a positive integer and returns the number of set bits it has (also known as the **Hamming weight**).

A **set bit** is a bit with value `1` in the binary representation.

### Examples:

```
Input: n = 11
Output: 3
Explanation: The input binary string 1011 has three set bits

Input: n = 128
Output: 1
Explanation: The input binary string 10000000 has one set bit

Input: n = 2147483645
Output: 30
Explanation: The binary representation has thirty set bits
```

---

## 💡 Understanding Binary Representation

### Decimal to Binary

Every number can be represented as a sum of powers of 2:

```
11 = 8 + 2 + 1
   = 2³ + 2¹ + 2⁰
   = 1011 (binary)

128 = 2⁷
    = 10000000 (binary)
```

### Counting Set Bits

Simply count how many `1`'s appear in the binary representation:

```
1011 → three 1's → 3
10000000 → one 1 → 1
```

---

## ✅ Solution 1: String Conversion

### Algorithm

1. Convert the integer to its binary string representation
2. Iterate through each character in the string
3. Count how many characters are `'1'`

### LeetCode Code:

```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        String binary = Integer.toBinaryString(n);
        for(char c : binary.toCharArray()) {
            if(c == '1') {
                count++;
            }
        }
        return count;
    }
}
```

### Pros and Cons

**Pros:**
- ✅ Very intuitive and easy to understand
- ✅ Easy to visualize the binary representation
- ✅ Works correctly for all cases

**Cons:**
- ❌ Creates an extra String object (memory overhead)
- ❌ Slightly slower due to string conversion
- ❌ Extra iteration through the string

### Example Walkthrough

```
Input: n = 11

Step 1: Integer.toBinaryString(11) → "1011"
Step 2: Iterate: '1' '0' '1' '1'
Step 3: Count '1's: 3
Output: 3
```

### Complexity

- Time: O(log n) - binary representation has log₂(n) bits
- Space: O(log n) - for the string

---

## ⚡ Solution 2: Bit Manipulation (Recommended)

### Algorithm

**Core Idea:** Use bitwise operators to check and shift bits without converting to string.

1. Check if the last bit is `1` using `n & 1`
2. If last bit is `1`, increment counter
3. Shift all bits right by 1 using `n >> 1`
4. Repeat until `n` becomes 0

### Operators Explained

#### Bitwise AND (`&`)

```
Compares two bits:
1 & 1 = 1
1 & 0 = 0
0 & 1 = 0
0 & 0 = 0

When you do n & 1:
- If the last bit is 1 → result is 1
- If the last bit is 0 → result is 0
```

#### Right Shift (`>>`)

```
Moves all bits one position to the right
1011 >> 1 = 0101 (rightmost bit is discarded)
0101 >> 1 = 0010
0010 >> 1 = 0001
0001 >> 1 = 0000

It's like integer division by 2
```

### LeetCode Code:

```java
class Solution {
    public int hammingWeight(int n) {
        int count = 0;
        while(n > 0) {
            if((n & 1) == 1) {
                count++;
            }
            n = n >> 1;
        }
        return count;
    }
}
```

### Pros and Cons

**Pros:**
- ✅ More efficient (no string creation)
- ✅ Demonstrates bitwise operation knowledge
- ✅ Faster in practice
- ✅ No extra memory for string

**Cons:**
- ❌ Less intuitive for beginners
- ❌ Requires understanding of bitwise operators
- ❌ Harder to visualize

### Example Walkthrough

```
Input: n = 11 (binary: 1011)

Iteration 1:
  n = 1011
  n & 1 = 1011 & 0001 = 0001 (true) → count = 1
  n >> 1 = 0101 (decimal: 5)

Iteration 2:
  n = 0101
  n & 1 = 0101 & 0001 = 0001 (true) → count = 2
  n >> 1 = 0010 (decimal: 2)

Iteration 3:
  n = 0010
  n & 1 = 0010 & 0001 = 0000 (false) → count = 2
  n >> 1 = 0001 (decimal: 1)

Iteration 4:
  n = 0001
  n & 1 = 0001 & 0001 = 0001 (true) → count = 3
  n >> 1 = 0000 (decimal: 0)

Loop ends (n == 0)
Output: 3
```

### Complexity

- Time: O(log n) - same as Solution 1
- Space: O(1) - no extra space needed

---

## 📊 Comparison

| Aspect | String Conversion | Bit Manipulation |
|--------|-------------------|------------------|
| **Intuitiveness** | ⭐⭐⭐⭐⭐ | ⭐⭐⭐ |
| **Performance** | ⭐⭐⭐ | ⭐⭐⭐⭐⭐ |
| **Memory** | More | Less |
| **Learning Value** | Lower | Higher |
| **Real Interview** | Acceptable | Preferred |

---

## 🎯 Key Concepts

### 1. Bitwise AND (`&`)

Used to check if a specific bit is set:
```
n & 1 checks the last bit
n & 2 checks the second-to-last bit
n & 4 checks the third-to-last bit
```

### 2. Right Shift (`>>`)

Used to examine each bit one by one:
```
n >> 0: original number
n >> 1: shift right by 1
n >> 2: shift right by 2
...
```

### 3. Loop Termination

The loop stops when `n == 0` because:
- Eventually all bits are shifted out
- A number with all 0 bits equals 0

---

## 🎓 Related Bit Operations

- `<<` (Left Shift): Multiply by 2
- `|` (OR): Set a bit to 1
- `~` (NOT): Flip all bits
- `^` (XOR): Toggle a bit

---

## Summary

This is an **Easy** problem teaching:
- Binary number representation
- Bitwise operations fundamentals
- Efficient bit manipulation techniques
- Two different approaches to solve the same problem

**Recommended:** Master Solution 2 (Bit Manipulation) - it's essential for advanced problems! 🚀