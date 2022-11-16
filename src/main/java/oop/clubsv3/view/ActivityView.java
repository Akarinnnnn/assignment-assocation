package oop.clubsv3.view;

import oop.clubsv3.data.ActivityContext;
import oop.clubsv3.data.ClubContext;
import oop.clubsv3.models.Activity;
import oop.clubsv3.models.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/activity")
public class ActivityView
{
	private final ActivityContext db;
	private final ClubContext dbClub;
	
	@Autowired
	public ActivityView(ActivityContext db, ClubContext dbClub) {this.db = db;
		this.dbClub = dbClub;
	}
	
	
	@RequestMapping({"/index", "/"})
	public String index(Model model)
	{
		model.addAttribute("clubs", dbClub.getOnePage(0));
		return "/activity/index";
	}
	
	@RequestMapping(value = "/edit", params = "id")
	public String edit(@RequestParam("id") int aid, Model model)
	{
		Activity activity = db.getOne(aid);
		model.addAttribute("activity", activity);
		return "/activity/edit";
	}
	
	@RequestMapping(value = "/create", params = "id")
	public String create(@RequestParam("id") int cid, Model model)
	{
		model.addAttribute("cid", cid);
		return "/activity/create";
	}
	
	@RequestMapping(value = "/showClub", params = "id")
	public String showClub(@RequestParam("id") int id, Model model)
	{
		Club club = dbClub.getClub(id);
		model.addAttribute("club", club);
		model.addAttribute("activities", db.getByClub(id));
		return "/activity/showClub";
	}
	
}
