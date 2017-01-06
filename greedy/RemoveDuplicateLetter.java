

// Given a string which contains only lowercase letters, 
// remove duplicate letters so that every letter appear once and only once. 
// You must make sure your result is the smallest in lexicographical order among all possible results.
public class RemoveDuplicateLetter {

	// stack solution: StringBuilder可以当作一个stack来用
	// 解题思路： 思考的时候想着什么时候可以去重又保证最小次序呢？ 
	// 由左向右扫，当遇到比前一个字母小的字母的时候，如果之前的子串中大于当前的字母的一些字母在后面还有，
	// 当然就可以删掉前面的重复字母，等后面的字母扫到的时候加进去，就可以减小lexical order
    public String removeDuplicateLettersStack(String s) {
       int[] count = new int[26];
       boolean[] selected = new boolean[26];
       char[] cs = s.toCharArray();
       for(char c : cs) count[c - 'a']++;
       Stack<Character> stack = new Stack<>();
       for(char c : cs){
       		int index = c - 'a';
            count[index]--;
            if(selected[index]) continue;
            while(!stack.isEmpty() && c < stack.peek() && count[stack.peek() - 'a'] != 0){
                selected[stack.pop() - 'a'] = false;
            }
            stack.push(c);
            selected[c - 'a'] = true;
       }
       StringBuilder builder = new StringBuilder();
       while(!stack.isEmpty()) builder.append(stack.pop());
       return builder.reverse().toString();
    }

    public String removeDuplicateLettersStringBuilder(String s) {
       int[] count = new int[26];
       boolean[] selected = new boolean[26];
       char[] cs = s.toCharArray();
       for(char c : cs) count[c - 'a']++;
       StringBuilder builder = new StringBuilder();
       for(char c : cs){
           count[c - 'a']--;
           if(selected[c - 'a']) continue;
           for(int i = builder.length() - 1; i >= 0 && c < builder.charAt(i) && count[builder.charAt(i) - 'a'] != 0; i--){
               selected[builder.charAt(i) - 'a'] = false;
               builder.deleteCharAt(i);
           }
           builder.append(c);
           selected[c - 'a'] = true;
       }
       return builder.toString();
    }

    // 每次从头到尾扫第一个只出现一次的字母x，和x之前最小的字母y的pos，则y无论有几个，它一定是在pos的这个
    //（adbcbda，c只有一个，ze一定是a...c...，拿出a，第二次：dbcbd -> bcbd，拿出b，c 第三次：d -> d， 最后结果就是abcd）
    public String removeDuplicateLettersGreedy(String s) {
        int[] cnt = new int[26];
        int pos = 0; // the position for the smallest s[i]
        for (int i = 0; i < s.length(); i++) cnt[s.charAt(i) - 'a']++;
        for (int i = 0; i < s.length(); i++) {
        	// find the smallest pos before cut (cut is letter only appear once)
            if (s.charAt(i) < s.charAt(pos)) pos = i;
            // find the cut and cut the string. (其实在cut之前的所有比cut小的字母都是位置确定的，可以再优化目前算法的，但要再加循环前子串)
            if (--cnt[s.charAt(i) - 'a'] == 0) break;
        }
        // 确定了最小的字母以后，在pos之后的所有该字母都要删掉。找到了cut每次可以唯一确定一个最小字母的位置。
        return s.length() == 0 ? "" : s.charAt(pos) + removeDuplicateLetters(s.substring(pos + 1).replaceAll("" + s.charAt(pos), ""));
    }
}