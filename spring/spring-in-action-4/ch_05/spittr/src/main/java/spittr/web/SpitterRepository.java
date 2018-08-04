package spittr.web;

import spittr.domain.Spitter;

/**
 * Created by wshcatkin on 2018-06-23.
 */
public interface SpitterRepository {
    Spitter save(Spitter spitter);

    Spitter findUserByName(String username);
}
