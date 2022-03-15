import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Project name(项目名称)：Mybatis实现分页功能
 * Package(包名): PACKAGE_NAME
 * Class(测试类名): SiteMapperTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2022/3/15
 * Time(创建时间)： 11:07
 * Version(版本): 1.0
 * Description(描述)： 测试类
 */

class SiteMapperTest
{

    @Test
    void select() throws IOException
    {
        //读取配置文件mybatis-config.xml
        InputStream config = Resources.getResourceAsStream("mybatis-config.xml");
        //根据配置文件构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(config);
        //通过SqlSessionFactory创建SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();

        SiteMapper siteMapper = sqlSession.getMapper(SiteMapper.class);

        PageHelper.startPage(2, 2);

        List<Site> list = siteMapper.select();

        for (Site site : list)
        {
            System.out.println(site);
            System.out.println();
        }

        PageInfo<Site> pageInfo = new PageInfo<>(list);
        System.out.println(pageInfo.getPageNum());
        System.out.println(pageInfo.getPageSize());
        System.out.println(pageInfo.getTotal());
        System.out.println(pageInfo.getPages());
        System.out.println(pageInfo.getNextPage());
        System.out.println(pageInfo.getPrePage());
        System.out.println(pageInfo.isIsFirstPage());
        System.out.println(pageInfo.isIsLastPage());

        sqlSession.close();
    }
}