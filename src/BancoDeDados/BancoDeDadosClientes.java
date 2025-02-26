package BancoDeDados;

import Clientes.Cliente;
import Clientes.PessoaFisica;
import Clientes.PessoaJuridica;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class BancoDeDadosClientes {

    private static List<Cliente> listaDeClientes = new ArrayList<>();

    public static List<Cliente> getListaDeClientes() {
        return listaDeClientes;
    }

    public static List<Cliente> cadastrarClientes(Cliente cliente) {
        listaDeClientes.add(cliente);
        return listaDeClientes;
    }

    public static Cliente buscarRegistroCliente() {
        Cliente clienteEncontrado = null;
        for (Cliente clienteDisponivel : listaDeClientes) {

            if (Objects.equals(clienteDisponivel.getTipoDeCliente(), "Pessoa Física")) {
                clienteEncontrado = new PessoaFisica(
                        clienteDisponivel.getIdCliente(),
                        clienteDisponivel.getNome(),
                        clienteDisponivel.getNumeroDoDocumento(),
                        clienteDisponivel.getEndereco(),
                        clienteDisponivel.getTelefone(),
                        clienteDisponivel.getTipoDeCliente()
                );
            } else {
                clienteEncontrado = new PessoaJuridica(
                        clienteDisponivel.getIdCliente(),
                        clienteDisponivel.getNome(),
                        clienteDisponivel.getNumeroDoDocumento(),
                        clienteDisponivel.getEndereco(),
                        clienteDisponivel.getTelefone(),
                        clienteDisponivel.getTipoDeCliente());
            }

        }
        return clienteEncontrado;
    }

    public static void listarClientes(){
        for (Cliente clientesCadastrados: listaDeClientes){
            System.out.println(clientesCadastrados);
        }
    }

    public static Cliente buscarClientePorId(int idCliente) {
        for (Cliente cliente : listaDeClientes) {
            if (cliente.getIdCliente() == idCliente) {
                return cliente;
            }
        }
        return null;
    }
}
