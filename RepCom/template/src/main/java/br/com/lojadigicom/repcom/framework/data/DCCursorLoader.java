package br.com.lojadigicom.repcom.framework.data;


import android.content.Context;
import android.net.Uri;
import android.support.v4.content.CursorLoader;

import br.com.lojadigicom.repcom.framework.dao.MontadorDaoI;
import br.com.lojadigicom.repcom.framework.log.DCLog;

/**
 * Created by Paulo on 14/11/2015.
 */
    public class DCCursorLoader extends CursorLoader {


    private MontadorDaoI montador = null;

    public DCCursorLoader(Context context) {
        super(context);
    }

    public DCCursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

        super(context, uri, projection, selection, selectionArgs, sortOrder);
        DCLog.d(DCLog.DISPLAY,this,"Vai criar o cursor - uri: " + uri.toString());
    }

    public DCCursorLoader(Context context, Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder, MontadorDaoI montador) {
        super(context, uri, projection, selection, selectionArgs, sortOrder);
        this.montador = montador;
    }

    public MontadorDaoI getMontador() {
        return montador;
    }
}
