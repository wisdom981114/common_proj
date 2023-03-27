package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
public class Address {


    private String city;
    private String Street;
    private String zipcode;

    protected Address(){}

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.Street = street;
        this.zipcode = zipcode;
    }
}
