package com.crud.base.demo.model;

import java.util.Map;
import java.util.Objects;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "id")
@Entity(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="id", updatable=false, unique=true, nullable=false)
    private UUID id;

    @NotBlank(message = "The apelido name is required.")
    private String apelido;

    @NotBlank(message = "The street name is required.")
    private String street;

    @NotBlank(message = "The district must be filled.")
    private String district;

    @NotBlank(message = "The state name is required.")
    private String state;

    @NotBlank(message = "The country name is required.")
    private String country;

    @NotBlank(message = "The city name is required.")
    private String city;

    @NotNull(message = "The number is required.")
    private int number;

    @NotBlank(message = "CEP is required.")
    private String cep;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User owner;

    public Address(String apelido, String country, String state, String city, String district, String street, int number, String cep){
        this.apelido = apelido;
        this.street = street;
        this.state = state;
        this.country = country;
        this.number = number;
        this.city = city;
        this.district = district;
        this.cep = cep;
    }
    public Address (){}

    @Override
    public boolean equals(Object arg0) {

        if (!(arg0 instanceof Address)){
            return false;
        }

        Address address = (Address) arg0;
        return Objects.equals(this.apelido+"-"+this.number+"-"+this.street+"-"+this.city+"-"+this.state+"-"+this.country,
                address.getApelido()+"-"+address.getNumber()+"-"+address.getStreet()+"-"+address.getCity()+"-"+address.getState()+"-"+address.getCountry());
    }

    @Override
    public int hashCode(){
        return 1;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public void setDistrict(String district){
        this.district = district;
    }

    public String getDistrict(){
        return this.district;
    }

    public static Address parseAddress(Map<String, Object> addressMap){

        String id = (String) addressMap.get("id");

        if (id != null){
            addressMap.put("id", UUID.fromString(id));
        }

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(addressMap, Address.class);
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
