package Desconto;

public class DescontoPercentual implements Desconto{
    private double valorDesconto;

    public DescontoPercentual(double valorDesconto) {
        if (valorDesconto < 0 || valorDesconto > 100) {
            throw new IllegalArgumentException("O desconto deve ser entre 0% e 100%.");
        }
        this.valorDesconto = valorDesconto;
    }

    @Override
    public double calcularDesconto(double valorPedido) {
        return valorPedido * (valorDesconto / 100);
    }

    @Override
    public String toString() {
        return valorDesconto + "% de desconto";
    }
}


