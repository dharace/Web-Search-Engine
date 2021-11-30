package WebSearchEngine;
import java.io.IOException;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class SearchEngine {

	public static void main(String[] args) throws IOException, InterruptedException{
		
		String pathForHTML = "F:\\University of Windsor\\Advance Computing Concepts\\Dhara\\ACCProject\\src\\files";
		String pathForTxt = "F:\\University of Windsor\\Advance Computing Concepts\\Dhara\\ACCProject\\src\\Text";
		
		//Code block for HTML to TEXT Conversion
		HtmlToText.fileConversion(pathForHTML, pathForTxt);
		
		
		Scanner sc = new Scanner(System.in);
		String search;
		
		Set<String> suggestedWords;
		String option;
		while(true) {
			System.out.println("Enter String to Search :: ");
			search = sc.next();
			search = search.toLowerCase();
			
			//call editDistance Algo to get suggested words
			suggestedWords = EditDistance.suggestedWords(pathForTxt, search);
			
			for(String s:suggestedWords) {
				search = s;
				System.out.println(s);
			}
			if(suggestedWords.size() == 0)
			{
				break;
			}
			else {
				System.out.println("Did you mean ?(Y/N) ");
				option = sc.next().toLowerCase();
				if(option.equals("y"))
				{
					break;
				}
				else
				{
					continue;
				}
				
			}
		}
				
		// Brute Force is used to find occurance of words with Offset and Sorting
		BruteForce.BruteForceHandle(pathForTxt,search);
		
		// This Code of block is used to fetch the URLs
		System.out.println("Do you want to check the associated links? (Y/N)");
		String reply = sc.next().toLowerCase();
		if(reply.equals("y"))
			Regex.urlFinder(pathForTxt);
		else
			System.out.println("Thanks for using our search engine!!!");
		sc.close();
	}
}