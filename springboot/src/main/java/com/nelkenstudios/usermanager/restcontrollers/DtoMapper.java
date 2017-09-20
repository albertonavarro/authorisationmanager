package com.nelkenstudios.usermanager.restcontrollers;

import com.navid.codegen.recordserver.model.ListOrganizationsResponse;
import com.navid.codegen.recordserver.model.PostOrganizationResponse;
import com.nelkenstudios.usermanager.domain.Org;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface DtoMapper {

    DtoMapper INSTANCE = Mappers.getMapper(DtoMapper.class);

    ListOrganizationsResponse sourceToTarget(List<Org> source);

    @InheritInverseConfiguration
    List<Org> targetToSource(ListOrganizationsResponse target);

    PostOrganizationResponse convertOrganizationResponse(Org source);

    @InheritInverseConfiguration
    Org convertOrganizationResponse(PostOrganizationResponse source);


}
