import com.hp.bean.Person;
import com.hp.bean.PersonDto;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MybatisTest {
    //让你讲一下 mybatis的执行流程
    //导包-配置sqlmapconfig链接数据库-用sqlSessionFactory读取xml文件-建立一个工厂类
    private SqlSession sqlSession;
    @Before//在@Test之前 执行的方法 提取重复的代码
    public void before() throws IOException {
        //加载并读取xml
        String path = "SqlMapConfig.xml";
        InputStream is = Resources.getResourceAsStream(path);
        //sql链接的工厂类
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);
        sqlSession = sqlSessionFactory.openSession();
        System.out.println("sqlSession = " + sqlSession);
        //sqlSession.close();
    }


    //全查 select * fron person---讲的点是resultmap
    @Test
    public void test01(){
        List<Person> personList = sqlSession.selectList("com.hp.dao.PersonDao.selectAll");
        for (Person person : personList) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    @Test
    public void test02(){
        List<Person> list = sqlSession.selectList("com.hp.dao.PersonDao.selectPersonBySex",2);
        for (Person person : list) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    @Test
    public void test03(){
        Long aLong = sqlSession.selectOne("com.hp.dao.PersonDao.selectCount");
        System.out.println("aLong = " + aLong);
        sqlSession.close();
    }

    //代参查询 第一种方式  实体类传参
    @Test
    public void test04(){
        Person person = new Person();
        person.setScore(100);
        person.setGender(2);
        Long o = sqlSession.selectOne("com.hp.dao.PersonDao.selectCountByParam01", person);
        System.out.println("o = " + o);
        sqlSession.close();
    }

    @Test
    public void test05() throws ParseException {
        String date = "2020-10-14";
        SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
        Date birthday = sd.parse(date);
        Map map = new HashMap();
        map.put("gender",2);//key一定要和#{gender} 值 保持一致
        map.put("birthday",birthday);//key一定要和#{gender} 值 保持一致
        List<Person> lists = sqlSession.selectList("com.hp.dao.PersonDao.selectCountByParam02",map);
        for (Person list : lists) {
            System.out.println("list = " + list);
        }
        sqlSession.close();
    }

    //查最高分
    @Test
    public void test06(){
        List<Person> list = sqlSession.selectList("com.hp.dao.PersonDao.selectByScore");
        System.out.println("list = " + list);
        sqlSession.close();
    }

    // 所有男生和女生的平均分值各是多少
    @Test
    public void test07(){
        List<PersonDto> list = sqlSession.selectList("com.hp.dao.PersonDao.selectavgscore");
        System.out.println("list = " + list);
        sqlSession.close();
    }
    // 所有人的分数大于100的或者性别是1的
    @Test
    public void test08(){
        Map map = new HashMap();
        map.put("gender",1);
        map.put("score",100);
        List<Person> list = sqlSession.selectList("com.hp.dao.PersonDao.selectsexByScore",map);
        for (Person person : list) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //所有男生和女生的平均分值大于200的
    @Test
    public void test09(){
        List<PersonDto> list = sqlSession.selectList("com.hp.dao.PersonDao.selectavgscore2",200);
        for (PersonDto personDto : list) {
            System.out.println("personDto = " + personDto);
        }
        sqlSession.close();
    }

    //所有男生和女生的平均分值大于200的  map 接收
    @Test
    public void test10(){
        List<Map> maps = sqlSession.selectList("com.hp.dao.PersonDao.selectavgscore3", 200);
        for (Map map : maps) {
            System.out.println("map = " + map);
        }
        sqlSession.close();
    }

    //查询性孙的  模糊查询
    //There is no getter for property named 'name'  因为$是拼接的没有getter这个概念,#相当于问号
    @Test
    public void test010(){
        Map map = new HashMap();
        map.put("name","孙");
        List<Person> list = sqlSession.selectList("com.hp.dao.PersonDao.selectlike",map);
        for (Person person : list) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //第二种方式 可以用
    @Test
    public void test11(){
        List<Person> list = sqlSession.selectList("com.hp.dao.PersonDao.selectlike2", "孙");
        for (Person person : list) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
    }

    //第3种方式 可以用
    @Test
    public void test12() {
        List<Person> list = sqlSession.selectList("com.hp.dao.PersonDao.selectlike3", "孙");
        for (Person person : list) {
            System.out.println("person = " + person);
        }
        sqlSession.close();
        //以上是单表查询
    }

        //下面是增加 insert into
        @Test
        public void test13(){
            Person person = new Person();
            person.setName("阿奇");
            person.setGender(1);
            person.setAddress("河南");
            person.setScore(100);
            person.setBirthday(new Date());

            int insert = sqlSession.insert("com.hp.dao.PersonDao.insertPerson",person);
            System.out.println("insert = " + insert);
            sqlSession.commit();
            sqlSession.close();
        }
}
