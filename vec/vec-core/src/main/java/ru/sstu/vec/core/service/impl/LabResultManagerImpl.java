package ru.sstu.vec.core.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.transaction.annotation.Transactional;

import ru.sstu.vec.core.dao.CourseResultDao;
import ru.sstu.vec.core.dao.LabDao;
import ru.sstu.vec.core.dao.LabResultDao;
import ru.sstu.vec.core.dao.LabVariantDao;
import ru.sstu.vec.core.domain.CourseResult;
import ru.sstu.vec.core.domain.Lab;
import ru.sstu.vec.core.domain.LabResult;
import ru.sstu.vec.core.domain.LabVariant;
import ru.sstu.vec.core.service.LabResultManager;

/**
 * {@code LabResultManagerImpl} class is {@link LabResultManager}
 * implementation.
 *
 * @author Denis_Murashev
 * @author Dmitry_Petrov
 * @since VEC 2.0
 */
abstract class LabResultManagerImpl implements LabResultManager {

    private static final long serialVersionUID = 734984585957169597L;

    private static final Random RANDOM = new Random();

    private Log log = LogFactory.getLog(LabResultManagerImpl.class);

    @Resource
    private CourseResultDao courseResultDao;

    @Resource
    private LabResultDao labResultDao;

    @Resource
    private LabDao labDao;

    @Resource
    private LabVariantDao labVariantDao;

    private CourseResult courseResult;

    @Transactional
    @Override
    public List<LabResult> find() {
        if (courseResult == null) {
            log.error("CourseResult is NULL!");
            System.err.println("CourseResult is NULL!");
            return new ArrayList<LabResult>();
        }
        List<Lab> labs = labDao.find(courseResult.getCourse());
        List<LabResult> results = new ArrayList<LabResult>();
        for (Lab lab : labs) {
            LabResult result = labResultDao.find(courseResult, lab);
            if (result == null) {
                result = new LabResult();
                result.setLab(lab);
                result.setVariant(getVariant(lab));
                result.setCourseResult(courseResult);
                labResultDao.save(result);
            }
            results.add(result);
        }
        return results;
    }

    @Transactional
    @Override
    public void save(LabResult object) {
        labResultDao.save(object);
    }

    @Transactional
    @Override
    public void delete(LabResult object) {
    }

    @Override
    public void setCourseResult(CourseResult courseResult) {
        this.courseResult = courseResult;
    }

    /**
     * @return {@link LabResultDao} implementation
     */
    protected LabResultDao getLabResultDao() {
        return labResultDao;
    }

    /**
     * @param lab
     *            lab
     * @return lab variant
     */
    private LabVariant getVariant(Lab lab) {
        List<LabVariant> variants = labVariantDao.find(lab);
        if (variants.isEmpty()) {
            return null;
        }
        int index = RANDOM.nextInt(variants.size());
        return variants.get(index);
    }

}
