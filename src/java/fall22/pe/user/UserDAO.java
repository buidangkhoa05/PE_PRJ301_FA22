/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fall22.pe.user;

import fall22.pe.utils.DBUtils;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


/**
 *
 * @author Hoadnt
 */
public class UserDAO implements Serializable{
//    your code here
    public UserDTO login(String userID, String password) throws SQLException, ClassNotFoundException {
        UserDTO dto = null;
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        
        try {
            con = DBUtils.getConnection();
            if (con == null) {
                return null;
            }
            String sql = "Select fullName, roleID "
                    + "From tblUsers "
                    + "Where userID = ? AND password = ? ";
            stm = con.prepareCall(sql);
            stm.setString(1, userID);
            stm.setString(2, password);
            rs = stm.executeQuery();
            if (rs.next()) {
                System.out.println("get succ");
                String fullName = rs.getString("fullName");
                String roleID = rs.getString("roleID");
                dto = new UserDTO(userID, fullName, roleID, password);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return dto;
    }
    
    
    
    
}
