package es.ifp.ejercicioevaluableuf1dam;

public class Operaciones { //CLASE EJEMPLO PARA PROBAR EL TESTING

    public float suma(float a, float b) {
        return a+b;
    }
    public boolean esPositivo(float a) {
        if (a>=0) return true;
        return false;
    }
    public float mayor(float a, float b) {
        if (a>=b) return a;
        return b;
    }
    public float dividir(float a, float b) {
        if (b == 0.0f) return -1.0f;
        return a/b;
    }
}
