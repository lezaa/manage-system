package cn.mju.admintle.provider;

import cn.mju.admintle.domain.Sign;
import org.apache.ibatis.jdbc.SQL;

public class SignProvider {
        public String insertSign(final Sign sign) {
            return new SQL() {{
                INSERT_INTO("tb_sign");
                if (sign.getUserId() != null) {
                    VALUES("user_id", "#{userId}");
                }
                if (sign.getTime() != null) {
                    VALUES("time", "#{time}");
                }
            }}.toString();
        }
}
