package ru.vito.web.app.jersey.model.dao.impl;

import ru.vito.web.app.jersey.model.entity.Operation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class AbstractRepository { // The implementation is hidden, how it will be stored and the specifics can be left

    /**
     * Map of pairs <partnerAccountId <-> ListOfOperations>
     */
    public static final Map<String, List<Operation>> data = new HashMap<>();
}
