package com.edu.service;

import java.util.List;

import com.edu.vo.Cocktail;
import com.edu.vo.CocktailPageBean;

public interface CocktailService {
	
	public List<Cocktail> searchAll(CocktailPageBean bean);
	public Cocktail search(int cid);
	public List<Cocktail> searchBest();
	public List<Cocktail> searchBestIndex();	
}
