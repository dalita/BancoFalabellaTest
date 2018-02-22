Feature: Falabella crate current accounts

  Scenario Outline: Escenario crear cuenta con exito
    Given Se determina el navegador <browser>
    And El usuario navega a la pagina de Falabella
    When Introduce <rut> y <celular> y <correoElectronico>
    And Se procede a solicitar cuenta
    And Se procede a ingresar <nombreApellido> y <fechaNacimiento> y  <rentaLiquida>
    And Se presiona boton continuar
    Then Se debe mostrar mensaje satisfactorio

    Examples: 
      | rut            | celular     | browser  | correoElectronico    | nombreApellido | fechaNacimiento | rentaLiquida |
      | "25.325.045-3" | "945115113" | "chrome" | "dalitagh@gmail.com" | "Dali Guzman"  | "19/01/1988"    | "1: 399999"  |
