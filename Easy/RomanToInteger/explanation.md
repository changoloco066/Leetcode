# 13. Roman to Integer

## Problem
Roman numerals are represented by seven different symbols:

| Symbol | Value |
|--------|-------|
| I | 1 |
| V | 5 |
| X | 10 |
| L | 50 |
| C | 100 |
| D | 500 |
| M | 1000 |

Roman numerals are usually written largest to smallest from left to right. However, there are special cases where subtraction is used:
- `I` before `V` (5) or `X` (10) = 4 or 9
- `X` before `L` (50) or `C` (100) = 40 or 90
- `C` before `D` (500) or `M` (1000) = 400 or 900

Given a roman numeral, convert it to an integer.

**Examples:**
- Input: `"III"` → Output: `3`
- Input: `"LVIII"` → Output: `58` (L=50, V=5, III=3)
- Input: `"MCMXCIV"` → Output: `1994` (M=1000, CM=900, XC=90, IV=4)

---

## Solution: HashMap with Subtraction Check

### Algorithm

This solution uses a **HashMap** to store the roman numeral values and processes the string from left to right. The key insight is recognizing when subtraction occurs: if the current symbol has a smaller value than the next symbol, we're in a subtraction case (like IV, IX, XL, etc.).

When we detect a subtraction case, we calculate the result directly (next - current) and skip both characters by advancing the index by 2. Otherwise, we simply add the current value and move to the next character.

### Code
```java
class Solution {
    public int romanToInt(String s) {
        Map<Character, Integer> romanMap = new HashMap<>();
        romanMap.put('I', 1);
        romanMap.put('V', 5);
        romanMap.put('X', 10);
        romanMap.put('L', 50);
        romanMap.put('C', 100);
        romanMap.put('D', 500);
        romanMap.put('M', 1000);

        int sum = 0;
        int i = 0;
        int n = s.length();

        while (i < n){
            if(i < n - 1 && romanMap.get(s.charAt(i)) < romanMap.get(s.charAt(i + 1))){
                sum += romanMap.get(s.charAt(i + 1)) - romanMap.get(s.charAt(i));
                i += 2;
            } else {
                sum += romanMap.get(s.charAt(i));
                i += 1; 
            }
        }
        return sum; 
    }
}
```

### Complexity
- **Time:** O(n) - Single pass through the string
- **Space:** O(1) - HashMap has fixed size (7 entries)

---

## Visual Example

**Input:** `s = "MCMXCIV"` (1994)

```
Step 1: i=0, 'M'
M(1000), next is C(100)
1000 > 100 → No subtraction
sum = 0 + 1000 = 1000
i = 1

Step 2: i=1, 'C'
C(100), next is M(1000)
100 < 1000 → Subtraction case! (CM = 900)
sum = 1000 + (1000 - 100) = 1900
i = 3 (skip both C and M)

Step 3: i=3, 'X'
X(10), next is C(100)
10 < 100 → Subtraction case! (XC = 90)
sum = 1900 + (100 - 10) = 1990
i = 5 (skip both X and C)

Step 4: i=5, 'I'
I(1), next is V(5)
1 < 5 → Subtraction case! (IV = 4)
sum = 1990 + (5 - 1) = 1994
i = 7 (end of string)

Result: 1994

```

## Summary

- **Problem Type:** String manipulation, HashMap
- **Key Insight:** Detect subtraction by comparing current value with next value
- **Optimization:** Skip both characters when subtraction is detected (i += 2)
