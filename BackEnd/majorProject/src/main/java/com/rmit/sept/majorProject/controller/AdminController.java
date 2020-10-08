package com.rmit.sept.majorProject.controller;

import com.rmit.sept.majorProject.dto.AdminSummary;
import com.rmit.sept.majorProject.dto.AdminBlueprint;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.rmit.sept.majorProject.model.Admin;
import com.rmit.sept.majorProject.service.AdminService;
import com.rmit.sept.majorProject.utility.Util;

@CrossOrigin(origins = Util.API_HOST)
@RestController
public class AdminController {
	
	@Autowired
	private AdminService adminService;

	@PostMapping("/api/admin")
	public ResponseEntity<?> newAdmin(@RequestBody AdminBlueprint admin){
		Admin newAdmin = adminService.registerNewAdminByBlueprint(admin);
		return new ResponseEntity<>(newAdmin, newAdmin != null ? HttpStatus.OK : HttpStatus.BAD_REQUEST);
	}
	
    @GetMapping("/api/admin")
	public Iterable<Admin> getAllAdmins(){
		return adminService.findAll();
	}

	@GetMapping("/api/admin/{adminId}")
	public ResponseEntity<?> getAdminById(@PathVariable Long adminId){
		AdminSummary admin = adminService.findByIdDTO(adminId);
		return new ResponseEntity<>(admin, admin != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@DeleteMapping("/api/admin/{adminId}")
	public ResponseEntity<?> deleteAdmin(@Valid @PathVariable Long adminId){
		if(adminId == null || adminId == 0)
		{
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(null, HttpStatus.OK);
	}
	
}
