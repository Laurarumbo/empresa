package com.empresas.empresa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/articulos")
public class ArticuloController {

    @Autowired
    private ArticuloRepository articuloRepository;

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("articulos", articuloRepository.findAll());
        return "articulos/lista";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("articulo", new Articulo());
        return "articulos/formularios";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Articulo articulo) {
        articuloRepository.save(articulo);
        return "redirect:/articulos";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, Model model) {
        model.addAttribute("articulo", articuloRepository.findById(id).orElse(null));
        return "articulos/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String confirmarEliminar(@PathVariable Long id, Model model) {
        model.addAttribute("articulo", articuloRepository.findById(id).orElse(null));
        return "articulos/confirmar";
    }

    @PostMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id) {
        articuloRepository.deleteById(id);
        return "redirect:/articulos";
    }
}
