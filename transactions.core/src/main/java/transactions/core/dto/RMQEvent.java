package transactions.core.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RMQEvent {
	private String eventSourceArn;
    private Map<String, List<Message>> rmqMessagesByQueue;
    private String eventSource;

    public RMQEvent() {
        rmqMessagesByQueue = new HashMap<>();
        rmqMessagesByQueue.put("core-queue::/", new ArrayList<>());
    }
    
    

    // Getters y setters

    public String getEventSourceArn() {
        return eventSourceArn;
    }

    public void setEventSourceArn(String eventSourceArn) {
        this.eventSourceArn = eventSourceArn;
    }

    public Map<String, List<Message>> getRmqMessagesByQueue() {
        return rmqMessagesByQueue;
    }

    public void setRmqMessagesByQueue(Map<String, List<Message>> rmqMessagesByQueue) {
        this.rmqMessagesByQueue = rmqMessagesByQueue;
    }

    public String getEventSource() {
        return eventSource;
    }

    public void setEventSource(String eventSource) {
        this.eventSource = eventSource;
    }

    @Override
    public String toString() {
        return "RMQEvent{" +
                "eventSourceArn='" + eventSourceArn + '\'' +
                ", rmqMessagesByQueue=" + rmqMessagesByQueue +
                ", eventSource='" + eventSource + '\'' +
                '}';
    }
}
