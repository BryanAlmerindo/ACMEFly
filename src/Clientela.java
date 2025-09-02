import java.util.*;

public class Clientela {

    private ArrayList<Cliente> clientes;

    public Clientela() {
        clientes = new ArrayList<>();
    }

    public Cliente verificadorCodigo(int codigo) {
        for (Cliente auxiliar : clientes) {
            if (auxiliar.getCodigo() == codigo) {
                return auxiliar;
            }
        }
        return null;
    }

    public boolean adicionaCliente(Cliente novoCliente) {
        Cliente auxiliar = verificadorCodigo(novoCliente.getCodigo());
        if (auxiliar != null) {
            return false;
        }
        clientes.add(novoCliente);
        return false;
    }

    public Cliente mostraClienteComMaiorRenda() {
        if (clientes.isEmpty()) {
            return null;
        }

        Cliente clienteMaiorRenda = clientes.get(0);
        for (Cliente auxiliar : clientes) {
            if (auxiliar.getRenda() > clienteMaiorRenda.getRenda()) {
                clienteMaiorRenda = auxiliar;
            }
        }
        return clienteMaiorRenda;
    }

    public Cliente clienteComMaisCarros() {

        if (clientes.isEmpty()) {
            return null;
        }

        boolean nenhumTemCarro = true;

        for (Cliente auxiliar : clientes) {
            ArrayList<CarroVoador> listaCarrosComDonos = auxiliar.getCarrosComDonos();
            if (listaCarrosComDonos != null) {
                nenhumTemCarro = false;
                break;
            }
        }
        if (nenhumTemCarro == true) {
            return null;
        }

        Cliente clienteComMaisCarros = null;
        int qtd = 0;

        for (Cliente clienteAux : clientes) {

            ArrayList<CarroVoador> auxComparativo = clienteAux.getCarrosComDonos();
            if (auxComparativo != null && auxComparativo.size() > qtd) {
                qtd = auxComparativo.size();
                clienteComMaisCarros = clienteAux;
            }
        }
        return clienteComMaisCarros;
    }


    public int quantidadeDeCarros() {

        if (clientes.isEmpty()) {
            return 0;
        }

        int quantidade = 0;

        for (Cliente clienteAux : clientes) {

            ArrayList<CarroVoador> auxComparativo = clienteAux.getCarrosComDonos();
            if (auxComparativo != null && auxComparativo.size() > quantidade) {
                quantidade = auxComparativo.size();
            }
        }
        return quantidade;
    }
}


