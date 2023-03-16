/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fall22.pe.user;

/**
 *
 * @author hoadnt
 */
public class UserErrorDTO {
    private String userIDAndPasswordInvalid;
    
    private String notAdminRole;

    public String getNotAdminRole() {
        return notAdminRole;
    }

    public void setNotAdminRole(String notAdminRole) {
        this.notAdminRole = notAdminRole;
    }
    
    

    public String getUserIDAndPasswordInvalid() {
        return userIDAndPasswordInvalid;
    }

    public void setUserIDAndPasswordInvalid(String userIDAndPasswordInvalid) {
        this.userIDAndPasswordInvalid = userIDAndPasswordInvalid;
    }
    
}
