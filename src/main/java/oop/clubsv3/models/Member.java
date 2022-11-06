package oop.clubsv3.models;

/**
 * 学生实体类
 */
public class Member
{
	private int cid;
	private int sid;
	private int mid;
	private String position;
	
	public final int getCid()
	{
		return cid;
	}
	
	public final void setCid(int cid)
	{
		this.cid = cid;
	}
	
	public final int getSid()
	{
		return sid;
	}
	
	public final void setSid(int sid)
	{
		this.sid = sid;
	}
	
	public final int getMid()
	{
		return mid;
	}
	
	public final void setMid(int mid)
	{
		this.mid = mid;
	}
	
	public final String getPosition()
	{
		return position;
	}
	
	public final void setPosition(String position)
	{
		this.position = position;
	}
}
