package spittr.dao;

import spittr.domain.Spittle;

import java.util.List;

/**
 * Created by wshcatkin on 2018-06-23.
 */
public interface SpittleRepository {
    List<Spittle> findRecentSpittles();

    List<Spittle> findSpittles(long max, int count);

    Spittle findOne(long id);

    void save(Spittle spittle);
}
