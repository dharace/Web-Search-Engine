package WebSearchEngine;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class EditDistance {
	
	public static Set<String> suggestedWords(String pathTEXT, String search) throws IOException
	{
		ArrayList<String> tokens = new ArrayList<String>(); 
		tokens = findTokens(pathTEXT);
		ArrayList<String> suggestions= new ArrayList<String>();
		for(int i=0;i<tokens.size();i++)
		{
			int dr = editDistance(tokens.get(i), search);
			if(dr == 0) {
				break;
			}
			else if(dr==1) {				
				if(!(tokens.get(i).contains(".") || tokens.get(i).contains(",") || tokens.get(i).contains(" ")))
				{
					suggestions.add(tokens.get(i));
				}
			}
			
		}
		Set<String> suggestedWords = new HashSet<>(suggestions);
		return suggestedWords;
	}
	
	public static ArrayList<String> findTokens(String pathTEXT) throws IOException
	{
		String txt = "";
		ArrayList<String> tokens = new ArrayList<String>();
		File fol= new File(pathTEXT);
		File[] listoffiles = fol.listFiles();
		String data = "";
		for(File f : listoffiles){
			//System.out.print(f.getName());
			BufferedReader br = new BufferedReader(new FileReader(f));   
			while((txt = br.readLine()) != null) 
		   		   data=data+txt;
		   	  StringTokenizer st = new StringTokenizer(data);  
		        while (st.hasMoreTokens())   
		      	  tokens.add(st.nextToken().toLowerCase());
			 br.close();
			 
		}
		return tokens;
	}

	public static int editDistance(String word1, String word2) {
		int len1 = word1.length();
		int len2 = word2.length();
	 
		// len1+1, len2+1, because finally return dp[len1][len2]
		int[][] dp = new int[len1 + 1][len2 + 1];
	 
		for (int i = 0; i <= len1; i++) {
			dp[i][0] = i;
		}
	 
		for (int j = 0; j <= len2; j++) {
			dp[0][j] = j;
		}
	 
		//iterate though, and check last char
		for (int i = 0; i < len1; i++) {
			char c1 = word1.charAt(i);
			for (int j = 0; j < len2; j++) {
				char c2 = word2.charAt(j);
	 
				//if last two chars equal
				if (c1 == c2) {
					//update dp value for +1 length
					dp[i + 1][j + 1] = dp[i][j];
				} else {
					int replace = dp[i][j] + 1;
					int insert = dp[i][j + 1] + 1;
					int delete = dp[i + 1][j] + 1;
	 
					int min = replace > insert ? insert : replace;
					min = delete > min ? min : delete;
					dp[i + 1][j + 1] = min;
				}
			}
		}
	 
		return dp[len1][len2];
	}
	
	public static void main(String[] args) throws IOException {
		// two arbitrary strings
	}
}