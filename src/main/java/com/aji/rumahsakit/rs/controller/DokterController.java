package com.aji.rumahsakit.rs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aji.rumahsakit.rs.exception.MessageResponse;
import com.aji.rumahsakit.rs.exception.ValidationException;
import com.aji.rumahsakit.rs.model.master.Dokter;
import com.aji.rumahsakit.rs.service.DokterService;

@RestController
@Controller
public class DokterController {

	@Autowired
	private DokterService dokterService;
	
	@RequestMapping(value = "/dokter/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 Dokter dokter=dokterService.findBykode(kode);

			 return ResponseEntity.ok(dokter);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/dokter/nama/{nama}/alamat/{alamat}/state/{state}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveFilters(@PathVariable String nama,@PathVariable String alamat,@PathVariable String state) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 List<Dokter> dokter=dokterService.findByFilters(nama, alamat, state);

			 return ResponseEntity.ok(dokter);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/dokter", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAll() throws ValidationException
	{
		 try 
		 {
			List<Dokter> dokterList=dokterService.findByAll();

			return ResponseEntity.ok(dokterList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/dokter", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody Dokter dokter) throws ValidationException{
		try {
			dokterService.save(dokter);
			MessageResponse mg  = new MessageResponse("success insert");
			
			return ResponseEntity.ok(mg);
			
		}
		catch(ValidationException val) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
			
		 }
		catch (Exception e) {
			MessageResponse mg = new MessageResponse("Failed insert" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
	
	@RequestMapping(value =  "/dokter", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Dokter dokter) throws Exception
	{
		 try 
		 {
			 dokterService.update(dokter);
			 MessageResponse mg = new MessageResponse("Update Success with id " + dokter.getId());
			 return ResponseEntity.ok(mg);
		 }
		 		 
		 catch(ValidationException val) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
				
		 }
		catch (Exception e) {
			MessageResponse mg = new MessageResponse("Failed insert" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
}
