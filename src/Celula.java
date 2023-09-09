public class Celula {
    private boolean estado;

    public Celula(Celula celula) {
        setEstado(celula.isAlive());
    }

    public Celula() {setEstado(false);}

    public void kill() {
        setEstado(false);
    }

    public void rebirth() {
        setEstado(true);
    }

    public boolean isAlive() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
}
