package org.gorditodev.minibiblioteca.Controllers;

import org.gorditodev.minibiblioteca.entities.Autor;
import org.gorditodev.minibiblioteca.entities.Categoria;
import org.gorditodev.minibiblioteca.entities.Editorial;
import org.gorditodev.minibiblioteca.entities.Libro;
import org.gorditodev.minibiblioteca.services.AutorService;
import org.gorditodev.minibiblioteca.services.CategoriaService;
import org.gorditodev.minibiblioteca.services.EditorialService;
import org.gorditodev.minibiblioteca.services.LibroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/libros")
public class LibroController {

    @Autowired
    private LibroService libroService;
    @Autowired
    private EditorialService editorialService;
    @Autowired
    private CategoriaService categoriaService;
    @Autowired
    private AutorService autorService;

    @GetMapping({"/listar", "/"})
    public String listarLibros(Model model){
        List<Libro> libros = libroService.findAll();
        model.addAttribute("libros", libros);
        return "libro/listar_libros";

    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoLibro(Model model){
        Libro libro = new Libro();
        model.addAttribute("libro", libro);
        model.addAttribute("editoriales", editorialService.findAll());
        model.addAttribute("categorias", categoriaService.findAll());
        model.addAttribute("autores", autorService.findAllAutor());//puse esta linea por que no salian los autores en el option
        model.addAttribute("autor/autores", autorService.findAllAutor());
        return "libro/formulario_libro";
    }

    @PostMapping("/guardar")
    public String guardarLibro(@ModelAttribute Libro libro, @RequestParam("editorialId") Long editorialId,
                                                            @RequestParam("categoriaId") Long categoriaId,
                                                            @RequestParam("autoresIds") List<Long> autoresIds){
        //Obtener y asignar la editorial y categoria al libre
        Optional<Editorial> editorial = editorialService.findById(editorialId);
        editorial.ifPresent(libro::setEditorial);

        Optional<Categoria> categoria = Optional.ofNullable(categoriaService.findById(categoriaId));
        categoria.ifPresent(libro::setCategoria);

        List<Autor> autores = autorService.findAutorByIds(autoresIds);
        libro.setAutores(new ArrayList<>(autores));

        libroService.saveLibro(libro);

        return "redirect:/libros/listar";
    }

    @GetMapping("/{id}/editar")
    public String mostrarFormularioEditarLibro(@PathVariable Long id, Model model){
        Optional<Libro> libro = libroService.findById(id);
        if(libro.isPresent()){
            model.addAttribute("libro", libro.get());
            model.addAttribute("editoriales", editorialService.findAll());
            model.addAttribute("categorias", categoriaService.findAll());
            model.addAttribute("autores", autorService.findAllAutor());
        }
        return "libro/formulario_libro";
    }

    @PostMapping("/{id}/actualizar")
    public String actualizarLibro(@PathVariable Long id,
                                  @ModelAttribute Libro libro,
                                  @RequestParam("editorialId") Long editorialId,
                                  @RequestParam("categoriaId") Long categoriaId,
                                  @RequestParam("autoresIds") List<Long> autoresIds){

        Optional<Editorial> editorial = editorialService.findById(editorialId);
        editorial.ifPresent(libro::setEditorial);

        Optional<Categoria> categoria = Optional.ofNullable(categoriaService.findById(categoriaId));
        categoria.ifPresent(libro::setCategoria);

        List<Autor> autores = autorService.findAutorByIds(autoresIds);
        libro.setAutores(new ArrayList<>(autores));
        libro.setId(id);

        libroService.updateLibro(libro);

        return "redirect:/libros/listar";
    }

    @GetMapping("{id}/autores")
    public String mostrarAutoresDelLibro(@PathVariable Long id, Model model){
        Optional<Libro> libroOptional = libroService.findById(id);
        if(libroOptional.isPresent()){
            Libro libro = libroOptional.get();
            model.addAttribute("libro", libro);
            model.addAttribute("autores", libro.getAutores());
        }
        return "libro/mostrar_autores_libros";
    }

    @GetMapping("/{id}/eliminar")
    public String eliminarLibro(@PathVariable Long id){
        libroService.deleteLibro(id);
        return "redirect:/libros/listar";
    }
}
