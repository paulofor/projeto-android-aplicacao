package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.Palavra;
import coletapreco.view.adapter.listaedicao.base.PalavraListEdicaoAdapterBase;

public class PalavraListEdicaoAdapter extends PalavraListEdicaoAdapterBase {
	
	
	public PalavraListEdicaoAdapter(List<Palavra> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
