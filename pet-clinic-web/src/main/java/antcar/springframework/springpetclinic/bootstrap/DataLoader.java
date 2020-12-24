package antcar.springframework.springpetclinic.bootstrap;

import antcar.springframework.springpetclinic.model.Owner;
import antcar.springframework.springpetclinic.model.Pet;
import antcar.springframework.springpetclinic.model.PetType;
import antcar.springframework.springpetclinic.model.Vet;
import antcar.springframework.springpetclinic.services.OwnerService;
import antcar.springframework.springpetclinic.services.PetTypeService;
import antcar.springframework.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

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

        Owner ownerMike = createOwner("Michael", "Weston", "123 Brickerel", "Miami", "801-555-8888");
        Owner ownerFiona = createOwner("Fiona", "Glenanne", "123 Brickerel", "Miami", "801-555-8888");
        System.out.println("Loaded Owners...");

        Pet mikePet = createPet("Rosco", savedDogPetType, ownerMike, LocalDate.now());
        Pet fionaPet = createPet("Kitty", savedCatPetType, ownerFiona, LocalDate.now());

        Vet vetSame = createVet("Sam", "Axe");
        Vet vetJessie = createVet("Jessie", "Porter");
        System.out.println("Loaded Vets...");

    }

    private PetType createPetType(String name) {
        PetType petType = new PetType();
        petType.setName(name);
        return petTypeService.save(petType);
    }

    private Owner createOwner(String firstName, String lastName, String address, String city, String telephone) {
        Owner owner = new Owner();
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        owner.setAddress(address);
        owner.setCity(city);
        owner.setTelephone(telephone);
        return ownerService.save(owner);
    }

    private Pet createPet(String name, PetType petType, Owner owner, LocalDate birthDate) {
        Pet pet = new Pet();
        pet.setName(name);
        pet.setPetType(petType);
        pet.setOwner(owner);
        pet.setBirthDate(birthDate);

        owner.getPets().add(pet);
        return pet;
    }

    private Vet createVet(String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        return vetService.save(vet);
    }
}
