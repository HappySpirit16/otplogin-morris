package co.approbe.payment.create.service;

public class Env {
	 public static final String API_KEY = System.getenv("API_KEY") == null ? "" : System.getenv("API_KEY");
	 public static final String API_PATH = System.getenv("API_PATH") == null ? "" : System.getenv("API_PATH");
	 public static final String API_CLIENT = System.getenv("API_CLIENT") == null ? "" : System.getenv("API_CLIENT");
	 public static final String API_CLIENTDUVERA = System.getenv("API_CLIENTDUVERA") == null ? "" : System.getenv("API_CLIENTDUVERA");
	 public static final String API_CLIENTEXT = System.getenv("API_CLIENTEXT") == null ? "" : System.getenv("API_CLIENTEXT");
	 public static final String API_SRVCODE = System.getenv("API_SRVCODE") == null ? "" : System.getenv("API_SRVCODE");
	 public static final String API_URLRESPONSE = System.getenv("API_URLRESPONSE") == null ? "" : System.getenv("API_URLRESPONSE");
	 public static final String API_URLREDIRECT = System.getenv("API_URLREDIRECT") == null ? "" : System.getenv("API_URLREDIRECT");
	 public static final String API_CURRENCY = System.getenv("API_CURRENCY") == null ? "" : System.getenv("API_CURRENCY");
	 public static final String QUEUE_NAME = System.getenv("QUEUE_NAME") == null ? "" : System.getenv("QUEUE_NAME");
	 public static final String EXCHANGE_NAME = System.getenv("EXCHANGE_NAME") == null ? "" : System.getenv("EXCHANGE_NAME");
	 public static final String MQ_SECUR = System.getenv("MQ_SECUR") == null ? "" : System.getenv("MQ_SECUR");
	 public static final String MQ_USER = System.getenv("MQ_USER") == null ? "" : System.getenv("MQ_USER");
	 public static final String MQ_PORT = System.getenv("MQ_PORT") == null ? "" : System.getenv("MQ_PORT");
	 public static final String MQ_HOST = System.getenv("MQ_HOST") == null ? "" : System.getenv("MQ_HOST");
	 public static final String ACCOUNTID_APPROBE = System.getenv("ACCOUNTID_APPROBE") == null ? "" : System.getenv("ACCOUNTID_APPROBE");
}
