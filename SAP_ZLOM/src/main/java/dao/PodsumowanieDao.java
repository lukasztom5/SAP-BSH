package dao;

import java.util.List;

import encje.*;

public interface PodsumowanieDao {
	public abstract Integer NewIdPodsumowanie();
	public abstract boolean PodsumowanieExists(Podsumowanie podsumowanie);	
	public abstract void save(Podsumowanie podsumowanie);
	public abstract List<Podsumowanie> findAll();
	public abstract List<Podsumowanie> findbytydzien(Integer tydzien, Integer rok);
	public abstract List<Podsumowanie> findbytygodnie(Integer tydzien1, Integer rok1, Integer tydzien2, Integer rok2);	
	public abstract List<Podsumowanie> findbytydzienobszar(Integer tydzien, Integer rok,String obszar);
	public abstract List<Podsumowanie> findbytygodnieobszar(Integer tydzien1, Integer rok1, Integer tydzien2, Integer rok2, String obszar);
	public abstract List<Podsumowanie> findbydaty(String data1, String data2, String obszar);
}
