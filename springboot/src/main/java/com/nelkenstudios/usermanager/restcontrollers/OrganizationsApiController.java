package com.nelkenstudios.usermanager.restcontrollers;

import com.navid.codegen.recordserver.api.OrganizationsApi;
import com.navid.codegen.recordserver.model.ListOrganizationsResponse;
import com.navid.codegen.recordserver.model.PostOrganizationRequest;
import com.navid.codegen.recordserver.model.PostOrganizationResponse;
import com.nelkenstudios.usermanager.domain.Org;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.security.Principal;

import static com.google.common.collect.Lists.newArrayList;

@Controller
public class OrganizationsApiController implements OrganizationsApi {

    @RequestMapping(value = "/organizations",
            produces = { "application/json" },
            method = RequestMethod.GET)
    public ResponseEntity<ListOrganizationsResponse> getOrganizations(
            @RequestHeader(value="correlationId", required=false) String correlationId) {
        // do some magic!
        return new ResponseEntity<>(DtoMapper.INSTANCE.sourceToTarget(newArrayList( new Org())), HttpStatus.OK);
    }

    @Override
    @RequestMapping(value = "/organizations",
            produces = { "application/json" },
            consumes = { "application/json" },
            method = RequestMethod.POST)
    public ResponseEntity<PostOrganizationResponse> createOrganization(@Valid @RequestBody PostOrganizationRequest organization,
                                                                       @RequestHeader(value="correlationId", required=false) String correlationId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();
        return new ResponseEntity<PostOrganizationResponse>(HttpStatus.OK);
    }

}
