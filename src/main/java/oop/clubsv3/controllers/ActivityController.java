package oop.clubsv3.controllers;

import oop.clubsv3.data.ActivityContext;
import oop.clubsv3.models.Activity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PreDestroy;
import java.util.List;

@RestController
@RequestMapping("/activity")
public class ActivityController
{
	private final ActivityContext db;
	
	@Autowired
	public ActivityController(ActivityContext db) {this.db = db;}
	
	@GetMapping("/summary/{id}")
	public Activity summary(@PathVariable("id") int aid)
	{
		return db.getOne(aid);
	}
	
	@GetMapping("/fromClub/{id}")
	public List<Activity> fromClub(@PathVariable("id") int cid)
	{
		return db.getByClub(cid);
	}
}
