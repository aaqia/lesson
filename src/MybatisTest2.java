import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest2 {
    //让你讲一下 mybatis的执行流程
    //导包-配置sqlmapconfig链接数据库-用sqlSessionFactory读取xml文件-建立一个工厂类
    private SqlSession sqlSession;
    @Before//在@Test之前 执行的方法 提取重复的代码
    public void before() throws IOException {
        //加载并读取xml
        String path = "SqlMapConfig2.xml";
        InputStream is = Resources.getResourceAsStream(path);
        //sql链接的工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession = " + sqlSession);
        //sqlSession.close();
    }

    @Test
    public void test01(){
        Map map = new HashMap();
        map.put("cid1",01);
        map.put("cid2",02);
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select1",map);
        for (Map map1 : list) {
            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }

    @Test
    public void test02(){
        Map map = new HashMap();
        map.put("cid1",01);
        map.put("cid2",02);
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select2",map);
        for (Map map1 : list) {
            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }

    @Test
    public void test03(){
        Map map = new HashMap();
        map.put("avgscore",60);
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select3",map);
        for (Map map1 : list) {
            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }

    @Test
    public void test04(){
        Map map = new HashMap();
        map.put("avgscore",60);
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select4",map);
        for (Map map1 : list) {
            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }

    @Test
    public void test05(){
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select5");
        for (Map map1 : list) {
            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }

    @Test
    public void test06(){
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select6","李");
        System.out.println("list = " + list);
        sqlSession.close();
    }

    @Test
    public void test07(){
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select7","张三");
        System.out.println("list = " + list);
        sqlSession.close();
    }

    @Test
    public void test08(){
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select8","张三");
        System.out.println("list = " + list);
        sqlSession.close();
    }

    @Test
    public void test09(){
        Map map = new HashMap();
        map.put("cid1",01);
        map.put("cid2",02);
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select9",map);
        for (Map map1 : list) {
            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }

    @Test
    public void test10(){
        Map map = new HashMap();
        map.put("cid1",01);
        map.put("cid2",02);
        List<Map> list = sqlSession.selectList("com.hp.dao.StudentDao.select10",map);
        for (Map map1 : list) {
            System.out.println("map1 = " + map1);
        }
        sqlSession.close();
    }
}
