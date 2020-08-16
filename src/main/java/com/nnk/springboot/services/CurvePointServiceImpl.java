package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.exceptions.ResourceNotFoundException;
import com.nnk.springboot.repositories.CurvePointRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CurvePointServiceImpl implements ICurvePointService {

    @Autowired
    CurvePointRepository curvePointRepository;

    public List<CurvePoint> findAllCurvePoints() {
        return curvePointRepository.findAll();
    }

    @Override
    public CurvePoint findCurvePointById(Integer id) throws ResourceNotFoundException {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);

        if(curvePoint.isPresent()) {
            return curvePoint.get();
        } else {
            throw new ResourceNotFoundException(id);
        }
    }

    @Override
    public CurvePoint createCurvePoint(CurvePoint CurvePoint) {
        return curvePointRepository.save(CurvePoint);
    }

    @Override
    public CurvePoint updateCurvePoint(CurvePoint CurvePoint) {
        return curvePointRepository.save(CurvePoint);
    }

    @Override
    public void deleteCurvePointById(Integer id) throws ResourceNotFoundException {
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);

        if(curvePoint.isPresent()) {
            curvePointRepository.deleteById(id);
        } else {
            throw new ResourceNotFoundException(id);
        }
    }
}