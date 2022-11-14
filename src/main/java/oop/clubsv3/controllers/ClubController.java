package oop.clubsv3.controllers;

import oop.clubsv3.data.ClubContext;
import oop.clubsv3.data.DbConnectionBean;
import oop.clubsv3.models.Club;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PreDestroy;
import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/api/club")
public class ClubController
{
	final ClubContext db;
	
	public ClubController(DbConnectionBean dbfac)
	{
		db = new ClubContext(dbfac);
	}
	
	@GetMapping("/list/{page}")
	public List<Club> listClubs(@PathVariable("page") int pageNumber)
	{
		return db.getOnePage(pageNumber);
	}
	
	@GetMapping("/search/{name}")
	public List<Club> searchByName(@PathVariable("name") String name)
	{
		return db.searchByName(name);
	}
	
	@PostMapping("/create")
	public void create(@RequestBody Club club)
	{
		db.create(club);
	}
	
	@PostMapping("/update/{id}")
	public void update(@PathVariable("id") int id, @RequestBody Club club)
	{
		club.setId(id);
		db.updateOne(club);
	}
	
	@PostMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id)
	{
		db.deleteClub(id);
	}
	
	@GetMapping("/summary/{id}")
	public Club getOne(@PathVariable("id") int id)
	{
		return db.getClub(id);
	}
	
	@PreDestroy
	public void destory()
	{
		db.destroy();
	}
}
