package com.tprest.controller;

import com.tprest.entities.Instance;
import com.tprest.service.InstanceService;
import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;
import org.springframework.data.domain.Page;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.zip.DataFormatException;

/**
 * Created by marwen on 02/03/16.
 */

@RestController
@RequestMapping(value = "/poc/instances")
//@Api --swagger
public class InstanceController {

    private static final String DEFAULT_PAGE_NUM = "";
    private static final String DEFAULT_PAGE_SIZE = "";
    private InstanceService instanceService;

    @RequestMapping(value = "", method = RequestMethod.POST,
            consumes = {"application/xml", "application/json"},
            produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.CREATED)
    public void createInstance(@RequestBody Instance instance, HttpServletRequest request, HttpServletResponse response) {
        Instance createInstance = this.instanceService.createInstance(instance);
        response.setHeader("Location", request.getRequestURL().append("/").append(createInstance.getId()).toString());
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = {"application/xml", "application/jspn"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Page<Instance> getAllInstance(@ApiParam(value = "number", required = true)
                                  @RequestParam(value = "page", required = true, defaultValue = DEFAULT_PAGE_NUM) Integer page,
                                  @ApiParam(value = "page size", required = true)
                                  @RequestParam(value = "size", required = true, defaultValue = DEFAULT_PAGE_SIZE) Integer size,
                                  HttpServletRequest request, HttpServletResponse response) {

        return this.instanceService.getAllInstance(page, size);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = {"application/xml", "application/json"})
    @ResponseStatus(HttpStatus.OK)
    public
    @ResponseBody
    Instance getInstance(@ApiParam(value = "the ID of instance", required = true)
                         @PathVariable("id") Long id,
                         HttpServletResponse response, HttpServletRequest request) throws DataFormatException {

        Instance instance = this.instanceService.getInstance(id);
        checkResourceFound(instance);
        //todo: http://goo.gl/6iNAkz
        return instance;
    }

    @RequestMapping(value = "/{id}",
            method = RequestMethod.PUT,
            consumes = {"application/json", "application/xml"},
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Update a hotel resource.", notes = "You have to provide a valid instance ID in the URL and in the payload. The ID attribute can not be updated.")
    public void updateInstance(@ApiParam(value = "The ID of the existing hotel resource.", required = true)
                               @PathVariable("id") Long id, @RequestBody Instance instance,
                               HttpServletRequest request, HttpServletResponse response) throws DataFormatException {
        checkResourceFound(this.instanceService.getInstance(id));
        if (id != instance.getId()) throw new DataFormatException("ID doesn't match!");
        this.instanceService.updateInstance(instance);
    }

    //todo: @ApiImplicitParams, @ApiResponses
    @RequestMapping(value = "/{id}",
            method = RequestMethod.DELETE,
            produces = {"application/json", "application/xml"})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ApiOperation(value = "Delete a hotel resource.", notes = "You have to provide a valid instance ID in the URL. Once deleted the resource can not be recovered.")
    public void deleteInstance(@ApiParam(value = "The ID of the existing hotel resource.", required = true)
                               @PathVariable("id") Long id, HttpServletRequest request,
                               HttpServletResponse response) {
        checkResourceFound(this.instanceService.getInstance(id));
        this.instanceService.deleteInstance(id);
    }

    // TODO : Replace with exception mapping
    private static <T> T checkResourceFound(final T resource) {
        if (resource == null) {
            throw new ResourceNotFoundException("resource not found");
        }
        return resource;
    }

}



