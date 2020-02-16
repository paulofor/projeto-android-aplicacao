
package  br.com.lojadigicom.coletorprecocliente.tela.base.fragment;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.coletorprecocliente.modelo.PrecoDiarioCliente;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.lojadigicom.coletorprecocliente.servico.base.PrecoDiarioClienteServico;
import br.com.lojadigicom.coletorprecocliente.servico.base.FabricaServico;
import br.com.lojadigicom.coletorprecocliente.modelo.PrecoDiarioClienteVo;
import br.com.lojadigicom.coletorprecocliente.framework.log.DCLog;

public abstract class PrecoDiarioClienteFragmentEditaBase extends Fragment{

	private Button btnOk = null;

	private PrecoDiarioCliente item = null;
	
	protected PrecoDiarioClienteServico srv = FabricaServico.getInstancia().getPrecoDiarioClienteServico();
	
	protected PrecoDiarioCliente getItem() {
		return item;
	}
	public void setItem(PrecoDiarioCliente valor) {
		item = valor;
	}
	
	@Override
    public final View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        DCLog.d(DCLog.TRACE_DISPLAY,this,this.getClass().getSimpleName());
         if (getIdLayout()==0) {
            throw new RuntimeException("Voce precisa implementar getIdLayout() em " + this.getClass().getSimpleName());
        }
        View v = inflater.inflate(getIdLayout(), container, false);
        inicializaCamposTela(v);
        btnOk = getButton(v,getIdBtnOk(),"btnOk");
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                enviaDados();
            }
        });
        return v;
    }
	
	private void enviaDados() {
		DCLog.d(DCLog.TRACE_CRUD,this,"enviaDados click");
		if (item==null) {
            // Insercao
            item = new PrecoDiarioClienteVo();
            DCLog.d(DCLog.TRACE_CRUD,this,"Insercao");
        }
        DCLog.d(DCLog.TRACE_CRUD,this,"antes  copiaTelaParaVo: " + item.getContentValues().toString());
        copiaTelaParaVo(item);
        DCLog.d(DCLog.TRACE_CRUD,this,"depois copiaTelaParaVo: " + item.getContentValues().toString());
        srv.insereAtualiza(item,this.getContext());
        fechar();
    }
	private void fechar() {
	}


    protected abstract void copiaTelaParaVo(PrecoDiarioCliente item);
	
	protected abstract void inicializaCamposTela(View v);
	
	protected abstract int getIdLayout();
	protected abstract int getIdBtnOk();

	// Tem em varios lugares, pode depois ficar centralizada.
    // (28-11-2016) ate 28-01-2017
    
    protected final EditText getEditText(View v, int id, String nome) {
        EditText saida = (EditText) v.findViewById(id);
        if (saida==null) throw new RuntimeException("EditText " + nome + " nao encontrado em " + v);
        return saida;
    }
    protected final Button getButton(View v, int id, String nome) {
        Button saida = (Button) v.findViewById(id);
        if (saida==null) throw new RuntimeException("Button " + nome + " nao encontrado em " + v);
        return saida;
    }
	
}