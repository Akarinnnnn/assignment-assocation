package oop.clubsv3.models;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 社团实体类
 */
public class Club
{
	private String name;
	private int id;
	private LocalDateTime foundDate;
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getId()
	{
		return id;
	}
	
	public void setId(int id)
	{
		this.id = id;
	}
	
	public LocalDateTime getFoundDate()
	{
		return foundDate;
	}
	
	public void setFoundDate(LocalDateTime foundDate)
	{
		this.foundDate = foundDate;
	}
}
