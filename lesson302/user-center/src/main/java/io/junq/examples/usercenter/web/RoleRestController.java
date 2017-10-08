package io.junq.examples.usercenter.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import io.junq.examples.common.util.QueryConstants;
import io.junq.examples.common.web.controller.AbstractController;
import io.junq.examples.common.web.controller.ISortingController;
import io.junq.examples.usercenter.persistence.model.Role;
import io.junq.examples.usercenter.service.IRoleService;
import io.junq.examples.usercenter.util.UserCenterMapping;
import io.junq.examples.usercenter.util.UserCenter.Privileges;

@Controller
@RequestMapping(value = UserCenterMapping.ROLES)
public class RoleRestController extends AbstractController<Role> implements ISortingController<Role> {

	@Autowired
    private IRoleService service;

    public RoleRestController() {
        super(Role.class);
    }

    // API

    // 查找：所有、分页

    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE, QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_PRIVILEGE_READ)
    public List<Role> findAllPaginatedAndSorted(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size, @RequestParam(value = QueryConstants.SORT_BY) final String sortBy,
            @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findPaginatedAndSortedInternal(page, size, sortBy, sortOrder);
    }

    @Override
    @RequestMapping(params = { QueryConstants.PAGE, QueryConstants.SIZE }, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_PRIVILEGE_READ)
    public List<Role> findAllPaginated(@RequestParam(value = QueryConstants.PAGE) final int page, @RequestParam(value = QueryConstants.SIZE) final int size) {
        return findPaginatedAndSortedInternal(page, size, null, null);
    }

    @Override
    @RequestMapping(params = { QueryConstants.SORT_BY }, method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_PRIVILEGE_READ)
    public List<Role> findAllSorted(@RequestParam(value = QueryConstants.SORT_BY) final String sortBy, @RequestParam(value = QueryConstants.SORT_ORDER) final String sortOrder) {
        return findAllSortedInternal(sortBy, sortOrder);
    }

    @Override
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_PRIVILEGE_READ)
    public List<Role> findAll(final HttpServletRequest request) {
        return findAllInternal(request);
    }

    // 查找：单条记录

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    @Secured(Privileges.CAN_PRIVILEGE_READ)
    public Role findOne(@PathVariable("id") final Long id) {
        return findOneInternal(id);
    }

    // 新建

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    @Secured(Privileges.CAN_ROLE_WRITE)
    public void create(@RequestBody final Role resource) {
        createInternal(resource);
    }

    // 更新

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.OK)
    @Secured(Privileges.CAN_ROLE_WRITE)
    public void update(@PathVariable("id") final Long id, @RequestBody final Role resource) {
        updateInternal(id, resource);
    }

    // 删除

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    @Secured(Privileges.CAN_ROLE_WRITE)
    public void delete(@PathVariable("id") final Long id) {
        deleteByIdInternal(id);
    }

    // Spring

    @Override
    protected final IRoleService getService() {
        return service;
    }
    
}
