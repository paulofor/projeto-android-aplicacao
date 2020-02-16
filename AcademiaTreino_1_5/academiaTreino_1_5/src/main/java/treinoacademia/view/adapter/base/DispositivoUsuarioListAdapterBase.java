package  treinoacademia.view.adapter.base;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import br.com.digicom.adapter.DCBaseAdapter;
import android.view.View.OnTouchListener;
import android.view.View.OnLongClickListener;
import android.os.Vibrator;
import treinoacademia.app.R;
import treinoacademia.modelo.DispositivoUsuario;

import br.com.digicom.layout.ItemLista;
import br.com.digicom.quadro.IQuadroListaEdicao;
import br.com.digicom.quadro.IFragment;
import treinoacademia.tela.quadro.base.DispositivoUsuarioQuadroListaBase;

public abstract class DispositivoUsuarioListAdapterBase extends DCBaseAdapter implements OnTouchListener, OnLongClickListener{
	
	private Context context;
	private List<DispositivoUsuario> lista;
	//private IQuadroListaEdicao raiz;
	
	private Vibrator v = null;
	private boolean edicao = false;


	public DispositivoUsuarioListAdapterBase(List<DispositivoUsuario> lista, Context context ) {
		this.context = context;
		this.lista = lista;
		//this.raiz = origem;
	}

	
	public Context getContextAndroid() {
		return context;
	}

	protected int getColor(int valor) {
		return context.getResources().getColor(valor);
	}

	
	
	public int getCount() {
		return lista.size();
	}
	public Object getItem(int position) {
		return lista.get(position);
	}
	public long getItemId(int position) {
		return position;
	}
	
	public View getView(int position, View convertView, ViewGroup parent) {
		DispositivoUsuario item = lista.get(position);
		
		View saida = LayoutInflater.from(context).inflate(R.layout.item_dispositivo_usuario, null);
		if (!(saida instanceof ItemLista))
			throw new RuntimeException("Alterar layout raiz de R.layout.item_dispositivo_usuario para br.com.digicom.layout.IItemLista");
		((ItemLista)saida).setItem(item);
		this.setTela(saida);
		montaItemLista(position, item, saida);
		/*
		TextView nomeTxt = (TextView) saida.findViewById(R.id.Nome);
		TextView codigoTxt = (TextView) saida.findViewById(R.id.Codigo);
		nomeTxt.setText(item.getNome());
		codigoTxt.setText("" + item.getIdProdutoEstrategico());
		*/
		saida.setOnTouchListener(this);
		saida.setOnLongClickListener(this);
		return saida;
		//return new ProdutoEstrategicoView(this.context, item );
	}
	
	protected void montaItemLista(int posicao, DispositivoUsuario item, View saida) {
		throw new UnsupportedOperationException("Fazer override de montaItemLista em "  + this.getClass().getSimpleName() + " ou verificar se nao esta sendo chamada via super. Arquivo de visao padrao eh: R.layout.item_dispositivo_usuario");
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent avent) {
	 	ItemLista objeto = (ItemLista) view;
		DispositivoUsuario item = (DispositivoUsuario) objeto.getItem();
		getQuadroLista().onToqueTela(item);
		return false;
	}
	
	@Override
	public boolean onLongClick(View view) {
		ItemLista objeto = (ItemLista) view;
		DispositivoUsuario item = (DispositivoUsuario) objeto.getItem();
		if (edicao) {
			v.vibrate(500);
		}
		((IQuadroListaEdicao)getQuadroLista()).onToqueLongoTela(item);
		return false;
	}
	
	

}
