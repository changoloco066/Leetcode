# Understanding HashMap in Java

## What is a HashMap?

A HashMap is a data structure that stores **key-value pairs** and provides **O(1) average lookup time**. Think of it like a real-world dictionary where you look up a word (key) to find its definition (value).

### Visual Representation
HashMap Structure:

```text
┌─────────┬─────────┐
│   Key   │  Value  │
├─────────┼─────────┤
│   "a"   │    1    │
│   "b"   │    2    │
│   "c"   │    3    │
└─────────┴─────────┘
```

In Java:
```java
Map<String, Integer> map = new HashMap<>();
map.put("a", 1);
map.put("b", 2);
map.put("c", 3);
```

---

## Key Operations

### 1. Put (Insert/Update)
```java
Map<Integer, String> map = new HashMap<>();
map.put(1, "One");    // Insert
map.put(2, "Two");    // Insert
map.put(1, "Uno");    // Update (key 1 already exists)
```

**Time Complexity:** O(1) average

### 2. Get (Retrieve)
```java
String value = map.get(1);  // Returns "Uno"
String missing = map.get(5); // Returns null (key doesn't exist)
```

**Time Complexity:** O(1) average

### 3. ContainsKey (Check existence)
```java
boolean exists = map.containsKey(1);  // Returns true
boolean missing = map.containsKey(5); // Returns false
```

**Time Complexity:** O(1) average

### 4. Remove
```java
map.remove(1);  // Removes key 1 and its value
```

**Time Complexity:** O(1) average

---

## How HashMap Works Internally

### The Hashing Process
Step 1: Hash Function
Key → Hash Code (integer)
Example: "apple" → 93029210
Step 2: Index Calculation
Hash Code → Array Index
93029210 % 16 = 10
(assuming array size is 16)
Step 3: Storage
Store key-value pair at index 10

### Visual Example
Input: put("apple", 5)

Calculate hash: "apple" → 93029210
Calculate index: 93029210 % 16 = 10
Store at index 10:
``` text
Internal Array:
┌────┬─────────────┐
│  0 │   null      │
│  1 │   null      │
│  2 │   null      │
│... │   ...       │
│ 10 │ "apple"→5   │  ← Stored here
│ 11 │   null      │
│... │   ...       │
│ 15 │   null      │
└────┴─────────────┘
```

---

## Handling Collisions

Sometimes different keys produce the same index. HashMap handles this with **chaining**.

### Collision Example
put("apple", 5)  → hash % 16 = 10
put("banana", 3) → hash % 16 = 10  (collision!)
Internal structure at index 10:
```text
┌───────────────────────────────┐
│ Index 10:                     │
│ ┌─────────┐   ┌──────────┐    │
│ │"apple"→5│→→ │"banana"→3│    │
│ └─────────┘   └──────────┘    │
│   (Linked list of entries)    │
└───────────────────────────────┘
```

When you do `get("banana")`:
1. Calculate hash → index 10
2. Go to index 10
3. Traverse the linked list
4. Find "banana" → return 3

**Note:** Good hash functions minimize collisions, keeping operations close to O(1).

---

## Real-World Analogy

### Library Book System

Imagine a library where:
- **Keys** = Book ISBN numbers
- **Values** = Shelf locations
Traditional search (like ArrayList):
"Where is book ISBN-12345?"
→ Check shelf 1, shelf 2, shelf 3...
→ Time: O(n) - could check all shelves
HashMap approach:
"Where is book ISBN-12345?"
→ Calculate: ISBN-12345 % 100 = 45
→ Go directly to shelf 45
→ Time: O(1) - instant lookup!

---

## When to Use HashMap

### ✅ Good Use Cases

1. **Fast lookups** - Need to find values quickly by key
```java
   // Student ID → Student Name
   Map<Integer, String> students = new HashMap<>();
   students.put(101, "Alice");
   students.put(102, "Bob");
   String name = students.get(101); // Instant lookup
```

2. **Counting occurrences**
```java
   // Count word frequency
   Map<String, Integer> wordCount = new HashMap<>();
   for(String word : words){
       wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
   }
```

3. **Caching/Memoization**
```java
   // Store computed results
   Map<Integer, Integer> fibCache = new HashMap<>();
```

4. **Removing duplicates**
```java
   // Keys are automatically unique
   Map<Integer, Boolean> seen = new HashMap<>();
```

### ❌ Bad Use Cases

1. **Maintaining order** - Use `LinkedHashMap` instead
2. **Sorting** - HashMap doesn't maintain sorted order
3. **Small datasets** - Overhead not worth it (use ArrayList)
4. **Thread-safe operations** - Use `ConcurrentHashMap` instead

---

## HashMap vs Other Data Structures

| Operation        | ArrayList | HashMap | TreeMap    |
|------------------|-----------|---------|------------|
| Get by index/key | O(1)      | O(1)    | O(log n)   |
| Search by value  | O(n)      | O(n)    | O(n)       |
| Insert           | O(1)*     | O(1)    | O(log n)   |
| Delete           | O(n)      | O(1)    | O(log n)   |
| Ordered          | ✅       | ❌      | ✅ (sorted)|
| Memory           | Low       | Medium  | Medium     | 

*ArrayList insert is O(1) at end, O(n) in middle

---

## Common Patterns in LeetCode

### Pattern 1: Finding Complements (Two Sum)
```java
Map<Integer, Integer> seen = new HashMap<>();
for(int i = 0; i < nums.length; i++){
    if(seen.containsKey(target - nums[i])){
        return new int[]{seen.get(target - nums[i]), i};
    }
    seen.put(nums[i], i);
}
```

### Pattern 2: Frequency Counter
```java
Map<Character, Integer> freq = new HashMap<>();
for(char c : s.toCharArray()){
    freq.put(c, freq.getOrDefault(c, 0) + 1);
}
```

### Pattern 3: Seen Tracker
```java
Map<Integer, Boolean> visited = new HashMap<>();
for(int num : nums){
    if(visited.containsKey(num)){
        return true; // Duplicate found
    }
    visited.put(num, true);
}
```

---

## Important Notes

1. **Null keys/values:** HashMap allows one null key and multiple null values
2. **Not synchronized:** Not thread-safe by default
3. **Load factor:** When HashMap is 75% full, it doubles in size (rehashing)
4. **Initial capacity:** Default is 16, but you can specify:
```java
   Map<Integer, String> map = new HashMap<>(100); // Initial capacity 100
```

---

## Summary

**HashMap is perfect when you need:**
- Fast lookups by key
- Fast insertions
- Fast deletions
- Unique keys

**Trade-off:**
- Uses extra memory
- No ordering guarantee
- Slightly slower than array for small datasets

**Golden Rule:** If you find yourself writing nested loops to search for something, consider if a HashMap could reduce it to a single loop!