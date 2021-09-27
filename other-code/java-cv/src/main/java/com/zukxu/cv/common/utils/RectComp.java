package com.zukxu.cv.common.utils;

import org.opencv.core.Rect;

/**
 * rect 比较工具
 */
public class RectComp implements Comparable<Object> {
	private Rect rm;

	public Rect getRm() {
		return rm;
	}

	public void setRm(Rect rm) {
		this.rm = rm;
	}

	public RectComp() {
		super();
	}

	public RectComp(Rect rm) {
		super();
		this.rm = rm;
	}

	@Override
	// 按面积排序，最大的放第一个
	public int compareTo(Object object) {
		if(this == object) {
			return 0;
		} else if(object instanceof RectComp) {
			RectComp rect = (RectComp) object;
			if(rm.area() >= rect.rm.area()) {
				return -1;
			} else {
				return 1;
			}
		} else {
			return -1;
		}
	}

}
