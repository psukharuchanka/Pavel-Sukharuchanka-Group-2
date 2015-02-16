package by.kino.jmp.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import by.kino.jmp.beans.Visitor;

public class VisitorRepositoryInMem implements VisitorRepository {

	private static final VisitorRepositoryInMem rep;
	static {
		rep = new VisitorRepositoryInMem();
	}

	private Map<String, Visitor> db;
	public static VisitorRepositoryInMem getInstance() {
		return rep;
	}

	private VisitorRepositoryInMem() {
		db = new HashMap<String, Visitor>();
	}

	public Visitor find(String login) {
		return db.get(login);
	}

	public List<Visitor> findAll() {
		return new ArrayList(db.values());
	}

	public void store(Visitor v) {
		db.put(v.getLogin(), v);
	}
}
