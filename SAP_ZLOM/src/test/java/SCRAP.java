import dao.*;
import encje.*;

import java.util.List;



public class SCRAP {

public static void main(String[] args) {
	
	PodsumowanieDaoImpl pdi = new PodsumowanieDaoImpl();
	List<Podsumowanie> lista=pdi.findAll();
	 for(int i=0;i<lista.size();i++){
		 System.out.println(lista.get(i).getCenaNaLiczbaZm());
	 }
	 
}
}
