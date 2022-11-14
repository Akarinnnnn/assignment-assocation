package oop.clubsv3.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Activity
{
	private int aid;
	private String location;
	private int cid;
	private LocalDate timestart, timeend;
	
	public int getAid()
	{
		return aid;
	}
	
	public void setAid(int aid)
	{
		this.aid = aid;
	}
	
	public String getLocation()
	{
		return location;
	}
	
	public void setLocation(String location)
	{
		this.location = location;
	}
	
	public int getCid()
	{
		return cid;
	}
	
	public void setCid(int cid)
	{
		this.cid = cid;
	}
	
	public LocalDate getTimestart()
	{
		return timestart;
	}
	
	public void setTimestart(LocalDate timestart)
	{
		this.timestart = timestart;
	}
	
	public LocalDate getTimeend()
	{
		return timeend;
	}
	
	public void setTimeend(LocalDate timeend)
	{
		this.timeend = timeend;
	}
}
