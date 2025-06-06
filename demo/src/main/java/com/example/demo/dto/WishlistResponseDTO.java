package com.example.demo.dto;

import java.util.List;


public class WishlistResponseDTO {
    private Long clinteId;
    private List<Long> produtosIds;

    public Long getClinteId() {
        return clinteId;
    }

    public void setClinteId(Long clinteId) {
        this.clinteId = clinteId;
    }

    public List<Long> getProdutosIds() {
        return produtosIds;
    }

    public void setProdutosIds(List<Long> produtosIds) {
        this.produtosIds = produtosIds;
    }
}
