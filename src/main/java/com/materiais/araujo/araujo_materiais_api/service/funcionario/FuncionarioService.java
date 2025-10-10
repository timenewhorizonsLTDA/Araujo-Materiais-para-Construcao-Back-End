package com.materiais.araujo.araujo_materiais_api.service.funcionario;

import com.materiais.araujo.araujo_materiais_api.DTO.produto.ProdutoDTO;
import com.materiais.araujo.araujo_materiais_api.model.produto.Produto;
import com.materiais.araujo.araujo_materiais_api.repository.produto.ProdutoRepository;
import com.materiais.araujo.araujo_materiais_api.repository.usuario.UsuarioRepository;
import com.materiais.araujo.araujo_materiais_api.service.usuario.EmailService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class FuncionarioService {
    private ProdutoRepository produtoRepository;

    public FuncionarioService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

        public void addProduto(ProdutoDTO dto){
            Produto produto = new Produto(
                    dto.nome(),
                    dto.codigo(),
                    dto.preco(),
                    dto.quantidade(),
                    dto.estoqueMinimo(),
                    dto.tipo()
            );

            produtoRepository.save(produto);
        }
}
