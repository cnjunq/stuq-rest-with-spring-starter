package io.junq.examples.common.web.controller;

import org.springframework.beans.factory.annotation.Autowired;

import io.junq.examples.common.persistence.model.IEntity;
import io.junq.examples.common.web.RestPreconditions;

public abstract class AbstractController <T extends IEntity> extends AbstractReadOnlyController<T> {
	
    @Autowired
    public AbstractController(final Class<T> clazzToSet) {
        super(clazzToSet);
    }

	// 新建并保存

    protected final void createInternal(final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestState(resource.getId() == null);
        getService().create(resource);
    }

	// 更新操作
    
    protected final void updateInternal(final long id, final T resource) {
        RestPreconditions.checkRequestElementNotNull(resource);
        RestPreconditions.checkRequestElementNotNull(resource.getId());
        RestPreconditions.checkRequestState(resource.getId() == id);
        RestPreconditions.checkNotNull(getService().findOne(resource.getId()));

        getService().update(resource);
    }

	// 删除操作

    protected final void deleteByIdInternal(final long id) {
        getService().delete(id);
    }
}
