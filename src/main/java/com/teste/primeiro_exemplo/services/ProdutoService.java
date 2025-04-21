package com.teste.primeiro_exemplo.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.teste.primeiro_exemplo.model.Produto;
import com.teste.primeiro_exemplo.model.exception.ResourceNotFoundException;
// import com.teste.primeiro_exemplo.repository.ProdutoRepository;
import com.teste.primeiro_exemplo.repository.ProdutoRepository;
import com.teste.primeiro_exemplo.shared.ProdutoDTO;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    /**
     * Metodo para retornar todos os produtos.
     * @return Retorna um produto se encontrado.
     */
    public List<ProdutoDTO> obterTodos() {
        //retorna uma lista de produto model
        List<Produto> produtos = produtoRepository.findAll();

        return produtos.stream()
        .map(produto -> new ModelMapper().map(produto, ProdutoDTO.class)) //converte produto em produtoDTO
        .collect(Collectors.toList()); //converte em lista de produtoDTO
    }

    /**
     * Busca por id.
     * @param id
     * @return produto encontrado ou null.
     */
    public Optional<ProdutoDTO> obterPorId(Integer id) {
        //obter optional de produto por id
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()) {
            throw new ResourceNotFoundException("Produto não encontrado");
        }

        ProdutoDTO dto = new ModelMapper().map(produto.get(), ProdutoDTO.class); //produto.get() pega o Produto dentro do Optional e transforma em ProdutoDTO
        return Optional.of(dto);
    }

    /**
     * Adicionar produto a lista,
     * @param produto
     * @return produto adicionado.
     */
    public ProdutoDTO adicionar(ProdutoDTO produtoDto) {
        //remover o id para fazer cadastro
        produtoDto.setId(null);

        //criar modelmapper
        ModelMapper mapper = new ModelMapper();

        //converter DTO em model
        Produto produto = mapper.map(produtoDto, Produto.class);

        //salvar produto no banco
        produto = produtoRepository.save(produto);

        //definir o id do produtoDTO para retornar
        produtoDto.setId(produto.getId());

        //retornar DTO atualizado
        return produtoDto;
    }

    /**
     * Deletar produto.
     * @param produto a ser deletado
     */
    public void deletar(Integer id) {
        //verificar se o produto existe
        Optional<Produto> produto = produtoRepository.findById(id);

        if(produto.isEmpty()) {
            throw new ResourceNotFoundException("Não foi possível deletar produto com id " + id + ". O produto não existe.");
        }

        produtoRepository.deleteById(id);
    }

    /**
     * Atualizar produto existente na lista.
     * @param id
     * @param produto
     * @return produto atualizado.
     */
    public ProdutoDTO atualizar(Integer id, ProdutoDTO produtoDto) {
        //passar o id para o produtoDTO
        produtoDto.setId(id);

        //criar um mapper
        ModelMapper mapper = new ModelMapper();

        //converter o DTO em um Produto
        Produto produto = mapper.map(produtoDto, Produto.class)

        //atualizar o produto no banco
        produtoRepository.save(produto);

        //retornar o produtoDTO atualizado
        return produtoDto;
    }
}
