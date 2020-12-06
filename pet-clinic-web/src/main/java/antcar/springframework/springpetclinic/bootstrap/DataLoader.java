package antcar.springframework.springpetclinic.bootstrap;

import antcar.springframework.springpetclinic.model.Owner;
import antcar.springframework.springpetclinic.model.Vet;
import antcar.springframework.springpetclinic.services.OwnerService;
import antcar.springframework.springpetclinic.services.VetService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final OwnerService ownerService;

    private final VetService vetService;

    public DataLoader(OwnerService ownerService, VetService vetService) {
        this.ownerService = ownerService;
        this.vetService = vetService;
    }

    @Override
    public void run(String... args) throws Exception {
        createOwner(1L, "Michael", "Weston");
        createOwner(2L, "Fiona", "Glenanne");
        System.out.println("Loaded Owners...");

        createVet(1L, "Sam", "Axe");
        createVet(2L, "Jessie", "Porter");
        System.out.println("Loaded Vets...");

    }

    private void createOwner(Long id, String firstName, String lastName) {
        Owner owner = new Owner();
        owner.setId(id);
        owner.setFirstName(firstName);
        owner.setLastName(lastName);
        ownerService.save(owner);
    }

    private void createVet(Long id, String firstName, String lastName) {
        Vet vet = new Vet();
        vet.setId(id);
        vet.setFirstName(firstName);
        vet.setLastName(lastName);
        vetService.save(vet);
    }
}
