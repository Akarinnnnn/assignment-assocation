package oop.clubsv3.data;

import oop.clubsv3.models.Club;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClubContext
{
	private static final String NameSpace = "oop.clubsv3.models.Club.";
	private final DbConnectionBean fac;
	
	
	public ClubContext(DbConnectionBean fac)
	{
		this.fac = fac;
	}
	
	
	public Club getClub(int id)
	{
		try (var session = fac.getConnection())
		{
			return session.selectOne(NameSpace + "getById", id);
		}
	}
	
	public void deleteClub(int id)
	{
		try (var session = fac.getConnection())
		{
			session.delete(NameSpace + "delete", id);
			session.commit();
		}
	}
	
	public void updateOne(Club club)
	{
		try (var session = fac.getConnection())
		{
			session.update(NameSpace + "update", club);
			session.commit();
		}
	}
	
	public void create(Club club)
	{
		try (var session = fac.getConnection())
		{
			session.insert(NameSpace + "new", club);
			session.commit();
		}
	}
	
	public List<Club> searchByName(String name)
	{
		try (var session = fac.getConnection())
		{
			return session.selectList(NameSpace + "search", name);
		}
	}
	
	// 游标日后再说
	public List<Club> getOnePage(int pageNumber)
	{
		try (var session = fac.getConnection())
		{
			int offset = pageNumber * 10;
			return session.selectList(NameSpace + "queryOnePage", offset);
		}
	}
}
