package antcar.springframework.springpetclinic.bootstrap;

import antcar.springframework.springpetclinic.model.Owner;
import antcar.springframework.springpetclinic.model.Pet;
import antcar.springframework.springpetclinic.model.PetType;
import antcar.springframework.springpetclinic.model.Specialty;
import antcar.springframework.springpetclinic.model.Vet;
import antcar.springframework.springpetclinic.services.OwnerService;
import antcar.springframework.springpetclinic.services.PetTypeService;
import antcar.springframework.springpetclinic.services.SpecialtyService;
import antcar.springframework.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    private final PetTypeService petTypeService;

    private final SpecialtyService specialtyService;

    public DataLoader(OwnerService ownerService, VetService vetService, PetTypeService petTypeService, SpecialtyService specialtyService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
        this.petTypeService = petTypeService;
        this.specialtyService = specialtyService;
    }

    @Override
    public void run(String... args) throws Exception {
        int count = petTypeService.findAll().size();
        if (count == 0) {
            loadData();
        }

    }

    private void loadData() {
        PetType dogPetType = createPetType("Dog");
        PetType catPetType = createPetType("Cat");

        Owner ownerMike = createOwner("Michael", "Weston", "123 Brickerel", "Miami", "801-555-8888");
        Owner ownerFiona = createOwner("Fiona", "Glenanne", "123 Brickerel", "Miami", "801-555-8888");
        System.out.println("Loaded Owners...");

        Pet mikePet = createPet("Rosco", dogPetType, ownerMike, LocalDate.now());
        Pet fionaPet = createPet("Kitty", catPetType, ownerFiona, LocalDate.now());

        Specialty radiology = createSpecialty("Radiology");
        Specialty surgery = createSpecialty("Surgery");
        Specialty dentistry = createSpecialty("Dentistry");

        Vet vetSam = createVet("Sam", "Axe", radiology);
        Vet vetJessie = createVet("Jessie", "Porter", surgery);
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

    private Specialty createSpecialty(String description) {
        Specialty specialty = new Specialty();
        specialty.setDescription(description);
        return specialtyService.save(specialty);
    }

    private Vet createVet(String firstName, String lastName, Specialty specialty) {
        Vet vet = new Vet();
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vet.getSpecialties().add(specialty);
        return vetService.save(vet);
    }
}
