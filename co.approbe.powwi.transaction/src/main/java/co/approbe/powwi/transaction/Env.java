package co.approbe.powwi.transaction;

public class Env {
	 public static final String AUTH_HOST = System.getenv("AUTH_HOST") == null ? "" : System.getenv("AUTH_HOST");
	 public static final String AUTH_CUENTA = System.getenv("AUTH_CUENTA") == null ? "" : System.getenv("AUTH_CUENTA");
	 public static final String AUTH_REGION = System.getenv("AUTH_REGION") == null ? "" : System.getenv("AUTH_REGION");
	 public static final String AUTH_PATH = System.getenv("AUTH_PATH") == null ? "" : System.getenv("AUTH_PATH");
	 public static final String AUTH_ACCESS_KEY = System.getenv("AUTH_ACCESS_KEY") == null ? "" : System.getenv("AUTH_ACCESS_KEY");
	 public static final String AUTH_SECRET_KEY = System.getenv("AUTH_SECRET_KEY") == null ? "" : System.getenv("AUTH_SECRET_KEY");
	 public static final String AUTH_USERNAME = System.getenv("AUTH_USERNAME") == null ? "" : System.getenv("AUTH_USERNAME");
	 public static final String AUTH_IDCUENTA = System.getenv("AUTH_IDCUENTA") == null ? "" : System.getenv("AUTH_IDCUENTA");
	 public static final String AUTH_API = System.getenv("AUTH_API") == null ? "" : System.getenv("AUTH_API");
	 public static final String QUEUE_NAME = System.getenv("QUEUE_NAME") == null ? "" : System.getenv("QUEUE_NAME");
	 public static final String EXCHANGE_NAME = System.getenv("EXCHANGE_NAME") == null ? "" : System.getenv("EXCHANGE_NAME");
	 public static final String MQ_SECUR = System.getenv("MQ_SECUR") == null ? "" : System.getenv("MQ_SECUR");
	 public static final String MQ_USER = System.getenv("MQ_USER") == null ? "" : System.getenv("MQ_USER");
	 public static final String MQ_PORT = System.getenv("MQ_PORT") == null ? "" : System.getenv("MQ_PORT");
	 public static final String MQ_HOST = System.getenv("MQ_HOST") == null ? "" : System.getenv("MQ_HOST");
}
