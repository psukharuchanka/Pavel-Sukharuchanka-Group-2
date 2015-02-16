package by.kino.jmp.repository;

import java.util.List;

import by.kino.jmp.beans.Visitor;

public interface VisitorRepository {
	
	Visitor find(String login);
  	List<Visitor> findAll();
  	void store(Visitor v);

}
