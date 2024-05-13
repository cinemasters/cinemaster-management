package pl.edu.pw.cinemasterbe.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.edu.pw.cinemasterbe.model.dto.PageDto;
import pl.edu.pw.cinemasterbe.model.dto.ReviewDetailsDto;
import pl.edu.pw.cinemasterbe.model.dto.ReviewGridDto;
import pl.edu.pw.cinemasterbe.model.mappers.PageMapper;
import pl.edu.pw.cinemasterbe.model.mappers.ReviewMapper;
import pl.edu.pw.cinemasterbe.model.util.ServiceResponse;
import pl.edu.pw.cinemasterbe.services.ReviewService;

@RestController
@CrossOrigin
@RequiredArgsConstructor
@RequestMapping(path = "/reviews")
public class ReviewController {
    private final ReviewService reviewService;
    private final PageMapper pageMapper;
    private final ReviewMapper reviewMapper;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<PageDto<ReviewGridDto>> getReviews(@RequestParam(defaultValue = "0", required = false) int page, @RequestParam(defaultValue = "50", required = false) int size) {
        var pageRequest = PageRequest.of(page, size);
        var response = reviewService.getReviews(pageRequest);

        return ResponseEntity.ok(pageMapper.map(response, reviewMapper::mapToGridDto));
    }

    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<ReviewDetailsDto> getReview(@PathVariable int id) {
        var review = reviewService.getReview(id);

        return review != null ? ResponseEntity.ok(reviewMapper.mapToDetailsDto(review)) : ResponseEntity.noContent().build();
    }

    @RequestMapping(path = "/{id}/toggleVisibility", method = RequestMethod.POST)
    public ResponseEntity<ServiceResponse<Boolean>> toggleVisibility(@PathVariable int id) {
        var response = reviewService.toggleReviewVisibility(id);

        return response.isSuccess() ? ResponseEntity.ok(response) : ResponseEntity.unprocessableEntity().body(response);
    }
}
