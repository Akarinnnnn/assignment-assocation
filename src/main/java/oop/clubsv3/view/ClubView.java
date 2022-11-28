package oop.clubsv3.view;

import oop.clubsv3.data.ClubContext;
import oop.clubsv3.models.Club;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/club")
public class ClubView
{
	private final ClubContext db;
	
	@Autowired
	public ClubView(ClubContext db) {this.db = db;}
	
	
	@RequestMapping({"/index", "/"})
	public String index(Model model)
	{
		model.addAttribute("clubs", db.getOnePage(0));
		return "club/index";
	}
	
	@RequestMapping(value = "/edit", params = "id")
	public String edit(@RequestParam("id") int id, Model model)
	{
		Club club = db.getClub(id);
		if (club == null)
			return "club/index";
		model.addAttribute("club", club);
		return "club/edit";
	}
	
	@RequestMapping("/create")
	public String create(Model model)
	{
		return "club/create";
	}
}
