package controllers;

/**
 * Created by sunny.singh on 5/9/2017.
 */
public class User {

  private String user_id;
  private String customer_Id;
  private String first_name;
  private String last_name;
  private String email;
  private String user_name;
  private String password;
  private String is_disabled;
 // private String role;



  public String getUserId() {
    return user_id;
  }

  public String getCustomerId() {
    return customer_Id;
  }

  public String getFirstName() {
    return first_name;
  }

  public String getLastName() {
    return last_name;
  }

  public String getUserName() {
    return user_name;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getIsDisable() {
    return is_disabled;
  }

  public void setUserId(String user_id) {
    this.user_id = user_id;
  }
  public void setFirstName(String first_name) {
    this.first_name = first_name;
  }
  public void setLastName(String last_name) {
    this.last_name = last_name;
  }
  public void setCustomerId(String customer_Id) {
    this.customer_Id = customer_Id;
  }
  public void setUserName(String user_name) {
    this.user_name = user_name;
  }
  public void setEmail(String email) {
    this.email = email;
  }
  public void setPassword(String password) {
    this.password = password;
  }
  public void setIsDisable(String is_disabled) {
    this.is_disabled = is_disabled;
  }

}

