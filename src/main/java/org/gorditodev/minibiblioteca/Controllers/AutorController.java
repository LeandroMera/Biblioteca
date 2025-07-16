package org.gorditodev.minibiblioteca.Controllers;


import org.gorditodev.minibiblioteca.entities.Autor;
import org.gorditodev.minibiblioteca.services.AutorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;

    @GetMapping({"/listar", "/"})
    public String listarAutores(Model model) {
        List<Autor> autores = autorService.findAllAutor();
        /*model.addAttribute("autores", autores);*/
        model.addAttribute("autores", autorService.findAllAutor());

        return "autor/lista_autores";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevoAutor(Model model) {
        model.addAttribute("autor", new Autor());
        model.addAttribute("autores", autorService.findAllAutor());//esta linea tambien se me habia olvidado
        return "autor/formulario_autor";
    }

    @PostMapping("/guardar")
    public String guardarAutor(@ModelAttribute Autor autor) {
        autorService.saveAutor(autor);
        return "redirect:/autores/listar";//Redirecciona a un endpoint recuerda wvn
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditarAutor(@PathVariable Long id, Model model) {
        Optional<Autor> autor = autorService.findAutor(id);
        autor.ifPresent(value -> model.addAttribute("autor", value));
        return "autor/formulario_autor";
    }

    @PostMapping("/actualizar")
    public String actualizarAutor(@ModelAttribute Autor autor) {
        autorService.updateAutor(autor);
        return "redirect:/autores/listar";//Redirecciona a un endpoint recuerda wvn
    }

    @GetMapping("/eliminar/{id}")
    public String eliminarAutor(@PathVariable Long id, Model model) throws ClassNotFoundException {
        autorService.deleteAutor(id);
        return "redirect:/autores/listar";
    }

}
