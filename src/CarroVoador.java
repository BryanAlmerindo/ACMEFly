import java.util.ArrayList;

public class CarroVoador {
    private int numero;
    private String placa;
    private double valor;
    private ArrayList<Cliente> clientesComCarro;

    public CarroVoador(int numero, String placa, double valor) {
        this.numero = numero;
        this.placa = placa;
        this.valor = valor;
        clientesComCarro = new ArrayList();
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getNumero() {
        return numero;
    }

    public String getPlaca() {
        return placa;
    }

    public double getValor() {
        return valor;
    }

    public void adicionaDono(Cliente cliente) {
        clientesComCarro.add(cliente);
    }

    public Cliente getDono() {
        if (clientesComCarro.isEmpty()) {
            return null;
        }
        return clientesComCarro.get(0);
    }


}
