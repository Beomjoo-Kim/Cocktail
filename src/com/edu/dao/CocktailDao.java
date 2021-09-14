package com.edu.dao;

import java.util.List;

import com.edu.vo.Cocktail;
import com.edu.vo.CocktailPageBean;

public interface CocktailDao {
	
	/**
	 * 칵테일 정보를 xml 파일에서 로딩 
	 */
	public void loadData();
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 칵테일 정보를  검색해서 리턴 
	 * @param bean 검색 조건과 검색 단어가 있는 객체
	 * @return 조회한 칵테일 목록
	 */	
	public List<Cocktail> searchAll(CocktailPageBean bean);
	/**
	 * 칵테일 코드에 해당하는 칵테일 정보를 검색해서 리턴 
	 * @param cid 검색할 칵테일 코드
	 * @return	식품 코드에 해당하는 칵테일 정보, 없으면 null이 리턴됨
	 */	
	public Cocktail search(int cid);
	
	/**
	 * 검색 조건(key) 검색 단어(word)에 해당하는 칵테일 정보의 개수를 리턴
	 * web에서 구현 
	 * @param bean  검색 조건과 검색 단어가 있는 객체
	 * @return 조회한  식품 개수
	 */
	public int cocktailCount(CocktailPageBean bean);
	
	/**
	 * 가장 많이 검색한 Cocktail 정보 리턴 
	 * web에서 구현 
	 * @return
	 */
	public List<Cocktail> searchBest();
}
