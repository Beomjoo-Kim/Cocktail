package com.edu.service;

import java.util.LinkedList;
import java.util.List;

import com.edu.dao.CocktailDao;
import com.edu.dao.CocktailDaoImpl;
import com.edu.vo.Cocktail;
import com.edu.vo.CocktailPageBean;

public class CocktailServiceImpl implements CocktailService{
	
	private CocktailDaoImpl cocktailDao;
	
	
	
	public CocktailServiceImpl() {
		if(cocktailDao==null) {
			cocktailDao = new CocktailDaoImpl();
		}
	}

	@Override
	public List<Cocktail> searchAll(CocktailPageBean bean) {
		List<Cocktail> list = new LinkedList<Cocktail>();
		try {
			list = cocktailDao.searchAll(bean);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Cocktail search(int cid) {
		Cocktail searchResult = new Cocktail();
		try {
			System.out.println("serviceImpl cid = " + cid);
			searchResult = cocktailDao.search(cid);
			System.out.println("serviceImpl search result = " + searchResult.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return searchResult;
	}

	@Override
	public List<Cocktail> searchBest() {
		return null;
	}

	@Override
	public List<Cocktail> searchBestIndex() {
		return null;
	}

}
