package WebSearchEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class TextFileCreator {
	
	public static void main(String[] args) {
		File fileLocation= new File("F:\\University of Windsor\\Advance Computing Concepts\\Dhara\\ACCProject\\src\\Text");
		TextFileCreator object= new TextFileCreator();
		File[] listOfFiles = fileLocation.listFiles();
		for(File file : listOfFiles){
			object.convertFile(file);
		}
	}
	
	public void convertFile(File file) {		
		String s,fileName=""; 
		try {
				Document doc = Jsoup.parse(file, "utf-8"); 
				s=doc.text();
				fileName=file.getName();
				fileName=fileName.split("\\.",2)[0];
				fileName=fileName+".txt";
				BufferedWriter writer = new BufferedWriter( new FileWriter("/F:\\University of Windsor\\Advance Computing Concepts\\Dhara\\ACCProject\\src\\Text\\"+fileName));
				writer.write(s);
				writer.close();
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}