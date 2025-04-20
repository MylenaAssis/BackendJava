package com.teste.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiro_exemplo.model.Produto;
// import com.teste.primeiro_exemplo.repository.ProdutoRepository;
import com.teste.primeiro_exemplo.repository.ProdutoRepository;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar todos os produtos.
     * @return Retorna um produto se encontrado.
     */
    public List<Produto> obterTodos() {
        return produtoRepository.obterTodos();
    }

    /**
     * Busca por id.
     * @param id
     * @return produto encontrado ou null.
     */
    public Optional<Produto> obterPorId(Integer id) {
        return produtoRepository.obterPorId(id);
    }

    /**
     * Adicionar produto a lista,
     * @param produto
     * @return produto adicionado.
     */
    public Produto adicionar(Produto produto) {
        return produtoRepository.adicionar(produto);
    }

    /**
     * Deletar produto.
     * @param produto a ser deletado
     */
    public void deletar(Integer id) {
        produtoRepository.deletar(id);
    }

    /**
     * Atualizar produto existente na lista.
     * @param id
     * @param produto
     * @return produto atualizado.
     */
    public Produto atualizar(Integer id, Produto produto) {
        produto.setId(id);
        return produtoRepository.atualizar(produto);
    }
}
