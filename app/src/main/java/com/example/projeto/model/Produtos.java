package com.example.projeto.model;

public class Produtos {
    String descricao;
    Double preco;
    Integer qtd_estoque;
    String Validade;
    int id;

    public Produtos(String descricao, Double preco, Integer qtd_estoque, String validade, int id) {
        this.descricao = descricao;
        this.preco = preco;
        this.qtd_estoque = qtd_estoque;
        Validade = validade;
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public Integer getQtd_estoque() {
        return qtd_estoque;
    }

    public Double getPreco() {
        return preco;
    }

    public String getValidade() {
        return Validade;
    }

    public int getId() {
        return id;
    }
}
