package t1;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.session.TransactionIsolationLevel;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransaction;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.util.Properties;

/**
 * Project name(项目名称)：Mybatis实现分页功能
 * Package(包名): PACKAGE_NAME
 * Class(类名): t1.Druid
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/3/15
 * Time(创建时间)： 12:17
 * Version(版本): 1.0
 * Description(描述)： 无
 */

public class Druid implements DataSourceFactory, TransactionFactory
{
    //属性
    private Properties properties;

    @Override
    public void setProperties(Properties properties)
    {
        this.properties = properties;
    }

    @Override
    public Transaction newTransaction(Connection conn)
    {
        //Transaction transaction=new JdbcTransaction(conn);
        //return transaction;
        return null;
    }

    @Override
    public Transaction newTransaction(DataSource dataSource, TransactionIsolationLevel level, boolean autoCommit)
    {
        return null;
    }

    @Override
    public DataSource getDataSource()
    {
        //创建druid数据源
        DruidDataSource dataSource = new DruidDataSource();
        //获取配置信息
        //驱动
        String driver = this.properties.getProperty("jdbc.driver");
        //url
        String url = this.properties.getProperty("jdbc.url");
        //用户名
        String username = this.properties.getProperty("jdbc.username");
        //密码
        String password = this.properties.getProperty("jdbc.password");
        //初始化连接数
        int initialSize = Integer.parseInt(this.properties.getProperty("jdbc.initialSize"));
        //最大活动连接数
        int maxActive = Integer.parseInt(this.properties.getProperty("jdbc.maxActive"));
        //最大等待时间
        int maxWait = Integer.parseInt(this.properties.getProperty("jdbc.maxWait"));
        //设置配置信息
        //驱动
        dataSource.setDriverClassName(driver);
        //url
        dataSource.setUrl(url);
        //用户名
        dataSource.setUsername(username);
        //密码
        dataSource.setPassword(password);
        //设置初始化连接数
        dataSource.setInitialSize(initialSize);
        //最大活动连接数
        dataSource.setMaxActive(maxActive);
        //设置最大等待时间
        dataSource.setMaxWait(maxWait);
        //初始化连接
        try
        {
            dataSource.init();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return dataSource;
    }

    public static void main(String[] args) throws IOException
    {
        Properties properties = new Properties();
        InputStream inputStream = Druid.class.getClassLoader().getResourceAsStream("jdbc.properties");
        properties.load(inputStream);
        Druid druid = new Druid();
        druid.setProperties(properties);
        druid.getDataSource();
    }
}
