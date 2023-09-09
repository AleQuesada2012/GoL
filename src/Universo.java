import java.util.Random;

public class Universo {
    private int tamaño;

    private int cantVivas; // minimo n y máximo nxn

    private Celula[][] matriz;

    public Universo() {
        setTamaño(3);
        setCantVivas(5);
        setMatriz(new Celula[3][3]);
    }
    public Universo(Universo anterior) {
        setCantVivas(anterior.getCantVivas());
        setTamaño(anterior.getTamaño());
        setMatriz(new Celula[getTamaño()][getTamaño()]);

        for (int i = 0; i < getTamaño(); i++) {
            for (int j = 0; j < getTamaño(); j++) {
                this.matriz[i][j] = new Celula(anterior.getMatriz()[i][j]);
            }
        }
    }

    public Universo(int n) {
        setTamaño(n);
        setCantVivas(n);
        setMatriz(new Celula[n][n]);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                this.matriz[i][j] = new Celula();
            }

        }
    }

    public Universo(int n, int vivas, Celula[][] matriz) {
        setTamaño(n);
        setCantVivas(vivas);
        setMatriz(new Celula[getTamaño()][getTamaño()]);
        for (int i = 0; i < getTamaño(); i++) {
            for (int j = 0; j < getTamaño(); j++) {
                this.matriz[i][j] = new Celula(matriz[i][j]);
            }
        }
    }
    
    public Universo(Celula[][] matriz){
        setTamaño(this.tamaño);
        setCantVivas(this.cantVivas);
        setMatriz(new Celula[getTamaño()][getTamaño()]);
        for (int i = 0; i < getTamaño(); i++) {
            for (int j = 0; j < getTamaño(); j++) {
                this.matriz[i][j] = new Celula(matriz[i][j]);
            }
        }
    }


    /**
     * Metodo para colocar las celulas vivas de la primera generacion. Usa randoms para determinar las posiciones.
     * @param n el tamaño de la matriz (se asume que es cuadrada, así que es igual para filas que columnas)
     */
    public void colocarVivas(int n) {
        // visitar todas las celulas e inicializarlas
        int min = n;
        int max = n * n;
        Random rand = new Random();
        int vivasIniciales = rand.nextInt(min, max + 1);
        setCantVivas(vivasIniciales);
        int vivasActuales = 0;
        while (vivasActuales < vivasIniciales) {
            int i = rand.nextInt(n);
            int j = rand.nextInt(n);

            if (!matriz[i][j].isAlive()) {
                matriz[i][j].setEstado(true);
                vivasActuales++;
            }
        }
    }

    // metodos para las reglas de las células

    public Universo aplicaReglas() {
        Universo nextGen = new Universo(this); // Create a new Universe as the next generation
        int[][] matDirecciones = {{-1, -1}, {-1, 0}, {-1, 1}, {0, -1}, {0, 1}, {1, -1}, {1, 0}, {1, 1}};
        int vecinasVivas;
        int n = this.getTamaño();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                vecinasVivas = 0;
                for (int[] dir : matDirecciones) {
                    int pos_i = (i + dir[0] + n) % n;
                    int pos_j = (j + dir[1] + n) % n;
                    if (getMatriz()[pos_i][pos_j].isAlive()) vecinasVivas++;
                }
                if (getMatriz()[i][j].isAlive() && (vecinasVivas >= 4 || vecinasVivas <= 1)) {
                    nextGen.getMatriz()[i][j].kill(); // Modify nextGen, not this
                    nextGen.setCantVivas(nextGen.getCantVivas() - 1); // Modify nextGen's count
                } else if (!getMatriz()[i][j].isAlive() && vecinasVivas == 3) {
                    nextGen.getMatriz()[i][j].rebirth(); // Modify nextGen, not this
                    nextGen.setCantVivas(nextGen.getCantVivas() + 1); // Modify nextGen's count
                }
            }
        }
        return nextGen; // Return the next generation
    }


    public int getTamaño() {
        return tamaño;
    }

    public void setTamaño(int tamaño) {
        this.tamaño = tamaño;
    }

    public int getCantVivas() {
        return cantVivas;
    }

    public void setCantVivas(int cantVivas) {
        this.cantVivas = cantVivas;
    }

    public Celula[][] getMatriz() {
        return matriz;
    }

    public void setMatriz(Celula[][] matriz) {
        this.matriz = matriz;
    }
    
    @Override
    public String toString() {
        StringBuilder mat = new StringBuilder();
        for (int i = 0; i < getTamaño(); i++) {
            for (int j = 0; j < getTamaño(); j++) {
                mat.append(getMatriz()[i][j].isAlive() ? "* " : "- ");
            }
            mat.append('\n');
        }
        // mat.append('\n');
        return mat.toString();
    }
}
