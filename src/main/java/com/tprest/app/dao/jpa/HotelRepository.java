package com.tprest.app.dao.jpa;

import com.tprest.app.domain.Hotel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Created by marwen on 15/03/16.
 */
public interface HotelRepository extends PagingAndSortingRepository<Hotel, Long> {

    Hotel findHotelByCity(String city);
    Page findAll(Pageable pageable);

}
