package oop.clubsv3.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Activity
{
	private int aid;
	private String location;
	
	
	private String name;
	private int cid;
	private LocalDateTime timestart, timeend;
	
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
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public LocalDateTime getTimestart()
	{
		return timestart;
	}
	
	public void setTimestart(LocalDateTime timestart)
	{
		this.timestart = timestart;
	}
	
	public LocalDateTime getTimeend()
	{
		return timeend;
	}
	
	public void setTimeend(LocalDateTime timeend)
	{
		this.timeend = timeend;
	}
}
