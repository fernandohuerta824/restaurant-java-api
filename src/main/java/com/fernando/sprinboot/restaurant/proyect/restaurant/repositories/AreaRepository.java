package com.fernando.sprinboot.restaurant.proyect.restaurant.repositories;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.models.Area;

public interface AreaRepository extends JpaRepository<Area, Long> {

    boolean existsByName(String name);

    @Query("SELECT new com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaShortInfoDto(a.id, a.name, a.parentArea.id) FROM Area a WHERE a.name = :name")
    Optional<AreaShortInfoDto> findShortInfoByName(@Param("name") String name);

    @Query("SELECT new com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaShortInfoDto(a.id, a.name, a.parentArea.id) FROM Area a WHERE a.id = :id")
    Optional<AreaShortInfoDto> findShortInfoById(@Param("id") Long id);

    @Query("SELECT new com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaShortInfoDto(a.id, a.name, a.parentArea.id) FROM Area a")
    Page<AreaShortInfoDto> findAllBy(Pageable pageable);

    // Obtener las areas hijas de una area padre
    Page<Area> findByParentArea(Area parentArea, Pageable pageable);

    // Obtener las areas que no tienen area padre (areas raiz)
    Page<Area> findByParentAreaIsNull(Pageable pageable);

    Page<Area> findByNameContainingIgnoringCaseAndAvailable(String name, Boolean available, Pageable pageable);

    long countByNameContainingIgnoringCaseAndAvailable(String name, Boolean available);

    long countByParentArea(Area parentArea);

    long countByParentAreaIsNull();

}