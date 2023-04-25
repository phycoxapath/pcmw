package indi.gd.pcmw.dao.provider;

import indi.gd.pcmw.domain.Vaccine;
import org.apache.ibatis.jdbc.SQL;

import java.math.BigDecimal;

public class VaccineDynamicUpdate {

    public String updateVaccine(final Vaccine vaccine){
        return new SQL(){{
            UPDATE("pcmw_vaccine");
            if (vaccine.getVaccinePrice().compareTo(new BigDecimal(0)) != 0) {
                SET("vaccine_price = #{vaccinePrice}");
            }
            if (vaccine.getVaccineDescription() != null && !"".equals(vaccine.getVaccineDescription())){
                SET("vaccine_description = #{vaccineDescription}");
            }

            WHERE("id = #{id}");
        }}.toString();
    }
}
