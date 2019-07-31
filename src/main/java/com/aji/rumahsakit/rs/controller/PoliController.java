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
import com.aji.rumahsakit.rs.model.master.Poli;
import com.aji.rumahsakit.rs.service.PoliService;

@RestController
@Controller
public class PoliController {

	@Autowired
	private PoliService poliService;
	
	@RequestMapping(value = "/poli/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 Poli poli=poliService.findByKode(kode);

			 return ResponseEntity.ok(poli);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/poli/nama/{nama}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilters(@PathVariable String nama) throws ValidationException
	{
		 try 
		 {
			List<Poli> poliList=poliService.findByFilter(nama);

			return ResponseEntity.ok(poliList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/poli", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveAll() throws ValidationException
	{
		 try 
		 {
			List<Poli> poliList=poliService.findAll();

			return ResponseEntity.ok(poliList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/poli", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody Poli poli) throws ValidationException{
		try {
			poliService.save(poli);
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
	
	@RequestMapping(value =  "/poli", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Poli poli) throws Exception
	{
		 try 
		 {
			 poliService.update(poli);
			MessageResponse mg = new MessageResponse("Update Success with id " + poli.getId());
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
