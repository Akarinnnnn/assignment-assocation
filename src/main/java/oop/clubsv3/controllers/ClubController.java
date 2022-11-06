package oop.clubsv3.controllers;

import oop.clubsv3.data.ClubContext;
import oop.clubsv3.data.DbConnectionBean;
import oop.clubsv3.models.Club;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.io.Closeable;
import java.io.IOException;
import java.util.List;

@RestController
public class ClubController
{
	final ClubContext db;
	
	public ClubController(DbConnectionBean dbfac)
	{
		db = new ClubContext(dbfac);
	}
	
	@GetMapping("/list")
	public List<Club> listClubs(int pageNumber)
	{
		return db.getOnePage(pageNumber);
	}
	
	@PreDestroy
	public void destory()
	{
		db.destroy();
	}
}
