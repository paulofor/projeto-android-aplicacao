package br.com.lojadigicom.repcom.modelo.agregado;

import java.util.List;
import br.com.lojadigicom.repcom.modelo.*;

public interface ContatoClienteAgregadoI{

	// ComChaveEstrangeira
  	
		public Cliente getCliente_ReferenteA(); 
		public void setCliente_ReferenteA(Cliente item); 
		
		public void addListaCliente_ReferenteA(Cliente item); 
		public Cliente getCorrenteCliente_ReferenteA(); 
		
		
	
	// SemChaveEstrangeira
	
	
	// UmPraUm
	
}
