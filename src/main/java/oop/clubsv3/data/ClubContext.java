package oop.clubsv3.data;

import oop.clubsv3.controllers.ClubController;
import oop.clubsv3.models.Club;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import javax.xml.stream.events.Namespace;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

@Repository
@Scope("request")
public class ClubContext implements DisposableBean
{
	private static final String NameSpace = "club.";
	
	private final SqlSession session;
	
	public ClubContext(DbConnectionBean fac)
	{
		session = fac.getConnection();
	}
	
	@Override
	public void destroy()
	{
		session.close();
	}
	
	public Club getClub(int id)
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
