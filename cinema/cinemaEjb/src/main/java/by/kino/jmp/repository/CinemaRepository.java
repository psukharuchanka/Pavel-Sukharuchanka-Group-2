package by.kino.jmp.repository;

import java.util.List;

import by.kino.jmp.beans.Cinema;

public interface CinemaRepository {
	
	Cinema find(int id);
  	List<Cinema> findAll();
  	void store(Cinema cargo);

}
