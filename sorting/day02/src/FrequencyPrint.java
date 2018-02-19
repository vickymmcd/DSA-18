import java.util.*;

public class FrequencyPrint {

    static String frequencyPrint(String s) {
        HashMap<String, Integer> words = new HashMap<>();
        HashMap<Integer, String> counts = new HashMap<>();
        String result = "";
        String word = "";
        String currWord = "";

        for(int i=0; i<s.length(); i++){
            if(s.charAt(i) == ' '){
                if(words.containsKey(currWord)){
                    words.put(currWord, words.get(currWord)+1);
                }
                else{
                    words.put(currWord, 1);
                }
                currWord = "";
            }
            else if( i==s.length()-1){
                currWord = currWord + s.charAt(i);
                if(words.containsKey(currWord)){
                    words.put(currWord, words.get(currWord)+1);
                }
                else{
                    words.put(currWord, 1);
                }
                currWord = "";
            }
            else{
                currWord = currWord + s.charAt(i);
            }
        }

        for (Map.Entry<String, Integer> entry : words.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
            if(counts.containsKey(value)){
                counts.put(value, counts.get(value)+" " + key);
            }
            else{
                counts.put(value, key);
            }
        }

        List<Integer> sortedKeys=new ArrayList<>(counts.keySet());
        Collections.sort(sortedKeys);

        for(Integer i: sortedKeys){
            word = counts.get(i);
            while(word.indexOf(' ') != -1){
                for(int k=0; k<i; k++) {
                    result = result + word.substring(0, word.indexOf(' ')) + " ";
                }
                word = word.substring(word.indexOf(' ')+1);
            }
            for(int k=0; k<i; k++) {
                result = result + word + " ";
            }

        }

        return result;
    }

}
