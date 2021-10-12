package academy.arima.store.product.controller;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@Builder
@ToString
public class ErrorMessage {
    private String code;
    private List<Map<String, String>> messages;
}
