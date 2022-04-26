Feature: Operaciones empleados example
#if the scenario fails the consumption of the @after throws the exception

  @run
  Scenario: Consulta empleados
    When un tester consulta empleados
    Then puede recuperar la información de los empleados
    And un tester consulta un empleado "1"
  #no fail Step then in @after tag execute same step "un tester consulta empleados"
    And puede recuperar la información del empleado

  @run
  Scenario: Consulta empleados
    When un tester consulta empleados
    Then puede recuperar la información de los empleados
    And un tester consulta un empleado "A"
  #fail Step then in @after tag execute same step "un tester consulta empleados"
    And puede recuperar la información del empleado