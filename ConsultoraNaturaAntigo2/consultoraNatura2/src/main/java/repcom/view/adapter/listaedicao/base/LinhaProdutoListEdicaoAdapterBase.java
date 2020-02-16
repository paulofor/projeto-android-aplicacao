package  repcom.view.adapter.listaedicao.base;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.view.View.OnTouchListener;
import android.view.View.OnLongClickListener;
import android.os.Vibrator;
import repcom.app.R;
import repcom.modelo.LinhaProduto;
import repcom.view.LinhaProdutoView;
import br.com.digicom.layout.ItemLista;
import br.com.digicom.quadro.IQuadroListaEdicao;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.BaseListFragment;
import repcom.tela.quadro.base.LinhaProdutoQuadroListaBase;

public abstract class LinhaProdutoListEdicaoAdapterBase extends BaseAdapter implements OnTouchListener, OnLongClickListener{
	
	private Context context;
	private List<LinhaProduto> lista;
	private IQuadroListaEdicao raiz;
	
	private Vibrator v = null;
	private boolean edicao = false;


	protected void setElementoTela(IFragment quadro, int recurso) {
		BaseListFragment frag = (BaseListFragment) raiz;
		frag.setElementoTela(quadro, recurso);
	}
	public LinhaProdutoListEdicaoAdapterBase(List<LinhaProduto> lista, IQuadroListaEdicao origem, Context context ) {
		this.context = context;
		this.lista = lista;
		this.raiz = origem;
		setEdicao(); // Forcando para fazer edicao - alterar se for necessario depois.
	}
	public void setEdicao() {
		v = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
		edicao = true;
	}
	
	public Context getContextAndroid() {
		return context;
	}

	protected int getColor(int valor) {
		return context.getResources().getColor(valor);
	}
	protected IFragment getOrigem() {
		return (IFragment) raiz;
	}
/*
	public void setRaiz(IQuadroLista valor) {
		raiz = valor;
	}
*/
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
		LinhaProduto item = lista.get(position);
		
		View saida = LayoutInflater.from(context).inflate(getLayout(), null);
		if (!(saida instanceof ItemLista))
			throw new RuntimeException("Alterar layout raiz de item_linha_produto.xml para br.com.digicom.layout.ItemLista");
		((ItemLista)saida).setItem(item);
		montaItemLista(position,item, saida);
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
	
	protected int getLayout() {
		return R.layout.item_linha_produto;
	}
	
	protected void montaItemLista(int position, LinhaProduto item, View saida) {
		throw new UnsupportedOperationException("Fazer override de montaItemLista em "  + this.getClass().getSimpleName() + " ou verificar se nao esta sendo chamada via super. Arquivo de visao padrao eh: R.layout.item_linha_produto");
	}
	
	@Override
	public boolean onTouch(View view, MotionEvent avent) {
	 	ItemLista objeto = (ItemLista) view;
		LinhaProduto item = (LinhaProduto) objeto.getItem();
		raiz.onToqueTela(item);
		return false;
	}
	
	@Override
	public boolean onLongClick(View view) {
		ItemLista objeto = (ItemLista) view;
		LinhaProduto item = (LinhaProduto) objeto.getItem();
		if (edicao) {
			v.vibrate(500);
		}
		raiz.onToqueLongoTela(item);
		return false;
	}
	
	protected void alteraQuadro(IFragment quadro) {
		raiz.alteraQuadro(quadro);
	}

}
