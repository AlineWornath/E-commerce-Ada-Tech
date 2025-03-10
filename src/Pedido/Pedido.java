package Pedido;

import Clientes.Cliente;
import Desconto.Desconto;
import Pedido.Notificacao.Notificacao;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Pedido {
    private int id;
    private Cliente cliente;
    private LocalDateTime dataCriacao;
    private List<ItemPedido> itens;
    private double valorTotal;
    private Desconto desconto;
    private StatusPedido status;
    private Notificacao notificacao;

    public Pedido(Cliente cliente) {
        this.id = gerarId();
        this.cliente = cliente;
        this.dataCriacao = LocalDateTime.now();
        this.itens = new ArrayList<>();
        this.valorTotal = 0.0;
        this.status = StatusPedido.ABERTO;
        this.desconto = null;
    }

    private int gerarId(){
        Random random = new Random();
        return random.nextInt(10000) + 1000;
    }
    public int getId() {
        return id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public StatusPedido getStatus() {
        return status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public Desconto getDesconto() {
        return desconto;
    }

    public void setDesconto(Desconto desconto) {
        this.desconto = desconto;
    }

    public List<ItemPedido> getItens(){
        return itens;
    }

    public void setNotificacao(Notificacao notificacao) {
        this.notificacao = notificacao;
    }

    public void enviarNotificacao(Cliente cliente, String mensagem) {
        if (notificacao != null) {
            notificacao.enviarNotificacao(cliente, mensagem);
        } else{
            System.out.println("Nenhuma notificação configurada.");
        }
    }

    public void alterarStatus(StatusPedido novoStatus) {
        this.status = novoStatus;
    }


    public void adicionarItem(ItemPedido item) {
            validarPedidoAberto("Não é possível adicionar itens a um pedido que não está aberto.");
            itens.add(item);
            calcularTotal();
    }

    public void removerItem(ItemPedido item){
        validarPedidoAberto("Não é possível remover itens de um pedido que não está aberto.");
        for (ItemPedido itemPedido : itens) {
            if (itemPedido.getProduto().equals(item.getProduto())) {
                itens.remove(itemPedido);
                calcularTotal();
                return;
            }

        }
        throw new IllegalArgumentException("Item não encontrado no pedido.");
    }

    public void alterarQuantidade(ItemPedido item, int novaQuantidade){
            validarPedidoAberto("Não é possível alterar a quantidade de itens de um pedido que não está aberto.");
            for (ItemPedido i : itens){
                if (i.getProduto().equals(item.getProduto())) {
                    i.setQuantidade(novaQuantidade);
                    calcularTotal();
                    return;
                }
            }
            throw new IllegalArgumentException("Produto não encontrado no pedido.");
    }

    public double calcularTotal(){
        double total = 0.0;
        for (ItemPedido item : itens){
           total += item.calcularSubtotal();
        }
        valorTotal = total;
        return total;
    }

    public double aplicarDesconto(){
        double totalComDesconto = calcularTotal();
        if (desconto != null){
        totalComDesconto -= desconto.calcularDesconto(totalComDesconto);
        }
        return totalComDesconto;
    }

    public boolean podeFinalizar(){
        return status == StatusPedido.ABERTO && !itens.isEmpty() && valorTotal > 0;
    }

    private void validarPedidoAberto(String mensagem) {
        if (status != StatusPedido.ABERTO) {
            throw new IllegalStateException(mensagem);
        }
    }

}
