# 1. Two Sum

## Problem Statement
Given an array of integers `nums` and an integer `target`, return the indices of the two numbers that add up to the target.

**Constraints:**
- Each input has exactly one solution
- You cannot use the same element twice
- You can return the answer in any order

**Example 1:**
Input: nums = [2,7,11,15], target = 9
Output: [0,1]
Explanation: nums[0] + nums[1] = 2 + 7 = 9

**Example 2:**
Input: nums = [3,2,4], target = 6
Output: [1,2]

**Example 3:**
Input: nums = [3,3], target = 6
Output: [0,1]

---

## Solution 1: Brute Force

### Algorithm

The solution uses two nested loops to check all possible pairs of numbers in the array. The outer loop (i) iterates from index 0 to n-2, while the inner loop (j) starts at i+1 and goes until n-1. For each pair of indices, we check if the sum of nums[i] and nums[j] equals the target. If we find a match, we immediately return the indices [i, j]. If no pair is found after checking all combinations, we return the original array.

The reason j starts at i+1 is crucial: it avoids checking the same pair twice (like [0,1] and [1,0]), prevents using the same element twice, and ensures we only check pairs where j > i, making the algorithm more efficient.

### Code (Copy-paste into LeetCode)
```java

class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++){        

            for (int j = i + 1; j < nums.length; j++ ){   
                if (nums[i] + nums[j] == target)
                    return new int [] {i, j};            
            }                  
        }
        return nums;
    }
    
```

### Complexity Analysis

**Time Complexity:** O(n²)
- Outer loop runs n times
- Inner loop runs (n-1) + (n-2) + ... + 1 times
- Total: approximately n²/2 comparisons

**Space Complexity:** O(1)
- Only uses a constant amount of extra space
- No additional data structures needed

### Example Walkthrough

**Input:** nums = [2, 7, 11, 15], target = 9
i=0, j=1: nums[0] + nums[1] = 2 + 7 = 9 ✓
Return [0, 1]

**Input:** nums = [3, 2, 4], target = 6
i=0, j=1: 3 + 2 = 5 ✗
i=0, j=2: 3 + 4 = 7 ✗
i=1, j=2: 2 + 4 = 6 ✓
Return [1, 2]

---

## Solution 2: HashMap (Optimized)

### Algorithm

Instead of checking all pairs like the brute force approach, we use a **single pass** through the array. The key insight is: if we're looking for two numbers that sum to `target`, and we're currently at `nums[i]`, we need to find if `(target - nums[i])` exists elsewhere in the array.

We use the HashMap to store **complements** (the number we're looking for) as we iterate. For each number, we first check if it exists in our HashMap (meaning we found its complement earlier). If yes, we found our pair! If no, we store what complement would be needed for this current number.

Here's how it works step by step: as we visit each number, we calculate what its complement should be (target - current number), then check if the current number is someone else's complement (by looking it up in the HashMap). If found, we have our answer. If not, we store our complement for future iterations to find.

### Visual Example
```text
**Input:** nums = [2, 7, 11, 15], target = 9
Step 1: i = 0, nums[0] = 2
┌──────────────────────────────────┐
│ Current number: 2                │
│ Looking for: 2 (in HashMap)      │
│ HashMap is empty → Not found     │
│                                  │
│ Store complement:                │
│ target - nums[i] = 9 - 2 = 7    │
│ Put (7, 0) in HashMap            │
└──────────────────────────────────┘
HashMap after step 1:
┌─────────┬─────────┐
│   Key   │  Value  │
├─────────┼─────────┤
│    7    │    0    │  ← "I need 7 to complete with index 0"
└─────────┴─────────┘
Step 2: i = 1, nums[1] = 7
┌──────────────────────────────────┐
│ Current number: 7                │
│ Looking for: 7 (in HashMap)      │
│ Found! 7 exists at index 0       │
│                                  │
│ MATCH FOUND! ✓                   │
│ Return [0, 1]                    │
└──────────────────────────────────┘
Answer: [0, 1] because nums[0] + nums[1] = 2 + 7 = 9

```

**Input:** nums = [3, 2, 4], target = 6
Step 1: i = 0, nums[0] = 3
HashMap: { 3: 0 }  ← Store "need 3 to pair with index 0"
(target - 3 = 3, store at index 0)
Step 2: i = 1, nums[1] = 2
Check: Is 2 in HashMap? No
HashMap: { 3: 0, 4: 1 }  ← Store "need 4 to pair with index 1"
(target - 2 = 4, store at index 1)
Step 3: i = 2, nums[2] = 4
Check: Is 4 in HashMap? YES! At index 1
MATCH FOUND! ✓
Return [1, 2]
Answer: [1, 2] because nums[1] + nums[2] = 2 + 4 = 6

### Why This Works
Traditional thinking:
"For each number, check ALL other numbers"
Time: O(n²)
HashMap thinking:
"For each number, remember what would complete it.
If I see that complement later, I instantly know!"
Time: O(n)

The HashMap acts like a **memory** of all the numbers we've seen and what they're waiting for.

### Code (Copy-paste into LeetCode)
```java
import java.util.Map;
import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> complements = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            Integer complementIndex = complements.get(nums[i]);
            if (complementIndex != null) {
                return new int[]{i, complementIndex};
            }

            complements.put(target - nums[i], i);
        }
        
        return nums;
    }
}
```

### Complexity Analysis

**Time Complexity:** O(n)
- Single loop through the array: n iterations
- Each HashMap operation (get, put): O(1) average
- Total: O(n) - **Much better than O(n²)!**

**Space Complexity:** O(n)
- HashMap can store up to n key-value pairs
- Worst case: we store every element before finding the pair

---

## Comparison: Brute Force vs HashMap

| Metric | Brute Force | HashMap |
|--------|-------------|---------|
| Time Complexity | O(n²) | O(n) |
| Space Complexity | O(1) | O(n) |
| Array size: 1,000 | ~500,000 comparisons | ~1,000 comparisons |
| Array size: 10,000 | ~50,000,000 operations | ~10,000 operations |
| Speedup | - | **5,000x faster!**  |

### Trade-off
- **Brute Force:** Slower but uses no extra memory
- **HashMap:** Much faster but requires extra space to store the map

For most practical cases, the HashMap solution is preferred due to its significant speed advantage.