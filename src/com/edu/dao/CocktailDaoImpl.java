package com.edu.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import com.edu.util.CocktailSaxParser;
import com.edu.util.DBUtil;
import com.edu.vo.Cocktail;
import com.edu.vo.CocktailPageBean;

public class CocktailDaoImpl implements CocktailDao{

	public CocktailDaoImpl() {
		loadData();
	}
	public void loadData() {

		CocktailSaxParser csp = null;
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			csp = new CocktailSaxParser();
			con = DBUtil.getConnection();
			for (Cocktail c : csp.getCocktails()) {
				// db insert
			}
		} catch (Exception e) {
			
		}
	}
	
	public List<Cocktail> searchAll(CocktailPageBean  bean){
		List<Cocktail> finds = new LinkedList<Cocktail>();
		if(bean !=null) {
			String key = bean.getKey();
			String word = bean.getWord();
			if(!key.equals("all") && word!=null && !word.trim().equals("")) {
				// 구현
				
			}

		}
		return finds;
	}
	
	public Cocktail search(int cid) {
		// cid에 맞는 칵테일 검색하여 리턴
		
		return null;
	}
	public int cocktailCount(CocktailPageBean  bean){
		return 0;
	}

	public List<Cocktail> searchBest() {
		return null;
	}
	
	public static void main(String[] args) {
		CocktailDaoImpl dao = new CocktailDaoImpl();
		dao.loadData();
		System.out.println(dao.search(1));
		System.out.println("===========================material로 검색=================================");
		print(dao.searchAll(new CocktailPageBean("material", "보드카", null, 0)));
		System.out.println("===========================base로 검색=================================");
		print(dao.searchAll(new CocktailPageBean("base", "whisky", null, 0)));
		System.out.println("===========================name으로 검색=================================");
		print(dao.searchAll(new CocktailPageBean("name", "칼루아", null, 0)));
		System.out.println("============================================================");
		print(dao.searchAll(null));
		System.out.println("============================================================");
	}
	
	public static void print(List<Cocktail> cocks) {
		for (Cocktail c : cocks) {
			System.out.println(c);
		}
	}
}
