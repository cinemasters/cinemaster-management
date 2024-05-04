package pl.edu.pw.cinemasterbe.services;

import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import pl.edu.pw.cinemasterbe.model.domain.ScreeningType;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.repositories.ScreeningTypeRepository;

@Service
@RequiredArgsConstructor
public class ScreeningTypeService {
    private final ScreeningTypeRepository screeningTypeRepository;
    private final Validator validator;

    public ServiceResponse<Page<ScreeningType>> getScreeningTypes(Pageable pageRequest) {
        return ServiceResponse.<Page<ScreeningType>>builder().success(true).data(screeningTypeRepository.findAll(pageRequest)).build();
    }

    public ServiceResponse<Iterable<ScreeningType>> getUsableScreeningTypes(int perkId) {
        return ServiceResponse.<Iterable<ScreeningType>>builder().success(true).data(screeningTypeRepository.findAllByPerkIdOrPerkNull(perkId)).build();
    }

    public ServiceResponse<ScreeningType> getScreeningTypeById(int id) {
        var screeningType = screeningTypeRepository.findById(id).orElse(null);

        return ServiceResponse.<ScreeningType>builder().data(screeningType).success(screeningType != null).build();
    }

    public ServiceResponse<Void> createScreeningType(ScreeningType newScreeningType) {
        if (!validateScreeningType(newScreeningType)) {
            return ServiceResponse.<Void>builder().success(false).message("The screening type data is invalid.").build();
        }
        if (screeningTypeRepository.existsByName(newScreeningType.getName())) {
            return ServiceResponse.<Void>builder().success(false).message("Screening type with name %s already exists.".formatted(newScreeningType.getName())).build();
        }

        newScreeningType.setId(-1);
        screeningTypeRepository.save(newScreeningType);

        return ServiceResponse.<Void>builder().success(true).build();
    }

    public ServiceResponse<Void> updateScreeningType(ScreeningType newScreeningType, int id) {
        var oldScreeningType = screeningTypeRepository.findById(id).orElse(null);

        if (oldScreeningType == null || !validateScreeningType(newScreeningType)) {
            return ServiceResponse.<Void>builder().success(false).message("The screening type data is invalid.").build();
        }

        if (!newScreeningType.getName().equals(oldScreeningType.getName()) && screeningTypeRepository.existsByName(newScreeningType.getName())) {
            return ServiceResponse.<Void>builder().success(false).message("Screening type with name %s already exists.".formatted(newScreeningType.getName())).build();
        }

        newScreeningType.setId(id);
        screeningTypeRepository.save(newScreeningType);

        return ServiceResponse.<Void>builder().success(true).build();
    }

    private boolean validateScreeningType(ScreeningType elem) {
        var violations = validator.validate(elem);

        return violations.isEmpty();
    }
}
