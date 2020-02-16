package br.com.digicom.activity;

import android.app.Activity;
import android.content.Context;
import android.support.v4.app.Fragment;
import br.com.digicom.quadro.IFragment;

public class DigicomContexto {

	
	private Context context;
	private IFragment quadro;
	
	public DigicomContexto(Context contexto, IFragment quadro) {
		this.context = contexto;
		this.quadro = quadro;
	}
	public IFragment getQuadro() {
		return quadro;
	}
	public Context getContext() {
		return context;
	}
	
	public Activity getActivity() {
		Fragment fragment = (Fragment) quadro;
		return fragment.getActivity();
	}
}
