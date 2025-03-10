package Pedido;

import BancoDeDados.BancoDeDadosPedidos;
import Produtos.Produto;
import BancoDeDados.BancoDeDadosProdutos;
import java.util.InputMismatchException;
import java.util.Scanner;

public class PedidoService {
    private BancoDeDadosPedidos bancoDeDadosPedidos;
    private BancoDeDadosProdutos bancoDeDadosProdutos;
    Scanner scanner = new Scanner(System.in);

    public PedidoService() {
        this.bancoDeDadosPedidos = BancoDeDadosPedidos.getInstancia();
        this.bancoDeDadosProdutos = BancoDeDadosProdutos.getInstancia();
    }

    public void adicionarItem(Pedido pedido, int idProduto, int quantidade) {
        Produto produto = bancoDeDadosProdutos.buscarPorId(idProduto);
        if (produto != null) {
            try {
                ItemPedido item = new ItemPedido(produto, quantidade, produto.getValorDeVenda());
                pedido.adicionarItem(item);
                bancoDeDadosPedidos.salvar(pedido);
                System.out.println("Item adicionado ao pedido.");
            } catch (IllegalStateException | IllegalArgumentException e) {
                System.out.println("Erro ao adicionar item: " + e.getMessage());
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }

    public void removerItem(Pedido pedido, int idProduto) {
        try {
            Produto produto = bancoDeDadosProdutos.buscarPorId(idProduto);
            if (produto != null) {
                try {
                    ItemPedido item = new ItemPedido(produto, 0, 0);
                    pedido.removerItem(item);
                    bancoDeDadosPedidos.salvar(pedido);
                    System.out.println("Item removido do pedido.");
                } catch (IllegalStateException | IllegalArgumentException e) {
                    System.out.println("Erro ao remover item: " + e.getMessage());
                }
            } else {
                System.out.println("Produto não encontrado.");
            }
        } catch (InputMismatchException e) {
            System.out.println("Por favor, digite um número inteiro.");
            scanner.nextLine();
        }
    }

    public void alterarQuantidade(Pedido pedido, int idProduto, int novaQuantidade) {
        Produto produto = bancoDeDadosProdutos.buscarPorId(idProduto);
        if (produto != null) {
            ItemPedido item = new ItemPedido(produto, 0, 0);
            while (true) {
                try {
                    System.out.println("Digite a nova quantidade: ");
                    novaQuantidade = scanner.nextInt();
                    scanner.nextLine();
                    pedido.alterarQuantidade(item, novaQuantidade);
                    bancoDeDadosPedidos.salvar(pedido);
                    System.out.println("Quantidade alterada com sucesso.");
                    break;
                } catch (InputMismatchException e) {
                    System.out.println("Por favor, digite um número inteiro.");
                    scanner.nextLine();
                } catch (IllegalStateException e) {
                    System.out.println("Erro ao alterar quantidade do item: " + e.getMessage());
                    break;
                }
            }
        } else {
            System.out.println("Produto não encontrado.");
        }
    }
}

