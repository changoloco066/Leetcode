# 12. Integer to Roman

## 📋 Problem Statement

Given an integer, convert it to a Roman numeral.

Roman numerals are represented by seven symbols:

| Symbol | Value |
|--------|-------|
| I      | 1     |
| V      | 5     |
| X      | 10    |
| L      | 50    |
| C      | 100   |
| D      | 500   |
| M      | 1000  |

The input is guaranteed to be in the range `1 <= num <= 3999`.

### Examples:

```
Input: num = 3
Output: "III"

Input: num = 58
Output: "LVIII"
Explanation: L = 50, V = 5, III = 3

Input: num = 1994
Output: "MCMXCIV"
Explanation: M = 1000, CM = 900, XC = 90, IV = 4
```

---

## 💡 Understanding the Problem

### Subtractive Notation

Some numbers are not written by simply adding symbols. Instead, a smaller symbol placed before a larger one means subtraction:

```
IV = 4     (5 - 1)
IX = 9     (10 - 1)
XL = 40    (50 - 10)
XC = 90    (100 - 10)
CD = 400   (500 - 100)
CM = 900   (1000 - 100)
```

### The Key Idea

Instead of applying the subtraction rule, we can include these six pairs as **values in a table**. Then every number is just a sum of table values, and we only need to ask: *how many times does each value fit?*

```
1000 → M      900 → CM     500 → D     400 → CD
 100 → C       90 → XC      50 → L      40 → XL
  10 → X        9 → IX       5 → V       4 → IV
   1 → I
```

---

## ✅ Solution: Greedy Approach

### Algorithm

1. Store the 13 values and their symbols, ordered from largest to smallest
2. Go through the table one value at a time
3. For each value, while `num` is at least that value:
   - Append its symbol to the result
   - Subtract the value from `num`
4. When `num` reaches 0, the result is complete

### Code (For LeetCode)

```java
class Solution {
    public String intToRoman(int num) {
        int[] valores = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] simbolos = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < valores.length; i++) {
            while (num >= valores[i]) {
                result.append(simbolos[i]);
                num = num - valores[i];
            }
        }

        return result.toString();
    }
}
```

### Walkthrough: `num = 1994`

```
valores:  1000  900  500  400  100  90  50  40  10  9  5  4  1
simbolos:  M    CM    D   CD    C   XC  L   XL  X   IX V  IV  I

i = 0 (1000): 1994 >= 1000 → append "M",  num = 994
              994 >= 1000? no
i = 1 (900):  994 >= 900   → append "CM", num = 94
               94 >= 900? no
i = 2..4:     94 is smaller than 500, 400, 100 → skip
i = 5 (90):   94 >= 90     → append "XC", num = 4
                4 >= 90? no
i = 6..10:    4 is smaller than 50, 40, 10, 9, 5 → skip
i = 11 (4):   4 >= 4       → append "IV", num = 0
                0 >= 4? no
i = 12 (1):   0 >= 1? no

Result: "MCMXCIV" ✓
```

### Why the `while` Is Needed

The `for` moves to the next value, but a value can appear several times. The `while` keeps appending the same symbol while `num` is still big enough:

```
num = 3:  the value 1 fits 3 times → "I" + "I" + "I" = "III"
```

### Complexity

- **Time:** O(1). The table has a fixed 13 entries, and the output has at most 15 symbols for `num <= 3999`.
- **Space:** O(1) for the table and the result, which is bounded in size.

---

## 🎯 Key Takeaways

1. Subtractive pairs (`IV`, `IX`, `XL`, ...) can be stored as regular table values
2. Ordering the table from largest to smallest guarantees a correct greedy result
3. The `for` picks **which** value to check, the `while` decides **how many times** it fits
4. Using `StringBuilder` avoids creating a new string on every append

---

## Summary

This is a **Medium** problem that teaches:
- Greedy algorithms
- Table-driven logic
- Building strings efficiently with `StringBuilder`

**Master this and you'll handle many number-conversion problems!** 🚀