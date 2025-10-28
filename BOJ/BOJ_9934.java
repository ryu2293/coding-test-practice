import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main{
  public static void main(String[] args) throws IOException {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      StringTokenizer st;
      String line = br.readLine();
      String pat = br.readLine();
      StringBuilder sb = new StringBuilder();
      for(int i=0; i<line.length(); i++){
          sb.append(line.charAt(i));

          if(sb.length() >= pat.length()){
              String mat = sb.substring(sb.length() - pat.length()).toString();
              if(mat.equals(pat)){
                  sb.delete(sb.length() - pat.length(), sb.length());
              }
          }
      }
      String answer = sb.toString();
      if(answer.isEmpty()){
          System.out.println("FRULA");
      }
      else {
          System.out.println(answer);
      }
  }
}