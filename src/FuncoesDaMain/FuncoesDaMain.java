package FuncoesDaMain;

import Clientes.*;
import Frete.CalculadoraFrete;
import Frete.FretePadrao;
import Pedido.*;
import Pedido.CadastroPedido;
import BancoDeDados.BancoDeDadosPedidos;
import Pedido.Pagamento.ProcessadorPagamento;
import Pedido.PedidoService;
import Produtos.CadastroProduto;
import BancoDeDados.BancoDeDadosProdutos;
import Produtos.ProdutoService;
import BancoDeDados.BancoDeDadosClientes;
import java.util.Scanner;

import static Clientes.CadastroCliente.cadastrarPessoaFisica;
import static Clientes.CadastroCliente.cadastrarPessoaJuridica;
import static ValidacaoInput.ValidacaoInput.obterOpcaoValida;

public class FuncoesDaMain {
    static int opcaoCliente;
    static int opcaoProduto;
    static int opcaoPedido;

    public static void escolherCadastroCliente() {
        BancoDeDadosClientes bancoDeDadosClientes = BancoDeDadosClientes.getInstancia();
        ClienteService clienteService = new ClienteService();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("\nEscolha a opção do cliente:\n");
            System.out.println("1 - Cadastro de Pessoa Física");
            System.out.println("2 - Cadastro de Pessoa Juridica");
            System.out.println("3 - Imprimir lista de Clientes");
            System.out.println("4 - Atualizar Cliente");
            System.out.println("5 - Voltar ao Menu Principal\n");
            System.out.print("Digite a opção do cliente desejada: ");

            opcaoCliente = obterOpcaoValida(scanner);

            PessoaFisica pessoafisica = new PessoaFisica();
            PessoaJuridica pessoaJuridica = new PessoaJuridica();
            switch (opcaoCliente) {
                case 1:
                    System.out.print("\n");
                    pessoafisica = cadastrarPessoaFisica();
                    bancoDeDadosClientes.cadastrarClientes(pessoafisica);
                    break;
                case 2:
                    System.out.print("\n");
                    pessoaJuridica = cadastrarPessoaJuridica();
                    bancoDeDadosClientes.cadastrarClientes(pessoaJuridica);
                    break;
                case 3:
                    System.out.println("Lista de clientes cadastrados:");
                    bancoDeDadosClientes.listarTodos();
                    break;
                case 4:
                    System.out.println("Escolha um cliente a ser atualizado: ");
                    bancoDeDadosClientes.listarTodos();
                    System.out.println("Digite o ID do cliente a ser atualizado:");
                    int idCliente = scanner.nextInt();
                    scanner.nextLine();
                    clienteService.atualizarDadosCliente(idCliente);
                    break;
                case 5:
                    System.out.println("\n------------------------------");
                    System.out.println("Voltando ao Menu Principal.");
                    System.out.println("------------------------------\n");
                    loop = false;
                    break;
                default:
                    System.out.println("\n------------------------------");
                    System.out.println("Digite uma opção válida.");
                    System.out.println("------------------------------\n");

            }
        }

    }

    public static void escolherCadastroProduto() {
        CadastroProduto cadastroProduto = new CadastroProduto();
        BancoDeDadosProdutos bancoDeDadosProdutos = BancoDeDadosProdutos.getInstancia();
        ProdutoService produtoService = new ProdutoService();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("\nEscolha a opção do produto:\n");
            System.out.println("1 - Cadastro de produto");
            System.out.println("2 - Imprimir lista de Produtos");
            System.out.println("3 - Atualizar Produto");
            System.out.println("4 - Voltar ao Menu Principal\n");
            System.out.print("Digite a opção desejada: ");

            opcaoProduto = obterOpcaoValida(scanner);

            switch (opcaoProduto) {
                case 1:
                    cadastroProduto.cadastrarProduto();
                    break;
                case 2:
                    System.out.print("\n");
                    System.out.println("Lista de produtos cadastrados: ");
                    bancoDeDadosProdutos.listarTodos();
                    break;
                case 3:
                    System.out.println("Escolha um produto a ser atualizado: ");
                    bancoDeDadosProdutos.listarTodos();
                    System.out.println("Digite o ID do produto:");
                    int idProduto = scanner.nextInt();
                    scanner.nextLine();
                    produtoService.atualizarProduto(idProduto);
                    break;
                case 4:
                    System.out.println("\n------------------------------");
                    System.out.println("Voltando ao Menu Principal.");
                    System.out.println("------------------------------\n");
                    loop = false;
                    break;
                default:
                    System.out.println("\n------------------------------");
                    System.out.println("Digite uma opção válida.");
                    System.out.println("------------------------------\n");

            }
        }
    }

    public static void escolherCadastroPedido() {
        BancoDeDadosClientes bancoDeDadosClientes = BancoDeDadosClientes.getInstancia();
        BancoDeDadosPedidos bancoDeDadosPedidos = BancoDeDadosPedidos.getInstancia();
        BancoDeDadosProdutos bancoDeDadosProdutos = BancoDeDadosProdutos.getInstancia();
        CadastroPedido cadastroPedido = new CadastroPedido();
        CalculadoraFrete calculadoraFrete = new FretePadrao();
        PedidoService pedidoService = new PedidoService();
        FinalizacaoPedido finalizacao = new FinalizacaoPedido(calculadoraFrete);
        EntregaPedido entrega = new EntregaPedido();
        ProcessadorPagamento processadorPagamento = new ProcessadorPagamento();

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("Escolha a opção do pedido:\n");
            System.out.println("1 - Cadastro de pedido");
            System.out.println("2 - Imprimir lista de Pedidos");
            System.out.println("3 - Consultar Status do Pedido");
            System.out.println("4 - Adicionar item ao pedido");
            System.out.println("5 - Remover item do pedido");
            System.out.println("6 - Alterar quantidade de item");
            System.out.println("7 - Finalizar pedido");
            System.out.println("8 - Pagar pedido");
            System.out.println("9 - Entregar pedido");
            System.out.println("10 - Voltar ao Menu Principal\n");
            System.out.print("Digite a opção desejada: ");

            opcaoPedido = obterOpcaoValida(scanner);

            Pedido pedido;
            int idPedido;
            int idProduto;


            switch (opcaoPedido) {
                case 1:
                    pedido = cadastroPedido.iniciarCadastroPedidos();
                    if (pedido != null) {
                        System.out.println("Pedido cadastrado com sucesso!");
                    }
                    break;
                case 2:
                    System.out.print("\n");
                    System.out.println("Lista de pedidos cadastrados: ");
                    bancoDeDadosPedidos.listarTodos();
                    break;
                case 3:
                    System.out.println("Escolha um pedido: ");
                    bancoDeDadosPedidos.listarPedidosSimplificado();
                    System.out.println("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        System.out.println("Status do pedido: " + pedido.getStatus());
                    }
                    break;
                case 4:
                    System.out.println("Escolha um pedido: ");
                    bancoDeDadosPedidos.listarPedidosSimplificado();
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        System.out.println("Escolha o produto a ser adicionado: ");
                        bancoDeDadosProdutos.listarTodos();
                        System.out.println("Digite o ID do produto: ");
                        idProduto = scanner.nextInt();
                        scanner.nextLine();
                        System.out.println("Digite a quantidade: ");
                        int quantidade = scanner.nextInt();
                        scanner.nextLine();
                        pedidoService.adicionarItem(pedido, idProduto, quantidade);
                    }
                    break;
                case 5:
                    System.out.println("Escolha um pedido: ");
                    bancoDeDadosPedidos.listarPedidosSimplificado();
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        System.out.println("Escolha o produto a ser removido: ");
                        bancoDeDadosPedidos.imprimirItensDoPedido(pedido); // imprimirItensDoPedido
                        System.out.print("Digite o ID do produto: ");
                        idProduto = scanner.nextInt();
                        scanner.nextLine();
                        pedidoService.removerItem(pedido, idProduto);
                    }
                    break;
                case 6:
                    System.out.println("Escolha um pedido: ");
                    bancoDeDadosPedidos.listarPedidosSimplificado();
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        System.out.println("Escolha um produto para alterar quantidade: ");
                        bancoDeDadosPedidos.imprimirItensDoPedido(pedido);
                        System.out.println("Digite o ID do produto: ");
                        idProduto = scanner.nextInt();
                        scanner.nextLine();
                        pedidoService.alterarQuantidade(pedido, idProduto, 0);
                    }
                    break;
                case 7:
                    System.out.println("Escolha um pedido: ");
                    bancoDeDadosPedidos.listarPedidosSimplificado();
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        finalizacao.finalizarPedido(pedido);
                    }
                    break;
                case 8:
                    System.out.println("Escolha um pedido: ");
                    bancoDeDadosPedidos.listarPedidosSimplificado();
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        System.out.println("Escolha a forma de pagamento:\n1 - Cartão\n2 - Pix");
                        int opcaoPagamento = obterOpcaoValida(scanner);
                        processadorPagamento.processarPagamento(pedido, opcaoPagamento);
                    }
                    break;
                case 9:
                    System.out.println("Escolha um pedido: ");
                    bancoDeDadosPedidos.listarPedidosSimplificado();
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        entrega.entregar(pedido);
                    }
                    break;
                case 10:
                    System.out.println("\n------------------------------");
                    System.out.println("Voltando ao Menu Principal.");
                    System.out.println("------------------------------\n");
                    loop = false;
                    break;
                default:
                    System.out.println("\n------------------------------");
                    System.out.println("Digite uma opção válida.");
                    System.out.println("------------------------------\n");
            }
        }
    }

    private static Pedido buscarPedidoPorId(int idPedido) {
        Pedido pedido = BancoDeDadosPedidos.getInstancia().buscarPorId(idPedido);
        if (pedido == null) {
            System.out.println("Pedido não encontrado.");
        }
        return pedido;
    }
}