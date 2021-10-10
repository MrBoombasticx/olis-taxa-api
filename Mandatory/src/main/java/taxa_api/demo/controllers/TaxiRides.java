package taxa_api.demo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import taxa_api.demo.models.TaxiRide;
import taxa_api.demo.repositories.TaxiRideRepository;

@RestController
public class TaxiRides {

    @Autowired
    TaxiRideRepository taxiRides;

    //  Vi henter alle oprettede taxiture.
    @GetMapping("/taxirides")
    public Iterable<TaxiRide> getTaxiRides() {
        return taxiRides.findAll();
    }
    
    //  Vi henter en specifik taxitur ud fra et id.
    @GetMapping("/taxirides/{id}")
    public TaxiRide getTaxirides(@PathVariable Long id) {
        return taxiRides.findById(id).get();
    }

    //  Der oprettes en ny taxitur.
    @PostMapping("/taxirides")
    public TaxiRide addTaxiRide(@RequestBody TaxiRide newTaxiRide) {
        return taxiRides.save(newTaxiRide);
    }
    
    
    //  Vi opdaterer hele ressoucen (Taxituren), ud fra et givent id.
    @PutMapping("/taxirides/{id}")
    public String updateTaxiRideById(@PathVariable Long id, @RequestBody TaxiRide taxiRideToUpdateWith) {
        if (taxiRides.existsById(id)) {
            taxiRideToUpdateWith.setTaxiRideID(id);
            taxiRides.save(taxiRideToUpdateWith);
            return "Taxi ride was created";
        } else {
            return "Taxi ride was not found";
        }
    }

    // Vi vil kunne opdatere enkelte dele af ressourcen.
    @PatchMapping("/taxirides/{id}")
    public String patchTaxiRideById(@PathVariable Long id, @RequestBody TaxiRide taxiRideToPatchWith) {
        //  Hvis taxi turen findes ved dets id, defineres den ved objektet foundTaxiRide
        //  og vi tjekker for at se om der laves nogle ændringer ved de forskellige attributter.
        //  Hvis en værdi ændres vil den del af ressourcen dermed være blevet patched.
        return taxiRides.findById(id).map(foundTaxiRide -> {
            if (taxiRideToPatchWith.getCarBrand() != null)
                foundTaxiRide.setCarBrand(taxiRideToPatchWith.getCarBrand());

            if (taxiRideToPatchWith.getChauffeurName() != null)
                foundTaxiRide.setChauffeurName(taxiRideToPatchWith.getChauffeurName());

            if (taxiRideToPatchWith.getDistanceKM() != 0)
                foundTaxiRide.setDistanceKM(taxiRideToPatchWith.getDistanceKM());

            if (taxiRideToPatchWith.getPriceKroner() != 0)
                foundTaxiRide.setPriceKroner(taxiRideToPatchWith.getPriceKroner());
            // Vi gemmer det nye patchede objekt.
            taxiRides.save(foundTaxiRide);
            return "The taxi ride has been patched";
        }).orElse("Taxi ride was not found");
    }

    //  Vi sletter en taxitur ud fra dets id.
    @DeleteMapping("/taxirides/{id}")
    public void deleteTaxiRideById(@PathVariable Long id) {
        taxiRides.deleteById(id);
    }

}
