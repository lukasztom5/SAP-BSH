package dao;
import java.util.List;

import encje.*;
public interface PodsumowanieSkumulowaneDao {
	public abstract Integer NewIdPodsumowanieSkumulowane();
	public abstract boolean PodsumowanieSkumulowaneExists(PodsumowanieSkumulowane podsumowaniesk);	
	public abstract void save(PodsumowanieSkumulowane podsumowaniesk);
	public abstract PodsumowanieSkumulowane findById(int id);
	public abstract List<PodsumowanieSkumulowane> findAll();
	public abstract List<PodsumowanieSkumulowane> findbytydzien(Integer tydzien, Integer rok);
	public abstract List<PodsumowanieSkumulowane> findbytygodnie(Integer tydzien1, Integer rok1, Integer tydzien2, Integer rok2);
	public abstract List<PodsumowanieSkumulowane> findbytydzienobszar(Integer tydzien, Integer rok,String obszar);
	public abstract List<PodsumowanieSkumulowane> findbytygodnieobszar(Integer tydzien1, Integer rok1, Integer tydzien2, Integer rok2, String obszar);

}
