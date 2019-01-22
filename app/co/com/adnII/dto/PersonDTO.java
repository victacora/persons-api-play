package co.com.adnII.dto;

import co.com.adnII.model.entities.Person;
import com.fasterxml.jackson.annotation.JsonProperty;


public class PersonDTO {

    private Long id;
    @JsonProperty("firts_name")
    private String firtsName;
    @JsonProperty("second_name")
    private String secondName;
    @JsonProperty("last_name")
    private String lastName;
    @JsonProperty("id_card_number")
    private String idCardNumber;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
    private String link;

    public PersonDTO(){

    }

    public PersonDTO(Person data, String link) {
        this.id = data.getId();
        this.firtsName = data.getFirtsName();
        this.secondName = data.getSecondName();
        this.lastName = data.getLastName();
        this.idCardNumber = data.getIdCardNumber();
        this.phoneNumber = data.getPhoneNumber();
        this.email = data.getEmail();
        this.link = link;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirtsName() {
        return firtsName;
    }

    public void setFirtsName(String firtsName) {
        this.firtsName = firtsName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
