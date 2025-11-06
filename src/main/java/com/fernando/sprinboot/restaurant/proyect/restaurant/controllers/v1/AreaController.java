package com.fernando.sprinboot.restaurant.proyect.restaurant.controllers.v1;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fernando.sprinboot.restaurant.proyect.restaurant.controllers.BuildResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaFullInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaRequestDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.area.AreaTreeDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.restaurantTable.RestaurantTableShortInfoDto;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnCreate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.dto.validation.OnUpdate;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiListResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.response.ApiResponse;
import com.fernando.sprinboot.restaurant.proyect.restaurant.services.AreaService;
import com.fernando.sprinboot.restaurant.proyect.restaurant.services.RestaurantTableService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/area")
@Tag(name="Area", description = "Area management endpoints")
public class AreaController {

    private AreaService areaService;
    private RestaurantTableService restaurantTableService;

    public AreaController(
        AreaService areaService, 
        RestaurantTableService restaurantTableService
    ) {
        this.areaService = areaService;
        this.restaurantTableService = restaurantTableService;
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Retrive one area by id", description = "Retrive one area by its unique id")
    public ResponseEntity<ApiResponse<AreaFullInfoDto>> getById(@PathVariable Long id)  {
        AreaFullInfoDto area = this.areaService.findById(id);

        return BuildResponse.buildResponse("Area retrieved successfully", HttpStatus.OK, area);
    }

    @PostMapping
    @Operation(summary = "Save a new Area", description = "Create and save a new area")
    public ResponseEntity<ApiResponse<AreaFullInfoDto>> save(
        @RequestBody  @Validated(OnCreate.class) AreaRequestDto body
    )  {
        AreaFullInfoDto area = areaService.save(body);

        return BuildResponse.buildResponse("Area saved successfully", HttpStatus.CREATED, area);
    }

    @GetMapping
    @Operation(summary = "Get all areas", description = "Retrieve all areas with pagination")
    public ResponseEntity<ApiListResponse<AreaFullInfoDto>> getAllFullInfo(
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "") String name,
        @RequestParam(required = false, defaultValue = "true") Boolean available
    ) {
        Page<AreaFullInfoDto> areas =  areaService.findAllFullInfo(page, name, available);

        return BuildResponse.buildResponse("All areas retrieved successfully", HttpStatus.OK, areas);
    }

    @GetMapping("/simple-list")
    @Operation(summary = "Get all areas as a simple list", description = "Retrieve all areas without pagination")
    public ResponseEntity<ApiListResponse<AreaShortInfoDto>> getAllAsList(
        @RequestParam(required = false, defaultValue = "1") Integer page,
        @RequestParam(required = false, defaultValue = "") String name,
        @RequestParam(required = false, defaultValue = "true") Boolean available
    ) {
        Page<AreaShortInfoDto> areas =  areaService.findAllShortInfo(page, name, available);

        return BuildResponse.buildResponse("All areas retrieved successfully", HttpStatus.OK, areas);

    }

    @GetMapping("/tree")
    @Operation(summary = "Get area tree", description = "Retrieve the area tree structure")
    public ResponseEntity<ApiListResponse<AreaTreeDto>> getAreaTree(
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<AreaTreeDto> areaTree = areaService.getAreaTree(page);
        return BuildResponse.buildResponse("Area tree retrieved successfully", HttpStatus.OK, areaTree);
    }

    @GetMapping("{id}/tree")
    @Operation(summary = "Get subtree of a specific area", description = "Retrieve the subtree structure of a specific area by its id")
    public ResponseEntity<ApiResponse<AreaTreeDto>> getSubTreeByAreaId(
        @PathVariable Long id
    ) {
        AreaTreeDto areaSubTree = areaService.getAreaTreeById(id);
        return BuildResponse.buildResponse("Area subtree retrieved successfully", HttpStatus.OK, areaSubTree);
    }

    @GetMapping("/{id}/children")
    @Operation(summary = "Get all child areas of a specific area", description = "Retrieve all child areas of a specific area by its id with pagination")
    public ResponseEntity<ApiListResponse<AreaFullInfoDto>> getChildren(
        @PathVariable Long id,
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<AreaFullInfoDto> children = areaService.getChildrenAreas(id, page);

        return BuildResponse.buildResponse("Child areas retrieved successfully", HttpStatus.OK, children);
    }

    @GetMapping("/{id}/children/simple-list")
    @Operation(summary = "Get all child areas of a specific area as a simple list", description = "Retrieve all child areas of a specific area by its id without pagination")
    public ResponseEntity<ApiListResponse<AreaShortInfoDto>> getChildrenAsSimpleList(
        @PathVariable Long id,
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<AreaShortInfoDto> children = areaService.getChildrenAreaShortInfoPage(id, page);

        return BuildResponse.buildResponse("Child areas retrieved successfully", HttpStatus.OK, children);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a specific area")
    public ResponseEntity<ApiResponse<AreaFullInfoDto>> update(@PathVariable Long id, @RequestBody @Validated(OnUpdate.class) AreaRequestDto body) {
        AreaFullInfoDto area = areaService.update(body, id);

        return BuildResponse.buildResponse("Area updated successfully", HttpStatus.OK, area);
    }

    @GetMapping("/{id}/tables")
    @Operation(summary = "Get all tables in a specific area", description = "Retrieve all tables in a specific area by its id")
    public ResponseEntity<ApiListResponse<RestaurantTableShortInfoDto>> getTablesByAreaId(
        @PathVariable Long id,
        @RequestParam(required = false, defaultValue = "1") Integer page
    ) {
        Page<RestaurantTableShortInfoDto> tables = restaurantTableService.findByAreaId(id, page);

        return BuildResponse.buildResponse("Tables retrieved successfully", HttpStatus.OK, tables);
    }
}
