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
import com.aji.rumahsakit.rs.model.transaksi.Registrasi;
import com.aji.rumahsakit.rs.service.RegistrasiService;

@RestController
@Controller
public class RegistrasiController {

	@Autowired
	private RegistrasiService regService;
	
	@RequestMapping(value = "/registrasi/{kode}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable String kode) throws ValidationException
	{
		 try 
		 {
			 System.out.println("try find");
			 Registrasi reg=regService.findByKode(kode);

			 return ResponseEntity.ok(reg);
		 }
		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/registrasi/nama/{nama}/tanggal/{tanggal}", method = RequestMethod.GET)
	public ResponseEntity<?> retrieve(@PathVariable String nama,@PathVariable String tanggal) throws ValidationException
	{
		 try 
		 {
			 System.out.println(nama+"1");
			List<Registrasi> regList=regService.findByFilter(nama,tanggal);

			return ResponseEntity.ok(regList);
		 }
		 		 
		 catch(Exception ex) 
		 {
			 MessageResponse mg = new MessageResponse("Retrieve Failed" );
		     return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(mg);
		 }
	}
	
	@RequestMapping(value = "/registrasi", method = RequestMethod.POST)
	public ResponseEntity<?> submit(@RequestBody Registrasi reg) throws ValidationException{
		try {
			regService.save(reg);
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
	
	@RequestMapping(value =  "/registrasi", method = RequestMethod.PUT)
	public ResponseEntity<?> update(@RequestBody Registrasi reg) throws Exception
	{
		 try 
		 {
			 regService.update(reg);
			 MessageResponse mg = new MessageResponse("Update Success with id " + reg.getId());
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
