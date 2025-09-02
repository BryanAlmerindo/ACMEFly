import java.util.ArrayList;

public class Cliente {
    ArrayList<CarroVoador> carrosComDonos;
    private int codigo;
    private String nome;
    private double renda;

    public Cliente(int codigo, String nome, double renda) {
        this.codigo = codigo;
        this.nome = nome;
        this.renda = renda;
        carrosComDonos = new ArrayList();
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setRenda(double renda) {
        this.renda = renda;
    }

    public int getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public double getRenda() {
        return renda;
    }


    public CarroVoador getCarroVoador() {
        if (carrosComDonos.isEmpty()) {
            return null;
        }
        return carrosComDonos.get(0);
    }

    public void adicionaCarroVoador(CarroVoador carroVoador) {
        carrosComDonos.add(carroVoador);
    }

    public ArrayList<CarroVoador> getCarrosComDonos() {
        if (carrosComDonos.isEmpty()) {
            return null;
        }
        ArrayList<CarroVoador> copia = (ArrayList<CarroVoador>) carrosComDonos.clone();
        return copia;
    }

}

