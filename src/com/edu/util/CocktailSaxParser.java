package com.edu.util;

import java.util.List;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.edu.vo.Cocktail;

public class CocktailSaxParser {
	private String cockFilePath = "res/cocktail.xml";
	private List<Cocktail> cocks;
 	public CocktailSaxParser() {
		loadData();
	}
 	
	private void loadData() {
		SAXParserFactory factory = SAXParserFactory.newInstance();
		try{
			SAXParser parser = factory.newSAXParser();
			CocktailSAXHandler handler = new CocktailSAXHandler();
			parser.parse(cockFilePath,handler);
			setCocktails(handler.getCocktails());
		}catch(Exception e){
			e.printStackTrace();
		}
	}
	public List<Cocktail> getCocktails() {
		return cocks;
	}
	public void setCocktails(List<Cocktail> cocks) {
		this.cocks = cocks;
	}
	
}
