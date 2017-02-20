package epam.com.app;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.List;

/**
 * Dao implementation.
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //String getAllUsersSql = "select user_id, login, password, description from app_user";
    //String getUserByIdSql = "select user_id, login, password, description from app_user where user_id = :p_user_id";
    //String addUserSql = "insert into app_user (login, password, description) values (:login, :password, :description)";
    //String updateUserSql = "update app_user set  login=:login, password=:password, description=:description where user_id=:id";
    //String deleteUserSql = "delete from app_user where user_id=:id";

    @Value("${sql.getAllUsers}")
    String getAllUsersSql;

    @Value("${sql.getUserById}")
    String getUserByIdSql;

    @Value("${sql.addUser}")
    String addUserSql;

    @Value("${sql.updateUser}")
    String updateUserSql;

    @Value("${sql.deleteUser}")
    String deleteUserSql;

    private static final Logger LOGGER = LogManager.getLogger();
    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
        namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.debug("get all users");
        return jdbcTemplate.query(getAllUsersSql, new UserRowMapper());
    }

    @Override
    public User getUserById(Integer userId) {
        LOGGER.debug("get user by id");
        SqlParameterSource namedParameters = new MapSqlParameterSource("p_user_id", userId);
        User user = namedParameterJdbcTemplate.queryForObject(
                getUserByIdSql, namedParameters, new UserRowMapper());
        return user;
    }

    @Override
    public Integer addUser(User user) {
        LOGGER.debug("add user");
        KeyHolder keyHolder=new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource=new MapSqlParameterSource();
        parameterSource.addValue("login",user.getLogin());
        parameterSource.addValue("password",user.getPassword());
        parameterSource.addValue("description",user.getDescription());
        namedParameterJdbcTemplate.update(addUserSql, parameterSource, keyHolder);

        return keyHolder.getKey().intValue();
    }

    @Override
    public void updateUser(User user) {
        LOGGER.debug("update user");
        MapSqlParameterSource parameterSource=new MapSqlParameterSource();
        parameterSource.addValue("id", user.getUserId());
        parameterSource.addValue("login",user.getLogin());
        parameterSource.addValue("password",user.getPassword());
        parameterSource.addValue("description",user.getDescription());
        namedParameterJdbcTemplate.update(updateUserSql, parameterSource);
    }

    @Override
    public void deleteUser(Integer userId) {
        LOGGER.debug("delete user");
        SqlParameterSource namedParameters=new MapSqlParameterSource("id",userId);
        namedParameterJdbcTemplate.update(deleteUserSql,namedParameters);

    }

    private class UserRowMapper implements RowMapper<User> {

        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User(
                    resultSet.getInt("user_id"),
                    resultSet.getString("login"),
                    resultSet.getString("password"),
                    resultSet.getString("description"));
            return user;
        }
    }
}