package by.kino.jmp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.kino.jmp.beans.Cinema;

public class CinemaRepositoryInMem implements CinemaRepository {
	
	private static final CinemaRepositoryInMem rep;

	static {
		rep = new CinemaRepositoryInMem();
	}
	
	public static CinemaRepositoryInMem getInstance() {
		return rep;
	}

	private Map<Integer, Cinema> db;
	private CinemaRepositoryInMem() {
		db = new HashMap<Integer, Cinema>();
	}

	public Cinema find(int id) {
		return db.get(id);
	}

	public void store(final Cinema cinema) {
		db.put(cinema.getId(), cinema);
	}

	public List<Cinema> findAll() {
		return new ArrayList(db.values());
	}

}
