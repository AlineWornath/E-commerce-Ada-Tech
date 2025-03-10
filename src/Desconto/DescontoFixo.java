package Desconto;

public class DescontoFixo implements Desconto {
    private double valorDesconto;

    public DescontoFixo(double valorDesconto) {
        this.valorDesconto = valorDesconto;
    }

    @Override
    public double calcularDesconto(double valor) {
        return Math.max(0, valorDesconto);
    }

    @Override
    public String toString() {
        return "R$" + valorDesconto + " de desconto";
    }
}
