public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        int[] table = new int[128];
        int start = 0, end = 0, counter = t.length(), head = 0, tail = Integer.MAX_VALUE;
        for(int i = 0; i < t.length(); i++){
            table[t.charAt(i)]++;
        }
        while(end < s.length()){
            if(table[s.charAt(end++)]-- > 0) counter--;
            while(counter == 0){
                if(tail - head > end - start){
                    head = start; 
                    tail = end;
                }

                if(table[s.charAt(start++)]++ == 0) counter++;
            }
        }
        
        return tail == Integer.MAX_VALUE ? "" : s.substring(head, tail);
    }
}