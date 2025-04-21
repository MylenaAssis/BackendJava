package com.teste.primeiro_exemplo.repository;
import com.teste.primeiro_exemplo.model.Produto;
import com.teste.primeiro_exemplo.model.exception.ResourceNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

@Repository
public class ProdutoRepositoryold {
    private List<Produto> produtos = new ArrayList<Produto>();
    private Integer ultimoId = 0;

    /**
     * Metodo para retornar lista de produtos
     * @return Lista de produtos
     */
    public List<Produto> obterTodos() {
        return produtos;
    }

    /**
     * Metodo que retorna o produto encontrado pelo seu id.
     * @param id para busca
     * @return retorna um produto ou null.
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtos
            .stream()
            .filter(produto -> produto.getId() == id)
            .findFirst();
    }

    /**
     * Metodo para adicionar produto a lista.
     * @param produto que será adicionado.
     * @return produto adicionado na lista.
     */
    public Produto adicionar(Produto produto) {
        ultimoId++;
        produto.setId(ultimoId);
        produtos.add(produto);

        return produto;
    }

    /**
     * Metodo para deletar um produto
     * @param id do produto que será deletado
     */
    public void deletar(Integer id) {
        produtos.removeIf(produto -> produto.getId() == id);
    }

    /**
     * Metodo para atualizar produto na lista
     * @param produto que será atualizado
     * @return produto atualizado.
     */
    public Produto atualizar(Produto produto){
        Optional<Produto> produtoEncontrado = obterPorId(produto.getId());

        if(produtoEncontrado.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        deletar(produto.getId());
        produtos.add(produto);

        return produto;
    }


}
