package com.springsecurity.events;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.springsecurity.user.User;

@Repository("EventRepo")
interface EventRepositiory extends CrudRepository<Event,Long>,PagingAndSortingRepository<Event, Long> {
	List<Event> findByUserOrUserIsNullAndDateBetween(User user,long startDate,long endDate);
	
	Page<Event> findByUserIsNull(Pageable pageable);
}
