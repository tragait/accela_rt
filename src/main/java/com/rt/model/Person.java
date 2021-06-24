package com.rt.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "person")
public class Person implements Serializable {
  private static final long serialVersionUID=1L;
  public Person(){}

  public Person(Integer id, String firstname, String lastname) {
    this.id = id;
    this.firstname = firstname;
    this.lastname = lastname;
  }

  @Id
  private Integer id;

  private String firstname;

  private String lastname;

  @OneToMany(targetEntity = Address.class , cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
  @JoinColumn(name = "person_id")
  private Set<Address> addresses= new HashSet<>();

  public Person id(Integer id) {
    this.id = id;
    return this;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public Person firstname(String firstname) {
    this.firstname = firstname;
    return this;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setFirstname(String firstname) {
    this.firstname = firstname;
  }

  public Person lastname(String lastname) {
    this.lastname = lastname;
    return this;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public Person addresses(Set<Address> addresses) {
    this.addresses = addresses;
    return this;
  }

  public Person addAddressesItem(Address addressesItem) {
    if (this.addresses == null) {
      this.addresses = new HashSet<Address>();
    }
    this.addresses.add(addressesItem);
    return this;
  }

  public Set<Address> getAddresses() {
    return addresses;
  }

  public void setAddresses(Set<Address> addresses) {
    this.addresses = addresses;
  }


  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Person person = (Person) o;
    return Objects.equals(this.id, person.id) &&
        Objects.equals(this.firstname, person.firstname) &&
        Objects.equals(this.lastname, person.lastname) &&
        Objects.equals(this.addresses, person.addresses);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, firstname, lastname, addresses);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Person {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    firstname: ").append(toIndentedString(firstname)).append("\n");
    sb.append("    lastname: ").append(toIndentedString(lastname)).append("\n");
    sb.append("    addresses: ").append(toIndentedString(addresses)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}
