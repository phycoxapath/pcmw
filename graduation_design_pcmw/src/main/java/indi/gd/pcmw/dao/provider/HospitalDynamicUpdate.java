package indi.gd.pcmw.dao.provider;

import indi.gd.pcmw.domain.Hospital;
import org.apache.ibatis.jdbc.SQL;

public class HospitalDynamicUpdate {
    public String updateHospital(final Hospital hospital){
        return new SQL(){{
            UPDATE("pcmw_hospital");
            if (hospital.getHospitalName() != null && !hospital.getHospitalName().equals("")){
                SET("hospital_name = #{hospitalName}");
            }
            if (hospital.getPassword() != null && !hospital.getPassword().equals("")){
                SET("password = #{password}");
            }
            if (hospital.getHospitalDescription() != null && !hospital.getHospitalDescription().equals("")){
                SET("hospital_description = #{hospitalDescription}");
            }
            WHERE("hospital_name = #{hospitalName} or id = #{id}");
        }}.toString();
    }
}
