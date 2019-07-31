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
import com.aji.rumahsakit.rs.model.master.JenisTransaksi;
import com.aji.rumahsakit.rs.service.JenisTransaksiService;

@RestController
@Controller
public class JenisTransaksiController {

	@Autowired
	private JenisTransaksiService jnsTrxService;
	
	@RequestMapping(value = "/jenistransaksi/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 JenisTransaksi jnsTrx=jnsTrxService.findByKode(kode);

			 return ResponseEntity.ok(jnsTrx);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/jenistransaksi", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve() throws ValidationException
	{
		 try 
		 {
			List<JenisTransaksi> trxList=jnsTrxService.findByFilter();

			return ResponseEntity.ok(trxList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/jenistransaksi", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody JenisTransaksi trx) throws ValidationException{
		try {
			jnsTrxService.save(trx);
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
	
	@RequestMapping(value =  "/jenistransaksi", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody JenisTransaksi trx) throws Exception
	{
		 try 
		 {
			 jnsTrxService.update(trx);
			 MessageResponse mg = new MessageResponse("Update Success with id " + trx.getId());
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
