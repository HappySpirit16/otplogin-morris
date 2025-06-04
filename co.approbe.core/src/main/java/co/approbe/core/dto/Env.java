package co.approbe.core.dto;

public class Env {
	 public static final String QUEUE_NAME = System.getenv("QUEUE_NAME") == null ? "" : System.getenv("QUEUE_NAME");
	 public static final String EXCHANGE_NAME = System.getenv("EXCHANGE_NAME") == null ? "" : System.getenv("EXCHANGE_NAME");
	 public static final String MQ_SECUR = System.getenv("MQ_SECUR") == null ? "" : System.getenv("MQ_SECUR");
	 public static final String MQ_USER = System.getenv("MQ_USER") == null ? "" : System.getenv("MQ_USER");
	 public static final String MQ_PORT = System.getenv("MQ_PORT") == null ? "" : System.getenv("MQ_PORT");
	 public static final String MQ_HOST = System.getenv("MQ_HOST") == null ? "" : System.getenv("MQ_HOST");
}
