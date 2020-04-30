package mx.unam.ciencias.edd;

/**
 * Práctica 8: Trayectoria mínima y algoritmo de Dijkstra.
 */
public class Practica8 {

    private static String cadena;

    public static void main(String[] args) {
        /*       3
         *    b─────d
         *  1╱│╲    │╲1
         *  ╱ │ ╲1  │ ╲
         * a 2│  ╲  │2 f
         *  ╲ │   ╲ │ ╱
         *  2╲│    ╲│╱1
         *    c─────e
         *       1           */
        Grafica<String> grafica = new Grafica<String>();
        grafica.agrega("a");
        grafica.agrega("b");
        grafica.agrega("c");
        grafica.agrega("d");
        grafica.agrega("e");
        grafica.agrega("f");

        grafica.conecta("a", "b", 1);
        grafica.conecta("a", "c", 2);
        grafica.conecta("b", "c", 2);
        grafica.conecta("b", "d", 3);
        grafica.conecta("b", "e", 1);
        grafica.conecta("c", "e", 1);
        grafica.conecta("d", "e", 2);
        grafica.conecta("d", "f", 1);
        grafica.conecta("e", "f", 1);

        System.out.println(grafica);

        /* BFS */
        grafica.paraCadaVertice(v -> grafica.setColor(v, Color.ROJO));
        Cola<VerticeGrafica<String>> c = new Cola<VerticeGrafica<String>>();
        VerticeGrafica<String> vertice = grafica.vertice("a");
        grafica.setColor(vertice, Color.NEGRO);
        c.mete(vertice);
        cadena = "BFS 1: ";
        while (!c.esVacia()) {
            vertice = c.saca();
            cadena += vertice.get() + ", ";
            for (VerticeGrafica<String> vecino : vertice.vecinos()) {
                if (vecino.getColor() == Color.ROJO) {
                    grafica.setColor(vecino, Color.NEGRO);
                    c.mete(vecino);
                }
            }
        }
        System.out.println(cadena);

        /* BFS de la clase */
        cadena = "BFS 2: ";
        grafica.bfs("a", v -> cadena += v.get() + ", ");
        System.out.println(cadena);

        /* DFS */
        grafica.paraCadaVertice(v -> grafica.setColor(v, Color.ROJO));
        Pila<VerticeGrafica<String>> p = new Pila<VerticeGrafica<String>>();
        vertice = grafica.vertice("a");
        grafica.setColor(vertice, Color.NEGRO);
        p.mete(vertice);
        cadena = "DFS 1: ";
        while (!p.esVacia()) {
            vertice = p.saca();
            cadena += vertice.get() + ", ";
            for (VerticeGrafica<String> vecino : vertice.vecinos()) {
                if (vecino.getColor() == Color.ROJO) {
                    grafica.setColor(vecino, Color.NEGRO);
                    p.mete(vecino);
                }
            }
        }
        System.out.println(cadena);

        /* DFS de la clase */
        cadena = "DFS 2: ";
        grafica.dfs("a", v -> cadena += v.get() + ", ");
        System.out.println(cadena);

        /* Trayectoria mínima */
        Lista<VerticeGrafica<String>> trayectoria =
            grafica.trayectoriaMinima("a", "f");
        String s = "Trayectoría mínima: ";
        for (VerticeGrafica<String> v : trayectoria)
            s += v.get() + ", ";
        System.out.println(s);

        /* Dijkstra */
        Lista<VerticeGrafica<String>> dijkstra =
            grafica.dijkstra("a", "f");
        s = "Dijkstra: ";
        for (VerticeGrafica<String> v : dijkstra)
            s += v.get() + ", ";
        System.out.println(s);

        /* Algoritmo de Prim */
        System.out.print("Árbol Generador de Peso Mínimo: ");
        Grafica<String> subGrafica = grafica.arbolPesoMinimo();
        System.out.println(subGrafica.toString());



        /**              4     5      3
        *             h-----a-------b----c
        *          7 |      | 3     | 1
        *            |----- I       e
        *                   |
        *                 1_|_1
        *                 J    K
        *
        **/
        Grafica<String> grafica2 = new Grafica<String>();
        grafica2.agrega("a");
        grafica2.agrega("b");
        grafica2.agrega("c");
        grafica2.agrega("e");
        grafica2.agrega("h");
        grafica2.agrega("i");
        grafica2.agrega("j");
        grafica2.agrega("k");

        grafica2.conecta("a", "b", 5);
        grafica2.conecta("a", "i", 3);
        grafica2.conecta("b", "c", 3);
        grafica2.conecta("b", "e", 1);
        grafica2.conecta("a", "h", 4);
        grafica2.conecta("h", "i", 7);
        grafica2.conecta("i", "j", 1);
        grafica2.conecta("i", "k", 1);

        /* Algoritmo de Prim */
        Grafica<String> arbolPesoMinimo = grafica2.arbolPesoMinimo();
        System.out.print("Árbol Generador de Peso Mínimo: "); 
        System.out.println(arbolPesoMinimo.toString());
    }
}
