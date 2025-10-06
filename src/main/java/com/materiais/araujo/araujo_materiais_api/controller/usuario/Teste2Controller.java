package com.materiais.araujo.araujo_materiais_api.controller.usuario;

import com.materiais.araujo.araujo_materiais_api.model.usuario.Usuario;
import com.materiais.araujo.araujo_materiais_api.service.usuario.UtilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("sla")
public class Teste2Controller {

    @Autowired
    private UtilUsuario utilUsuario;

    @GetMapping
    public Usuario sla(){
        return utilUsuario.obterUsuarioDaVez();
    }
}
