package com.edu.util;

import java.util.ArrayList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.edu.vo.Cocktail;

public class CocktailSAXHandler extends DefaultHandler {
	private List<Cocktail> cocktails;
	private Cocktail cock;
	private String temp;
	public CocktailSAXHandler(){
		cocktails = new ArrayList<Cocktail>();
	}
	public void startElement(String uri, String localName
			                           , String qName, Attributes att){
		if(qName.equals("row")){
			cock = new Cocktail();
		}
	}
	public void endElement(String uri, String localName, String qName){
		if(qName.equals("cid")) { 
			cock.setCid(Integer.parseInt(temp));
		}else if(qName.equals("cname")) { 
			cock.setCname(temp);
		}else if(qName.equals("base")) { 
			cock.setBase(temp);
		}else if(qName.equals("degree")) { 
			for (int i = 0; i < temp.length(); i++) {
				char ch = temp.charAt(i);
				if(ch < 48 || ch > 58) {
					cock.setDegree(0);
					return;
				}
			}
			cock.setDegree(Integer.parseInt(temp));
		}else if(qName.equals("description")) { 
			cock.setDescription(temp);
		}else if(qName.equals("method")) { 
			cock.setMethod(temp);
		}else if(qName.equals("material")) { 
			cock.setMaterial(temp);
		}else if(qName.equals("imageURL")) { 
			cock.setImageURL(temp);
		}else if(qName.equals("snack") || qName.equals("bar")) { 
		}else if(qName.equals("row")) { 
			cocktails.add(cock);
		}
	}
	public void characters(char[]ch, int start, int length){
		temp = new String(ch, start, length);
	}
	public List<Cocktail> getCocktails() {
		return cocktails;
	}
	public void setCocktails(List<Cocktail> cocks) {
		this.cocktails = cocks;
	}
}







