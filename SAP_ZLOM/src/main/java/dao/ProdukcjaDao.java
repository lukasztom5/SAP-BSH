package dao;

import java.util.List;

import encje.*;

public interface ProdukcjaDao {
	public abstract Produkcja findById(int id);
	public abstract List<Produkcja> findAll();
	public abstract List<Produkcja> findtydzien();
	public abstract List<Produkcja> findrok();
	public abstract List<Produkcja> findlinia();
}
