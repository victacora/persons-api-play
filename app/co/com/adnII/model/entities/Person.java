package co.com.adnII.model.entities;

import javax.persistence.*;

    @Entity
    @Table(name = "person")
    public class Person {

        public Person() {
        }

        public Person(String firtsName,
                 String secondName,
                 String lastName,
                 String idCardNumber,
                 String phoneNumber,
                 String email) {
            this.firtsName = firtsName;
            this.secondName = secondName;
            this.lastName = lastName;
            this.idCardNumber = idCardNumber;
            this.phoneNumber = phoneNumber;
            this.email = email;
        }

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "ID")
        private Long id;

        @Column(name = "FIRTS_NAME",length = 50)
        private String firtsName;

        @Column(name = "SECOND_NAME",length = 50)
        private String secondName;

        @Column(name = "LAST_NAME",length = 100)
        private String lastName;

        @Column(name = "ID_CARD_NUMBER",length = 20)
        private String idCardNumber;

        @Column(name = "PHONE_NUMBER",length = 15)
        private String phoneNumber;

        @Column(name = "EMAIL",length = 100)
        private String email;

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
    }

