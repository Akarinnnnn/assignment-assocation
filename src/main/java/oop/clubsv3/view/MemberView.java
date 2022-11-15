package oop.clubsv3.view;

import oop.clubsv3.data.ClubContext;
import oop.clubsv3.data.MemberContext;
import oop.clubsv3.models.Club;
import oop.clubsv3.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/member")
public class MemberView
{
	private final ClubContext dbClub;
	private final MemberContext db;
	
	@Autowired
	public MemberView(ClubContext db, MemberContext db1) {this.dbClub = db;
		this.db = db1;
	}
	
	
	@RequestMapping({"/index", "/"})
	public String index(Model model)
	{
		model.addAttribute("clubs", dbClub.getOnePage(0));
		return "/member/index";
	}
	
	@RequestMapping(value = "/showClub", params = "id")
	public String showClub(@RequestParam("id") int id, Model model)
	{
		Club club = dbClub.getClub(id);
		model.addAttribute("club", club);
		model.addAttribute("members", db.searchByClubId(club));
		return "/member/showClub";
	}
	
	@RequestMapping(value = "/edit", params = "id")
	public String edit(@RequestParam("id") int mid, Model model)
	{
		Member member = db.getMember(mid);
		model.addAttribute("member", member);
		return "/member/edit";
	}
	
	@RequestMapping("/create")
	public String create(Model model)
	{
		return "/club/create";
	}
}
