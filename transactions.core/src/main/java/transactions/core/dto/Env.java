package transactions.core.dto;

public class Env {
	 public static final String QUEUE_NAME = System.getenv("QUEUE_NAME") == null ? "" : System.getenv("QUEUE_NAME");
}
