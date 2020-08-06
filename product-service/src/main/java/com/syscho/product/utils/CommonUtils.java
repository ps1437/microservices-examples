package com.syscho.product.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;

public class CommonUtils {

	private CommonUtils() {
	}

	public static String generateId() {
		return UUID.randomUUID().toString();
	}

	public static <S, T> List<T> copyList(List<S> source, Class<T> type) {

		List<T> target = new ArrayList<>();
		Assert.notNull(source, "Source cannot be null");
		Assert.notNull(target, "Target cannot be null");
		Assert.notNull(type, "Target Class type is required");

		source.forEach(sourceClass -> {
			T targetObj = BeanUtils.instantiateClass(type);
			BeanUtils.copyProperties(sourceClass, targetObj);
			target.add(targetObj);
		});

		return target;
	}

	public static <S, T> T voToEntityt(S source, Class<T> type) {

		Assert.notNull(source, "Source cannot be null");
		Assert.notNull(type, " Class type is required");
		T targetObj = BeanUtils.instantiateClass(type);
		BeanUtils.copyProperties(source, targetObj);
		return targetObj;
	}

}
