

public class ScrambleString {

	// when we cut s1 in the middle i, either s1[0...i] match with s2[0...i] or s2[i...n], and can iteratively check if it's match.
	public boolean isScrambleDivideNConquer(String s1, String s2) {
		if(s1.length()!=s2.length()) return false;
	    if(s1.length()==0 || s1.equals(s2)) return true;
	    char[] arr1 = s1.toCharArray();
	    char[] arr2 = s2.toCharArray();
	    Arrays.sort(arr1);
	    Arrays.sort(arr2);
	    if(!new String(arr1).equals(new String(arr2))) return false;

	    // check from 1 to the n, it's 
	    for(int i=1; i<s1.length(); i++){
	        String s11 = s1.substring(0, i);
	        String s12 = s1.substring(i, s1.length());
	        String s21 = s2.substring(0, i);
	        String s22 = s2.substring(i, s2.length());
	        String s23 = s2.substring(0, s2.length()-i);
	        String s24 = s2.substring(s2.length()-i, s2.length());
 
	        if(isScramble(s11, s21) && isScramble(s12, s22))
	            return true;
	        if(isScramble(s11, s24) && isScramble(s12, s23))
	            return true;    
	    }    
	    
	    return false;
	}

	// see explanation in readme.md
    public boolean isScrambleDP(String s1, String s2) {
        if((s1 == null && s2 == null) || s1.equals(s2)){
            return true;
        }
        
        if(s1.length() != s2.length()){
            return false;
        }
        
        int length = s1.length();

        boolean[][][] state = new boolean[length][length][length + 1];
        
        for(int i = 0; i < length; i++){
            for(int j = 0; j < length; j++){
                state[i][j][1] = s1.charAt(i) == s2.charAt(j);
            }
        }

        for(int len = 2; len <= length; len++){
            for(int i = 0; i <= length - len; i++){
               for(int j = 0; j <= length - len; j++){ 
                    for(int k = 1; k < len; k++){
                        state[i][j][len] = state[i][j][len] || state[i][j][k] & state[i + k][j + k][len - k] || state[i][j + len - k][k] && state[i + k][j][len - k];
                    }
               } 
            }
        }
        
        return state[0][0][length];
    }
}