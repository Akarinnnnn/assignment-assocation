package oop.clubsv3.data;

import oop.clubsv3.models.Club;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.DisposableBean;

import java.util.List;

public class MemberContext implements DisposableBean
{
	private static final String NameSpace = "member.";
	
	private final SqlSession session;
	
	public MemberContext(DbConnectionBean fac)
	{
		session = fac.getConnection();
	}
	
	@Override
	public void destroy()
	{
		session.close();
	}
	
	public Club getMember(int id)
	{
		return session.selectOne(NameSpace + "getByName", id);
	}
	
	public List<Club> searchByName(String name)
	{
		return session.selectList(NameSpace + "search", name);
	}
	
	// 游标日后再说
	public List<Club> getOnePage(int pageNumber)
	{
		int offset = pageNumber * 10;
		return session.selectList(NameSpace+"queryOnePage", offset);
	}
}
