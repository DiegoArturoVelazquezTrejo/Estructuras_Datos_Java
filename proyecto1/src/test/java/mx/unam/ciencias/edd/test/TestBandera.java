package mx.unam.ciencias.edd.test;

import java.util.NoSuchElementException;
import java.util.Random;
import mx.unam.ciencias.edd.proyecto1.Bandera;
import mx.unam.ciencias.edd.proyecto1.Oracion;
import mx.unam.ciencias.edd.proyecto1.Lista;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

/**
 * Clase para pruebas unitarias de la clase {@link Bandera}.
 */
public class TestBandera {

    /** Expiración para que ninguna prueba tarde más de 5 segundos. */
    @Rule public Timeout expiracion = Timeout.seconds(5);

    /**
     * Constructor por omisión.
     */
    public TestBandera() {}

    /**
     * Prueba unitaria para {@link Cola#Cola}.
     */
    @Test public void testAnaliza() {
      String agumentosString = "poema1.txt -r poema2.txt poema3.txt";
      String[] argumentos = agumentosString.split(" ");
      Bandera bandera = new Bandera(argumentos);
      Assert.assertTrue(bandera.getReversa());
      Assert.assertFalse(bandera.getGuarda());

      String agumentosString1 = "poema1.txt -r -o poema3.txt";
      String[] argumentos1 = agumentosString1.split(" ");
      Bandera bandera1 = new Bandera(argumentos1);
      Assert.assertTrue(bandera1.getReversa());
      Assert.assertTrue(bandera1.getGuarda());
      System.out.println(bandera1.getDocumentoGuarda());
      Assert.assertTrue(bandera1.getDocumentoGuarda().equals(argumentos1[3]));

      String agumentosString2 = "poema1.txt poema2.txt poema3.txt";
      String[] argumentos2 = agumentosString2.split(" ");
      Bandera bandera2 = new Bandera(argumentos2);
      Assert.assertFalse(bandera2.getReversa());
      Assert.assertFalse(bandera2.getGuarda());
    }



}
