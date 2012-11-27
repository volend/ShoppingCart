/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Repositories.UserRepository;

/**
 *
 * @author volen
 */
public class UserInfo {

    public final String FirstName;
    public final String LastName;
    public final String EmailAddress;
    public final String AccessToken;
    public final String PhoneNumber;
    public final AccessPrivileges Privileges;


    public UserInfo(String fName, String lName, String email, String token, String number, AccessPrivileges privileges) {
        FirstName = fName;
        LastName = lName;
        EmailAddress = email;
        AccessToken = token;
        PhoneNumber = number;
        Privileges = privileges;
    }
}