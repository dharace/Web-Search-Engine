package WebSearchEngine;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HtmlToText {
	public static void fileConversion(String inputPath, String outputPath) {		
		String s,fileName=""; 
		try {
			File location= new File(inputPath);

			TextFileCreator obj= new TextFileCreator();
			File[] listoffiles = location.listFiles();
			for(File f : listoffiles){
				obj.convertFile(f);
				Document docr = Jsoup.parse(f, "utf-8"); 
				s=docr.text();
				fileName=f.getName();
				fileName=fileName.split("\\.",2)[0];
				fileName=fileName+".txt";
				BufferedWriter writer = new BufferedWriter( new FileWriter(outputPath+fileName));
				writer.write(s);
				writer.close();
			}
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
