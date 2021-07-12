package com.fanshawe.nfttracker.helper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;

public class CopyListProperties<S, T> {
	private Class<T> targetType;

	public CopyListProperties(Class<T> targetType) {
		this.targetType = targetType;
	}

	public List<T> copy(List<S> src) {
		List<T> target = new ArrayList<T>();
		for (S s : src) {
			T t = BeanUtils.instantiateClass(targetType);
			BeanUtils.copyProperties(s, t);
			target.add(t);
		}
		return target;
	}
}
