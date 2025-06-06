package com.example.demo.dto;

import com.example.demo.entity.Pedido;
import com.example.demo.entity.Produto;
import com.example.demo.enums.PedidoStatus;

import java.util.ArrayList;
import java.util.List;

public class PedidoRespondeDTO {
    private Long id;
    private Integer quantidade;
    private Double preco;
    private Long clienteId;
    private Long enderecoId;
    private PedidoStatus status;
    private List<Produto> produtos = new ArrayList<>();

    public PedidoRespondeDTO() {
    }

    public PedidoRespondeDTO(Long id, Integer quantidade, Double preco, Long clienteId, Long enderecoId, PedidoStatus status, List<Produto> produtos) {
        this.id = id;
        this.quantidade = quantidade;
        this.preco = preco;
        this.clienteId = clienteId;
        this.enderecoId = enderecoId;
        this.status = status;
        this.produtos = produtos;
    }

    public static PedidoRespondeDTO fromEntity(Pedido pedido) {
        return new PedidoRespondeDTO(
                pedido.getId(),
                pedido.getQuantidade(),
                pedido.getPreco(),
                pedido.getCliente() != null ? pedido.getCliente().getId() : null,
                pedido.getEndereco() != null ? pedido.getEndereco().getId() : null,
                pedido.getStatus(),
                pedido.getProdutos()
        );
    }

    // Getters e Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Long getClienteId() {
        return clienteId;
    }

    public void setClienteId(Long clienteId) {
        this.clienteId = clienteId;
    }

    public Long getEnderecoId() {
        return enderecoId;
    }

    public void setEnderecoId(Long enderecoId) {
        this.enderecoId = enderecoId;
    }

    public PedidoStatus getStatus() {
        return status;
    }

    public void setStatus(PedidoStatus status) {
        this.status = status;
    }

    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }
}