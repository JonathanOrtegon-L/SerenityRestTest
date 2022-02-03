package com.serenity.rest.models.data;

import com.github.javafaker.Faker;
import com.serenity.rest.models.empleado.NuevoEmpleado;

import java.security.SecureRandom;
import java.util.Locale;

public class EmpleadoData {

    private static Faker faker = Faker.instance(new Locale("es", "CO"), new SecureRandom());
    private static final int edadMinima = 18;
    private static final int edadMaxima = 85;
    private static final int salarioMinimo = 1200000;
    private static final int salarioMaximo = 8000000;

    public static NuevoEmpleado getNuevoEmpleado() {
        return NuevoEmpleado.builder()
                .name(faker.name().fullName())
                .salary(faker.number().numberBetween(salarioMinimo,salarioMaximo))
                .age(faker.number().numberBetween(edadMinima,edadMaxima))
                .build();
    }
}
