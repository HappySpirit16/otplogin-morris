package transactions.core.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Message {
    private boolean redelivered;
    private String data;
    private BasicProperties basicProperties;


    public boolean isRedelivered() {
        return redelivered;
    }

    public void setRedelivered(boolean redelivered) {
        this.redelivered = redelivered;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Message{" +
                ", redelivered=" + redelivered +
                ", data='" + data + '\'' +
                '}';
    }
}
