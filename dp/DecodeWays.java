/**
* see: https://leetcode.com/problems/decode-ways/
**/
public class DecodeWays {
    public int numDecodings(String s) {
     	
     	//  state[i] is s[0...i]’s ways of decoding.
		//  init: state[0] = 0;
		//  state transfer: 
		//    if chars[i] == '0'
        //  	if( chars[i-1] > 2 || chars[i-1] == '0' ) return 0
        //  	else state[i] = state[i-2]
        //    else
        //      int val = (chars[i-1] - ‘0’) * 10 + (chars[i] - ‘0’);
        //      if (val >= 10 and val <= 26) state[i] = state[i-2] + state[i-1]
        //      else state[i] = state[i-1]
		//  result: state[s.length]
   		
   		if(s == null || s.isEmpty() || s.charAt(0) == '0') return 0;
   		int[] state = new int[s.length() + 1];
   		state[0] = 1;
   		state[1] = 1;
   		for(int i = 2; i < state.length; i++){
   		    char current = s.charAt(i-1);
   		    char prev = s.charAt(i-2);
   			if(current == '0'){
   				if(prev != '1' && prev != '2') return 0;
   					state[i] = state[i-2];
   				} else if(current >= '1' && current <= '9'){
   				    int val = (prev - '0') * 10 + current - '0';
   				    if(val >= 10 && val <= 26){
   				        state[i] = state[i-1] + state[i-2];
   				    }else{
   				        state[i] = state[i-1];
   				    }
   				}else{
   				    // not a number
   				    return 0;
   				}
   		}

   		return state[s.length()];
    }
}