/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates/. Created on : 30-10-2022
 * and open the template in the editor.
 */
package fall22.pe.food;

import fall22.pe.utils.DBUtils;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author hd
 */
public class FoodDAO {

    private List<FoodDTO> list;

    public List<FoodDTO> search(String searchValue) throws ClassNotFoundException, SQLException {
        Connection con = null;
        PreparedStatement stm = null;
        ResultSet rs = null;
        FoodDTO dto = null;

        try {
            con = DBUtils.getConnection();
            if (con != null) {
                // 2. Create SQL string
                String sql = "select id, name, description, price, cookingTime "
                        + "from tblFoods "
                        + "where status = 'true' AND name like ?";
                // 3. create Statement 
                stm = con.prepareStatement(sql);
                stm.setString(1, "%" + searchValue + "%");
                //4. execute Query
                rs = stm.executeQuery();
                //5. process result
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String description = rs.getString("description");
                    float price = rs.getFloat("price");
                    int cookingTime = rs.getInt("cookingTime");
                    dto = new FoodDTO(id, name, description, price, cookingTime, true);
                    if (dto != null) {
                        if (this.list == null) {
                            this.list = new ArrayList<>();
                        }
                        this.list.add(dto);
                    }
                }
            }
        } finally {
            if (con != null) {
                con.close();
            }
            if (stm != null) {
                stm.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return this.list;
    }

    public boolean update(String pk) throws SQLException, ClassNotFoundException {
        Connection con = null;
        PreparedStatement stm = null;
        boolean result = false;

        try {
            con = DBUtils.getConnection();
            String sql = "UPDATE tblFoods "
                    + "SET status = 'false' "
                    + "WHERE id =  ? ";
            stm = con.prepareStatement(sql);
            stm.setString(1, pk);

            int effectRows = stm.executeUpdate();
            if (effectRows > 0) {
                result = true;
            }
        } finally {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        }
        return result;
    }

}
