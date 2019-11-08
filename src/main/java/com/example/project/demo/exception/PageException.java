package com.example.project.demo.exception;

import com.example.project.demo.constant.Status;
import lombok.Getter;

/**
 * <p>
 * 页面异常
 * </p>
 *
 * @description: 页面异常
 * @date: Created in 2018/10/2 9:18 PM
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 */
@Getter
public class PageException extends BaseException {

	public PageException(Status status) {
		super(status);
	}

	public PageException(Integer code, String message) {
		super(code, message);
	}
}
