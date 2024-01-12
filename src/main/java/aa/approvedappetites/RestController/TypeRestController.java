package aa.approvedappetites.RestController;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import aa.approvedappetites.Domain.Type;
import aa.approvedappetites.Domain.TypeRepository;

@CrossOrigin
@Controller
public class TypeRestController {

    @Autowired
    private TypeRepository typeRepository;

    /* Lists all Type java objects into JSON list */
    @GetMapping("/types")
    public @ResponseBody List<Type> getAllTypesRest() {
        return (List<Type>) typeRepository.findAll();
    }

    /* Returns type by id */
    @GetMapping("/type/{id}")
    public @ResponseBody Optional<Type> getTypeByIdRest(@PathVariable("id") Long typeId) {
        return typeRepository.findById(typeId);
    }

    /* Saves type */
    @PostMapping("/types")
    public @ResponseBody Type saveTypeRest(@RequestBody Type type) {
        return typeRepository.save(type);
    }

    /* Patches type by id */
    @PatchMapping("/types/{id}")
    public @ResponseBody Type patchTypeRest(@PathVariable Long typeId, @RequestBody Type updatedType) {
        Optional<Type> existingTypeOptional = typeRepository.findById(typeId);

        if (existingTypeOptional.isPresent()) {
            Type existingType = existingTypeOptional.get();

            if (updatedType.getName() != null) {
                existingType.setName(updatedType.getName());
            }

            return typeRepository.save(existingType);
        } else {
            throw new ResourceNotFoundException("Type not found with id " + typeId);
        }

    }

    /* Deletes type by id */
    @DeleteMapping("/types/{id}")
    public ResponseEntity<String> deleteTypeByIdRest(@PathVariable("id") Long typeId) {
        Optional<Type> typeOptional = typeRepository.findById(typeId);

        if (typeOptional.isPresent()) {
            typeRepository.deleteById(typeId);
            return ResponseEntity.ok("Type deleted successfully!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Type not found with id " + typeId);
        }
    }
}
