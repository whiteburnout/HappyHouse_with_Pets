package com.happyhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.happyhouse.service.ReviewService;
import com.happyhouse.vo.Review;

@RestController
@CrossOrigin
public class ReviewController {
	
	@Autowired
	ReviewService service;

	//리뷰 리스트
	@GetMapping(value = "/house/review/{no}")
	public List<Review> searchReview(@PathVariable String no)
	{
		return service.searchReview(no);
	}
	//리뷰 생성
	@PostMapping(value = "/house/review")
	public int RegisterReview(@RequestBody Review review)
	{
		return service.RegisterReview(review);
	}
	//리뷰 수정
	@PutMapping(value = "/house/review")
	public int ModifyReview(@RequestBody Review review)
	{
		return service.ModifyReview(review);
	}
	//리뷰 삭제
	@DeleteMapping(value = "/house/review/{no}")
	public int deleteReview(@PathVariable String no)
	{
		return service.DeleteReview(no);
	}
}
