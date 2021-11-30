package WebSearchEngine;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class BruteForce {
	
	public static void BruteForceHandle(String pathTEXT, String search) throws IOException {
		String txt;
        String data="";
        int offset = 0;
		File fol= new File(pathTEXT);
		TextFileCreator obj= new TextFileCreator();
		File[] listoffiles = fol.listFiles();
		
		ArrayList<String> filenames = new ArrayList<String>();
		ArrayList<Integer> occurrence= new ArrayList<Integer>();
		for(File f : listoffiles){
			//System.out.print(f.getName());
			BufferedReader br = new BufferedReader(new FileReader(f));   
			   while((txt = br.readLine()) != null) 
				     data=data+txt;
			   
				   offset = search1(search, data, f.getName());
		       	filenames.add(f.getName());
		       	occurrence.add(offset);
			 br.close();
		}
		/** Sorting highest occurrence of word in file **/
		for(int i=1;i<6;i++){
			System.out.println("File " + filenames.get(filenames.size() - i) +" has occurrence of " + search + " :: "+ occurrence.get(occurrence.size() - i));
		}		
	}
	
	// return offset of first match or N if no match
    public static int search1(String pat, String txt, String fiename) {
       int M = pat.length();
       int N = txt.length();
       ArrayList<Integer>offset=new ArrayList<Integer>();

       for (int i = 0; i <= N - M; i++) {
           int j;
           for (j = 0; j < M; j++) {
               if (txt.charAt(i+j) != pat.charAt(j))
                   break;
           }
           if (j == M)
           {            // found at offset i
        	   offset.add(i);
           }
       }
       //System.out.println(" No of Occurrence is :: " + offset.size());
       //System.out.println();
       return offset.size();                             
   }
}