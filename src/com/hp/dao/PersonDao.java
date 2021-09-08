package com.hp.dao;

import com.hp.bean.Person;
import com.hp.bean.PersonDto;

import java.util.List;
import java.util.Map;

public interface PersonDao {
    //全查
    List<Person> selectAll();
    //单查
    List<Person> selectPersonBySex(int sex);
   // List<Person> selectPersonBySex(int sex,String name);不支持
    //查总条数
    long selectCount();
    //查总条数+多个参数  第一种方式 两个参数都是实体类中的属性  直接把实体类作为参数
    long selectCountByParam01(Person person);
    //当查询出处的sql语句不确定是唯一的一条时  返回值一定要用list
    List<Person> selectCountByParam02(Map map);
    //查询最高分的人
    List<Person> selectByScore();
    //所有人的分数大于100的或者性别是1的
    List<Person> selectsexByScore(Map map);
    //所有男生和女生的平均分值各是多少
    List<PersonDto> selectavgscore();
    //所有男生和女生的平均分值大于200的
    List<PersonDto> selectavgscore2(int score);
    //所有男生和女生的平均分值大于200的  使用map做返回值
    List<Map> selectavgscore3(int score);
    //查询性孙的  模糊查询
    List<Person> selectlike(String name);
    //查询性孙的  模糊查询
    List<Person> selectlike2(String name);
    //查询性孙的  模糊查询
    List<Person> selectlike3(String name);


    //增加
    int insertPerson(Person person);
}
