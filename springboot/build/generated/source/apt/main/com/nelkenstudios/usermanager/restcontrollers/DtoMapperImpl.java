package com.nelkenstudios.usermanager.restcontrollers;

import com.navid.codegen.recordserver.model.ListOrganizationsResponse;

import com.nelkenstudios.usermanager.domain.Org;

import javax.annotation.Generated;

@Generated(

    value = "org.mapstruct.ap.MappingProcessor",

    comments = "version: 1.2.0.CR1, compiler: javac, environment: Java 1.8.0_131 (Oracle Corporation)"

)

public class DtoMapperImpl implements DtoMapper {

    @Override

    public ListOrganizationsResponse sourceToTarget(Org source) {

        if ( source == null ) {

            return null;
        }

        ListOrganizationsResponse listOrganizationsResponse = new ListOrganizationsResponse();

        return listOrganizationsResponse;
    }

    @Override

    public Org targetToSource(ListOrganizationsResponse target) {

        if ( target == null ) {

            return null;
        }

        Org org = new Org();

        return org;
    }
}

