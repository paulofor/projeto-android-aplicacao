
package  treinoacademia.tela.quadro.base;

import treinoacademia.app.R;
import treinoacademia.modelo.ExecucaoItemSerie;
import treinoacademia.servico.FabricaServico;
import treinoacademia.servico.ExecucaoItemSerieServico;
import treinoacademia.view.adapter.ExecucaoItemSerieListAdapter;
import treinoacademia.modelo.vo.FabricaVo;
import treinoacademia.servico.ExecucaoItemSerieServico;

import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.Button;
import br.com.digicom.multimidia.AudioRecurso;
import br.com.digicom.quadro.BaseListFragment;
import br.com.digicom.quadro.IQuadroListaEdicao;
import br.com.digicom.activity.DigicomContexto;
import br.com.digicom.log.DCLog;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.app.DialogFragment;
import br.com.digicom.quadro.IFragment;
import br.com.digicom.quadro.IFragmentEdicao;
import android.util.Log;
import br.com.digicom.modelo.DCIObjetoDominio;
import br.com.digicom.layout.ResourceObj;
import android.widget.BaseAdapter;
import br.com.digicom.animacao.TrocaQuadro;

public abstract class ExecucaoItemSerieQuadroListaBase extends BaseListFragment implements IQuadroListaEdicao {

	public final static String ITEM_CLICK = "ExecucaoItemSerieItemClick";

	ExecucaoItemSerieListAdapter adapt = null;
	private Button btnCriaItem = null;
	
	ExecucaoItemSerieServico servico = null;
	
	
	public BaseAdapter getAdapter() {
		return adapt;
	}
	
	@Override
	protected void inicializaItensTelaBase() {
		btnCriaItem = (Button) getTela().findViewById(R.id.btnCriaExecucaoItemSerie);
	}
	@Override
	protected void inicializaListenersBase() {
		btnCriaItem.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				chamaCriaItem();
			}
		});
	}
	

	
	
	
	@Override
	protected void inicializaServicos() {
		servico = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
	}

	
	@Override
	public void onToqueLongoTela(DCIObjetoDominio obj) {
		IFragmentEdicao quadro = criaQuadroTrata();
		quadro.setItem(obj);
		quadro.setAlteracao();
		alteraQuadro(quadro);
	}

	
	@Override
	public void audioRawConcluido(AudioRecurso audioRecurso) {
	}

	protected String getPalavraChave() {
		return ITEM_CLICK;
	}

	@Override
	public void onAlteraQuadro() {
	}
	

	public void onStart() {
		super.onStart();
		preencheLista(); // Talvez nao precise fazer isso sempre j? que existe o adapt.notifyDataSetChanged
	    // Ao se conhecer melhor o ciclo de vida dos fragments pensar em otimizar esse trecho
		// evitar processar algo que n?o muda e evitar n?o processar algo que muda.
		// Fazendo um algoritmo que sirva para fragments de smartphone, que fica um por tela
		// Quanto de tablet que pode ficar mais de um
		// Decidir ate 28-06-2014
	}
	
	@Override
	protected ResourceObj getLayoutTela() {
		ResourceObj recurso = new ResourceObj(R.layout.lista_execucao_item_serie,"R.layout.lista_execucao_item_serie");
		return recurso;
	}
	// Dois metodos com mesmo objetivo. Excluir at? 21-07-2015 ( 3 meses )
	// TelaListaUsoBase, TelaQuadroListaBase, ViewBase
	
	
	// Delegando a cria??o de objeto ao inicializaItemTela na camada servico
	public final void chamaCriaItem() {
		IFragmentEdicao quadro = criaQuadroTrata();
		//ExecucaoItemSerie nova = servico.inicializaItemTela(getContext());
		ExecucaoItemSerie nova = FabricaVo.criaExecucaoItemSerie();
		//nova = insereObjetoPrincipal(nova);
		nova = servico.inicializaItemTela(nova,getContext());
		quadro.setItem(nova);
		quadro.setInsercao();
		
	}
	
	protected boolean getDialog() {
		return false;
	}
	
	
	
	protected ExecucaoItemSerie criaNova() {
		return servico.inicializaItemTela(getContext());
		//throw new UnsupportedOperationException("Fazer override de criaNova em ExecucaoItemSerieQuadroLista retornando new ExecucaoItemSerie com inicializa??o de listas internas se necessario");
		/* Exemplo - Criar inicializando dados e listas internas 
		protected SerieTreino criaNova() {
			SerieTreino nova = FabricaVo.criaSerieTreino();
			nova.setDataInicial(UtilDatas.getTimestampAtual());
			List<ItemSerie> lista = new ArrayList<ItemSerie>();
			nova.setListaItemSerie_Possui(lista);
			return nova;
		}
		*/
	}
	protected IFragmentEdicao criaQuadroTrata() {
		throw new UnsupportedOperationException("Fazer override de criaQuadroTrata em ExecucaoItemSerieQuadroLista retornando new ExecucaoItemSerieQuadroTrata ou verificar se nao esta sendo chamada via super");
	}

	@Override
	public void onToqueTela(DCIObjetoDominio obj) {
	}

	@Override
	protected void inicializaItensTela() {
	}

	@Override
	protected void inicializaListeners() {
	}

	protected void preencheLista() {
        //ListView lista = (ListView) getActivity().findViewById(R.id.listViewPrincipal);
        ExecucaoItemSerieServico servico = FabricaServico.getInstancia().getExecucaoItemSerieServico(FabricaServico.TIPO_SQLITE);
        DigicomContexto dContexto = getContext();
        List<ExecucaoItemSerie> lista = getListaCorrente(dContexto.getContext(),servico);
        DCLog.d(DCLog.SERVICO_QUADRO_LISTA, this, "preencheLista : List<ExecucaoItemSerie> -> " + lista.size() + " itens");
        // Pode ser necessario um adapter customizado (diferenciar o editar e usar)
        adapt =  getAdapter(lista, dContexto);
        adapt.setRaiz(this);
        setListAdapter(adapt);
        adapt.notifyDataSetChanged();
	}
	
	protected ExecucaoItemSerieListAdapter getAdapter(List<ExecucaoItemSerie> lista,DigicomContexto dContexto) {
		return new ExecucaoItemSerieListAdapter(lista,dContexto.getContext());
	}
	
	protected List<ExecucaoItemSerie> getListaCorrente(Context contexto,ExecucaoItemSerieServico servico) {
		List<ExecucaoItemSerie> saida = servico.getAllTela(contexto);
		return saida;
	}
	
	
	// Tive que manter para que se possa criar Dialogs caso seja necess?rio.
	// Pode ser feito de outra forma mas acho essa mais simples. Avaliar !!! ( 18-10-2015)
	private void alteraQuadro(IFragment quadro) {
		if (getDialog())
			TrocaQuadro.getInstancia().criaDialog(quadro,this);
		else
			TrocaQuadro.getInstancia().alteraQuadroPrincipal(quadro);
	}
	
	

}