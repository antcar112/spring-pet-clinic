package antcar.springframework.springpetclinic.services.map;

import antcar.springframework.springpetclinic.model.Owner;
import antcar.springframework.springpetclinic.model.Pet;
import antcar.springframework.springpetclinic.model.PetType;
import antcar.springframework.springpetclinic.services.OwnerService;
import antcar.springframework.springpetclinic.services.PetService;
import antcar.springframework.springpetclinic.services.PetTypeService;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

    private final PetTypeService petTypeService;
    private final PetService petService;

    public OwnerServiceMap(PetTypeService petTypeService, PetService petService) {
        this.petTypeService = petTypeService;
        this.petService = petService;
    }

    @Override
    public Set<Owner> findAll() {
        return super.findAll();
    }

    @Override
    public Owner findById(Long id) {
        return super.findById(id);
    }

    @Override
    public Owner save(Owner object) {
        if (object == null) {
            return null;
        }

        Set<Pet> pets = object.getPets();
        if (pets != null) {
            pets.forEach(pet -> {
                PetType petType = pet.getPetType();
                if (petType == null) {
                    throw new RuntimeException("PetType is required");
                }
                if (petType.getId() == null) {
                    pet.setPetType(petTypeService.save(petType));
                }
                if (pet.getId() == null) {
                    Pet savedPet = petService.save(pet);
                    pet.setId(savedPet.getId());
                }
            });
        }

        return super.save(object);

    }

    @Override
    public void delete(Owner object) {
        super.delete(object);
    }

    @Override
    public void deleteById(Long id) {
        super.deleteById(id);
    }

    @Override
    public Owner findByLastName(String lastName) {
        return null;
    }
}
