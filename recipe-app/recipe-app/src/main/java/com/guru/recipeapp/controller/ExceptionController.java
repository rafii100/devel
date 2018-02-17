package com.guru.recipeapp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.guru.recipeapp.exceptions.BusinessException;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice
@Slf4j
public class ExceptionController {

	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public ModelAndView handleException(Exception e) {
		log.error("Runtime exception");
	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("404");
		
		mv.addObject("exception", e);
		return mv;
	}
	
	
	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(BusinessException.class)
	public ModelAndView handleBusinessException(Exception e) {
		log.error("Business exception");
	
		ModelAndView mv = new ModelAndView();
		mv.setViewName("404");
		
		mv.addObject("exception", e);
		return mv;
	}
}
