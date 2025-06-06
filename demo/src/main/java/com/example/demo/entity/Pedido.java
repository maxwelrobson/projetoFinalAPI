package com.example.demo.entity;

import com.example.demo.enums.PedidoStatus;
import jakarta.persistence.*;


import java.util.List;

@Entity

public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer quantidade;
    private Double preco;

    @OneToOne
	@JoinColumn(name = "cliente_id")
    private Usuario usuario;

    @OneToOne
    private Endereco endereco;

    @ManyToMany
    @JoinTable(name = "pedido_produto",
    joinColumns = @JoinColumn(name = "id_pedido"),
    inverseJoinColumns = @JoinColumn(name = "id_produto"))
    private List<Produto> produtos;

	@Enumerated(EnumType.STRING)
	private PedidoStatus status;

	public Pedido() {
	}
	
	public Pedido(Long id, Integer quantidade, Double preco, Usuario usuario, Endereco endereco, List<Produto> produtos,
                  PedidoStatus status) {
		super();
		this.id = id;
		this.quantidade = quantidade;
		this.preco = preco;
		this.usuario = usuario;
		this.endereco = endereco;
		this.produtos = produtos;
		this.status = status;
	}



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

	public Usuario getCliente() {
		return usuario;
	}

	public void setCliente(Usuario usuario) {
		this.usuario = usuario;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public PedidoStatus getStatus() {
		return status;
	}

	public void setStatus(PedidoStatus status) {
		this.status = status;
	}

}
