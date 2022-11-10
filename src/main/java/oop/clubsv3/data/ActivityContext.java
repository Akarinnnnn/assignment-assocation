package oop.clubsv3.data;

import oop.clubsv3.models.Activity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository()
public interface ActivityContext
{
	Activity getOne(int id);
	List<Activity> getByClub(int cid);
	void delete(int id);
	void create(Activity activity);
	void update(Activity activity);
}
