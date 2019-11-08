package com.example.project.demo.exception;

import com.example.project.demo.constant.Status;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 异常基类
 * </p>
 *
 * @description: 异常基类
 * @date: Created in 2018/10/2 9:31 PM
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class BaseException extends RuntimeException {
	private Integer code;
	private String message;

	public BaseException(Status status) {
		super(status.getMessage());
		this.code = status.getCode();
		this.message = status.getMessage();
	}

	public BaseException(Integer code, String message) {
		super(message);
		this.code = code;
		this.message = message;
	}
}
