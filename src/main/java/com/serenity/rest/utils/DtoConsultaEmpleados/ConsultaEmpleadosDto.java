package com.serenity.rest.utils.DtoConsultaEmpleados;

import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonInclude;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonProperty;
import io.cucumber.datatable.dependency.com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@JsonPropertyOrder({
        "status",
        "data",
        "message"
})
public class ConsultaEmpleadosDto {
    @JsonProperty("status")
    private String status;
    @JsonProperty("data")
    private List<ConsultaEmpleadoDetalleDto> data = null;
    @JsonProperty("message")
    private String message;
}
