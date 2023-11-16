package com.ssafy.util;

import java.util.List;

import com.ssafy.happyhouse.model.dto.HouseDealDto;

public class QuickSort {
	public static void quickSort(List<HouseDealDto> list) {
		quickSort(list, 0, list.size()-1);
	}
	private static void quickSort(List<HouseDealDto> list, int start, int end) {
		
		if (start>=end) {
			return;
		}

		int pivot = start;
		int lo = start + 1;
		int hi = end;

		while(lo <= hi) {
			while(lo<=end && list.get(lo).getDealAmount().compareTo(list.get(pivot).getDealAmount()) >= 0)
				lo++;
			while(hi>start && list.get(hi).getDealAmount().compareTo(list.get(pivot).getDealAmount()) <= 0)
				hi--;

			if(lo>hi)
				swap(list, hi, pivot);
			else
				swap(list, lo, hi);

		}
		quickSort(list, start, hi-1);
		quickSort(list, hi+1, end);
		
		System.out.print("퀵소트 : ");
		for(int i=0;i<list.size();i++) {
			System.out.println(list.get(i));
		}
	}
	private static void swap(List<HouseDealDto> list, int i, int j) {
		HouseDealDto tmp = list.get(i);
		list.set(i, list.get(j));
		list.set(j, tmp);
	}
}
