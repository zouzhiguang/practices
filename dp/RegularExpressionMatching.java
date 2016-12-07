// see https://leetcode.com/problems/regular-expression-matching/
public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        
    }


    private boolean matchSingle(String s, String p, int i, int j){
    	return p.charAt(j-1) == '.' || s.charAt(i - 1) == p.charAt(j - 1);
    }
}