package com.aji.rumahsakit.rs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.aji.rumahsakit.rs.service.BiayaPelayananPoliService;
import com.aji.rumahsakit.rs.service.BiayaPelayananTambahanService;
import com.aji.rumahsakit.rs.service.DokterService;
import com.aji.rumahsakit.rs.service.JenisKamarService;
import com.aji.rumahsakit.rs.service.JenisObatService;
import com.aji.rumahsakit.rs.service.JenisTransaksiService;
import com.aji.rumahsakit.rs.service.ListKamarService;
import com.aji.rumahsakit.rs.service.ObatService;
import com.aji.rumahsakit.rs.service.PasienService;
import com.aji.rumahsakit.rs.service.PoliService;
import com.aji.rumahsakit.rs.service.PraktekService;


@SpringBootApplication 
@EnableAutoConfiguration
@ComponentScan(basePackages= "com.aji.rumahsakit.rs")
@EnableTransactionManagement
@EntityScan(basePackages= {"com.aji.rumahsakit.rs.model"})
public class App implements CommandLineRunner
{
	@Autowired
	private BiayaPelayananPoliService a;
	
	@Autowired
	private	BiayaPelayananTambahanService b;
	
	@Autowired
	private DokterService c;
	
	@Autowired
	private JenisKamarService d;
	
	@Autowired
	private JenisObatService e;
	
	@Autowired
	private JenisTransaksiService g;
	
	@Autowired
	private ListKamarService h;
	
	@Autowired
	private ObatService i;
	
	@Autowired
	private PasienService j;
	
	@Autowired
	private PoliService k;
	
	@Autowired
	private PraktekService l;
	
    public static void main( String[] args )
    {
    	SpringApplication.run(App.class, args);
    }

	@Override
	public void run(String... args) throws Exception {
		
	}
}
