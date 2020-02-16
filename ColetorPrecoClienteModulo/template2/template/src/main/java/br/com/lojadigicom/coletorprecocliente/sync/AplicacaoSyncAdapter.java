
package  br.com.lojadigicom.coletorprecocliente.sync;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.AbstractThreadedSyncAdapter;
import android.content.ContentProviderClient;
import android.content.ContentResolver;
import android.content.SyncRequest;
import android.content.SyncResult;
import android.os.Build;
import android.os.Bundle;
import android.content.Context;
import br.com.lojadigicom.coletorprecocliente.template.*;
import br.com.lojadigicom.coletorprecocliente.remoto.*;

public abstract class AplicacaoSyncAdapter extends AbstractThreadedSyncAdapter{
	
	public static final int SYNC_INTERVAL = 60 * 180;
    public static final int SYNC_FLEXTIME = SYNC_INTERVAL/3;
	//private ContentResolver mContentResolver; // Melhor fazer isso ou usar metodo estatico ?
	
	public AplicacaoSyncAdapter(Context context, boolean autoInitialize) {
        super(context, autoInitialize);
        //mContentResolver = context.getContentResolver();
    }
	
    
     /**
     * Helper method to schedule the sync adapter periodic execution
     */
    public static void configurePeriodicSync(Context context, int syncInterval, int flexTime) {
        Account account = getSyncAccount(context);
        String authority = context.getString(R.string.content_authority);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // we can enable inexact timers in our periodic sync
            SyncRequest request = new SyncRequest.Builder().
                    syncPeriodic(syncInterval, flexTime).
                    setSyncAdapter(account, authority).
                    setExtras(new Bundle()).build();
            //mContentResolver.requestSync(request);
            ContentResolver.requestSync(request);
        } else {
            //mContentResolver.addPeriodicSync(account,authority, new Bundle(), syncInterval);
            ContentResolver.addPeriodicSync(account,authority, new Bundle(), syncInterval);
        }
    }

    /**
     * Helper method to have the sync adapter sync immediately
     * @param context The context used to access the account service
     */
    public static void syncImmediately(Context context) {
        Bundle bundle = new Bundle();
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_EXPEDITED, true);
        bundle.putBoolean(ContentResolver.SYNC_EXTRAS_MANUAL, true);
        //mContentResolver.requestSync(getSyncAccount(context),context.getString(R.string.content_authority), bundle);
        ContentResolver.requestSync(getSyncAccount(context),context.getString(R.string.content_authority), bundle);
    }

    /**
     * Helper method to get the fake account to be used with SyncAdapter, or make a new one
     * if the fake account doesn't exist yet.  If we make a new account, we call the
     * onAccountCreated method so we can initialize things.
     *
     * @param context The context used to access the account service
     * @return a fake account.
     */
    public static Account getSyncAccount(Context context) {
        // Get an instance of the Android account manager
        AccountManager accountManager = (AccountManager) context.getSystemService(Context.ACCOUNT_SERVICE);

        // Create the account type and default account
        Account newAccount = new Account(context.getString(R.string.app_name), context.getString(R.string.sync_account_type));

        // If the password doesn't exist, the account doesn't exist
        if ( null == accountManager.getPassword(newAccount) ) {

        /*
         * Add the account and account type, no password or user data
         * If successful, return the Account object, otherwise report an error.
         */
            if (!accountManager.addAccountExplicitly(newAccount, "", null)) {
                return null;
            }
            /*
             * If you don't set android:syncable="true" in
             * in your <provider> element in the manifest,
             * then call ContentResolver.setIsSyncable(account, AUTHORITY, 1)
             * here.
             */

            onAccountCreated(newAccount, context);
        }
        return newAccount;
    }

    private static void onAccountCreated(Account newAccount, Context context) {
        /*
         * Since we've created an account
         */
       	AplicacaoSyncAdapter.configurePeriodicSync(context, SYNC_INTERVAL, SYNC_FLEXTIME);

        /*
         * Without calling setSyncAutomatically, our periodic sync will not be enabled.
         */
        ContentResolver.setSyncAutomatically(newAccount, context.getString(R.string.content_authority), true);

        /*
         * Finally, let's do a sync to get things started
         */
        syncImmediately(context);
    }

    public static void initializeSyncAdapter(Context context) {
        getSyncAccount(context);
    }
    
    
	protected void sincronizaNaturezaProduto(Context contexto, boolean forcaAtualizacao, boolean delete) {
		NaturezaProdutoSincronismo sincSrv = new NaturezaProdutoSincronismo();
		
    	sincSrv.sincroniza(getContext(),true, delete);
    	
	}
	
	protected void sincronizaOportunidadeDia(Context contexto, boolean forcaAtualizacao, boolean delete) {
		OportunidadeDiaSincronismo sincSrv = new OportunidadeDiaSincronismo();
		
    	sincSrv.sincroniza(getContext(),true, delete);
    	
	}
	
	protected void sincronizaPrecoDiario(Context contexto, boolean forcaAtualizacao, boolean delete) {
		PrecoDiarioSincronismo sincSrv = new PrecoDiarioSincronismo();
		
    	sincSrv.sincroniza(getContext(),true, delete);
    	
	}
	
	protected void sincronizaUsuario(Context contexto, boolean forcaAtualizacao, boolean delete) {
		UsuarioSincronismo sincSrv = new UsuarioSincronismo();
		
    	sincSrv.sincroniza(getContext(),true, delete);
    	
	}
	
	protected void sincronizaDispositivoUsuario(Context contexto, boolean forcaAtualizacao, boolean delete) {
		DispositivoUsuarioSincronismo sincSrv = new DispositivoUsuarioSincronismo();
		
    	sincSrv.sincroniza(getContext(),true);
    	
	}
	
	protected void sincronizaUsuarioPesquisa(Context contexto, boolean forcaAtualizacao, boolean delete) {
		UsuarioPesquisaSincronismo sincSrv = new UsuarioPesquisaSincronismo();
		
    	sincSrv.sincroniza(getContext(),true, delete);
    	
	}
	
	protected void sincronizaProdutoCliente(Context contexto, boolean forcaAtualizacao, boolean delete) {
		ProdutoClienteSincronismo sincSrv = new ProdutoClienteSincronismo();
		
    	sincSrv.sincroniza(getContext(),true, delete);
    	
	}
	
	protected void sincronizaInteresseProduto(Context contexto, boolean forcaAtualizacao, boolean delete) {
		InteresseProdutoSincronismo sincSrv = new InteresseProdutoSincronismo();
		
    	sincSrv.sincroniza(getContext(),true, delete);
    	
	}
	
	protected void sincronizaPalavraChavePesquisa(Context contexto, boolean forcaAtualizacao, boolean delete) {
		PalavraChavePesquisaSincronismo sincSrv = new PalavraChavePesquisaSincronismo();
		
    	sincSrv.sincroniza(getContext(),true, delete);
    	
	}
	
	protected void sincronizaAppProduto(Context contexto, boolean forcaAtualizacao, boolean delete) {
		AppProdutoSincronismo sincSrv = new AppProdutoSincronismo();
		
    	sincSrv.sincroniza(getContext(),true, delete);
    	
	}
	


}