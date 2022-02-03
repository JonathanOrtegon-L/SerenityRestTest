package com.serenity.rest.models.empleado;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NuevoEmpleado {
    private String name;
    private int salary;
    private int age;
}
