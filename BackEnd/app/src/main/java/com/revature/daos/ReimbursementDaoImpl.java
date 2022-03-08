package com.revature.daos;

import com.revature.models.Reimbursement;
import com.revature.util.ConnectionUtil;
import com.revature.util.LoggingSingleton;
import org.postgresql.util.PGmoney;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDaoImpl implements ReimbursementDao{

    @Override
    public boolean create(Reimbursement reimbursement) {
        String sql = "insert into \"ProjectOne\".ers_reimbursement (reimb_amount, reimb_submitted, " +
                "reimb_description, reimb_author, reimb_status_id, reimb_type_id)" +
                " values (?, ?, ?, ?, ?, ?)";
        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            PGmoney money = new PGmoney(reimbursement.getAmount());
            ps.setObject(1, money);
            Timestamp submitted = Timestamp.valueOf(LocalDateTime.now());
            ps.setTimestamp(2, submitted);
            ps.setString(3, reimbursement.getDescription());
            ps.setInt(4, reimbursement.getAuthor());
            ps.setInt(5, 1);//for pending
            ps.setInt(6, reimbursement.getTypeId());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 1)
                return true;
        } catch (SQLException e){
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                    + "failed to create: " + reimbursement.toString(), e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                + "failed to create: " + reimbursement.toString());
        return false;
    }

    @Override
    public boolean updateStatus(Reimbursement reimbursement) {
        String sql = "update \"ProjectOne\".ers_reimbursement " +
                     "set reimb_resolved = ?, reimb_status_id = ?, reimb_resolver = ? " +
                     "where reimb_id = ?;";

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setObject(1,Timestamp.valueOf(LocalDateTime.now()));
            ps.setInt(2,reimbursement.getStatusId());
            ps.setInt(3,reimbursement.getResolver());
            ps.setInt(4,reimbursement.getId());

            int rowsAffected = ps.executeUpdate();
            if(rowsAffected == 1)
                return true;

        } catch (SQLException e){
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                    + "failed to update: " + reimbursement.toString(), e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                + "failed to update: " + reimbursement.toString());
        return false;
    }

    @Override
    public List<Reimbursement> getAll() {
        String sql = "select * from \"ProjectOne\".ers_reimbursement;";
        List<Reimbursement> list = new ArrayList<>();

        try(Connection c = ConnectionUtil.getConnection();
            Statement s = c.createStatement()){
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("reimb_id"));
                r.setAmount(rs.getFloat("reimb_amount"));
                r.setSubmitted(rs.getObject("reimb_submitted", Timestamp.class));
                if(rs.getObject("reimb_resolved", Timestamp.class) != null)
                    r.setResolved(rs.getObject("reimb_resolved", Timestamp.class));
                r.setDescription(rs.getString("reimb_description"));
                r.setAuthor(rs.getInt("reimb_author"));
                r.setResolver(rs.getInt("reimb_resolver"));
                r.setStatusId(rs.getInt("reimb_status_id"));
                r.setTypeId(rs.getInt("reimb_type_id"));

                list.add(r);
            }
            return list;
        } catch (SQLException e){
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                    + "failed to get All",e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                + "failed to get All");
        return null;
    }

    @Override
    public List<Reimbursement> getAllPending() {
        String sql = "select * from \"ProjectOne\".ers_reimbursement where reimb_status_id = 1;";
        List<Reimbursement> list = new ArrayList<>();

        try(Connection c = ConnectionUtil.getConnection();
            Statement s = c.createStatement()){
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("reimb_id"));
                r.setAmount(rs.getFloat("reimb_amount"));
                r.setSubmitted(rs.getObject("reimb_submitted", Timestamp.class));
                r.setResolved(rs.getObject("reimb_resolved", Timestamp.class));
                r.setDescription(rs.getString("reimb_description"));
                r.setAuthor(rs.getInt("reimb_author"));
                r.setResolver(rs.getInt("reimb_resolver"));
                r.setStatusId(rs.getInt("reimb_status_id"));
                r.setTypeId(rs.getInt("reimb_type_id"));

                list.add(r);
            }
            return list;
        } catch (SQLException e){
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                    + "failed to get All Pending",e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                + "failed to get All Pending");
        return null;
    }

    @Override
    public List<Reimbursement> getAllResolved() {
        String sql = "select * from \"ProjectOne\".ers_reimbursement where reimb_status_id != 1";
        List<Reimbursement> list = new ArrayList<>();

        try(Connection c = ConnectionUtil.getConnection();
            Statement s = c.createStatement()){
            ResultSet rs = s.executeQuery(sql);

            while(rs.next()){
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("reimb_id"));
                r.setAmount(rs.getFloat("reimb_amount"));
                r.setSubmitted(rs.getObject("reimb_submitted", Timestamp.class));
                r.setResolved(rs.getObject("reimb_resolved", Timestamp.class));
                r.setDescription(rs.getString("reimb_description"));
                r.setAuthor(rs.getInt("reimb_author"));
                r.setResolver(rs.getInt("reimb_resolver"));
                r.setStatusId(rs.getInt("reimb_status_id"));
                r.setTypeId(rs.getInt("reimb_type_id"));

                list.add(r);
            }
            return list;
        } catch (SQLException e){
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                    + "failed to get All Resolved",e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                + "failed to get All Resolved");
        return null;
    }

    @Override
    public List<Reimbursement> getByAuthor(int sid) {
        String sql = "select * from \"ProjectOne\".ers_reimbursement where reimb_author = ?";
        List<Reimbursement> list = new ArrayList<>();

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1,sid);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("reimb_id"));
                r.setAmount(rs.getFloat("reimb_amount"));
                r.setSubmitted(rs.getObject("reimb_submitted", Timestamp.class));
                r.setResolved(rs.getObject("reimb_resolved", Timestamp.class));
                r.setDescription(rs.getString("reimb_description"));
                r.setAuthor(rs.getInt("reimb_author"));
                r.setResolver(rs.getInt("reimb_resolver"));
                r.setStatusId(rs.getInt("reimb_status_id"));
                r.setTypeId(rs.getInt("reimb_type_id"));

                list.add(r);
            }
            return list;
        } catch (SQLException e){
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                    + "failed to get Author",e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                + "failed to get Author");
        return null;
    }

    @Override
    public List<Reimbursement> getByAuthorAndPending(int sid) {
        String sql = "select * from \"ProjectOne\".ers_reimbursement where reimb_author = ?" +
                     " and where reimb_status_id = 1";
        List<Reimbursement> list = new ArrayList<>();

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1,sid);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("reimb_id"));
                r.setAmount(rs.getFloat("reimb_amount"));
                r.setSubmitted(rs.getObject("reimb_submitted", Timestamp.class));
                r.setResolved(rs.getObject("reimb_resolved", Timestamp.class));
                r.setDescription(rs.getString("reimb_description"));
                r.setAuthor(rs.getInt("reimb_author"));
                r.setResolver(rs.getInt("reimb_resolver"));
                r.setStatusId(rs.getInt("reimb_status_id"));
                r.setTypeId(rs.getInt("reimb_type_id"));

                list.add(r);
            }
            return list;
        } catch (SQLException e){
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                    + "failed to get Author and Pending",e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                + "failed to get Author and Pending");
        return null;
    }

    @Override
    public List<Reimbursement> getByAuthorAndResolved(int sid) {
        String sql = "select * from \"ProjectOne\".ers_reimbursement where reimb_author = ?" +
                     " and where reimb_status_id != 1";
        List<Reimbursement> list = new ArrayList<>();

        try(Connection c = ConnectionUtil.getConnection();
            PreparedStatement ps = c.prepareStatement(sql)){

            ps.setInt(1,sid);

            ResultSet rs = ps.executeQuery();

            while(rs.next()){
                Reimbursement r = new Reimbursement();

                r.setId(rs.getInt("reimb_id"));
                r.setAmount(rs.getFloat("reimb_amount"));
                r.setSubmitted(rs.getObject("reimb_submitted", Timestamp.class));
                r.setResolved(rs.getObject("reimb_resolved", Timestamp.class));
                r.setDescription(rs.getString("reimb_description"));
                r.setAuthor(rs.getInt("reimb_author"));
                r.setResolver(rs.getInt("reimb_resolver"));
                r.setStatusId(rs.getInt("reimb_status_id"));
                r.setTypeId(rs.getInt("reimb_type_id"));

                list.add(r);
            }
            return list;
        } catch (SQLException e){
            //e.printStackTrace();
            LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                    + "failed to get Author and Resolved",e);
        }
        LoggingSingleton.logger.warn(this.getClass().getCanonicalName()
                + "failed to get Author and Resolved");
        return null;
    }
}
