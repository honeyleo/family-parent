package com.family.web;

import java.util.List;

public class BaseController {

	protected boolean isMore(List<?> list, int limit ) {
		boolean more = false;
		if(list != null) {
			more = list.size() > limit;
		} else {
			more = false;
		}
		return more;
	}
}
