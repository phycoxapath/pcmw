package indi.gd.pcmw.service.impl;

import indi.gd.pcmw.dao.VaccineDao;
import indi.gd.pcmw.domain.Vaccine;
import indi.gd.pcmw.service.VaccineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VaccineServiceImpl implements VaccineService {
    @Autowired
    private VaccineDao vaccineDao;
    @Override
    public int save(Vaccine vaccine) {
        return vaccineDao.save(vaccine);
    }

    @Override
    public List<Vaccine> getVaccinesByHospId(Integer hospId) {
        return vaccineDao.getVaccinesByHospId(hospId);
    }

    @Override
    public int deleteVaccineById(Integer id) {
        return vaccineDao.deleteVaccineById(id);
    }

    @Override
    public int updateVaccine(Vaccine vaccine) {
        return vaccineDao.updateVaccine(vaccine);
    }
}
