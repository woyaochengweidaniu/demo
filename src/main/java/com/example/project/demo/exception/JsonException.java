package com.example.project.demo.exception;

import com.example.project.demo.constant.Status;
import lombok.Getter;

/**
 * <p>
 * JSON异常
 * </p>
 *
 * @description: JSON异常
 * @date: Created in 2018/10/2 9:18 PM
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 */
@Getter
public class JsonException extends BaseException {

	public JsonException(Status status) {
		super(status);
	}

	public JsonException(Integer code, String message) {
		super(code, message);
	}
}
