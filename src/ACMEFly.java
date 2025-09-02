import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.Locale;
import java.util.Scanner;

public class ACMEFly {
    private Scanner in = new Scanner(System.in);
    private PrintStream saidaPadrao = System.out;
    private final String nomeArquivoEntrada = "dadosentrada.txt";
    private final String nomeArquivoSaida = "dadossaida.txt";
    private Clientela clientela;
    private Frota frota;

    public ACMEFly() {
        redirecionaEntrada();
        redirecionaSaida();
        frota = new Frota();
        clientela = new Clientela();
    }

    public void executar() {
        cadastrarCarrosVoadores();
        cadastrarCliente();
        mostraCarroMaisBarato();
        mostraClienteComMaiorRenda();
        venderCarrosVoadores();
        mostrarClienteComMaisCarros();
        mostrarDadosDeUmCarro();
        carroMaisProximoDaMedia();
    }


    public void cadastrarCarrosVoadores() {
        int opcao;
        do {
            int num = in.nextInt();
            in.nextLine();
            if (num == -1) {
                break;
            }

            String placa = in.nextLine();
            double preco = in.nextDouble();
            in.nextLine();

            if (frota.consultaCarroVoadorNumero(num) != null || frota.consultaCarroVoadorPlaca(placa) != null) {
                System.out.println("1:erro-carro repetido.");
                continue;
            }

            CarroVoador novoCarro = new CarroVoador(num, placa, preco);
            frota.adicionaCarroVoador(novoCarro);
            System.out.println("1:" + novoCarro.getNumero() + ":" + novoCarro.getPlaca() + ":" + novoCarro.getValor());
        } while (true);
    }

    public void cadastrarCliente() {
        int opcao;
        do {


            int cod = in.nextInt();
            in.nextLine();
            if (cod == -1) {
                break;
            }

            String nome = in.nextLine();


            double renda = in.nextDouble();
            in.nextLine();

            if (clientela.verificadorCodigo(cod) != null) {
                System.out.println("2:erro-cliente repetido.");
                continue;
            }


            Cliente novoCliente = new Cliente(cod, nome, renda);
            clientela.adicionaCliente(novoCliente);
            System.out.println("2:" + novoCliente.getCodigo() + ":" + novoCliente.getNome() + ":" + novoCliente.getRenda());

        } while (true);
    }

    public void mostraCarroMaisBarato() {
        CarroVoador aux = frota.mostraCarroMaisBarato();
        if (aux != null) {
            System.out.println("3:" + aux.getNumero() + ":" + aux.getPlaca() + ":" + aux.getValor());
        } else {
            System.out.println("3:nenhum carro encontrado.");
        }

    }

    public void mostraClienteComMaiorRenda() {
        Cliente aux = clientela.mostraClienteComMaiorRenda();
        if (aux != null) {
            System.out.println("4:" + aux.getCodigo() + ":" + aux.getNome() + ":" + aux.getRenda());
        } else {
            System.out.println("4:nenhum cliente encontrado.");
        }
    }

    public void venderCarrosVoadores() {
        int opcao;
        do {

            int codCliente = in.nextInt();
            in.nextLine();
            if (codCliente == -1) {
                break;
            }

            Cliente clienteComprador = clientela.verificadorCodigo(codCliente);
            if (clienteComprador == null) {
                System.out.println("5:erro-cliente inexistente.");
                in.nextInt();
                continue;
            }

            int numCarro = in.nextInt();

            CarroVoador carroComprado = frota.consultaCarroVoadorNumero(numCarro);
            if (carroComprado == null) {
                System.out.println("5:erro-carro inexistente.");
                continue;
            }
            if (carroComprado.getDono() != null) {
                System.out.println("5:erro-carro possui cliente.");
                continue;
            }

            clienteComprador.adicionaCarroVoador(carroComprado);
            carroComprado.adicionaDono(clienteComprador);
            System.out.println("5:" + clienteComprador.getNome() + ":" + carroComprado.getPlaca());
        } while (true);
    }

    public void mostrarClienteComMaisCarros() {


        Cliente clienteComMaisCarros = clientela.clienteComMaisCarros();
        int quantidade;
        if (clienteComMaisCarros != null) {
            quantidade = clientela.quantidadeDeCarros();
            System.out.println("6:" + clienteComMaisCarros.getNome() + ":" + quantidade);
        } else {
            System.out.println("6:erro-nenhum cliente encontrado.");
        }
    }

    public void mostrarDadosDeUmCarro() {
        String placa = in.nextLine();

        CarroVoador carroAuxiliar = frota.consultaCarroVoadorPlaca(placa);
        if (carroAuxiliar == null) {
            System.out.println("7:erro-carro inexistente.");
        } else if (carroAuxiliar.getDono() == null) {
            System.out.println("7:erro-carro sem cliente.");
        } else {
            System.out.println("7:" + carroAuxiliar.getPlaca() + ":" + carroAuxiliar.getDono().getNome());
        }
    }

    public void carroMaisProximoDaMedia() {
        CarroVoador carroMedia = frota.carroMaisPertoDaMedia();
        if (carroMedia != null && carroMedia.getDono() != null) {
            System.out.println("8:" + carroMedia.getNumero() + ":" + carroMedia.getPlaca() + ":" + carroMedia.getValor() + ":" + carroMedia.getDono().getNome() + ":" + carroMedia.getDono().getRenda());
        } else {
            System.out.println("8:erro-carro inexistente.");

        }
    }

    private void redirecionaEntrada() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader(nomeArquivoEntrada));
            in = new Scanner(streamEntrada);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
        in.useLocale(Locale.ENGLISH);
    }


    private void redirecionaSaida() {
        try {
            PrintStream streamSaida = new PrintStream(new File(nomeArquivoSaida), Charset.forName("UTF-8"));
            System.setOut(streamSaida);
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);
    }

    private void restauraEntrada() {
        in = new Scanner(System.in);
    }

    private void restauraSaida() {
        System.setOut(saidaPadrao);
    }
}
