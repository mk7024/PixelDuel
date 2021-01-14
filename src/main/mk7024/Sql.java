package main.mk7024;

import com.sun.org.apache.bcel.internal.ExceptionConst;
import org.bukkit.entity.Player;

import java.sql.*;
import java.util.UUID;

public class Sql {
    private Connection connection;
    private String ip = Duel.getPlugin().getConfig().getString("Mysql.ip");
    private String username = Duel.getPlugin().getConfig().getString("Mysql.username");
    private String database = Duel.getPlugin().getConfig().getString("Mysql.database");
    private String passwd = Duel.getPlugin().getConfig().getString("Mysql.passwd");
    private String sql = "jdbc:mysql://" + ip + ":3306/" + database +"?user=" + username + "&password=" + passwd +"&autoReconnect=true";
    private PreparedStatement preparedStatement = null;
    private String insert = "CREATE TABLE IF NOT EXISTS `stats` ( `uuid` VARCHAR(255) NOT NULL , `kills` INT(11) NOT NULL DEFAULT '0', `death` INT(11) NOT NULL DEFAULT '0' , `gameplay` INT(11) NOT NULL DEFAULT '0',PRIMARY KEY(`uuid`))";


    public void establishConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            this.connection = DriverManager.getConnection(sql);

        } catch (ClassNotFoundException cnfe) {
            System.err.println("加载数据库失败");
            cnfe.printStackTrace();
        } catch (SQLException sqle) {
            System.err.println("无法连接数据库!");
            sqle.printStackTrace();
        }
    }

    public void disConnect() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (Exception e) {
            System.out.println("关闭数据库出错:");
            e.printStackTrace();
        }
    }


    public void insertSQL() {
        try {
            preparedStatement = connection.prepareStatement(insert);
            preparedStatement.execute();
        } catch (SQLException sqle) {
            System.out.println("插入数据库时出错");
            sqle.printStackTrace();
        } catch (Exception e) {
            System.out.println("执行出错");
            e.printStackTrace();
        }
    }

    public void addData(UUID uuid) {
        String sql1 = "INSERT INTO stats (`uuid`,`kills`,`death`,`gameplay`) VALUES ('" + uuid + "',0,0,0)";
        System.out.println(sql1);
        try {
            preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();

        } catch (SQLException sqle) {
            System.out.println("更新数据出错");
            sqle.printStackTrace();
        }
    }

    public void setData(UUID uuid, String type) {
        String update = "UPDATE `stats` SET `" + type + "` = " + type + " + '1'" + " WHERE `stats`.`uuid`= '" + uuid + "'";
        System.out.println(update);
        try {
            preparedStatement = connection.prepareStatement(update);
            preparedStatement.execute();
        } catch (SQLException sqle) {
            System.out.println("更新数据出错");
            sqle.printStackTrace();
        }
    }

    public boolean isInDataBase(Player player) {
        String sql = "SELECT * FROM `stats` WHERE `uuid`=" + "'" + player.getUniqueId() + "' LIMIT 1";
        try {
            preparedStatement = connection.prepareStatement(sql);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    return true;
                }
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        }
        return false;
    }

    public int getData(UUID uuid, String type) {
        int data = -1;
        String sql = "SELECT `" + type + "` FROM `stats` WHERE `uuid`=? LIMIT 1";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, String.valueOf(uuid));
            try(ResultSet resultSet = preparedStatement.executeQuery()){
                while(resultSet.next()){
                    data = Integer.valueOf(resultSet.getString(type));
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();

        }
            return data;
    }
}
