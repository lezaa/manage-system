package cn.mju.admintle.mapper;

import cn.mju.admintle.domain.Sign;
import cn.mju.admintle.provider.RoleProvider;
import cn.mju.admintle.provider.SignProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Repository
public interface SignMapper {

    @Select("select * from tb_sign order by time desc")
    List<Sign> getAll();

    @Select("select * from tb_sign where id = #{id}")
    Sign getOne(int id);

    @Select("select * from tb_sign where user_id = #{userId} order by time desc")
    List<Sign> getOneList(long userId);

    @Select("select * from tb_sign where user_id =#{userId} and time = #{time}")
    Sign getSign(long userId, Date time);

    @InsertProvider(type = SignProvider.class, method = "insertSign")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    int insertSign(Sign sign);


}
