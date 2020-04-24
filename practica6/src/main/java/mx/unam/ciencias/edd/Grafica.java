package mx.unam.ciencias.edd;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Clase para gráficas. Una gráfica es un conjunto de vértices y aristas, tales
 * que las aristas son un subconjunto del producto cruz de los vértices.
 */
public class Grafica<T> implements Coleccion<T> {

    /* Clase interna privada para iteradores. */
    private class Iterador implements Iterator<T> {

        /* Iterador auxiliar. */
        private Iterator<Vertice> iterador;

        /* Construye un nuevo iterador, auxiliándose de la lista de vértices. */
        public Iterador() {
            iterador = vertices.iterator();
        }

        /* Nos dice si hay un siguiente elemento. */
        @Override public boolean hasNext() {
            return iterador.hasNext();
        }

        /* Regresa el siguiente elemento. */
        @Override public T next() {
            return iterador.next().get();
        }
    }

    /* Clase interna privada para vértices. */
    private class Vertice implements VerticeGrafica<T> {

        /* El elemento del vértice. */
        public T elemento;
        /* El color del vértice. */
        public Color color;
        /* La lista de vecinos del vértice. */
        public Lista<Vertice> vecinos;

        /* Crea un nuevo vértice a partir de un elemento. */
        public Vertice(T elemento) {
            this.elemento = elemento;
            color = Color.NINGUNO;
            vecinos = new Lista<Vertice>();
        }

        /* Regresa el elemento del vértice. */
        @Override public T get() {
            return elemento;
        }

        /* Regresa el grado del vértice. */
        @Override public int getGrado() {
            return vecinos.getLongitud();
        }

        /* Regresa el color del vértice. */
        @Override public Color getColor() {
            return color;
        }

        /* Regresa un iterable para los vecinos. */
        @Override public Iterable<? extends VerticeGrafica<T>> vecinos() {
            return vecinos;
        }
    }

    /* Vértices. */
    private Lista<Vertice> vertices;
    /* Número de aristas. */
    private int aristas;

    /**
     * Constructor único.
     */
    public Grafica() {
        vertices = new Lista<Vertice>();
    }

    /**
     * Regresa el número de elementos en la gráfica. El número de elementos es
     * igual al número de vértices.
     * @return el número de elementos en la gráfica.
     */
    @Override public int getElementos() {
        return vertices.getLongitud();
    }

    /**
     * Regresa el número de aristas.
     * @return el número de aristas.
     */
    public int getAristas() {
        return aristas;
    }

    /**
     * Agrega un nuevo elemento a la gráfica.
     * @param elemento el elemento a agregar.
     * @throws IllegalArgumentException si el elemento es <code>null</code> o ya
     *         había sido agregado a la gráfica.
     */
    @Override public void agrega(T elemento) {
        if(elemento == null || contiene(elemento)) throw new IllegalArgumentException("Elemento inválido");
        Vertice vertice = new Vertice(elemento);
        vertices.agregaFinal(vertice);
    }

    /**
     * Conecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica. El peso de la arista que conecte a los elementos será 1.
     * @param a el primer elemento a conectar.
     * @param b el segundo elemento a conectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b ya están conectados, o si a es
     *         igual a b.
     */
    public void conecta(T a, T b) {
        if(!contiene(a) || !contiene(b)) throw new NoSuchElementException("No se ha encontrado alguno de los elemenots");
        if(a == b || sonVecinos(a, b)) throw new IllegalArgumentException("Los elementos "+a.toString()+ " "+b.toString()+" ya están conectados");
        Vertice verticeA = getVertice(a);
        Vertice verticeB = getVertice(b);
        verticeA.vecinos.agregaFinal(verticeB);
        verticeB.vecinos.agregaFinal(verticeA);
        this.aristas++;
    }

    /**
     * Desconecta dos elementos de la gráfica. Los elementos deben estar en la
     * gráfica y estar conectados entre ellos.
     * @param a el primer elemento a desconectar.
     * @param b el segundo elemento a desconectar.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     * @throws IllegalArgumentException si a o b no están conectados.
     */
    public void desconecta(T a, T b) {
        if(a == null || b == null || !contiene(a) || !contiene(b)) throw new NoSuchElementException("No se contienen los elementos "+a.toString()+" o "+b.toString());
        if(!sonVecinos(a, b)) throw new IllegalArgumentException("Los elementos "+a.toString()+" "+b.toString()+" No son vecinos");
        Vertice verticeA = getVertice(a);
        Vertice verticeB = getVertice(b);
        verticeA.vecinos.elimina(verticeB);
        verticeB.vecinos.elimina(verticeA);
        aristas--;
    }

    /**
     * Nos dice si el elemento está contenido en la gráfica.
     * @return <code>true</code> si el elemento está contenido en la gráfica,
     *         <code>false</code> en otro caso.
     */
    @Override public boolean contiene(T elemento) {
        if(elemento == null) throw new IllegalArgumentException("Elemento inválido");
        Vertice vertice = getVertice(elemento);
        return vertice != null;
    }

