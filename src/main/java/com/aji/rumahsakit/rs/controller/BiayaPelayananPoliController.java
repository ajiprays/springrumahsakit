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
import com.aji.rumahsakit.rs.model.master.BiayaPelayananPoli;
import com.aji.rumahsakit.rs.service.BiayaPelayananPoliService;

@RestController
@Controller
public class BiayaPelayananPoliController {
	
	@Autowired
	private BiayaPelayananPoliService bplService;

	@RequestMapping(value = "/biayapelayananpoli/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 BiayaPelayananPoli bpl=bplService.findByKode(kode);

			 return ResponseEntity.ok(bpl);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/biayapelayananpoli", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilter() throws ValidationException
	{
		 try 
		 {
			List<BiayaPelayananPoli> bplList=bplService.findByFilter();

			return ResponseEntity.ok(bplList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/biayapelayananpoli", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody BiayaPelayananPoli bpl) throws ValidationException{
		try {
			bplService.save(bpl);
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
	
	@RequestMapping(value =  "/biayapelayananpoli", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody BiayaPelayananPoli bpl) throws Exception
	{
		 try 
		 {
			 bplService.update(bpl);
			 MessageResponse mg = new MessageResponse("Update Success with id " + bpl.getId());
			 return ResponseEntity.ok(mg);
		 }
		 		 
		 catch(ValidationException val) {
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
				
		 }
		catch (Exception e) {
			MessageResponse mg = new MessageResponse("Failed update" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
}
