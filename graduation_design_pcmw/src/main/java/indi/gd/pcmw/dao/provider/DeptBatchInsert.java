package indi.gd.pcmw.dao.provider;

import indi.gd.pcmw.domain.Department;
import org.apache.ibatis.jdbc.SQL;

import java.util.List;

public class DeptBatchInsert {
    public String batchInsert(final List<Department> departments) {


        String sql = "insert into pcmw_department(dept_name,hosp_id) values";
        for (Department dept : departments
        ) {
            sql = sql + "('" + dept.getDeptName() + "'," + dept.getHospId() + "),";
        }
        sql = sql.substring(0, (sql.lastIndexOf(",")));
        return sql;

    }
}