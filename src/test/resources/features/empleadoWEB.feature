Feature: Operaciones empleados example
#if the scenario fails the consumption of the @after throws the exception

  @runweb
  Scenario: abre el email y se logea successful
    When ingresa al correo electrónico y da clic en "//button[contains(text(),'GO')]"

  @runweb
  Scenario: abre el email y se logea successful 2
    When ingresa al correo electrónico y da clic en "//button[contains(text(),'GO')]"

  @runweb
  Scenario: Consulta empleados failed 1
    When ingresa al correo electrónico y da clic en "//button[contains(text(),'GO1')]"

  @runweb
  Scenario: Consulta empleados failed 2
    When ingresa al correo electrónico y da clic en "//button[contains(text(),'GO1')]"


  @runweb
  Scenario: Consulta empleados failed 3
    When ingresa al correo electrónico y da clic en "//button[contains(text(),'GO1')]"


  @runweb
  Scenario: Consulta empleados failed 4
    When ingresa al correo electrónico y da clic en "//button[contains(text(),'GO1')]"