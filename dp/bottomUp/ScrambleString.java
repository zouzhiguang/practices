public class ScrambleString {
    public boolean isScramble(String s1, String s2) {
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