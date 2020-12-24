package antcar.springframework.springpetclinic.bootstrap;

import antcar.springframework.springpetclinic.model.Owner;
import antcar.springframework.springpetclinic.model.PetType;
import antcar.springframework.springpetclinic.model.Vet;
import antcar.springframework.springpetclinic.services.OwnerService;
import antcar.springframework.springpetclinic.services.PetTypeService;
import antcar.springframework.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetTypeService petTypeService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
    }

    @Override
    public void run(String... args) throws Exception {

        PetType savedDogPetType = createPetType("Dog");
        PetType savedCatPetType = createPetType("Cat");

        createOwner("Michael", "Weston");
        createOwner("Fiona", "Glenanne");
        System.out.println("Loaded Owners...");

        createVet("Sam", "Axe");
        createVet("Jessie", "Porter");
        System.out.println("Loaded Vets...");

    }

    private PetType createPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        return petTypeService.save(petType);
    }

    private Owner createOwner(String firstName, String lastName) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        return ownerService.save(owner);
    }

    private Vet createVet(String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        return vetService.save(vet);
    }
}