    /**
     * Elimina un elemento de la gráfica. El elemento tiene que estar contenido
     * en la gráfica.
     * @param elemento el elemento a eliminar.
     * @throws NoSuchElementException si el elemento no está contenido en la
     *         gráfica.
     */
    @Override public void elimina(T elemento) {
      if(elemento == null) throw new IllegalArgumentException("Elemento inválido");
      Vertice vertice = getVertice(elemento);
      if(vertice == null) throw new NoSuchElementException("No se encontró el elemento"+elemento.toString());
      int aristasEliminados = 0;
      for(Vertice v : vertice.vecinos){
        v.vecinos.elimina(vertice);
        aristasEliminados++;
      }
      vertices.elimina(vertice);
      this.aristas -= aristasEliminados;
    }

    /**
     * Nos dice si dos elementos de la gráfica están conectados. Los elementos
     * deben estar en la gráfica.
     * @param a el primer elemento.
     * @param b el segundo elemento.
     * @return <code>true</code> si a y b son vecinos, <code>false</code> en otro caso.
     * @throws NoSuchElementException si a o b no son elementos de la gráfica.
     */
    public boolean sonVecinos(T a, T b) {
      if(a == null || b == null) throw new IllegalArgumentException("Elemento inválido");
      if(!contiene(a) || !contiene(b)) throw new NoSuchElementException("No se ha encontrado uno de los elementos");
      Vertice va = getVertice(a);
      Vertice vb = getVertice(b);
      if(va.vecinos.contiene(vb) && vb.vecinos.contiene(va)) return true;
      return false;
    }

    /**
     * Regresa el vértice correspondiente el elemento recibido.
     * @param elemento el elemento del que queremos el vértice.
     * @throws NoSuchElementException si elemento no es elemento de la gráfica.
     * @return el vértice correspondiente el elemento recibido.
     */
    public VerticeGrafica<T> vertice(T elemento) {
        if(elemento == null) throw new IllegalArgumentException("Elemento inválido");
        Vertice v = null;
        for(Vertice vertice : vertices){
            if(vertice.get().equals(elemento))
              v = vertice;
        }
        if(v == null) throw new NoSuchElementException("No se ha encontrado el vértice");
        return v;
    }

    /**
     * Define el color del vértice recibido.
     * @param vertice el vértice al que queremos definirle el color.
     * @param color el nuevo color del vértice.
     * @throws IllegalArgumentException si el vértice no es válido.
     */
    public void setColor(VerticeGrafica<T> vertice, Color color) {
        if (vertice == null || vertice.getClass() != Vertice.class) throw new IllegalArgumentException("Vértice inválido");
        Vertice v = (Vertice)vertice;
        if(!vertices.contiene(v))
          throw new IllegalArgumentException();
        v.color = color;
    }

    /**
     * Nos dice si la gráfica es conexa.
     * @return <code>true</code> si la gráfica es conexa, <code>false</code> en
     *         otro caso.
     */
    public boolean esConexa() {
        if(vertices.getLongitud() == 0 || vertices.getLongitud() == 1) return true;
        Cola<Vertice> estructura = new Cola<>();
        Vertice w = vertices.getPrimero();
        paraCadaVertice(v -> setColor(v, Color.ROJO));
        setColor(w, Color.NEGRO);
        estructura.mete(w);
        while(!estructura.esVacia()){
          Vertice aux = estructura.saca();
          for(Vertice v : aux.vecinos)
            if(v.getColor().equals(Color.ROJO)){
              setColor(v, Color.NEGRO);
              estructura.mete(v);
            }
        }
        for(Vertice v : vertices)
          if(!v.getColor().equals(Color.NEGRO))
            return false;
        paraCadaVertice(v -> setColor(v, Color.NINGUNO));
        return true;
    }

