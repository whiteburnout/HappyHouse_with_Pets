package com.happyhouse.service;

import java.util.List;

import com.happyhouse.vo.Review;

public interface ReviewService {

	public List<Review> searchReview(String no);
	public int RegisterReview(Review review);
	public int ModifyReview(Review review);
	public int DeleteReview(String no);
}
