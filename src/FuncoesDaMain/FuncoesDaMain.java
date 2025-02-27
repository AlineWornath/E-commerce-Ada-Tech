package FuncoesDaMain;

import Clientes.*;
import Pedido.*;
import Pedido.CadastroPedido;
import Pedido.PedidoRepository;
import Pedido.PedidoService;
import Produtos.CadastroProduto;
import Produtos.Produto;
import Produtos.ProdutoRepository;
import Produtos.ProdutoService;
import BancoDeDados.BancoDeDadosClientes;
import java.util.Scanner;

import static Clientes.CadastroCliente.cadastrarPessoaFisica;
import static Clientes.CadastroCliente.cadastrarPessoaJuridica;

public class FuncoesDaMain {
    static int opcaoCliente;
    static int opcaoProduto;
    static int opcaoPedido;

    public static int obterOpcaoValida(Scanner scanner) {
        while (true) {
            String entrada = scanner.nextLine();
            try {
                return Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("\n------------------------------");
                System.out.println("Entrada inválida! Digite um número válido.");
                System.out.println("------------------------------\n");
            }
        }
    }

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
                    bancoDeDadosClientes.listarClientes();
                    break;
                case 4:
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
        ProdutoRepository produtoRepository = ProdutoRepository.getInstancia();
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
                    System.out.println("Lista de produtos cadastrados:");
                    produtoRepository.listarProdutos();
                    break;
                case 3:
                    System.out.println("Digite o ID do produto a ser atualizado:");
                    int idProduto = scanner.nextInt();
                    produtoService.atualizarProduto(idProduto);
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
        PedidoRepository pedidoRepository = PedidoRepository.getInstancia();
        ProdutoRepository produtoRepository = ProdutoRepository.getInstancia();
        PedidoService pedidoService = new PedidoService();
        CadastroPedido cadastroPedido = new CadastroPedido(bancoDeDadosClientes, pedidoRepository, produtoRepository);

        Scanner scanner = new Scanner(System.in);
        boolean loop = true;

        while (loop) {
            System.out.println("Escolha a opção do pedido:\n");
            System.out.println("1 - Cadastro de pedido");
            System.out.println("2 - Imprimir lista de Pedidos");
            System.out.println("3 - Adicionar item ao pedido");
            System.out.println("4 - Remover item do pedido");
            System.out.println("5 - Alterar quantidade de item");
            System.out.println("6 - Finalizar pedido");
            System.out.println("7 - Pagar pedido");
            System.out.println("8 - Entregar pedido");
            System.out.println("9 - Voltar ao Menu Principal\n");
            System.out.print("Digite a opção desejada: ");

            opcaoPedido = obterOpcaoValida(scanner);

            Pedido pedido;
            int idPedido;


            switch (opcaoPedido) {
                case 1:
                    pedido = cadastroPedido.cadastrarPedido();
                    if (pedido != null) {
                        System.out.println("Pedido cadastrado com sucesso!");
                    }
                    break;
                case 2:
                    System.out.print("\n");
                    System.out.println("Lista de pedidos cadastrados:");
                    pedidoRepository.listarPedidos();
                    break;
                case 3:
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = pedidoRepository.buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        System.out.print("Digite o ID do produto: ");
                        int idProduto = scanner.nextInt();
                        scanner.nextLine();
                        Produto produto = produtoRepository.buscarProduto(idProduto);
                        if (produto != null) {
                            System.out.print("Digite a quantidade: ");
                            int quantidade = scanner.nextInt();
                            scanner.nextLine();
                            pedidoService.adicionarItem(pedido, produto, quantidade);
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    } else {
                        System.out.println("Pedido não encontrado.");
                    }
                    break;
                case 4:
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = pedidoRepository.buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        System.out.print("Digite o ID do produto: ");
                        int idProduto = scanner.nextInt();
                        scanner.nextLine();
                        Produto produto = produtoRepository.buscarProduto(idProduto);
                        if (produto != null) {
                            pedidoService.removerItem(pedido, new ItemPedido(produto, 0, 0));
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    } else {
                        System.out.println("Pedido não encontrado.");
                    }
                    break;
                case 5:
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = pedidoRepository.buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        System.out.print("Digite o ID do produto: ");
                        int idProduto = scanner.nextInt();
                        scanner.nextLine();
                        Produto produto = produtoRepository.buscarProduto(idProduto);
                        if (produto != null) {
                            System.out.print("Digite a nova quantidade: ");
                            int novaQuantidade = scanner.nextInt();
                            scanner.nextLine();
                            pedidoService.alterarQuantidade(pedido, new ItemPedido(produto, 0, 0), novaQuantidade);
                        } else {
                            System.out.println("Produto não encontrado.");
                        }
                    } else {
                        System.out.println("Pedido não encontrado.");
                    }
                    break;
                case 6:
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = pedidoRepository.buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        pedidoService.finalizarPedido(pedido);
                    } else {
                        System.out.println("Pedido não encontrado.");
                    }
                    break;
                case 7:
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = pedidoRepository.buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        pedidoService.pagar(pedido);
                    } else {
                        System.out.println("Pedido não encontrado.");
                    }
                    break;
                case 8:
                    System.out.print("Digite o ID do pedido: ");
                    idPedido = scanner.nextInt();
                    scanner.nextLine();
                    pedido = pedidoRepository.buscarPedidoPorId(idPedido);
                    if (pedido != null) {
                        pedidoService.entregar(pedido);
                    } else {
                        System.out.println("Pedido não encontrado.");
                    }
                    break;
                case 9:
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
}