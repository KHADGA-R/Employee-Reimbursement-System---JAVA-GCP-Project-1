package com.revature.daos;

import com.revature.models.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.LoggingSingleton;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {


    @Override
    public List<User> getAllUsers() {
        String sql = "select * from \"ProjectOne\".ers_user;";
        List<User> users = new ArrayList<>();

        try (Connection c = ConnectionUtil.getConnection();
             Statement s = c.createStatement();) {

            ResultSet rs = s.executeQuery(sql);

            while (rs.next()) {
                User user = new User();

                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setRoleId(rs.getInt(7));

                users.add(user);
            }
            return users;

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return null;
    }


    @Override
    public User getUserById(int id) {
        String sql = "select * from \"ProjectOne\".ers_user where ers_users_id = ? ";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = new User();
                user.setId(id);

                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setRoleId(rs.getInt(7));

                return user;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public boolean updateUser(User u) {
        String sql = "update \"ProjectOne\".ers_user set ers_password =?, user_first_name =?, " +
                "user_last_name = ?, user_email = ?, user_role_id = ? where ers_users_id = ?";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, u.getPassword());
            ps.setString(2, u.getFirstName());
            ps.setString(3, u.getLastName());
            ps.setString(4, u.getEmail());
            ps.setInt(5, u.getRoleId());
            ps.setInt(6, u.getId());

            int rowAffected = ps.executeUpdate();

            if (rowAffected == 1) {
                return true;
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName() + "Update User Failed", e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName() + "Update User Failed");
        return false;
    }


    @Override
    public boolean createUser(User u) {

        String sql = "insert into \"ProjectOne\".ers_user (ers_username, ers_password, user_first_name, user_last_name, " +
                "user_email, user_role_id) values (?, ?, ?, ?, ?, ?)";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {


            ps.setString(1, u.getUsername());
            ps.setString(2, u.getPassword());
            ps.setString(3, u.getFirstName());
            ps.setString(4, u.getLastName());
            ps.setString(5, u.getEmail());
            ps.setInt(6, u.getRoleId());

            int rowAffected = ps.executeUpdate();

            if (rowAffected == 1) {
                return true;
            }

        } catch (SQLException e) {
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName() + "Create User Failed", e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName() + "Create User Failed");
        return false;
    }

    @Override
    public User getUserByUsernameAndPassword(String username, String password) {

        String sql = "select *from \"ProjectOne\".ers_user where ers_username = ? and ers_password = ?";

        try (Connection c = ConnectionUtil.getConnection();
             PreparedStatement ps = c.prepareStatement(sql);) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if(rs.next()){
                User user = new User();

                user.setId(rs.getInt(1));
                user.setUsername(rs.getString(2));
                user.setPassword(rs.getString(3));
                user.setFirstName(rs.getString(4));
                user.setLastName(rs.getString(5));
                user.setEmail(rs.getString(6));
                user.setRoleId(rs.getInt(7));

                return user;

            }

        }catch(SQLException e){
            e.printStackTrace();

            }
            return null;

    }
}
