package org.gorditodev.minibiblioteca.Controllers;

import org.gorditodev.minibiblioteca.entities.Categoria;
import org.gorditodev.minibiblioteca.entities.Libro;
import org.gorditodev.minibiblioteca.services.CategoriaService;
import org.gorditodev.minibiblioteca.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @Autowired
    private LibroService libroService;

    @GetMapping({"", "/"})
    public String redirigirCategorias() {
        return "redirect:/categorias/listar";
    }

    @GetMapping("/listar")
    public String ListarCategorias(Model model){
        List<Categoria> categorias = categoriaService.findAll();
        model.addAttribute("categorias", categorias);
        return "categoria/listar_categorias";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevaCategoria(Model model){
        Categoria categoria = new Categoria();
        model.addAttribute("categoria", categoria);
        return "categoria/formulario_categoria";
    }

    @PostMapping("/guardar")
    public String guardarCategoria(@ModelAttribute Categoria categoria){
        Categoria categoriaGuardada = categoriaService.savecategoria(categoria);
        List<Libro> libros = libroService.findAllByCategoria(categoriaGuardada);
        categoriaGuardada.setLibros(libros);
        categoriaService.savecategoria(categoriaGuardada);
        return "redirect:/categorias/listar";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarCategoria(@PathVariable Long id,  Model model){
        Optional<Categoria> categoria;
        categoria = Optional.ofNullable(categoriaService.findById(id));// analizar esta linea en caso de error
        categoria.ifPresent(value -> model.addAttribute("categoria", value));
        return "categoria/formulario_categoria";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarCategoria(@PathVariable Long id, @ModelAttribute Categoria categoria){
        Categoria categoriaActual = categoriaService.findById(id);

        if(categoriaActual != null){
            categoria.setLibros(categoria.getLibros());
            categoriaService.updateCategoria(categoria);
        }
        return "redirect:/categorias/listar";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarCategoria(@PathVariable Long id){
        categoriaService.deleteCategoria(id);
        return "redirect:/categorias/listar";
    }

}
