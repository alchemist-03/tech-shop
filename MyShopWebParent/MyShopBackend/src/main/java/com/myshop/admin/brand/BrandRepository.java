package com.myshop.admin.brand;

import com.myshop.common.entity.Brand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface BrandRepository extends PagingAndSortingRepository<Brand,Integer> {

    @Query("select b from Brand b where concat(b.name, ' ',b.id,' ') like %?1%")
    Page<Brand> search(String keyword, Pageable pageable);
}
