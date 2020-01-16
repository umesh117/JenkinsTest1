package com.maven.springMVC;

import javax.persistence.*;

@Entity
@Table(name="userdata")
public class UserBean {


   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "User_id", updatable = false, nullable = false)
   private int user_id;

  @Column(name="Username")
  private String username;

  @Column(name="email")
  private String email;

  @Column(name="password")
  private String password;

  @Column(name="dateofbirth")
  private String dateofbirth;

  @Column(name="filepath")
  private String filename;

    public UserBean() {
    }

    public UserBean(int user_id,String username, String email, String password, String dateofbirth, String filename) {
        this.user_id=user_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.dateofbirth = dateofbirth;
        this.filename = filename;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "username=" + username + "<br/>" +
                ", email=" + email +"<br/>"+
                ", dateofbirth=" + dateofbirth;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDateofbirth() {
        return dateofbirth;
    }

    public void setDateofbirth(String dateofbirth) {
        this.dateofbirth = dateofbirth;
    }
}
