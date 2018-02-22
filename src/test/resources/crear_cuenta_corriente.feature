Feature: Falabella crate current accounts

  Scenario Outline: Escenario crear cuenta con exito
    Given Se determina el navegador <browser>
    And El usuario navega a la pagina de Falabella
    When Introduce <rut> y <celular> y <correo electronico>
    And Se procede a solicitar cuenta
    And Se procede a ingresar <nombre y apellido >
    And Se procede a ingresar <fehca de nacimiento>
    And Se procede a ingresar <renta liquida>
    And Se presiona boton continuar
    Then Se debe mostrar mensaje satisfactorio

    Examples: 
      | rut            | celular     | browser     | correo electronico   | nombre y apellido | fecha de nacimiento | renta liquida             |
      | "25.325.045-3" | "945115113" | "iexplorer" | "dalitagh@gmail.com" | "Dali Guzman"     | "19/01/1988"        | "$1.300.000 - $2.499.999" |
      | "25.325.045-3" | "945115113" | "chrome"    | "dalitagh@gmail.com" | "Dali Guzman"     | "19/01/1988"        | "$1.300.000 - $2.499.999" |
