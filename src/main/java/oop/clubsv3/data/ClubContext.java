package oop.clubsv3.data;

import oop.clubsv3.models.Club;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import javax.annotation.PreDestroy;
import java.util.List;

@Repository
public class ClubContext
{
	private static final String NameSpace = "oop.clubsv3.models.Club.";
	
	private final SqlSession session;
	
	public ClubContext(DbConnectionBean fac)
	{
		session = fac.getConnection();
	}
	
	@PreDestroy
	public void destroy()
	{
		session.close();
	}
	
	public Club getClub(int id)
	{
		return session.selectOne(NameSpace + "getByMid", id);
	}
	
	public void deleteClub(int id)
	{
		session.delete(NameSpace + "delete", id);
	}
	
	public void updateOne(Club club)
	{
		session.update(NameSpace + "update", club);
	}
	
	public void create(Club club)
	{
		session.insert(NameSpace + "new", club);
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
