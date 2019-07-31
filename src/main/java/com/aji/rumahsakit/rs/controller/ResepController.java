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
import com.aji.rumahsakit.rs.model.transaksi.DetailResep;
import com.aji.rumahsakit.rs.model.transaksi.Resep;
import com.aji.rumahsakit.rs.service.ResepService;

@RestController
@Controller
public class ResepController {

	@Autowired
	private ResepService resService;
	
	@RequestMapping(value = "/resep/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByKode(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 Resep rs=resService.findByKode(kode);

			 return ResponseEntity.ok(rs);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/resep/namapasien/{pasien}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveByFilters(@PathVariable String pasien) throws ValidationException
	{
		 try 
		 {
			List<Resep> rmList=resService.findByFilter(pasien);

			return ResponseEntity.ok(rmList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}

	@RequestMapping(value = "/resep", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody Resep rs) throws ValidationException{
		try {
			resService.saveResepAndDetail(rs);
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
	
	@RequestMapping(value =  "/resep", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Resep rs) throws Exception
	{
		 try 
		 {
			 resService.update(rs);
			 MessageResponse mg = new MessageResponse("Update Success with id " + rs.getId());
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
	
	//////////////////////////////////////detail////////////////////////////////////////////////////////////////////////////////////////
	
	@RequestMapping(value = "/detailresep", method = RequestMethod.GET)
	public ResponseEntity<?> retrieveDetail() throws ValidationException
	{
		 try 
		 {
			List<DetailResep> detRmList=resService.findDetailByFilter();

			return ResponseEntity.ok(detRmList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
		
	
	@RequestMapping(value = "/detailresep", method = RequestMethod.POST)
	public ResponseEntity<?> submitDetail(@RequestBody DetailResep detRs) throws ValidationException{
		try {
			resService.saveDetail(detRs);
			MessageResponse mg  = new MessageResponse("success insert");
			
			return ResponseEntity.ok(mg);
			
		}
		catch(ValidationException val) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(val.getMessage());
			
		 }
		catch (Exception e) {
			System.out.println(e);
			MessageResponse mg = new MessageResponse("Failed insert" );
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		}
	}
	
	@RequestMapping(value =  "/detailresep", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDetail(@RequestBody DetailResep detRs) throws Exception
	{
		 try 
		 {
			 resService.updateDetail(detRs);
			 MessageResponse mg = new MessageResponse("Update Success with id " + detRs.getId());
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
