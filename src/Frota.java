import java.util.*;

public class Frota {
    private ArrayList<CarroVoador> frota;

    public Frota() {
        frota = new ArrayList<>();
    }

    public CarroVoador verificaDisponibilidadeCarro(String placa, int numero) {
        for (CarroVoador auxiliar : frota) {
            if (auxiliar.getPlaca().equals(placa) || auxiliar.getNumero() == numero) {
                return auxiliar;
            }
        }
        return null;
    }


    public boolean adicionaCarroVoador(CarroVoador carroVoador) {
        CarroVoador auxiliar = verificaDisponibilidadeCarro(carroVoador.getPlaca(), carroVoador.getNumero());
        if (auxiliar == null) {
            frota.add(carroVoador);
            return true;
        }
        return false;
    }

    public CarroVoador consultaCarroVoadorNumero(int numero) {
        for (CarroVoador aux : frota) {
            if (aux.getNumero() == numero) {
                return aux;
            }
        }
        return null;
    }

    public CarroVoador consultaCarroVoadorPlaca(String placa) {
        for (CarroVoador aux : frota) {
            if (aux.getPlaca().equals(placa)) {
                return aux;
            }
        }
        return null;
    }

    public CarroVoador mostraCarroMaisBarato() {

        if (frota.isEmpty()) {
            return null;
        }

        CarroVoador carroMaisBarato = frota.get(0);
        for (CarroVoador auxiliar : frota) {
            if (auxiliar.getValor() < carroMaisBarato.getValor()) {
                carroMaisBarato = auxiliar;
            }
        }
        return carroMaisBarato;
    }

    public CarroVoador carroMaisPertoDaMedia() {
        if (frota.isEmpty()) {
            return null;
        }
        double soma = 0;
        double media;

        ArrayList<CarroVoador> carrosComDonos = new ArrayList<>();
        for (CarroVoador auxiliar : frota) {
            auxiliar.getDono();
            if (auxiliar != null) {
                carrosComDonos.add(auxiliar);
            }
        }

        if (carrosComDonos.isEmpty()) {
            return null;
        }

        for (CarroVoador aux : carrosComDonos) {
            soma = soma + aux.getValor();
        }
        media = soma / carrosComDonos.size();

        CarroVoador carroProximoDaMedia = carrosComDonos.get(0);
        double menorDiferencaFinal = Math.abs(carroProximoDaMedia.getValor() - media);

        for (CarroVoador aux : carrosComDonos) {
            double diferencaBaseInicial = Math.abs(aux.getValor() - media);
            if (diferencaBaseInicial < menorDiferencaFinal) {
                menorDiferencaFinal = diferencaBaseInicial;
                carroProximoDaMedia = aux;
            }
        }
        return carroProximoDaMedia;
    }
}


