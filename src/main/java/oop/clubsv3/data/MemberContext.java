package oop.clubsv3.data;

import oop.clubsv3.models.Club;
import oop.clubsv3.models.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberContext
{
	private static final String NameSpace = "oop.clubsv3.models.Member.";
	
	private final DbConnectionBean fac;
	
	public MemberContext(DbConnectionBean fac)
	{
		this.fac = fac;
	}
	
	
	public Member getMember(int mid)
	{
		try (var session = fac.getConnection())
		{
			return session.selectOne(NameSpace + "getByMid", mid);
			
		}
	}
	
	public void delete(int mid)
	{
		try (var session = fac.getConnection())
		{
			session.delete(NameSpace + "delete", mid);
			session.commit();
			
		}
	}
	
	public void update(Member member)
	{
		try (var session = fac.getConnection())
		{
			session.update(NameSpace + "update", member);
			session.commit();
			
		}
	}
	
	public void create(Member member)
	{
		try (var session = fac.getConnection())
		{
			session.insert(NameSpace + "new", member);
			session.commit();
			
		}
	}
	
	public List<Member> searchByClubId(Club club)
	{
		try (var session = fac.getConnection())
		{
			return searchByClubId(club.getId());
			
		}
	}
	
	public List<Member> searchByClubId(int clubId)
	{
		try (var session = fac.getConnection())
		{
			return session.selectList(NameSpace + "getByCid", clubId);
			
		}
	}
}
