package com.fernando.sprinboot.restaurant.proyect.restaurant.controllers.v1;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/v1/roles")
@Tag(name = "Role", description = "Role management endpoints")
public class RoleController {
    // private RoleService roleService;

    // public RoleController(RoleService roleService) {
    //     this.roleService = roleService;
    // }

    // @GetMapping
    // @Operation(summary = "Get all roles", description = "Retrieve a list of all roles")
    // public ResponseEntity<ApiListResponse<RoleDto>> getAll() {
    //     List<RoleDto> roles = this.roleService.findAll();

    //     return BuildResponse.buildResponse(
    //         "All roles retrieved successfully", 
    //         HttpStatus.OK, 
    //         roles
    //     );
    // }
}
