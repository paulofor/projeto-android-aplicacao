package  coletapreco.view.adapter.listaedicao;

import java.util.List;

import android.content.Context;
import br.com.digicom.quadro.IQuadroListaEdicao;
import coletapreco.modelo.Marca;
import coletapreco.view.adapter.listaedicao.base.MarcaListEdicaoAdapterBase;

public class MarcaListEdicaoAdapter extends MarcaListEdicaoAdapterBase {
	
	
	public MarcaListEdicaoAdapter(List<Marca> lista,IQuadroListaEdicao origem, Context context) {
		super(lista, origem, context);
	}
	
	
}
