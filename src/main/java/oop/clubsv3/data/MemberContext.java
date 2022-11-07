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
	
	public Member getMember(int mid)
	{
		return session.selectOne(NameSpace + "getByMid", mid);
	}
	
	public List<Member> searchByClubId(Club club)
	{
		return searchByClubId(club.getId());
	}
	public List<Member> searchByClubId(int clubId)
	{
		return session.selectList(NameSpace + "search", clubId);
	}
	
	// 游标日后再说
	public List<Club> getOnePage(int pageNumber)
	{
		int offset = pageNumber * 10;
		return session.selectList(NameSpace+"queryOnePage", offset);
	}
}
