package com.webproject.simplewebapplication.dao;

import com.webproject.simplewebapplication.entity.User;
import com.webproject.simplewebapplication.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
@Repository
public class UserDaoImpl implements UserDao {


    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Override
    public Boolean save(User user) {
        String query="insert into employee values(?,?,?,?,?,?,?)";
        return jdbcTemplate.execute(query,new PreparedStatementCallback<Boolean>(){
            @Override
            public Boolean doInPreparedStatement(PreparedStatement ps)
                    throws SQLException, DataAccessException {
                ps.setInt(1,user.getEmployee_id());
                ps.setString(2,user.getFirst_name());
                ps.setString(3,user.getLast_name());
                ps.setInt(4,user.getDepartment_id());
                ps.setString(5,user.getJob_title());
                ps.setString(6,user.getGender());
                ps.setString(7,user.getDate_of_birth());

                return ps.execute();

            }
        });
    }

    @Override
    public User getById(int employee_id) {
        String sql = "SELECT * FROM public.employee WHERE employee_id=?";
        try{
            return  (User) this.jdbcTemplate.queryForObject(
                    sql, new Object[] { employee_id }, new UserMapper());
        }
        catch(EmptyResultDataAccessException ex){
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        return jdbcTemplate.query("SELECT * FROM public.employee", new UserMapper());
    }

    @Override
    public Integer update(User user) {
        String query="UPDATE public.employee SET first_name=?, last_name=?, department_id=?, job_title=?, gender=?, date_of_birth=? WHERE employee_id=?";
        Object[] params = {user.getFirst_name(), user.getLast_name(),user.getDepartment_id(),user.getJob_title(),user.getGender(),user.getDate_of_birth(), user.getEmployee_id()};
        int[] types = {Types.VARCHAR, Types.VARCHAR,Types.INTEGER,Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};

        return jdbcTemplate.update(query, params, types);
    }

    @Override
    public Integer delete(int employee_id) {
        return jdbcTemplate.update("DELETE FROM public.employee WHERE employee_id=?",employee_id);
    }
}
