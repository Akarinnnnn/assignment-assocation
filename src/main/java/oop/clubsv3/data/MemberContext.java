package oop.clubsv3.data;

import oop.clubsv3.models.Club;
import oop.clubsv3.models.Member;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberContext implements DisposableBean
{
	private static final String NameSpace = "oop.clubsv3.models.Member.";
	
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
	
	public Member getMember(int mid)
	{
		return session.selectOne(NameSpace + "getByMid", mid);
	}
	
	public void delete(int mid)
	{
		session.delete(NameSpace + "delete", mid);
		session.commit();
	}
	
	public void update(Member member)
	{
		session.update(NameSpace + "update", member);
		session.commit();
	}
	
	public void create(Member member)
	{
		session.insert(NameSpace + "new", member);
		session.commit();
	}
	
	public List<Member> searchByClubId(Club club)
	{
		return searchByClubId(club.getId());
	}
	
	public List<Member> searchByClubId(int clubId)
	{
		return session.selectList(NameSpace + "getByCid", clubId);
	}
}
