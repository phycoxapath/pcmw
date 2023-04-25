package indi.gd.pcmw.service;

import indi.gd.pcmw.domain.Vaccine;

import java.util.List;

public interface VaccineService {
    int save(Vaccine vaccine);

    List<Vaccine> getVaccinesByHospId(Integer hospId);

    int deleteVaccineById(Integer id);

    int updateVaccine(Vaccine vaccine);
}
