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
		//loadData();
	}
	public void loadData() {
//		CID, CNAME, BASE, DEGREE, DESCRIPTION, METHOD, MATERIAL, IMAGEURL
		CocktailSaxParser csp = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		String sql = "INSERT INTO COCKTAIL VALUES(?,?,?,?,?,?,?,?)"; //체크
		
		try {
			csp = new CocktailSaxParser();
			con = DBUtil.getConnection();
			con.setAutoCommit(false); //c
			for (Cocktail c : csp.getCocktails()) {
				// db insert
				pstmt = con.prepareStatement(sql); //c
				pstmt.setInt(1, c.getCid());
				pstmt.setString(2, c.getCname());
				pstmt.setString(3, c.getBase());
				pstmt.setInt(4, c.getDegree());
				pstmt.setString(5, c.getDescription());
				pstmt.setString(6, c.getMethod());
				pstmt.setString(7, c.getMaterial());
				pstmt.setString(8, c.getImageURL());
				pstmt.execute();
				con.commit();
				try {
					pstmt.close();
					//닫아주지 않으면 최대 커서수 초과로 인한 오라클 에러 발생
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			try {
				con.rollback();
				e.printStackTrace();
			} catch (Exception e2) {
				}
		}finally {
			try {
				if(con!=null)con.close();
			} catch (Exception e2) {
			}
		}
	}
	
//	"all","name","base","material"
//		CID, CNAME, BASE, DEGREE, DESCRIPTION, METHOD, MATERIAL, IMAGEURL
	public List<Cocktail> searchAll(CocktailPageBean  bean){
		List<Cocktail> finds = new LinkedList<Cocktail>();
		StringBuffer sqlBuffer = new StringBuffer();
		Connection con = null;
		PreparedStatement pstmt = null;
		sqlBuffer.append("SELECT CID, CNAME, BASE, DEGREE, DESCRIPTION, METHOD, MATERIAL, IMAGEURL FROM COCKTAIL ");
		if(bean !=null) {
			String key = bean.getKey();
			//name일 경우 cname으로 변경
			if(key.equals("name"))key="cname";
			String word = bean.getWord();
			if(!key.equals("all")) {
				sqlBuffer.append("WHERE " + key + " LIKE ? ");
			}
			String sql = sqlBuffer.toString();
			if(!key.isEmpty()) {
				// 구현
				try {
					con = DBUtil.getConnection();
					pstmt = con.prepareStatement(sql);
					if(key!="all") {
						pstmt.setString(1, "%"+word+"%");
					}
					ResultSet rs = pstmt.executeQuery();
					while(rs.next()) {
						Cocktail cocktail = new Cocktail();
						cocktail.setCid(rs.getInt(1));
						cocktail.setCname(rs.getString(2));
						cocktail.setBase(rs.getString(3));
						cocktail.setDegree(rs.getInt(4));
						cocktail.setDescription(rs.getString(5));
						cocktail.setMethod(rs.getString(6));
						cocktail.setMaterial(rs.getString(7));
						cocktail.setImageURL(rs.getString(8));
						finds.add(cocktail);
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if(con!=null)con.close();
						if(pstmt!=null)pstmt.close();
					} catch (Exception e2) {
						e2.printStackTrace();
					}
				}
			}
		}
		return finds;
	}
	
	public Cocktail search(int cid) {
		System.out.println("daoImpl cid = " + cid);
		// cid에 맞는 칵테일 검색하여 리턴
		Cocktail cocktail = new Cocktail();
		StringBuffer sqlBuffer = new StringBuffer();
		Connection con = null;
		PreparedStatement pstmt = null;
		sqlBuffer.append("SELECT CID, CNAME, BASE, DEGREE, DESCRIPTION, METHOD, MATERIAL, IMAGEURL FROM COCKTAIL ");
		sqlBuffer.append("WHERE CID = ? ");
		String sql = sqlBuffer.toString();
		// 구현
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cid);
			System.out.println(cid);
			ResultSet rs = pstmt.executeQuery();
			rs.next();
			System.out.println(rs.toString());
			cocktail.setCid(rs.getInt(1));
			cocktail.setCname(rs.getString(2));
			cocktail.setBase(rs.getString(3));
			cocktail.setDegree(rs.getInt(4));
			cocktail.setDescription(rs.getString(5));
			cocktail.setMethod(rs.getString(6));
			cocktail.setMaterial(rs.getString(7));
			cocktail.setImageURL(rs.getString(8));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if(con!=null)con.close();
				if(pstmt!=null)pstmt.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		
		return cocktail;
	}
	public int cocktailCount(CocktailPageBean  bean){
		return 0;
	}

	public List<Cocktail> searchBest() {
		return null;
	}
	
	public static void main(String[] args) {
		CocktailDaoImpl dao = new CocktailDaoImpl();
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
