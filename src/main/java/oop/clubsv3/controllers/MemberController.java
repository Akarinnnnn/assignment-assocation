package oop.clubsv3.controllers;

import oop.clubsv3.data.MemberContext;
import oop.clubsv3.models.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("/member")
public class MemberController
{
	private final MemberContext db;
	
	@Autowired
	public MemberController(MemberContext db) {this.db = db;}
	
	
	@GetMapping("get/{id}")
	public Member getMember(@PathVariable("id") int id)
	{
		return db.getMember(id);
	}
	
	@PostMapping("update/{id}")
	public void update(@PathVariable("id") int mid, @RequestBody Member member)
	{
		member.setMid(mid);
		db.update(member);
	}
	
	@PostMapping("/delete/{id}")
	public void delete(@PathVariable("id") int id)
	{
		db.delete(id);
	}
	
	@PostMapping("create")
	public void create(@RequestBody Member member)
	{
		member.setMid(0);
		db.create(member);
	}
	
	@GetMapping("inclub/{id}")
	public List<Member> searchByClub(@PathVariable("id") int cid)
	{
		return db.searchByClubId(cid);
	}
}
