package br.com.fiap.petcall.controllers93509;

import br.com.fiap.petcall.model93509.Tutor;
import br.com.fiap.petcall.repositories93509.TutorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/tutores")
public class TutorController {

@Autowired
private TutorRepository repository;

    @GetMapping("/listar")
    @Transactional(readOnly = true)
    public String listar(Model model){
        model.addAttribute("tutores", repository.findAll());
        return "petcall/listar-tutores";
    }

    @GetMapping("/editar/{id}")
    @Transactional(readOnly = true)
    public String editar(@PathVariable("id") Long id, Model model){
        Tutor tutor = repository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Recurso não encontrado - id: " + id)
        );
        model.addAttribute("tutor",tutor);
        return "petcall/editar-tutor";
    }


    @PostMapping("/editar/{id}")
    @Transactional
    public String editarTutor(@PathVariable("id") Long id, @Valid Tutor tutor,
                             BindingResult result){
        if(result.hasErrors()){
            tutor.setId(id);
            return "petcall/editar-tutor";
        }
        repository.save(tutor);
        return "redirect:/tutores/listar";
    }

    @GetMapping("/delete/{id}")
    @Transactional
    public String delete(@PathVariable("id") Long id, Model model) {
        if (!repository.existsById(id)) {
            throw new IllegalArgumentException("Recurso não encontrado - id: " + id);
        }
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            throw new IllegalArgumentException("Recurso não encontrado - id: " + id);
        }
        return "redirect:/tutores/listar";
    }

}
