// 공통 정렬/페이징 유틸 (선택)
package com.backoven.catdogshelter.common.util.pagination;

public class PagingUtil {
    public static int safePage(Integer page) { return (page == null || page < 1) ? 1 : page; }
    public static int safeSize(Integer size) { return (size == null || size < 1) ? 10 : size; }
}