    /**
     * Realiza la acción recibida en cada uno de los vértices de la gráfica, en
     * el orden en que fueron agregados.
     * @param accion la acción a realizar.
     */
    public void paraCadaVertice(AccionVerticeGrafica<T> accion) {
        for(Vertice vertice : vertices)
          accion.actua(vertice);
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por BFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void bfs(T elemento, AccionVerticeGrafica<T> accion) {
        if(elemento == null || !contiene(elemento)) throw new NoSuchElementException("El elemento "+elemento.toString()+" no está en la gráfica");
        auxiliarRecorrido(elemento, accion, new Cola<Vertice>());
    }

    /**
     * Realiza la acción recibida en todos los vértices de la gráfica, en el
     * orden determinado por DFS, comenzando por el vértice correspondiente al
     * elemento recibido. Al terminar el método, todos los vértices tendrán
     * color {@link Color#NINGUNO}.
     * @param elemento el elemento sobre cuyo vértice queremos comenzar el
     *        recorrido.
     * @param accion la acción a realizar.
     * @throws NoSuchElementException si el elemento no está en la gráfica.
     */
    public void dfs(T elemento, AccionVerticeGrafica<T> accion){
        if(elemento == null || !contiene(elemento)) throw new NoSuchElementException("El elemento "+elemento.toString()+" no está en la gráfica");
        auxiliarRecorrido(elemento, accion, new Pila<Vertice>());
    }

    /**
    * Método auxiliar para el recorrido
    * @param T elemento por el que se iniciará
    * @param AccionVerticeGrafica<T> acción que se realizará a los vértices
    * @param MeteSaca<T> estructura que se utilizará
    */
    private void auxiliarRecorrido(T elemento, AccionVerticeGrafica<T> accion, MeteSaca<Vertice> estructura){
      paraCadaVertice(v -> setColor(v, Color.ROJO));
      Vertice w = getVertice(elemento);
      setColor(w, Color.NEGRO);
      estructura.mete(w);
      while(!estructura.esVacia()){
        Vertice aux = estructura.saca();
        accion.actua(aux);
        for(Vertice v : aux.vecinos)
          if(v.getColor().equals(Color.ROJO)){
            setColor(v, Color.NEGRO);
            estructura.mete(v);
          }
      }
      paraCadaVertice(v -> setColor(v, Color.NINGUNO));
    }

    /**
     * Nos dice si la gráfica es vacía.
     * @return <code>true</code> si la gráfica es vacía, <code>false</code> en
     *         otro caso.
     */
    @Override public boolean esVacia() {
        return vertices.esVacia();
    }

    /**
     * Limpia la gráfica de vértices y aristas, dejándola vacía.
     */
    @Override public void limpia() {
        vertices.limpia();
        aristas = 0;
    }

    /**
     * Regresa una representación en cadena de la gráfica.
     * @return una representación en cadena de la gráfica.
     */
    @Override public String toString() {
        paraCadaVertice(v -> setColor(v, Color.ROJO));
        String s = "{";
        String e = "{";
        for(Vertice v : vertices){
           s += v.get() + ", ";
          for(Vertice vv : v.vecinos){
            if(vv.color == Color.ROJO)
              e += "(" + v.get() + ", " + vv.get() + "), ";
            v.color = Color.NEGRO;
          }
        }
        paraCadaVertice(v -> setColor(v, Color.NINGUNO));
        return s + "}, " + e + "}";
    }

    /**
     * Nos dice si la gráfica es igual al objeto recibido.
     * @param objeto el objeto con el que hay que comparar.
     * @return <code>true</code> si la gráfica es igual al objeto recibido;
     *         <code>false</code> en otro caso.
     */
    @Override public boolean equals(Object objeto) {
        if (objeto == null || getClass() != objeto.getClass())
            return false;
        @SuppressWarnings("unchecked") Grafica<T> grafica = (Grafica<T>)objeto;
        if(grafica.getAristas() != aristas || grafica.vertices.getLongitud() != vertices.getLongitud()) return false;
        /* Checamos si ambas gráficas tienen los mismos vértices */
        for(Vertice v : vertices){
          if(!grafica.contiene(v.get())) return false;
        }
        for(Vertice v : vertices){
          for(Vertice vg : v.vecinos){
            if(!grafica.sonVecinos(v.elemento, vg.elemento))
                return false;
          }
        }
        return true;
    }

    /**
     * Regresa un iterador para iterar la gráfica. La gráfica se itera en el
     * orden en que fueron agregados sus elementos.
     * @return un iterador para iterar la gráfica.
     */
    @Override public Iterator<T> iterator() {
        return new Iterador();
    }

    /**
    * Método para obtener el vértice que contiene el elemento T
    * @param T elemento
    * @return VerticeGrafica<T> vertice que contiene el elemento T
    */
    private Vertice getVertice(T elemento){
      if(elemento == null) throw new IllegalArgumentException("Elemento inválido");
      for(Vertice vertice : vertices)
          if(vertice.get().equals(elemento)) return vertice;
      return null;
    }
    /* Método que colorea el número de componentes conexas de la gráfica */
  private void coloreaComponenteConexa(Vertice v){
      Cola<Vertice> q = new Cola<>();
      setColor(v, Color.NEGRO);
      q.mete(v);
      while(!q.esVacia()){
        v = q.saca();
        for(Vertice w : v.vecinos)
          if(w.getColor().equals(Color.ROJO)){
            setColor(w, Color.NEGRO);
            q.mete(w);
          }
      }
  }
  /* Método que calcula el número de componentes conexas de la gráfica */
  public int calculaComponentesConexas(){
    paraCadaVertice((v) -> setColor(v, Color.ROJO));
    boolean c;
    int n=0;
    do{
      c=false;
      for(Vertice v: vertices) {
        if(v.getColor() !=Color.ROJO)
          continue;
        c=true;
        coloreaComponenteConexa(v);
        n++;
      }
    }while(c);
    return n;
  }
}
