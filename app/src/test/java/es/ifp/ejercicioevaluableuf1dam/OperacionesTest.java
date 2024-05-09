package es.ifp.ejercicioevaluableuf1dam;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class OperacionesTest { //Clase creada por el IDE

    @Test
    public void testSuma() {
        Operaciones op = new Operaciones();
        float res = op.suma(20.0f, 30.0f);
        assertEquals(50.0f, res, 0.0001f); //0.0001f margen de error, Delta.
    }

    @Test
    public void TestEsPositivo() {
    }

    @Test
    public void TestMayor() {
        Operaciones op= new Operaciones();
        float res= op.mayor(10.0f,6.0f);
        assertEquals(10.0f, res, 0.0001f);
        res= op.mayor(-10.0f,-6.0f);
        assertEquals(-6.0f, res, 0.0001f);
    }

    @Test
    public void TestDividir() {
    }
}