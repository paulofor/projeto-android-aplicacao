package br.com.lojadigicom.coletorprecocliente.remoto;


public class DataSourceRemoto {

    public static final String SERVER = "digicom.kinghost.net";
    public static final String APLICACAO = "ColetorPrecoCliente";

    //public static final String SERVER = "10.0.2.2:63199";
    //public static final String APLICACAO = null;


    public static String getServer() {
        return SERVER;
    }
    public static String getAplicacao() {
        return APLICACAO;
    }
}