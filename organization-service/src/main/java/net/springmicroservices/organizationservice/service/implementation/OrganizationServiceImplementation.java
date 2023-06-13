package net.springmicroservices.organizationservice.service.implementation;

import lombok.AllArgsConstructor;
import net.springmicroservices.organizationservice.dto.OrganizationDto;
import net.springmicroservices.organizationservice.entity.Organization;
import net.springmicroservices.organizationservice.mapper.OrganizationMapper;
import net.springmicroservices.organizationservice.repository.OrganizationRepository;
import net.springmicroservices.organizationservice.service.OrganizationService;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
public class OrganizationServiceImplementation implements OrganizationService {

    private OrganizationRepository organizationRepository;
    @Override
    public OrganizationDto saveOrganization(OrganizationDto organizationDto) {

        Organization organization = OrganizationMapper.mapToOrganization(organizationDto);
        Organization savedOrganization = organizationRepository.save(organization);

        return OrganizationMapper.mapToOrganizationDto(savedOrganization);
    }

    @Override
    public OrganizationDto getOrganizationByCode(String organizationCode) {
        Organization organization = organizationRepository.findByOrganizationCode(organizationCode);
        return OrganizationMapper.mapToOrganizationDto(organization);
    }
}
