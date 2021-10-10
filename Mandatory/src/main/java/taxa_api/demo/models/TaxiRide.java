package taxa_api.demo.models;


import lombok.Data;

import javax.persistence.*;


@Data
@Table(name = "TaxiRide")
@Entity

public class TaxiRide {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long taxiRideID;

    @Column
    private String carBrand;

    @Column
    private String chauffeurName;

    @Column
    private int distanceKM;

    @Column
    private int priceKroner;

}
