package Controlador;

public class SopaLetras {

    private String[][] matrizLetras;
    private int[][] matrizOcupada;

    public SopaLetras() {
        matrizLetras = new String[20][20];
        matrizOcupada = new int[20][20];
    }

    public int generarNumeroAleatorio(int limite) {
        return (int) Math.floor(Math.random() * limite);
    }

    public void inicializarMatrices() {
        String abecedario = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZ";
        String[] letrasDisponibles = abecedario.split("");

        for (int fila = 0; fila < matrizLetras.length; fila++) {
            for (int columna = 0; columna < matrizLetras[0].length; columna++) {
                matrizLetras[fila][columna] = letrasDisponibles[generarNumeroAleatorio(letrasDisponibles.length)];
                matrizOcupada[fila][columna] = 0;
            }
        }
    }

    public boolean colocarPalabraHorizontal(String[] letrasPalabra) {
        int longitudPalabra = letrasPalabra.length;
        int filaInicial = generarNumeroAleatorio(matrizLetras.length);
        int columnaInicial = generarNumeroAleatorio(matrizLetras[0].length - longitudPalabra + 1);

        boolean puedeColocarse = true;

        for (int posicion = 0; posicion < longitudPalabra; posicion++) {
            if (matrizOcupada[filaInicial][columnaInicial + posicion] == 1
                    && !matrizLetras[filaInicial][columnaInicial + posicion].equals(letrasPalabra[posicion])) {
                puedeColocarse = false;
            }
        }

        if (puedeColocarse) {
            for (int posicion = 0; posicion < longitudPalabra; posicion++) {
                matrizLetras[filaInicial][columnaInicial + posicion] = letrasPalabra[posicion];
                matrizOcupada[filaInicial][columnaInicial + posicion] = 1;
            }
        }

        return puedeColocarse;
    }

    public boolean colocarPalabraVertical(String[] letrasPalabra) {
        int longitudPalabra = letrasPalabra.length;
        int filaInicial = generarNumeroAleatorio(matrizLetras.length - longitudPalabra + 1);
        int columnaInicial = generarNumeroAleatorio(matrizLetras[0].length);

        boolean puedeColocarse = true;

        for (int posicion = 0; posicion < longitudPalabra; posicion++) {
            if (matrizOcupada[filaInicial + posicion][columnaInicial] == 1
                    && !matrizLetras[filaInicial + posicion][columnaInicial].equals(letrasPalabra[posicion])) {
                puedeColocarse = false;
            }
        }

        if (puedeColocarse) {
            for (int posicion = 0; posicion < longitudPalabra; posicion++) {
                matrizLetras[filaInicial + posicion][columnaInicial] = letrasPalabra[posicion];
                matrizOcupada[filaInicial + posicion][columnaInicial] = 1;
            }
        }

        return puedeColocarse;
    }

    public void colocarTodasLasPalabras(String textoPalabras) {
        String[] listaPalabras = textoPalabras.split(",");
        int indicePalabra = 0;

        while (indicePalabra < listaPalabras.length) {
            String palabraLimpia = listaPalabras[indicePalabra].trim().toUpperCase();

            if (!palabraLimpia.isEmpty()) {
                String[] letrasPalabra = palabraLimpia.split("");
                boolean palabraColocada = false;

                while (!palabraColocada) {
                    int orientacionAleatoria = generarNumeroAleatorio(2);

                    switch (orientacionAleatoria) {
                        case 0 -> palabraColocada = colocarPalabraHorizontal(letrasPalabra);
                        case 1 -> palabraColocada = colocarPalabraVertical(letrasPalabra);
                    }
                }
            }

            indicePalabra++;
        }
    }

    public String obtenerSopaComoTexto() {
        String textoSopa = "";

        for (int fila = 0; fila < matrizLetras.length; fila++) {
            for (int columna = 0; columna < matrizLetras[0].length; columna++) {
                textoSopa += matrizLetras[fila][columna] + " ";
            }
            textoSopa += "\n";
        }

        return textoSopa;
    }

    public String generarSopa(String textoPalabras) {
        inicializarMatrices();
        colocarTodasLasPalabras(textoPalabras);
        return obtenerSopaComoTexto();
    }
}