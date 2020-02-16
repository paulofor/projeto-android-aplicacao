
package  br.com.lojadigicom.coletorprecocliente.data.helper;

import br.com.lojadigicom.coletorprecocliente.data.contract.*;

public class UsuarioDbHelper {

	protected static String getSqlChavesEstrangeiras() {
		return  "ALTER TABLE " +
        UsuarioContract.TABLE_NAME + " " +
        ";";
	}

	protected static String getSqlCreate(){
		return  "CREATE TABLE IF NOT EXISTS "
        + UsuarioContract.TABLE_NAME + " (" +
        UsuarioContract.COLUNA_CHAVE + " INTEGER PRIMARY KEY " 
        + " , " + UsuarioContract.COLUNA_NOME_USUARIO + " TEXT "
        + " , " + UsuarioContract.COLUNA_EMAIL + " TEXT "
        + " , " + UsuarioContract.COLUNA_PLANO01 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_PLANO02 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_PLANO03 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_PLANO04 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_PLANO05 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_DATA_ULTIMA_ALTERACAO + " INTEGER "
        + " , " + UsuarioContract.COLUNA_PERMITE_SINCRONIZAR + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_CODIGO_EXTERNO + " TEXT "
		//+ getSqlChaveEstrangeira()
		+ getSqlProcValor()
		+ getSqlIndices()
        + ");";
	}
	protected static String getSqlCreateSinc(){
		return  "CREATE TABLE IF NOT EXISTS "
        + UsuarioContract.TABLE_NAME_SINC + " (" +
        UsuarioContract.COLUNA_CHAVE + " INTEGER " 
        + " , " + UsuarioContract.COLUNA_NOME_USUARIO + " TEXT "
        + " , " + UsuarioContract.COLUNA_EMAIL + " TEXT "
        + " , " + UsuarioContract.COLUNA_PLANO01 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_PLANO02 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_PLANO03 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_PLANO04 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_PLANO05 + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_DATA_ULTIMA_ALTERACAO + " INTEGER "
        + " , " + UsuarioContract.COLUNA_PERMITE_SINCRONIZAR + " NUMERIC "
        + " , " + UsuarioContract.COLUNA_CODIGO_EXTERNO + " TEXT "
        + ", operacao_sinc TEXT);";
	}
	
	
	private static String getSqlIndices() {
		return "";
	}
	
	private static String getSqlProcValor() {
		String saida = "";
		
		return saida;
	}
	
	
	private static String getSqlChaveEstrangeira() {
		String saida = "";
		return saida;
	}
	
	public static String getSqlDrop() {
		return "DROP TABLE IF EXISTS " + UsuarioContract.TABLE_NAME;
	}
	public static String getSqlDropSinc() {
		return "DROP TABLE IF EXISTS " + UsuarioContract.TABLE_NAME_SINC;
	}
	
	public static String onUpgrade(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + UsuarioContract.TABLE_NAME;
    }
    public static String onUpgradeSinc(int oldVersion, int newVersion) { // pode precisar dos params no futuro
	 	return "DROP TABLE IF EXISTS " + UsuarioContract.TABLE_NAME_SINC;
    }
    
   
   
    
}