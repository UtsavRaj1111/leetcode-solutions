class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i;
        }
        
        Stack<Character> stack = new Stack<>();
        boolean[] seen = new boolean[26];
        
        for (int i = 0; i < s.length(); i++) {
            char curr = s.charAt(i);
            
            if (seen[curr - 'a']) continue;
            
            while (!stack.isEmpty() && curr < stack.peek() && i < lastIndex[stack.peek() - 'a']) {
                seen[stack.pop() - 'a'] = false;
            }
            
            stack.push(curr);
            seen[curr - 'a'] = true;
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : stack) {
            sb.append(c);
        }
        
        return sb.toString();
    }
}