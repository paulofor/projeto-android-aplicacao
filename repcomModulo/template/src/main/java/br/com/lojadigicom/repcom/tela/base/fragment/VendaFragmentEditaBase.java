
package  br.com.lojadigicom.repcom.tela.base.fragment;

import android.content.ContentResolver;
import android.net.Uri;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.support.v4.app.Fragment;
import br.com.lojadigicom.repcom.modelo.Venda;
import android.view.View;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import br.com.lojadigicom.repcom.servico.base.VendaServico;
import br.com.lojadigicom.repcom.servico.base.FabricaServico;
import br.com.lojadigicom.repcom.modelo.VendaVo;
import br.com.lojadigicom.repcom.framework.log.DCLog;

public abstract class VendaFragmentEditaBase extends Fragment{

	private Button btnOk = null;

	private Venda item = null;
	
	protected VendaServico srv = FabricaServico.getInstancia().getVendaServico();
	
	protected Venda getItem() {
		return item;
	}
	public void setItem(Venda valor) {
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
            item = new VendaVo();
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


    protected abstract void copiaTelaParaVo(Venda item);
	
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