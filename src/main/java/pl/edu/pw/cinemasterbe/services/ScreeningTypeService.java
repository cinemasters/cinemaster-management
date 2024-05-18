package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.movie.ScreeningType;
import pl.edu.pw.cinemasterbe.model.dto.movie.ScreeningTypeDto;
import pl.edu.pw.cinemasterbe.model.mappers.ScreeningTypeMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.ScreeningTypeRepository;

import static pl.edu.pw.cinemasterbe.utils.ServiceUtils.buildErrorMessage;

@Service
@RequiredArgsConstructor
public class ScreeningTypeService {
    private final ScreeningTypeRepository screeningTypeRepository;
    private final Validator validator;
    private final ScreeningTypeMapper screeningTypeMapper;

    public Page<ScreeningType> getScreeningTypes(Pageable pageRequest) {
        return screeningTypeRepository.findAll(pageRequest);
    }

    public Iterable<ScreeningType> getUsableScreeningTypes(int perkId) {
        return screeningTypeRepository.findAllByPerkIdOrPerkNull(perkId);
    }

    public ScreeningType getScreeningType(int id) {
        return screeningTypeRepository.findById(id).orElse(null);
    }

    public boolean hasLinkedPerk(int id) {
        var screeningType = getScreeningType(id);

        return screeningType == null || screeningType.getPerk() != null;
    }

    public ServiceResponse<Integer> createScreeningType(ScreeningTypeDto dto) {
        var response = buildScreeningTypeFromDto(dto, -1);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var scrType = screeningTypeRepository.save(response.getData());

        return ServiceResponse.<Integer>builder().success(true).data(scrType.getId()).build();
    }

    public ServiceResponse<Integer> updateScreeningType(ScreeningTypeDto dto, int id) {
        var oldScreeningType = getScreeningType(id);

        if (oldScreeningType == null) {
            return ServiceResponse.<Integer>builder().success(false).message("Screening type with id %d does not exist.".formatted(id)).build();
        }

        var response = buildScreeningTypeFromDto(dto, id);

        if (!response.isSuccess()) {
            return ServiceResponse.<Integer>builder().success(false).message(response.getMessage()).build();
        }

        var scrType = response.getData();

        scrType.setId(id);
        scrType = screeningTypeRepository.save(scrType);

        return ServiceResponse.<Integer>builder().success(true).data(scrType.getId()).build();
    }

    private ServiceResponse<ScreeningType> buildScreeningTypeFromDto(ScreeningTypeDto dto, int id) {
        var scrType = screeningTypeMapper.mapToEntity(dto);
        var violations = validator.validate(dto);

        if (!violations.isEmpty()) {
            return ServiceResponse.<ScreeningType>builder().success(false).message(buildErrorMessage(violations)).build();
        }

        if (screeningTypeRepository.existsByNameAndIdNot(scrType.getName(), id)) {
            return ServiceResponse.<ScreeningType>builder().success(false).message("Screening type with name %s already exists.".formatted(scrType.getName())).build();
        }

        return ServiceResponse.<ScreeningType>builder().success(true).data(scrType).build();
    }
}
